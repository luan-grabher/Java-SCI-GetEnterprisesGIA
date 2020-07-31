package sci.getenterprisesgia.Model;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sci.getenterprisesgia.Model.Entities.Gia;
import sci.getenterprisesgia.Model.Entities.GpsConfiguration;
import sci.getenterprisesgia.Model.Entities.InssBases;
import sql.Database;

public class GiaModel {
    private Integer reference;
    private Map<Integer,String> enterprises;
    
    private List<Gia> gias = new ArrayList<>();

    public Integer getReference() {
        return reference;
    }

    public void setReference(Integer reference) {
        this.reference = reference;
    }

    public Map<Integer, String> getEnterprises() {
        return enterprises;
    }

    public void setEnterprises(Map<Integer, String> enterprises) {
        this.enterprises = enterprises;
    }

    public List<Gia> getGias() {
        return gias;
    }

    public void setGias() {
        for (Map.Entry<Integer, String> entry : enterprises.entrySet()) {
            Integer enterpriseCode = entry.getKey();
            if(enterpriseCode == 19){
                System.out.println("Aqui");
            }
            String enterpriseName = entry.getValue();
            
            Integer cnae = getEnterpriseCNAE(enterpriseCode);
            BigDecimal cnaePercent = getCnaePercent(cnae).setScale(2).divide(new BigDecimal(100).setScale(2)).setScale(2);
            GpsConfiguration gpsConfiguration = getGpsConfiguration(enterpriseCode);
            
            Map<String, InssBases> inssBasesList = getInssBases(enterpriseCode);
            InssBases colaboratorsInssBase = inssBasesList.get("colaborador")==null?new InssBases():inssBasesList.get("colaborador");
            InssBases employersInssBase = inssBasesList.get("empregador")==null?new InssBases():inssBasesList.get("empregador");
            
            BigDecimal salaries = colaboratorsInssBase.getBaseInss().setScale(2).add(colaboratorsInssBase.getBaseInss13().setScale(2)).setScale(2);
            
            //Inss Percents
            BigDecimal salariesInssPercent = salaries.multiply(gpsConfiguration.getCollaboratorPercent().setScale(2)).setScale(2,RoundingMode.DOWN);
            BigDecimal employersInssPercent = employersInssBase.getBaseInss().multiply(gpsConfiguration.getEmployersPercent()).setScale(2);
            
            BigDecimal inssEnterprise = salaries.add(employersInssPercent).setScale(2);
            
            BigDecimal ratFap = salaries.multiply(cnaePercent).setScale(2, RoundingMode.DOWN);
            BigDecimal inssThird = salaries.multiply(gpsConfiguration.getThirdPercent()).setScale(2, RoundingMode.DOWN);
            
            System.out.println("\n\n========================================");
            System.out.println("Empresa: " + enterpriseName + "("+ enterpriseCode + ")");
            System.out.println("Total empregados:" + colaboratorsInssBase.getQuantity());
            System.out.println("Salários: " + salaries.toString());
            System.out.println("Inss Empregados: " + salariesInssPercent);
            System.out.println("Inss Empregadores: " + employersInssPercent);
            System.out.println("Inss RAT e FAP: " + ratFap);
            System.out.println("Inss Terceiros: " + inssThird);
            System.out.println("========================================");
        }
    }
    
    public Integer getEnterpriseCNAE(Integer enterpriseCode){
        File file = new File("sql/getEnterpriseCnae.sql");
        
        Map<String,String> variableChanges = new HashMap<>();
        variableChanges.put("enterpriseCode", enterpriseCode.toString());
        variableChanges.put("reference", reference.toString());
        
        
        ArrayList<String[]> result = Database.getDatabase().select(file, variableChanges);
        try {
            return Integer.valueOf(result.get(0)[0]);
        } catch (Exception e) {
            return 0;
        }                       
    }
    
    public BigDecimal getCnaePercent(Integer cnae){
        File file = new File("sql/getCnae.sql");
        
        Map<String,String> variableChanges = new HashMap<>();
        variableChanges.put("cnae", cnae.toString());
        variableChanges.put("reference", reference.toString());
        
        
        ArrayList<String[]> result = Database.getDatabase().select(file, variableChanges);
        try {
            return new BigDecimal(result.get(0)[2]);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }  
    }
    
    public GpsConfiguration getGpsConfiguration(Integer enterpriseCode){
        File file = new File("sql/getGpsConfiguration.sql");
        
        Map<String,String> variableChanges = new HashMap<>();
        variableChanges.put("enterpriseCode", enterpriseCode.toString());
        variableChanges.put("reference", reference.toString());
        
        
        ArrayList<String[]> result = Database.getDatabase().select(file, variableChanges);
        try {
            String[] firstRow = result.get(0);
            
            GpsConfiguration gpsConfiguration = new GpsConfiguration();
            gpsConfiguration.setEnterpriseCode(enterpriseCode);
            gpsConfiguration.setThirdPercent(new BigDecimal(firstRow[3]==null?"0":firstRow[3]));
            gpsConfiguration.setEmployersPercent(new BigDecimal(firstRow[4]==null?"0":firstRow[4]));
            gpsConfiguration.setAutonomousPercent(new BigDecimal(firstRow[5]==null?"0":firstRow[5]));
            gpsConfiguration.setCollaboratorPercent(new BigDecimal(firstRow[6]==null?"0":firstRow[6]));
            
            return gpsConfiguration;
        } catch (Exception e) {
            e.printStackTrace();
            return new GpsConfiguration();
        }  
    } 
    
    public Map<String,InssBases> getInssBases(Integer enterpriseCode){
        File file = new File("sql/getInssBase.sql");
        
        Map<String,String> variableChanges = new HashMap<>();
        variableChanges.put("enterpriseCode", enterpriseCode.toString());
        variableChanges.put("reference", reference.toString());
        
        
        ArrayList<String[]> results = Database.getDatabase().select(file, variableChanges);
        try {
            Map<String,InssBases> map = new HashMap<>();
            
            for (String[] result : results) {
                InssBases inssBases = new InssBases();
                inssBases.setEnterpriseCode(enterpriseCode);
                inssBases.setQuantity(Integer.valueOf(result[1]));
                inssBases.setBaseInss(new BigDecimal(result[3]==null?"0":result[3]));
                inssBases.setBaseInss13(new BigDecimal(result[4]==null?"0":result[4]));
                inssBases.setType(result[5].trim());
                
                map.put(inssBases.getType(),inssBases);
            }                                    
            
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }  
    } 
    
    
}

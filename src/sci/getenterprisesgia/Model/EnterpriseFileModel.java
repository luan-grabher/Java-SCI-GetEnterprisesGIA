package sci.getenterprisesgia.Model;

import fileManager.FileManager;
import java.io.File;
import java.util.Map;
import java.util.TreeMap;


public class EnterpriseFileModel {
    private File file;
    private final Map<Integer,String> enterprises = new TreeMap<>();

    public EnterpriseFileModel() {
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }        
    
    public void populateMap(){
        String fileText = FileManager.getText(file);
        String[] rows = fileText.split("\r\n");
        for (String row : rows) {
            try {
                String[] cols = row.split(";");
                Integer enterpriseNumber = Integer.valueOf(cols[0]);
                String enterpriseName = cols[1];
                
                enterprises.put(enterpriseNumber, enterpriseName);
            } catch (Exception e) {
            }            
        }
    }

    public Map<Integer, String> getEnterprises() {
        return enterprises;
    }        
}

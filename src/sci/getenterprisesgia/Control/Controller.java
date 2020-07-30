package sci.getenterprisesgia.Control;

import Entity.Executavel;
import fileManager.FileManager;
import java.io.File;
import sql.Database;


public class Controller {
    private final Integer month;
    private final Integer year;
    private Integer reference;
    private final File enterprisesFile;

    public Controller(Integer month, Integer year, File enterprisesFile) {
        this.month = month;
        this.year = year;
        this.enterprisesFile = enterprisesFile;        
    }
    
    public class setReference extends Executavel{

        public setReference() {
            name = "Definindo referência...";
        }

        @Override
        public void run() {
            if(year > 2000 && year < 3000 && month >= 1 && month <= 12){
                reference = Integer.valueOf(year + (month<10?"0":"") + month);
            }else{
                throw new Error("Ano ou mês inválido!");
            }
        }                
    }    
    
    public class connectToDatabase extends Executavel{
        public connectToDatabase() {
            name = "Conectando ao banco de dados...";
        }
        @Override
        public void run(){
            Database.setStaticObject(new Database(FileManager.getFile("sci.cfg")));
            if(!Database.getDatabase().testConnection()){
                throw new Error("Erro ao conectar ao banco de dados!");
            }
        }
    }
}

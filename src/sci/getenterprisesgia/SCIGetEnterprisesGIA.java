package sci.getenterprisesgia;

import Entity.Executavel;
import Executor.Execution;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import sci.getenterprisesgia.Control.Controller;

public class SCIGetEnterprisesGIA {

    
    public static void main(String[] args) {
        try {
            Integer month = Integer.valueOf(JOptionPane.showInputDialog("Digite o mês:"));
            Integer year = Integer.valueOf(JOptionPane.showInputDialog("Digite o ano:"));
            
            JOptionPane.showMessageDialog(null, "Selecione o arquivo CSV com as empresas pertencentes a GIA");
            File file = Selector.Arquivo.selecionar("C:/", "CSV", "csv");
            
            execute(month, year, file);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mês ou Ano inválido!", "Valores inválidos", JOptionPane.ERROR_MESSAGE);
        }        
    }
    
    public static void execute(Integer month, Integer year, File file){
        Controller controller = new Controller(month, year, file);
        
        List<Executavel> execs = new ArrayList<>();
        execs.add(controller.new setReference());
        execs.add(controller.new connectToDatabase());
        
        Execution exec = new Execution("Buscar informações GIA mensal");
        
        exec.setExecutables(execs);
        exec.runExecutables();
        exec.endExecution();        
    } 
    
}

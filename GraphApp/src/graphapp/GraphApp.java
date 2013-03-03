
package graphapp;

import java.io.File;

/**
 *
 * @author Marek
 */
public class GraphApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ScriptParser scriptP = new ScriptParser();
        Graph[] grafy = scriptP.parse(new TextFileReader(new File("graf.gs")));
        
        for(Graph g : grafy){
            System.out.println(g.getName() + ":");
            for(Edge e : g.getAllEdges()){
                System.out.println(e.getSource().getLabel() + " : " + e.getTarget().getLabel());
            }
        }
    }
}


package graphapp;

/**
 *
 * @author Marek
 */
public class GraphApp {
    private static ScriptParser scriptParser;
    private static TextFileWriter writer;
    private static Graph[] graphs;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Menu menu = new Menu();
        
        menu.RunMenu(graphs, scriptParser, writer);
    }
}


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
        System.out.println("Zapis...");
        TextFileWriter writer = new TextFileWriter("drugi.gs");
        writer.writeGraphs(grafy);
    }
}

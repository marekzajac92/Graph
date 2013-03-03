
package graphapp;

import java.util.ArrayList;

/**
 * Class which parse blocks of operations and create graphs as a list
 * @author Marek
 */
public class ScriptParser {
    
    /**
     * Private class that add to graph type, which is using by parser
     */
    class GraphEx{
        public Graph graph;
        public String type;
        
        GraphEx(Graph graph, String type){
            this.graph = graph;
            this.type = type;
        }
    }
    
    private ArrayList<GraphEx> graphs;
    
    public ScriptParser(){
        graphs = new ArrayList<>();
    }
    
    private GraphEx findGraph(String name){
        for(GraphEx g : graphs){
            if(g.graph.getName().equals(name)){
                return g;
            }
        }
        return null;
    }
}

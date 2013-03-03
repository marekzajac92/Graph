
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
    
    private Graph[] getAllGraphs(){
        ArrayList<Graph> allGraphs = new ArrayList<>();
        for(GraphEx gEx : graphs){
            allGraphs.add(gEx.graph);
        }
        
        return allGraphs.toArray(new Graph[allGraphs.size()]);
    }
    
    public Graph[] parse(Reader reader){
        graphs.clear();
        
        for(String block : reader.getBlocks()){
            int index = block.indexOf(Characters.OPEN_BLOCK);
            String[] commands = block.substring(0, index).split(Characters.COMMAND_SEPARATOR);
            
            GraphEx nextGraph = null;
            switch(commands.length){ 
                case 1:
                    nextGraph = findGraph(commands[0]);
                    break;
                case 2:
                    switch(commands[0]){
                        case Characters.NEW_GRAPH_COMMAND:
                            if(findGraph(commands[1]) != null){
                                System.err.println("Graph already exist. Can't create new with this name.");
                                break;
                            }
                            nextGraph = new GraphEx(new Graph(commands[1]), Characters.TYPE_MIX);
                            graphs.add(nextGraph);
                            break;
                        default:
                            System.err.println("Invalid command!");
                            break;
                    }
                    break;
                case 3:
                    switch(commands[0]){
                        case Characters.NEW_GRAPH_COMMAND:
                            if(findGraph(commands[2]) != null){
                                System.err.println("Graph already exist. Can't create new with this name.");
                                break;
                            }
                            nextGraph = new GraphEx(new Graph(commands[2]), commands[1]);
                            graphs.add(nextGraph);
                            break;
                        default:
                            System.err.println("Invalid command!");
                            break;
                    }
                    break;
                default:
                    System.err.println("Invalid number of command parameters!");
                    break;
            }
            
            if(nextGraph == null){
                System.err.println("No graph!");
                break;
            }
            
            block = block.substring(index + 1);
            
            commands = block.split(Characters.END_COMMAND_SEPARATOR);
            
            for(String nextCommand : commands){
                String[] command = nextCommand.split(Characters.COMMAND_SEPARATOR);
                
                switch(command.length){
                    case 2:
                        switch(command[0]){
                            case Characters.NEW_VERTEX_COMMAND:
                                nextGraph.graph.addVertex(command[1]);
                                break;
                            case Characters.NEW_UEDGE_COMMAND:
                                if(nextGraph.type.equals(Characters.TYPE_MIX) ||
                                        nextGraph.type.equals(Characters.TYPE_UDIR)){
                                    command = command[1].split(Characters.ARGUMENTS_SEPARATOR);
                                    if(command.length != 2){
                                        System.err.println("Invalid number of arguments!");
                                        break;
                                    }
                                    Vertex source = nextGraph.graph.getVertexByLabel(command[0]);
                                    Vertex target = nextGraph.graph.getVertexByLabel(command[1]);
                                    
                                    if(source == null || target == null){
                                        System.err.println("Vertex doesn't exist!");
                                        break;
                                    }
                                    else if(source == target){
                                        System.err.println("Source and target are the same vertex!");
                                        break;
                                    }
                                    
                                    nextGraph.graph.addEdge(source, target, false);
                                }
                                else{
                                    System.err.println("Invalid type of edge! Graph is directed.");
                                }
                                break;
                            case Characters.NEW_DEDGE_COMMAND:
                                if(nextGraph.type.equals(Characters.TYPE_MIX) ||
                                        nextGraph.type.equals(Characters.TYPE_DIR)){
                                    command = command[1].split(Characters.ARGUMENTS_SEPARATOR);
                                    if(command.length != 2){
                                        System.err.println("Invalid number of arguments!");
                                        break;
                                    }
                                    Vertex source = nextGraph.graph.getVertexByLabel(command[0]);
                                    Vertex target = nextGraph.graph.getVertexByLabel(command[1]);
                                    
                                    if(source == null || target == null){
                                        System.err.println("Vertex doesn't exist!");
                                        break;
                                    }
                                    else if(source == target){
                                        System.err.println("Source and target are the same vertex!");
                                        break;
                                    }
                                    
                                    nextGraph.graph.addEdge(source, target, true);
                                }
                                else{
                                    System.err.println("Invalid type of edge! Graph is undirected.");
                                }
                                break;
                            default:
                                System.err.println("Invalid graph command!");
                                break;
                        }
                        break;
                    default:
                        System.err.println("Invalid number parts of command!");
                        break;
                }
            }
            
        }
        
        return getAllGraphs();
    }
}

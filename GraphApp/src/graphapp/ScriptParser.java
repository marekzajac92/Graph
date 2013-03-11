
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
            
            getNextGraph(commands, nextGraph);
            
            if(nextGraph == null){
                System.err.println("No graph!");
                break;
            }
            
            block = block.substring(index + 1);
            
            commands = block.split(Characters.END_COMMAND_SEPARATOR);
            
            for(String nextCommand : commands){
                String[] command = nextCommand.split(Characters.COMMAND_SEPARATOR);
                analizeCommand(command, nextGraph);
            }
            
        }
        
        return getAllGraphs();
    }
    
    private void newVertex(Graph graph, String label){
        graph.addVertex(new Vertex(label));
    }
    
    private void newUEdge(String[] command, GraphEx nextGraph){
        if(nextGraph.type.equals(Characters.TYPE_MIX) ||
                nextGraph.type.equals(Characters.TYPE_UDIR)){
            command = command[1].split(Characters.ARGUMENTS_SEPARATOR);
            if(command.length != 2){
                System.err.println("Invalid number of arguments!");
                return;
            }
            Vertex source = nextGraph.graph.getVertexByLabel(command[0]);
            Vertex target = nextGraph.graph.getVertexByLabel(command[1]);

            if(source == null || target == null){
                System.err.println("Vertex doesn't exist!");
                return;
            }
            else if(source == target){
                System.err.println("Source and target are the same vertex!");
                return;
            }

            nextGraph.graph.addEdge(new Edge(source, target, false));
        }
        else{
            System.err.println("Invalid type of edge! Graph is directed.");
        }
    }
        
        private void newDEdge(String[] command, GraphEx nextGraph){
            if(nextGraph.type.equals(Characters.TYPE_MIX) ||
                    nextGraph.type.equals(Characters.TYPE_DIR)){
                command = command[1].split(Characters.ARGUMENTS_SEPARATOR);
                if(command.length != 2){
                    System.err.println("Invalid number of arguments!");
                    return;
                }
                Vertex source = nextGraph.graph.getVertexByLabel(command[0]);
                Vertex target = nextGraph.graph.getVertexByLabel(command[1]);

                if(source == null || target == null){
                    System.err.println("Vertex doesn't exist!");
                    return;
                }
                else if(source == target){
                    System.err.println("Source and target are the same vertex!");
                    return;
                }

                nextGraph.graph.addEdge(new Edge(source, target, true));
            }
            else{
                System.err.println("Invalid type of edge! Graph is undirected.");
            }
        }
        
        private void newGraph(String[] commands, GraphEx nextGraph){
            if(findGraph(commands[2]) != null){
                System.err.println("Graph already exist. Can't create new with this name.");
                return;
            }
            nextGraph = new GraphEx(new Graph(commands[2]), commands[1]);
            graphs.add(nextGraph);
        }
        
        private void analizeCommand(String[] command, GraphEx nextGraph){
            switch(command.length){
                    case 2:
                        switch(command[0]){
                            case Characters.NEW_VERTEX_COMMAND:
                                newVertex(nextGraph.graph, command[1]);
                                break;
                            case Characters.NEW_UEDGE_COMMAND:
                                newUEdge(command, nextGraph);
                                break;
                            case Characters.NEW_DEDGE_COMMAND:
                                newDEdge(command, nextGraph);
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
        
        private void getNextGraph(String[] commands, GraphEx nextGraph){
            switch(commands.length){ 
                case 1:
                    nextGraph = findGraph(commands[0]);
                    break;
                case 2:
                    switch(commands[0]){
                        case Characters.NEW_GRAPH_COMMAND:
                            commands = new String[]{commands[0], "MIX", commands[1]};
                            newGraph(commands, nextGraph);
                            break;
                        default:
                            System.err.println("Invalid command!");
                            break;
                    }
                    break;
                case 3:
                    switch(commands[0]){
                        case Characters.NEW_GRAPH_COMMAND:
                            newGraph(commands, nextGraph);
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
        }
}

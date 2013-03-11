
package graphapp;

import java.util.Scanner;
import java.io.File;
/**
 *
 * @author Marek
 */
public class Menu {
    private Scanner inputScanner;
    
    Menu(){
        inputScanner = new Scanner(System.in);
    }
    
    private Graph findGraph(String name, Graph[] graphs){
        for(Graph graph : graphs){
            if(graph.getName().equals(name)){
                return graph;
            }
        }
        return null;
    }
    
    public void RunMenu(Graph[] graphs, ScriptParser parser, TextFileWriter writer){
        boolean exit = false;
        
        while(!exit){
            switch(showMenu()){
                case 1:
                    graphs = loadGraph(parser);
                    break;
                case 2:
                    saveGraph(writer, graphs);
                    break;
                case 3:
                    showAllGraphs(graphs);
                    break;
                case 4:
                    addVertex(graphs);
                    break;
                case 5:
                    addEdge(graphs);
                    break;
                case 8:
                    exit = true;
                    break;
                default:
                    System.err.println("Invalid command!");
            }
        }
    }
    
    private int showMenu(){
        System.out.println("Graph Editor - MENU");
        System.out.println("1 - Load graphs from file");
        System.out.println("2 - Save graphs to file");
        System.out.println("3 - Show all graphs");
        System.out.println("4 - Add vertex to graph");
        System.out.println("5 - Add edge to graph");
        System.out.println("6 - Remove vertex");
        System.out.println("7 - Remove edge");
        System.out.println("8 - Exit");
        
        return inputScanner.nextInt();
    }
    private Graph[] loadGraph(ScriptParser parser){
        System.out.println("File to load:");
        String fileName = inputScanner.next();
        return parser.parse(new TextFileReader(new File(fileName)));
    }
    
    private void saveGraph(TextFileWriter writer, Graph[] graphs){
        System.out.println("File to save:");
        String fileName = inputScanner.next();
        
        writer = new TextFileWriter(fileName);
        writer.writeGraphs(graphs);
    }
    
    private void showAllGraphs(Graph[] graphs){
        for(Graph graph : graphs){
            System.out.println("Graph name: " + graph.getName() + 
                    " : vertices: " + graph.getAllVertices().length + 
                    " , edges: " + graph.getAllEdges().length);
        }
    }
    
    private void addVertex(Graph[] graphs){
        System.out.println("Which graph:");
        String fileName = inputScanner.next();
        
        Graph graph = findGraph(fileName, graphs);
        
        if(graph != null){
            System.out.println("Label of the new vertex:");
            String vertexLabel = inputScanner.next();
            if(graph.getVertexByLabel(vertexLabel) == null){
                graph.addVertex(new Vertex(vertexLabel));
                System.out.println("Vertex added");
            }
            else{
                System.err.println("Vertex with the label already exist!");
            }
        }
        else{
            System.err.println("Graph doesn't exist!");
        }
    }
    
    private void addEdge(Graph[] graphs){
        System.out.println("Which graph:");
        String fileName = inputScanner.next();
        
        Graph graph = findGraph(fileName, graphs);
        
        if(graph != null){
            System.out.println("Label of the source:");
            String sourceLabel = inputScanner.next();
            System.out.println("Label of the target:");
            String targetLabel = inputScanner.next();
            System.out.println("Directed edge?:");
            boolean isDirected = inputScanner.nextBoolean();
            
            Vertex source = graph.getVertexByLabel(sourceLabel);
            Vertex target = graph.getVertexByLabel(targetLabel);
            if(source != null && target != null){
                graph.addEdge(new Edge(source, target, isDirected));
                System.out.println("Edge added");
            }
            else{
                System.err.println("Source or taget doesn't exist!");
            }
        }
        else{
            System.err.println("Graph doesn't exist!");
        }
    }
}

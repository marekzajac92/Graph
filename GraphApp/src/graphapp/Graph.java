package graphapp;

import java.util.ArrayList;
import java.util.List;

//klasa grafu
public class Graph {
    
    private String name;
    
    private int id;
    private List<Vertex> vertexList;
    private List<Edge> edgeList;
    
    private int getNextId(){
        return id++;
    }
    
    public Graph(String name){
        this.name = name;
        id = 0;
        vertexList = new ArrayList<>();
        edgeList = new ArrayList<>();
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public Vertex getVertexByLabel(String label){
        for(Vertex v : vertexList){
            if(v.getLabel().equals(label)) {
                return v;
            }
        }
        return null;
    }
    
    public Vertex[] getAllVertices(){
        return vertexList.toArray(new Vertex[vertexList.size()]);
    }
    
    public Edge[] getAllEdges(){
        return edgeList.toArray(new Edge[edgeList.size()]);
    }
    
    public Edge[] getEdgesBySoruce(Vertex vertex){
        List<Edge> matchingEdges = new ArrayList<>();
        for(Edge e: edgeList){
            if(e.getSource() == vertex){
                matchingEdges.add(e);
            }
            else if(!e.isDirected()){
                if(e.getTarget() == vertex){
                    matchingEdges.add(e);
                }
            }
        }
        
        return matchingEdges.toArray(new Edge[matchingEdges.size()]);
    }
    
    public Edge[] getEdgesByTarget(Vertex vertex){
        List<Edge> matchingEdges = new ArrayList<>();
        for(Edge e: edgeList){
            if(e.getTarget() == vertex){
                matchingEdges.add(e);
            }
            else if(!e.isDirected()){
                if(e.getSource() == vertex){
                    matchingEdges.add(e);
                }
            }
        }
        
        return matchingEdges.toArray(new Edge[matchingEdges.size()]);
    }
    
    public Edge[] getEdges(Vertex vertex){
        List<Edge> matchingEdges = new ArrayList<>();
        for(Edge e: edgeList){
            if(e.getTarget() == vertex || e.getSource() == vertex){
                matchingEdges.add(e);
            }
        }
        
        return matchingEdges.toArray(new Edge[matchingEdges.size()]);
    }
    
    public boolean containsVertex(Vertex v){
        return vertexList.contains(v);
    }
    
    public void addVertex(String label){
        vertexList.add(new Vertex(getNextId(), label));
    }
    
    public void addEdge(Edge newEdge){
        edgeList.add(newEdge);
    }
    public void addEdge(Vertex source, Vertex target, boolean isDirected){
        edgeList.add(new Edge(source, target, isDirected));
    }
}

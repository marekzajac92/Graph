package graphapp;
//klasa krawedzi
public class Edge {
    private Vertex source;
    private Vertex target;
    
    private boolean isDirected;
    
    public Edge(Vertex source, Vertex target, boolean isDirected){
        this.source = source;
        this.target = target;
        this.isDirected = isDirected;
    }
    
    public Vertex getSource(){
        return source;
    }
    public void setSource(Vertex source){
        this.source = source;
    }
    
    public Vertex getTarget(){
        return target;
    }
    public void setTarget(Vertex target){
        this.target = target;
    }
    
    public boolean isDirected(){
        return isDirected;
    }
}

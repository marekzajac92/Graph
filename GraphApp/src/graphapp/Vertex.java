package graphapp;
//klasa wierzcholka
public class Vertex{
    private int id;
    private String label;
    
    public Vertex(int id, String label){
        this.id = id;
        this.label = label;
    }
    
    public String getLabel(){
        return label;
    }
    public void setLabel(String newLabel){
        label = newLabel;
    }
    public int getId(){
        return id;
    }
}
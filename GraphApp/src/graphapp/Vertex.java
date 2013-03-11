package graphapp;
//klasa wierzcholka
public class Vertex{
    private int id;
    private String label;
    
    public Vertex(String label){
        this.label = label;
    }
    
    public void setId(int id){
        this.id = id;
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
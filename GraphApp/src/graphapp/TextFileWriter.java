package graphapp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 *
 * @author Marek
 */
public class TextFileWriter implements Writer{
    private String fileName;

    public TextFileWriter(String fileName) {
        this.fileName = fileName;
    }
    
    @Override
    public void writeGraphs(Graph[] graphs){
        FileOutputStream fileStream = null;
        try{
            fileStream = new FileOutputStream(fileName);
        
            try{
                for(Graph graph : graphs){
                    String textToWrite = Characters.NEW_GRAPH_COMMAND +
                            Characters.COMMAND_SEPARATOR + graph.getName() +
                            Characters.OPEN_BLOCK + "\n";
                    byte[] buffer = textToWrite.getBytes();
                    fileStream.write(buffer);
                    for(Vertex vertex : graph.getAllVertices()){
                        textToWrite = "\t" + Characters.NEW_VERTEX_COMMAND +
                                Characters.COMMAND_SEPARATOR + vertex.getLabel() +
                                Characters.END_COMMAND_SEPARATOR +"\n";
                        buffer = textToWrite.getBytes();
                        fileStream.write(buffer);
                    }
                    for(Edge edge : graph.getAllEdges()){
                        if(edge.isDirected()){
                            textToWrite = "\t" + Characters.NEW_DEDGE_COMMAND + 
                                    Characters.COMMAND_SEPARATOR;
                        }
                        else{
                            textToWrite = "\t" + Characters.NEW_UEDGE_COMMAND + 
                                    Characters.COMMAND_SEPARATOR;
                        }
                        textToWrite += edge.getSource().getLabel() + 
                                Characters.ARGUMENTS_SEPARATOR + 
                                edge.getTarget().getLabel() + 
                                Characters.END_COMMAND_SEPARATOR +"\n";
                        buffer = textToWrite.getBytes();
                        fileStream.write(buffer);
                    }
                    textToWrite = Characters.CLOSE_BLOCK + "\n";
                    buffer = textToWrite.getBytes();
                    fileStream.write(buffer);
                }
                fileStream.close();
            }
            catch(IOException ex){
                System.err.println("IOException!");
            }
            catch(Exception ex){
                System.err.println("Other exception: " + ex.getMessage());
            }
        }
        catch(FileNotFoundException ex){
            System.err.println("FileNotFoundException!");
        }
        catch(Exception ex){
            System.err.println("Other exception: " + ex.getMessage());
        }
    }
    
}

package graphapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Class that implements Reader interface for text files.
 * @author Marek
 */
public class TextFileReader implements Reader{
    
    private String script;
    
    /**
     * Constructor
     * @param file Object file which contains script to read. 
     */
    public TextFileReader(File file){
        try{
            script = new String();
            Scanner fileScanner = new Scanner(file);
            while(fileScanner.hasNextLine()){
                script += fileScanner.nextLine();
            }
        }
        catch(FileNotFoundException ex){
            System.err.println("FileNotFoundException");
        }
        catch(Exception ex){
            System.err.println("Other exception: " + ex.getLocalizedMessage());
        }
    }
    
    /**
     * Method remove cooments from script
     * @return String with script which hasn't comments
     */
    private String clearScript(String script){
        String scriptWithoutComments = new String();
       
        scriptWithoutComments = script.replaceAll("\\s", "");
        return scriptWithoutComments;
    }
    
    /**
     * 
     * @return Table of strings that contains all blocks of operations readed from file
     */
    @Override
    public String[] getBlocks(){
        script = clearScript(script);
        
        String[] blocks = script.split(Characters.CLOSE_BLOCK);
        return blocks;
    }
}

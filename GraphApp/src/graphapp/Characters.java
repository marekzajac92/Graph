
package graphapp;

/**
 * Class that contains all separators, special characters as a finaly strings etc.
 * @author Marek
 */
public class Characters {
    public static final String OPEN_COMMENT = "/*";
    public static final String CLOSE_COMMENT = "*/";
    
    public static final String COMMAND_SEPARATOR = ":";
    public static final String END_COMMAND_SEPARATOR = ";";
    
    public static final String ARGUMENTS_SEPARATOR = ",";
    
    public static final String OPEN_BLOCK = "{";
    public static final String CLOSE_BLOCK = "}";
    
    public static final String NEW_GRAPH_COMMAND = "@graph";
    public static final String NEW_VERTEX_COMMAND = "#vertex";
    public static final String NEW_UEDGE_COMMAND = "#uedge";
    public static final String NEW_DEDGE_COMMAND = "#dedge";
    public static final String REMOVE_VERTEX = "#removeVertex";
    public static final String REMOVE_EDGE = "#removeEdge";
    
    public static final String TYPE_MIX = "MIX";
    public static final String TYPE_DIR = "DIR";
    public static final String TYPE_UDIR = "UDIR";
}

import java.util.    HashMap;
import java.util.    Map;
import java.util.    Arrays;
import java.util.    Set;
import java.util.    Iterator;
public class Prim {
    public static HashMap<String, Integer> primMap = new HashMap<String, Integer>();
    public static String[] spanningTree;
    public static String mapInput = "A,B,3|B,C,10|C,A,4|D,E,2|E,A,6";

    public static void main(String[] args) {
        String[] tuples = mapInput.split("\\|");

        for (int i = 0; i < tuples.length; i++) {
            tuples[i] = tuples[i].replace(",", "");
            primMap.put(tuples[i].substring(0, 2), Integer.parseInt(tuples[i].substring(2)));
        }

        


    }

    public static void printMap(HashMap primMap) {
        Set setOfKeys = primMap.keySet();
        Iterator iterator = setOfKeys.iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            Integer value = (Integer)primMap.get(key);
            System.out.println("Key: "+ key+", Value: "+ value);
        }
    }





}
import java.util.*;

/** All the commented part is HashMap implementation, used TreeMap to order it. */
public class MapTest {

	public static void main(String[] args) {
		//HashMap<Integer, String> myMap = new HashMap<Integer, String>();
		TreeMap<Integer, String> orderedMyMap = new TreeMap<Integer, String>();

		for (int i = 0; i <= 300; i++) {
			orderedMyMap.put(i, "AYYYE LMAFO");
		}

		//TreeMap<Integer, String> orderedMyMap = new TreeMap<Integer, String>();
		//orderedMyMap.putAll(myMap);

		for (Integer name: orderedMyMap.keySet()) {
            String key = name.toString();
            String value = orderedMyMap.get(name).toString();  
            System.out.println(key + " " + value);  
		} 
	}















}
package logic;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class RandomMapGenerator {
	private Map<String,Integer>randMap;
	public RandomMapGenerator(String filename) {
		parseFile(filename);

	}
private void parseFile(String f){
	File freq = new File(f);
	randMap = new HashMap<String,Integer>();
}
}

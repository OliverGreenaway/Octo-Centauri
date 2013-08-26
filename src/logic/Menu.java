package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Menu {

	private final List<String> options;

	public Menu(String... opts) {
		options = new ArrayList<String>();
		options.addAll(Arrays.asList(opts));
	}

	public Menu(List<String> opts){
		options = new ArrayList<String>();
		options.addAll(opts);
	}

	public List<String> getOptions(){
		return options;
	}





}

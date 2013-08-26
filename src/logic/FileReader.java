package logic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import state.TileInterface;


/**
 * Helper class for reading menus for various objects
 * from a file.
 * @author markovmadd
 *
 */
public class FileReader {

	/**
	 * Read the menu file, which specifies the right-click menu options for game classes.
	 * @param filename - File location.
	 * @return - A Map from class names to lists of menu items.
	 */
	public static Map<String, List<String>> readMenus(String filename){
		File file = new File(filename);
		Scanner scan = null;
		Map<String, List<String>> menus;
		try{
			scan = new Scanner(file);
		}catch(IOException e){System.out.println("Menu settings file not found.");}
		menus = new HashMap<String, List<String>>();

		while(scan.hasNextLine()){
			List<String> menuOptions = new ArrayList<String>();
			Scanner lineScan = new Scanner(scan.nextLine());
			lineScan.useDelimiter("\t");
			String className = lineScan.next();
				while(lineScan.hasNext()){
					menuOptions.add(lineScan.next());
				}
			lineScan.close();
			menus.put(className, menuOptions);
		}
		scan.close();
		return menus;
	}

	public static TileInterface[][] readMap(String filename){
		File file = new File(filename);
		Scanner scan = null;
		TileInterface[][] tiles;

		try{
			scan = new Scanner(file);
		}catch(IOException e){System.out.println("Map file not found.");}
		Scanner lineScan = new Scanner(scan.nextLine());
		lineScan.useDelimiter("\t");
		int x = Integer.parseInt(lineScan.next());
		int y = Integer.parseInt(lineScan.next());
		tiles = new TileInterface[x][y];

		while(scan.hasNextLine()){
			List<String> menuOptions = new ArrayList<String>();
			Scanner lineScan = new Scanner(scan.nextLine());
			lineScan.useDelimiter("\t");
			String className = lineScan.next();
				while(lineScan.hasNext()){
					menuOptions.add(lineScan.next());
				}
			lineScan.close();
			menus.put(className, menuOptions);
		}
		scan.close();
		return menus;
	}

}

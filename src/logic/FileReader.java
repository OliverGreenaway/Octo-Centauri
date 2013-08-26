package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
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
		TileInterface[][] tiles;

		try{
			InputStream in = new FileInputStream(file);
			Reader fileReader = new InputStreamReader(in, Charset.defaultCharset());
			BufferedReader buffer = new BufferedReader(fileReader);

			String line1 = buffer.readLine();
			Scanner lineScan = new Scanner(line1);
			lineScan.useDelimiter("\t");
			int x = Integer.parseInt(lineScan.next());
			int y = Integer.parseInt(lineScan.next());

			tiles = new TileInterface[x][y];
			lineScan = new Scanner(buffer.readLine());
			lineScan.useDelimiter("\t");
			Map<String, String> symbols = new HashMap<String, String>();
			while(lineScan.hasNext()){
				String sym = lineScan.next();
				String img = lineScan.next();
				symbols.put(sym, img);
			}

			boolean r;
			while (r = buffer.read() != -1){
				for(int row = 0; row < y; ++row){
					for(int col = 0; col < x; ++col){

					}

				}
			}
		}catch(IOException e){System.out.println("Error reading map file." + e.getMessage());}

//		try{
//			scan = new Scanner(file);
//		}catch(IOException e){System.out.println("Map file not found.");}
//		Scanner lineScan = new Scanner(scan.nextLine());
//		lineScan.useDelimiter("\t");
//		int x = Integer.parseInt(lineScan.next());
//		int y = Integer.parseInt(lineScan.next());
//		tiles = new TileInterface[x][y];
//		lineScan = new Scanner(scan.nextLine());
//		lineScan.useDelimiter("\t");
//		Map<String, String> symbols = new HashMap<String, String>();
//		while(lineScan.hasNext()){
//			String sym = lineScan.next();
//			String img = lineScan.next();
//			symbols.put(sym, img);
//		}
//
//		while(scan.hasNextLine()){
//			Scanner lineScan = new Scanner(scan.nextLine());
//			lineScan.useDelimiter("\t");
//			String className = lineScan.next();
//				while(lineScan.hasNext()){
//					menuOptions.add(lineScan.next());
//				}
//			lineScan.close();
//			menus.put(className, menuOptions);
//		}
//		scan.close();
//		return menus;
	}

}

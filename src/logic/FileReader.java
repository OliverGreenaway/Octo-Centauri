package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import state.Tile;
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


	/**
	 * Read the terrain from the map file.  At the moment, this only reads terrain.
	 * @param filename - map file to read from.
	 * @return - a 2D array of TileInterface.
	 */
	public static TileInterface[][] readMap(String filename){
		File file = new File(filename);
		TileInterface[][] tiles = null;

		try{
			InputStream in = new FileInputStream(file);
			Reader fileReader = new InputStreamReader(in, Charset.defaultCharset());
			BufferedReader buffer = new BufferedReader(fileReader);

			String line1 = buffer.readLine();
			Scanner lineScan = new Scanner(line1);
			lineScan.useDelimiter("\\s");
			int x = Integer.parseInt(lineScan.next());
			int y = Integer.parseInt(lineScan.next());
			System.out.println(y);

			tiles = new TileInterface[x][y];
			String line2 = buffer.readLine();
			System.out.println("Line:" +line2);
			lineScan = new Scanner(line2);
			lineScan.useDelimiter(",");
			Map<String, String> symbols = new HashMap<String, String>();
			while(lineScan.hasNext()){
				String sym = lineScan.next();
				System.out.println(sym);
				String img = lineScan.next();
				System.out.println("Image: " +img);
				symbols.put(sym, img);
			}

			boolean r;
			//while (r = buffer.read() != -1){
				int row, col;
				for(row = 0; row < y; ++row){
					StringReader lineReader = new StringReader(buffer.readLine());
					for(col = 0; col < x; ++col){
						int charInt = lineReader.read();
						char c = (char)charInt;
						String symb = Character.toString(c);
						System.out.println("Row: "+row+ " Col" +col+ " Symbol: " +symb);
						assert(symbols.get(symb) != null);
						tiles[row][col] = new Tile(symbols.get(symb));
						tiles[row][col].setX(row);
						tiles[row][col].setY(col);
					}
//					if(col == 199)
//						col = 0;
					System.out.println("One column done");

				}
			//}
			buffer.close();
			fileReader.close();

		}catch(IOException e){System.out.println("Error reading map file." + e.getMessage());}
		assert(tiles != null);
		return tiles;
	}
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
//			lineScan.useDelimiter("\t");//		for(int i = 0; i < 200; i++){
//	for(int j = 0; j < 200; j++){
//	map[i][j] = new Tile(generateRandomTile());
//}
//}
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

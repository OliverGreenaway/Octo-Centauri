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

import state.Crate;
import state.Crystal;
import state.DudeSpawnBuilding;
import state.Plant;
import state.Resource;
import state.Structure;
import state.Tile;
import state.Tree;
import state.World;
import util.TileImageStorage;


/**
 * Helper class for reading menus for various objects
 * from a file.
 * @author markovmadd
 *
 */
public class FileReader {

	private static List<Structure> structures;
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
	public static Tile[][] readMap(String filename){
		File file = new File(filename);
		Tile[][] tiles = null;
		structures = new ArrayList<Structure>();

		try{
			InputStream in = new FileInputStream(file);
			Reader fileReader = new InputStreamReader(in, Charset.defaultCharset());
			BufferedReader buffer = new BufferedReader(fileReader);

			String line1 = buffer.readLine();//Read the first line which specifies the dimensions
			Scanner lineScan = new Scanner(line1);
			lineScan.useDelimiter("\\s");
			int x = Integer.parseInt(lineScan.next());
			int y = Integer.parseInt(lineScan.next());
			lineScan.close();

			tiles = new Tile[x][y];

			String line2 = buffer.readLine(); //Read the second line which specifies mapping from symbols to images
			tiles = new Tile[x][y];

			lineScan = new Scanner(line2);
			lineScan.useDelimiter(",");
			Map<String, String> symbols = new HashMap<String, String>();
			while(lineScan.hasNext()){
				String sym = lineScan.next();
				String img = lineScan.next();
				symbols.put(sym, img);
			}
			lineScan.close();
			//Read the text representation of the map
				int row, col;
				for(row = 0; row < y; ++row){
					StringReader lineReader = new StringReader(buffer.readLine());
					for(col = 0; col < x; ++col){
						int charInt = lineReader.read();
						char c = (char)charInt;
						String symb = Character.toString(c);
						assert(symbols.get(symb) != null);
						tiles[row][col] = new Tile(symbols.get(symb),0, null, row, col);
					}
				}

				//Now read objects
				buffer.readLine();
				int numLines = Integer.parseInt(buffer.readLine());
				String line = buffer.readLine();
				Scanner lineScanner = null;
				while(numLines > 0){
					lineScanner = new Scanner(line);
					lineScanner.useDelimiter("\\s");
					String fileName = lineScanner.next();
					while(lineScanner.hasNext()){
						int strucX = Integer.parseInt(lineScanner.next());
						int strucY = Integer.parseInt(lineScanner.next());
						Structure temp;
						if(fileName.equals("DarkTree"))
							temp = new Tree(strucX, strucY);
						else if(fileName.equals("Resources"))
							temp = new Crystal(strucX, strucY);
						else if(fileName.equals("Plant"))
							temp = new Plant(strucX, strucY);
						else if(fileName.equals("Crate"))
							temp = new Crate(strucX, strucY);
						else if(fileName.equals("Spawner"))
							temp = new DudeSpawnBuilding(strucX, strucY);
						else
							temp = new Structure(strucX, strucY, 1, 1, "Assets/EnvironmentTiles/" +fileName+ ".png");
						structures.add(temp);;
					}
					numLines--;
					line = buffer.readLine();
				}
				lineScanner.close();
			//read height
				System.out.println(line);
				numLines = Integer.parseInt(line);
				line = buffer.readLine();
				lineScanner = null;
				while(numLines > 0){
					lineScanner = new Scanner(line);
					lineScanner.useDelimiter("\\s");
					while(lineScanner.hasNext()){
						int strucX = Integer.parseInt(lineScanner.next());
						int strucY = Integer.parseInt(lineScanner.next());
						int height = Integer.parseInt(lineScanner.next());
						tiles[strucX][strucY].setHeight(height);
					}
					numLines--;
					line = buffer.readLine();
				}
			lineScanner.close();

			buffer.close();
			fileReader.close();

		}catch(IOException e){System.out.println("Error reading map file." + e.getMessage());}
		assert(tiles != null);
		return tiles;
	}

	public static void setStructures(World w){
		for(Structure struct: structures){
			w.addStructure(struct);
		}
	}



}

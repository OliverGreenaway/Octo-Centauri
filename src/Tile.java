import java.awt.Image;
import javax.swing.ImageIcon;


public class Tile {

	// FIELDS
	private Image img;

	public Tile(){
		img = new ImageIcon("resources/tile0.png").getImage();
	}


	public Image getImage(){
		return img;
	}


}

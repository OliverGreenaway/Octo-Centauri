import javax.swing.JFrame;


public class Test extends JFrame {
	private static final long serialVersionUID = -1711852809983174095L;
	Display canvas;

	public Test(){
		super("TEST WINDOW");
		Tile[][] map = new Tile[200][200];
		for(int i = 0; i < 200; i++){
			for(int j = 0; j < 200; j++){
				map[i][j] = new Tile();
			}
		}
		canvas = new Display(map);
		add(canvas);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setResizable(true);
		setVisible(true);
		canvas.repaint();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Window();
	//	new Test();

	}

}

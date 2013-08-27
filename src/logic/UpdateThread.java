package logic;
import Display;
import state.World;


public class UpdateThread extends Thread {
private final Display display;
private final World world;
	public UpdateThread(World w, Display d) {
    world = w;
    display = d;
	}
public void run(){
	while(1 == 1){
	world.update();
	display.repaint();
	try {
		Thread.sleep(100);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}

}
}
}

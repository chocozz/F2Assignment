import javax.swing.*;
import java.awt.*;

public class Main{
	public static void main(String args[]){

		JFrame f = new JFrame("5410110223");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(400, 630);
		f.getContentPane().setLayout(new BorderLayout());

		SpaceShip v = new SpaceShip(180, 550, 20, 20);
		GamePanel gp = new GamePanel();
		GameEngine engine = new GameEngine(gp, v);
		f.addKeyListener(engine);
		f.getContentPane().add(gp, BorderLayout.CENTER);
		f.setVisible(true);		// show 
		
		engine.start();
			
	}
}
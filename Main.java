import javax.swing.*;
import java.awt.*;


public class Main{
	public static void main(String args[]){
		JFrame f = new JFrame("5410110223");
		
		JPanel p = new JPanel();
		p.setBackground(Color.BLACK);
		f.add(p);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(400, 650);
		f.setVisible(true);		// show 	
	}
}
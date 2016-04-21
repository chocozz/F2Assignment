package f2.spw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

import java.awt.Toolkit;
import java.awt.Image;

public class GamePanel extends JPanel {
	
	private BufferedImage bi;	
	Graphics2D big;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	public GamePanel() {
		bi = new BufferedImage(400, 600, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();

	}

	public void updateGameUI(GameReporter reporter){
		big.clearRect(0, 0, 400, 600);

		big.setColor(Color.RED);
		big.drawString(String.format("Scores: %d", reporter.getScore()), 300, 20);	

		big.setColor(Color.YELLOW);
		big.drawString(String.format("Items: %d", reporter.getScoreItem()), 300, 40);


		for(Sprite s : sprites){
			s.draw(big);
		}
		
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}
}

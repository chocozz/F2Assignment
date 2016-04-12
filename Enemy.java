package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;

public class Enemy extends Sprite{
	
	public Enemy(int x, int y) {
		super(x, y, 5, 10);	
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
		
	}
}
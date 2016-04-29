import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;

import java.awt.Toolkit;
import java.awt.Image;

public class Item extends Sprite{
	private int step = 10;
	private boolean alive = true;
	public static final int Y_TO_FADE = 400;
	public static final int Y_TO_DIE = 600;

	public Item(int x, int y){
		super(x, y, 30, 30);
	}

	@Override
	public void draw(Graphics2D g){
		Image img = Toolkit.getDefaultToolkit().getImage("item.gif");
        g.drawImage(img, x, y, width, height, null);
        //g.setColor(Color.CYAN);
		//g.fillRect(x, y, width, height);
	}

	public void proceed(){
		y+=step;
		if(y> Y_TO_DIE){
			alive = false;
		}
	}

	public void getHit(){
		this.alive = false;
	}
	public boolean isAlive(){
		return alive;
	}
}
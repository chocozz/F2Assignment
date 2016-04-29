import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
		
	private SpaceShip v;	
	private Timer timer;
	private double difficulty = 0.1;
	private double diffItem = 0.01;

	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Item> item = new ArrayList<Item>();
	private ArrayList<Boss> boss= new ArrayList<Boss>();

	private long score = 0;	
	private long scoreitem = 0;

	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		
		gp.sprites.add(v);
		
		timer = new Timer(50, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
			}
		});
		timer.setRepeats(true);
	}
	
	public void start(){
		//timer.start();
		gp.StartGameUI(this);
	}
	
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		enemies.add(e);
	}

	private void generateItem(){
		Item e = new Item((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		item.add(e);
	}

	private void generateBoss(){
		Boss e = new Boss((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		boss.add(e);
	}

	private void process(){
		if(Math.random() < difficulty){  // define difficul of Enemy
			generateEnemy();
		}
		if(Math.random() < diffItem){  // define difficul of Item
			generateItem();
		}
		
		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();

			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				score += 100;
				if(score%1000 == 0)
					generateBoss();
			}	
		}

		Iterator<Boss> b_iter =boss.iterator();
 		while(b_iter.hasNext()){
 			Boss b = b_iter.next();
 			b.proceed();
 	
 			if(!b.isAlive()){
 				b_iter.remove();
 				gp.sprites.remove(b);
 			}
 		} 	

 		Iterator<Item> i_iter = item.iterator();
		while(i_iter.hasNext()){
			Item i = i_iter.next();
			i.proceed();
			
			if(!i.isAlive()){
				i_iter.remove();
				gp.sprites.remove(i);
				//scoreitem += 1;
			}
		}

		gp.updateGameUI(this);

		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				die();
				return;
			}
		}
		for(Boss b : boss){
			er = b.getRectangle();
			if(er.intersects(vr)){
				die();
				return;
			}
		}

		for(Item i : item){
			er = i.getRectangle();
			if(er.intersects(vr)){
				i.getHit();
				scoreitem += 1;
				return;
			}
		}

	}
	
	public void die(){
		timer.stop();
	}	

	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v.move(-1);
			break;
		case KeyEvent.VK_RIGHT:
			v.move(1);
			break;
		case KeyEvent.VK_D:
			difficulty += 0.1;
			break;

		case KeyEvent.VK_ENTER:
 			timer.start();
 			break;
		}
	}

	public long getScore(){
		return score;
	}

	public long getScoreItem(){
		return scoreitem;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//
	}

	@Override
	public void keyTyped(KeyEvent e) {		
		//
	}
}

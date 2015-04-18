import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.Vector;

import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable{
	
	Vector<Pillar> allPillars;
	Bird bird;
	
	public GamePanel() {
		super();
		allPillars = new Vector<Pillar>();
		bird = new Bird(this);
		Thread t = new Thread(this);
		
		this.setFocusable(true);
		
		// Use key arrow to control the bird
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_UP) {
					bird.fly();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		

		
		t.start();
	}
	
	
	public void drawPillars(Graphics g) {
		for(int i=0;i<allPillars.size();i++) {
			Pillar pillar = allPillars.get(i);
			g.drawImage(allPillars.get(i).pillarImage, pillar.x, pillar.baseY,50,400-pillar.baseY, null);
			g.drawImage(allPillars.get(i).pillarImage, pillar.x, pillar.topY,50, pillar.baseY-70, null);
		}
	}
	
	private void drawBirds(Graphics g) {
		g.drawImage(this.bird.birdImage, 100,this.bird.getY(), 35,35,null);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// First draw background image
//		drawBackground();
		
		// Then draw all pillars
		drawPillars(g);
		
		// Finally draw birds
		drawBirds(g);
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				// generate new pillar every seconds
				Pillar newPillar = new Pillar(this);
				allPillars.add(newPillar);
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

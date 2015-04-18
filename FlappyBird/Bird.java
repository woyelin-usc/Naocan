import java.awt.Image;

import javax.swing.ImageIcon;


public class Bird extends Thread {
	
	private int y;
	private int vy;
	private GamePanel myPanel;
	
	private ImageIcon birdII = new ImageIcon("Bird.jpg");
	Image birdImage = birdII.getImage();
	
	public Bird(GamePanel myPanel) {
		y = 200;
		this.myPanel = myPanel;
		vy = 2;
		birdImage = birdImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		
		this.start();
	}
	
	public int getY() {
		return this.y;
	}
	
	public void drop() {
		y += vy;
		myPanel.repaint();
	}
	
	public void fly() {
			try {
				y -= 30;
				myPanel.repaint();
				this.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void run() {
		while(true) {
			// keep dropping
			try {
				this.drop();
				this.sleep(25);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

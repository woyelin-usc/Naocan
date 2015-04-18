import java.awt.Image;

import javax.swing.ImageIcon;


public class Pillar extends Thread {
	
	int gap, top, baseY,topY, x, vx;
	private GamePanel myPanel;
	
	ImageIcon pillarII = new ImageIcon("Pillar.jpg");
	Image pillarImage = pillarII.getImage();
	
	public Pillar(GamePanel myPanel) {
		gap = 70;
		vx = 3;
		x = 400;
		this.baseY = (int)(Math.random()*1000)%170+120;
		this.topY = this.baseY-70-(330 - (400-this.baseY));
		this.myPanel = myPanel;
		
		this.start();
	}
	
	public void move() {
		x -= vx;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			if (x >= -100) {
				try {
					move();
					myPanel.repaint();
					this.sleep(30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				myPanel.allPillars.remove(this);
				return;
			}
		}
	}
}

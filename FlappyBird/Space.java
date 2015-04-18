import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class Space extends JFrame {
	
	private Vector<Pillar> allPillars;
	private Bird bird;
	private GamePanel myPanel;
	
	private ImageIcon image = new ImageIcon("pillar.jpg");
	
	
	public Space() {
		super("FlappyBirds!");
		setLocation(200,100);
		setSize(400,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		myPanel = new GamePanel();
		add(myPanel);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Space();
	}

}

package pigu;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class Pigu extends JFrame {
	
	private JPanel generalPanel, welcomePanel, gamePanel, timePanel, gridPanel, enterGamePanel;
	private JLabel timeLabel, welcomeLabel;
	
	private JLabel[] allLabel;
	
	private JButton enterGameBtn;
	
	private int targetNum;
	private int partitionNum = 2;
	
	private ImageIcon falseII = new ImageIcon("zhuzi.jpg");
	private ImageIcon targetII = new ImageIcon("target.jpg");
	
	private Font timeFont = new Font("Courier", Font.BOLD, 24);
	
	private void configureGridPanel(int partitionNum) {
		
		int imageNum = partitionNum * partitionNum;
		targetNum = ((int)((Math.random())*1000))%imageNum;
		
		if(this.gridPanel==null) {
			gridPanel = new JPanel();
			gridPanel.setPreferredSize(new Dimension(600,600));
		}
		
		resizeImage(partitionNum);

		gridPanel.removeAll();
		gridPanel.updateUI();
		gridPanel.setLayout(new GridLayout(partitionNum, partitionNum));

		allLabel = new JLabel[imageNum];
		for (int i = 0; i < imageNum; i++) {
			allLabel[i] = new JLabel();
			allLabel[i].setIcon(falseII);
			gridPanel.add(allLabel[i]);
		}

		allLabel[targetNum].setIcon(targetII);
		
		partitionNum = partitionNum+1;
		final int partitionFinalNum = partitionNum;
		allLabel[targetNum].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				configureGridPanel(partitionFinalNum);
			}
		});
	}
	
	private void configureTimePanel() {
		timePanel = new JPanel();
		timeLabel = new JLabel("Remaining time: ");
		timePanel.add(timeLabel);
		timeLabel.setFont(timeFont);
	}
	
	private void configureWelcomePanel() {
		welcomePanel = new JPanel();
		welcomePanel.setLayout(new BorderLayout());
		welcomeLabel = new JLabel("Welcome");
		welcomeLabel.setFont(new Font("Courier", Font.BOLD, 30));
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomePanel.add(welcomeLabel);
		
		enterGamePanel = new JPanel();
		enterGameBtn = new JButton("Enter the game");
		enterGamePanel.add(enterGameBtn);
		welcomePanel.add(enterGamePanel, BorderLayout.SOUTH);
	}
	
	private void configureGamePanel() {
		gamePanel = new JPanel();
		generalPanel.add(gamePanel, "game");
		gamePanel.setLayout(new BorderLayout());
		
		configureTimePanel();
		gamePanel.add(timePanel, BorderLayout.NORTH);
		
		configureGridPanel(partitionNum);
		gamePanel.add(gridPanel);
	}
	
	private void configureGUI() {
		
		generalPanel = new JPanel();
		generalPanel.setLayout(new CardLayout());
		
		configureWelcomePanel();
		generalPanel.add(welcomePanel, "welcome");
		
		configureGamePanel();
		generalPanel.add(gamePanel, "game");

	}
	
	private int calculateHeight(int num) {
		return this.getWidth()/num;
	}
	
	private int calculateWidth(int num) {
		return this.getHeight()/num;
	}
	
	// resize image size based on given width
	private void resizeImage(int partitionNum) {
		
		int width = calculateWidth(partitionNum);
		int height = calculateHeight(partitionNum);
		
		Image falseImage = falseII.getImage();
		
		falseImage = falseImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		falseII = new ImageIcon(falseImage);
		
		Image targetImage = targetII.getImage();
		targetImage = targetImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		targetII = new ImageIcon(targetImage);
	}
	
	// constructor
	public Pigu() {

		super("FindAss!");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		configureGUI();
		this.add(generalPanel);

		// click the button to enter into the game panel
		enterGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout) generalPanel.getLayout();
				c.show(generalPanel, "game");
				configureGridPanel(partitionNum);
			}
		});

		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Pigu();
	}
}

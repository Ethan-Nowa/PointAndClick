

// PointAndClick
// A small point and click adventure game.

/* Features:
1. dialog/text area
2. backgrounds
3. objects
4. interactive elements
5. multiple scenes
6. choice system
7. life system
8. items
9. combat
		combat will be turned-based, it will tell you what the enemy plans to do then you have 3 energy to attack or defend.
10. entry and game over screens to start/load game
11. saving and loading by reading/writing file
!12. sounds/music not implemented in 1.0 version
*/

// https://game-icons.net/ contains quality free game icons.

//////////////////////////////////////////////////////////////////////////////
// This class initializes the program and UI elements.
//////////////////////////////////////////////////////////////////////////////

package PointAndClick.views;

import PointAndClick.common.ActionHandler;
import PointAndClick.common.Enemy;
import PointAndClick.common.Player;
import PointAndClick.common.SceneChanger;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;

/**
 * This class initializes the program and UI elements.
 * Date:   May 2, 2023
 * @author Ethan Nowaczyk
 * @version 1.0
 */
public class UI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4108031534336085926L;

	ActionHandler aHandler = new ActionHandler();
	
	private JPanel contentPane;
	public static JPanel bgPanel[] = new JPanel[10];
	private JLabel bgLabel[] = new JLabel[10];
	public static JTextArea mainTextArea;
	
	// PLAYER UI
	JPanel lifePanel;
	public static JLabel lifeLabel[] = new JLabel[6];
	JPanel inventoryPanel;
	public static JLabel lanternLabel, daggerLabel, armorLabel;
	
	/**
	 * Creates the frame.
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI frame = new UI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// INTRO & GAME OVER UI
	public static JLabel diedLabel;
	public static JLabel titleLabel;
	public static JButton newGameButton;
	public static JButton loadButton;
	
	// COMBAT UI
	public static JLabel enemyLabel;
	public static JLabel goblinLabel;
	public static JTextField enemyHealthArea;
	public static JButton attackButton;
	public static JButton defendButton;
	public static JLabel energyLabel;

	/**
	 * Runs the primary functions required for the game to start.
	 */
	public UI() {
		SceneChanger sChanger = new SceneChanger();
		createMain();
		initScene();
		createPlayerUI();
		createIntroGameOverScreens();
		Player.setPlayerDefaultStatus();
		Enemy.setEnemyDefaultStatus();
		sChanger.showScene0();
	}
	
	/**
	 * Creates the main UI elements of the application.
	 */
	public void createMain() {
		setTitle("PointAndClick");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 820, 620);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		setContentPane(contentPane);
		
		mainTextArea = new JTextArea();
		mainTextArea.setText("You were going about your day when an awry magical spell struck you, teleporting you to this unknown location. You don't have many supplies and will have to find your way back to civilization soon.");
		mainTextArea.setForeground(Color.white);
		mainTextArea.setBackground(new Color(0, 0, 150));
		mainTextArea.setFont(new Font("Century", Font.PLAIN, 20));
		mainTextArea.setWrapStyleWord(true);
		mainTextArea.setLineWrap(true);
		mainTextArea.setEditable(false);
		mainTextArea.setBounds(50, 410, 700, 150);
		contentPane.add(mainTextArea);
		
		attackButton = new JButton();
		attackButton.setBounds(25, 110, 130, 25);
		attackButton.setBackground(Color.red);
		attackButton.setForeground(Color.white);
		attackButton.setFocusPainted(false);
		attackButton.addActionListener(aHandler);
		attackButton.setActionCommand("attack");
		attackButton.setFont(new Font("Century", Font.PLAIN, 20));
		mainTextArea.add(attackButton);
		
		defendButton = new JButton();
		defendButton.setBounds(170, 110, 130, 25);
		defendButton.setBackground(Color.red);
		defendButton.setForeground(Color.white);
		defendButton.setFocusPainted(false);
		defendButton.addActionListener(aHandler);
		defendButton.setActionCommand("defend");
		defendButton.setFont(new Font("Century", Font.PLAIN, 20));
		mainTextArea.add(defendButton);
		
		energyLabel = new JLabel();
		energyLabel.setBounds(320, 110, 150, 25);
		energyLabel.setBorder(null);
		energyLabel.setForeground(new Color(255, 245, 0));
		energyLabel.setText("Energy: 3/3");
		energyLabel.setFont(new Font("Century", Font.PLAIN, 20));
		mainTextArea.add(energyLabel);
		
		attackButton.setVisible(false);
		defendButton.setVisible(false);
		energyLabel.setVisible(false);
	}
	
	/**
	 * Creates the backgrounds.
	 * @param bgNum The background's number.
	 * @param bgFileName The image filename for the background.
	 */
	public void createBackground(int bgNum, String bgFileName) {
		bgPanel[bgNum] = new JPanel();
		bgPanel[bgNum].setBounds(50, 50, 700, 350);
		bgPanel[bgNum].setBackground(null);
		bgPanel[bgNum].setLayout(null);
		contentPane.add(bgPanel[bgNum]);
		
		bgLabel[bgNum] = new JLabel();
		bgLabel[bgNum].setBounds(0, 0, 700, 350);
		ImageIcon bgIcon = new ImageIcon(UI.class.getResource(bgFileName));
		Image bgImage = bgIcon.getImage().getScaledInstance(700, 350, Image.SCALE_DEFAULT);
		bgIcon = new ImageIcon(bgImage);
		bgLabel[bgNum].setIcon(bgIcon);
	}
	
	/**
	 * Creates the UI elements related to the player's status.
	 */
	public void createPlayerUI() {
		lifePanel = new JPanel();
		lifePanel.setBounds(50, 0, 250, 50);
		lifePanel.setBackground(new Color(0, 0, 0));
		lifePanel.setLayout(new GridLayout(1, 5));
		contentPane.add(lifePanel);
		
		ImageIcon lifeIcon = new ImageIcon(UI.class.getResource("/PointAndClick/resources/heart15x15.png"));
		Image image = lifeIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		lifeIcon = new ImageIcon(image);
		
		int i = 1;
		while (i < 6) {
			lifeLabel[i] = new JLabel();
			lifeLabel[i].setIcon(lifeIcon);
			lifePanel.add(lifeLabel[i]);
			i++;
		}
		
		inventoryPanel = new JPanel();
		inventoryPanel.setBounds(650, 0, 100, 50);
		contentPane.add(inventoryPanel);
		inventoryPanel.setBackground(new Color(0, 0, 0));
		inventoryPanel.setLayout(new GridLayout(1, 3));
		
		// CREATE ITEMS
		lanternLabel = new JLabel();
		ImageIcon lanternIcon = new ImageIcon(UI.class.getResource("/PointAndClick/resources/old-lantern.png"));
		image = lanternIcon.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT);
		lanternIcon = new ImageIcon(image);
		lanternLabel.setIcon(lanternIcon);
		inventoryPanel.add(lanternLabel);
		
		daggerLabel = new JLabel();
		ImageIcon daggerIcon = new ImageIcon(UI.class.getResource("/PointAndClick/resources/plain-dagger.png"));
		image = daggerIcon.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT);
		daggerIcon = new ImageIcon(image);
		daggerLabel.setIcon(daggerIcon);
		inventoryPanel.add(daggerLabel);
		
		armorLabel = new JLabel();
		ImageIcon armorIcon = new ImageIcon(UI.class.getResource("/PointAndClick/resources/chest-armor.png"));
		image = armorIcon.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT);
		armorIcon = new ImageIcon(image);
		armorLabel.setIcon(armorIcon);
		inventoryPanel.add(armorLabel);
	}
	
	/**
	 * Creates the objects.
	 * @param bgNum The number of the background the object is placed in.
	 * @param objx The x coordinate of the object.
	 * @param objy The y coordinate of the object.
	 * @param objWidth The width of the object.
	 * @param objHeight The height of the object.
	 * @param objFileName The image filename for the object.
	 * @param choice1Name The first JMenuItem.
	 * @param choice2Name The second JMenuItem.
	 * @param choice3Name The third JMenuItem.
	 * @param choice1Command The first ACTION command.
	 * @param choice2Command The second ACTION command.
	 * @param choice3Command The third ACTION command.
	 */
	public void createObject(int bgNum, int objx, int objy, int objWidth, int objHeight, String objFileName, String choice1Name, String choice2Name, String choice3Name, String choice1Command, String choice2Command, String choice3Command) {
		// CREATE OBJECTS
		JLabel objectLabel = new JLabel();
		objectLabel.setBounds(objx, objy, objWidth, objHeight);
		objectLabel.setIcon(new ImageIcon(UI.class.getResource(objFileName)));
		bgPanel[bgNum].add(objectLabel);
		
		// CREATE POP MENU
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(objectLabel, popupMenu);
		// CREATE POP MENU ITEMS
		JMenuItem menuItem[] = new JMenuItem[4];
		
		menuItem[1] = new JMenuItem(choice1Name);
		menuItem[1].addActionListener(aHandler);
		menuItem[1].setActionCommand(choice1Command);
		popupMenu.add(menuItem[1]);
		
		menuItem[2] = new JMenuItem(choice2Name);
		menuItem[2].addActionListener(aHandler);
		menuItem[2].setActionCommand(choice2Command);
		popupMenu.add(menuItem[2]);
		
		menuItem[3] = new JMenuItem(choice3Name);
		menuItem[3].addActionListener(aHandler);
		menuItem[3].setActionCommand(choice3Command);
		popupMenu.add(menuItem[3]);
	}
	
	/**
	 * Creates the arrow buttons to transition scenes.
	 * @param bgNum The number of the background the arrow is placed in.
	 * @param x The x coordinate of the arrow.
	 * @param y The y coordinate of the arrow.
	 * @param width The width of the arrow.
	 * @param height The height of the arrow.
	 * @param arrowFileName The image filename for the arrow.
	 * @param command Which scene to transition to.
	 */
	public void createArrowButton(int bgNum, int x, int y, int width, int height, String arrowFileName, String command) {
		JButton arrowBtn = new JButton();
		arrowBtn.setBounds(x, y, width, height);
		arrowBtn.setFocusPainted(false);
		arrowBtn.setContentAreaFilled(false);
		arrowBtn.setBorderPainted(false);
		arrowBtn.setIcon(new ImageIcon(UI.class.getResource(arrowFileName)));
		arrowBtn.addActionListener(aHandler);
		arrowBtn.setActionCommand(command);
		bgPanel[bgNum].add(arrowBtn);
	}
	
	/**
	 * Creates the UI elements for the Intro and Game Over Screens.
	 */
	public void createIntroGameOverScreens() {
		diedLabel = new JLabel();
		diedLabel.setBounds(230, 150, 400, 200);
		diedLabel.setForeground(Color.red);
		diedLabel.setFont(new Font("Times New Roman", Font.PLAIN, 70));
		diedLabel.setVisible(false);
		diedLabel.setText("YOU DIED");
		contentPane.add(diedLabel);
		
		titleLabel = new JLabel();
		titleLabel.setBounds(200, 150, 430, 200);
		titleLabel.setForeground(new Color(255, 245, 0));
		titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 70));
		titleLabel.setVisible(false);
		titleLabel.setText("PointAndClick");
		contentPane.add(titleLabel);
		
		newGameButton = new JButton();
		newGameButton.setBounds(325, 300, 150, 50);
		newGameButton.setBorder(null);
		newGameButton.setBackground(null);
		newGameButton.setForeground(Color.white);
		newGameButton.setFocusPainted(false);
		newGameButton.addActionListener(aHandler);
		newGameButton.setActionCommand("restart");
		newGameButton.setVisible(false);
		newGameButton.setText("NEW GAME");
		contentPane.add(newGameButton);
		
		loadButton = new JButton();
		loadButton.setBounds(325, 350, 150, 50);
		loadButton.setBorder(null);
		loadButton.setBackground(null);
		loadButton.setForeground(Color.white);
		loadButton.setFocusPainted(false);
		loadButton.addActionListener(aHandler);
		loadButton.setActionCommand("load");
		loadButton.setVisible(false);
		loadButton.setText("LOAD GAME");
		contentPane.add(loadButton);
	}
	
	/**
	 * Initializes the Scenes by creating all of the backgrounds and objects.
	 */
	public void initScene() {	
		// SCENE 1
		createBackground(1, "/PointAndClick/resources/istockphoto-1139610215-170667a.jpg");
		createObject(1, 225, 30, 200, 254, "/PointAndClick/resources/bird-house-6563517_200_254.png", "Inspect", "Rest", "Save Game", "inspectHouse", "restHouse", "saveHouse");
		createObject(1, 550, 250, 150, 100, "/PointAndClick/resources/chest-1657362_150_100.png", "Inspect", "Kick", "Open", "inspectChest", "kickChest", "openChest");
		createArrowButton(1, 0, 150, 50, 50, "/PointAndClick/resources/leftArrow50x50.png", "goScene2");
		bgPanel[1].add(bgLabel[1]);
		
		// SCENE 2
		createBackground(2, "/PointAndClick/resources/istockphoto-641236218-612x612.jpg");
		createObject(2, 400, 272, 200, 115, "", "Inspect", "Kick", "Cut", "inspectGround", "kickGround", "cutGround");
		createArrowButton(2, 650, 150, 50, 50, "/PointAndClick/resources/rightArrow50x50.png", "goScene1");
		createArrowButton(2, 325, 0, 50, 50, "/PointAndClick/resources/upArrow50x50.png", "goScene3");
		bgPanel[2].add(bgLabel[2]);
		
		// SCENE 3
		createBackground(3, "/PointAndClick/resources/istockphoto-1088221208-612x612.jpg");
			// Adds the goblin.
		goblinLabel = new JLabel();
		goblinLabel.setBounds(275, 100, 300, 300);
		ImageIcon goblinIcon = new ImageIcon(UI.class.getResource("/PointAndClick/resources/character-5959774_1280.png"));
		Image image = goblinIcon.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
		goblinIcon = new ImageIcon(image);
		goblinLabel.setIcon(goblinIcon);
		bgPanel[3].add(goblinLabel);
			// Adds the enemy intent label.
		enemyLabel = new JLabel();
		enemyLabel.setForeground(Color.white);
		enemyLabel.setBackground(new Color(0, 0, 0, 0));
		enemyLabel.setFont(new Font("Century", Font.PLAIN, 20));
		enemyLabel.setBounds(230, 45, 400, 25);
		bgPanel[3].add(enemyLabel);
			// Adds the enemy health bar.
		enemyHealthArea = new JTextField();
		enemyHealthArea.setForeground(Color.white);
		enemyHealthArea.setBackground(Color.red);
		enemyHealthArea.setFont(new Font("Century", Font.PLAIN, 20));
		enemyHealthArea.setHorizontalAlignment(JTextField.CENTER);
		enemyHealthArea.setEditable(false);
		enemyHealthArea.setBounds(300, 75, 200, 25);
		bgPanel[3].add(enemyHealthArea);

		bgPanel[3].add(bgLabel[3]);
		
		// SCENE 4
		createBackground(4, "/PointAndClick/resources/75f0ae768607ae68400579aa4bf4547e.jpg");
		bgPanel[4].add(bgLabel[4]);
	}
	
	/**
	 * For the JMenu to function.
	 */
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}

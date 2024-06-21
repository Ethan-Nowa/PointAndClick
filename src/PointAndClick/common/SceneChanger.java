//////////////////////////////////////////////////////////////////////////////
// This class contains the code for changing scenes.
//////////////////////////////////////////////////////////////////////////////

package PointAndClick.common;

import PointAndClick.views.UI;

/**
 * This class contains the code for changing scenes.
 * Date:   May 2, 2023
 * @author Ethan Nowaczyk
 * @version 1.0
 */
public class SceneChanger {
	/**
	 * The intro screen.
	 */
	public void showScene0() {
		UI.bgPanel[1].setVisible(false);
		UI.bgPanel[2].setVisible(false);
		UI.bgPanel[3].setVisible(false);
		UI.bgPanel[4].setVisible(false);
		UI.bgPanel[5].setVisible(false);
		UI.mainTextArea.setVisible(false);
		UI.titleLabel.setVisible(true);
		UI.newGameButton.setVisible(true);
		UI.loadButton.setVisible(true);
		Player.playerLife = 0;
		Player.updatePlayerUI();
	}
	
	/**
	 * Scene 1, the forest when a new game starts.
	 */
	public void firstScene1() {
		Player.setPlayerDefaultStatus();
		reset();
		UI.mainTextArea.setText("You were going about your day when an awry magical spell struck you, teleporting you to this unknown location. You don't have many supplies and will have to find your way back to civilization soon.");
	}
	
	/**
	 * Scene 1, returning to the forest or loading save data.
	 */
	public void showScene1() {
		Player.updatePlayerUI();
		reset();
		UI.mainTextArea.setText("You return to where you started.");
	}
	
	/**
	 * Resets the UI for Scene 1.
	 */
	public void reset() {
		Enemy.setEnemyDefaultStatus();
		UI.diedLabel.setVisible(false);
		UI.titleLabel.setVisible(false);
		UI.newGameButton.setVisible(false);
		UI.loadButton.setVisible(false);
		UI.bgPanel[1].setVisible(true);
		UI.bgPanel[2].setVisible(false);
		UI.bgPanel[3].setVisible(false);
		UI.bgPanel[4].setVisible(false);
		UI.mainTextArea.setVisible(true);
	}
	
	/**
	 * Scene 2, the cave entrance.
	 */
	public void showScene2() {
		UI.bgPanel[1].setVisible(false);
		UI.bgPanel[2].setVisible(true);
		UI.bgPanel[3].setVisible(false);
		UI.mainTextArea.setText("You stumble across a cave entrance as you walk through the forest.");
	}
	
	/**
	 * Scene 3, inside the cave.
	 */
	public void showScene3() {
		if (Player.lantern == true) {
			UI.bgPanel[2].setVisible(false);
			UI.bgPanel[3].setVisible(true);
			UI.attackButton.setVisible(true);
			UI.defendButton.setVisible(true);
			UI.energyLabel.setVisible(true);
			Player.updatePlayerUI();
			Enemy.updateEnemyUI();
			UI.mainTextArea.setText("You enter the cave. Your lantern lights up your surroundings. A monster blocks your path, and the only way to escape now is to fight it.\n\nYou have 3 energy to allocate between attacking and defending.");
		} else {
			UI.mainTextArea.setText("You are hesitant to enter the cave without a way to see where you are going.");
		}
	}
	
	/**
	 * Scene 4, the cave exit.
	 */
	public void showScene4() {
		UI.bgPanel[3].setVisible(false);
		UI.bgPanel[4].setVisible(true);
		UI.mainTextArea.setText("After defeating the goblin, you push on through the cave. After walking down the relatively straight path for a few minutes, you see light ahead! Upon leaving the cave, you find yourself surrounded by mountains. In the distance, you spot a pleasant-looking town settled in the center of the valley. You have survived!");
		UI.attackButton.setVisible(false);
		UI.defendButton.setVisible(false);
		UI.energyLabel.setVisible(false);
		UI.newGameButton.setVisible(true);
	}
	
	/**
	 * The Game Over screen.
	 * @param currentBgNum The current background's number so that it can be hidden.
	 */
	public void showGameOverScreen(int currentBgNum) {
		UI.bgPanel[currentBgNum].setVisible(false);
		UI.attackButton.setVisible(false);
		UI.defendButton.setVisible(false);
		UI.energyLabel.setVisible(false);
		UI.diedLabel.setVisible(true);
		UI.newGameButton.setVisible(true);
		UI.loadButton.setVisible(true);
	}
}

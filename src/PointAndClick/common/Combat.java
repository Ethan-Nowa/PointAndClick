/////////////////////////////////////////////////////////////////////////////
// This class contains the code for the combat encounter.
/////////////////////////////////////////////////////////////////////////////

package PointAndClick.common;

import java.util.Random;

import PointAndClick.views.UI;

/**
 * This class contains the code for the combat encounter.
 * Date:   May 2, 2023
 * @author Ethan Nowaczyk
 * @version 1.0
 */
public class Combat {
	static Random rand = new Random();
	static SceneChanger sChanger = new SceneChanger();
	
	/**
	 * Triggers when the player pushes the Attack button during combat.
	 */
	public void attack() {
		if (Player.playerEnergy > 0) {
			Player.playerATK = Player.playerATK + Player.playerDamage;
			Player.playerEnergy--;
			Player.updatePlayerUI();
		}
		if (Player.playerEnergy == 0) {
			endTurn();
		}
	}
	
	/**
	 * Triggers when the player pushes the Defend button during combat.
	 */
	public void defend() {
		if (Player.playerEnergy > 0) {
			Player.playerDEF = Player.playerDEF + Player.playerDefense;
			Player.playerEnergy--;
			Player.updatePlayerUI();
		}
		if (Player.playerEnergy == 0) {
			endTurn();
		}
	}
	
	/**
	 * Triggers when the player runs out of energy. Also triggers Game Over if the player has run out of health.
	 */
	public void endTurn() {
		Enemy.enemyHealth = Enemy.enemyHealth - Player.playerATK;
		if (Player.playerDEF < Enemy.enemyAtk) {
			Player.playerLife = Player.playerLife + (Player.playerDEF - Enemy.enemyAtk);
		}
		if (Player.playerLife <= 0) {
			Player.playerLife = 0;
			Player.updatePlayerUI();
			if (Player.armor == false) {
				UI.mainTextArea.setText("The goblin was simply too powerful for you to handle.\n\nPerhaps there is something you missed?");
			} else {
				UI.mainTextArea.setText("The goblin was simply too powerful for you to handle.\n\nPerhaps try defending more?");
			}
			sChanger.showGameOverScreen(3);
		} else {
			Player.playerATK = 0;
			Player.playerDEF = 0;
			Player.playerEnergy = 3;
			Player.updatePlayerUI();
			Enemy.enemyAtk = rand.nextInt(3, 9);
			Enemy.updateEnemyUI();
		}
	}
}

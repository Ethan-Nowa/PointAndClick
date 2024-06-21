/////////////////////////////////////////////////////////////////////////////
// This class contains and updates enemy data.
/////////////////////////////////////////////////////////////////////////////

package PointAndClick.common;

import java.util.Random;

import PointAndClick.views.UI;

/**
 * This class contains and updates enemy data.
 * Date:   May 2, 2023
 * @author Ethan Nowaczyk
 * @version 1.0
 */
public class Enemy {
	static Random rand = new Random();
	static SceneChanger sChanger = new SceneChanger();
	
	public static int enemyAtk;
	public static int enemyHealth;
	public static int enemyMaxHealth;
	
	public static void setEnemyDefaultStatus() {
		enemyAtk = rand.nextInt(3, 9);
		enemyHealth = 20;
		enemyMaxHealth = 20;
	}
	
	public static void updateEnemyUI() {
		UI.enemyLabel.setText("Enemy intends to attack for " + Enemy.enemyAtk + " damage!");
		UI.enemyHealthArea.setText(Enemy.enemyHealth + "/" + Enemy.enemyMaxHealth);
		if (enemyHealth <= 0) {
			sChanger.showScene4();
		}
	}
}

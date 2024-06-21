//////////////////////////////////////////////////////////////////////////////
// This class contains and updates player data.
//////////////////////////////////////////////////////////////////////////////

package PointAndClick.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import PointAndClick.views.UI;

/**
 * This class contains and updates player data.
 * Date:   May 2, 2023
 * @author Ethan Nowaczyk
 * @version 1.0
 */
public class Player {
	public static int playerMaxLife;
	public static int playerLife;
	public static int playerDamage;
	public static int playerDefense;
	public static int playerEnergy;
	
	public static int playerATK;
	public static int playerDEF;
	
	public static boolean lantern;
	public static boolean dagger;
	public static boolean armor;
	public static boolean rod;
	public static boolean fish;
	
	/**
	 * Sets the Player's default status.
	 */
	public static void setPlayerDefaultStatus() {
		playerMaxLife = 5;
		playerLife = 3;
		playerDamage = 1;
		playerDefense = 2;
		playerEnergy = 3;
		
		playerATK = 0;
		playerDEF = 0;
		
		lantern = false;
		dagger = false;
		armor = false;
		rod = false;
		fish = false;
		
		updatePlayerUI();
	}
	
	/**
	 * Loads the Player's data from the save file.
	 * @throws FileNotFoundException 
	 */
	public void loadPlayerStatus() throws FileNotFoundException {
		// Try to open file
		//System.out.println("Opening file saveData.txt.");
		FileInputStream fileByteStream = new FileInputStream("saveData.txt");
		Scanner fileInput = new Scanner(fileByteStream);
		
		playerLife = fileInput.nextInt();
		playerDamage = fileInput.nextInt();
		playerDefense = fileInput.nextInt();
		lantern = fileInput.nextBoolean();
		dagger = fileInput.nextBoolean();
		armor = fileInput.nextBoolean();
		rod = fileInput.nextBoolean();
		fish = fileInput.nextBoolean();
		
		fileInput.close();
		
		playerEnergy = 3;
		playerATK = 0;
		playerDEF = 0;
	}
	
	/**
	 * Updates the Player UI.
	 */
	public static void updatePlayerUI() {
		if (UI.attackButton.isVisible()) {
			UI.attackButton.setText("Attack: " + playerDamage);
		}
		if (UI.defendButton.isVisible()) {
			UI.defendButton.setText("Defend: " + playerDefense);
		}
		if (UI.energyLabel.isVisible()) {
			UI.energyLabel.setText("Energy: " + playerEnergy + "/3");
		}
		if (UI.attackButton.isVisible()) {
			UI.mainTextArea.setText("You plan to attack for " + Player.playerATK + " damage!\nYou plan to defend against " + Player.playerDEF + " damage!");
		}
			
		int i = 1;
		while (i < 6) {
			UI.lifeLabel[i].setVisible(false);
			i++;
		}
		
		int lifeCount = playerLife;
		while (lifeCount != 0) {
			UI.lifeLabel[lifeCount].setVisible(true);
			lifeCount--;
		}
		
		if (lantern == false) {
			UI.lanternLabel.setVisible(false);
		}
		if (lantern == true) {
			UI.lanternLabel.setVisible(true);
		}
		if (dagger == false) {
			UI.daggerLabel.setVisible(false);
		}
		if (dagger == true) {
			UI.daggerLabel.setVisible(true);
		}
		if (armor == false) {
			UI.armorLabel.setVisible(false);
		}
		if (armor == true) {
			UI.armorLabel.setVisible(true);
		}
		if (rod == false) {
			UI.rodLabel.setVisible(false);
		}
		if (rod == true) {
			UI.rodLabel.setVisible(true);
		}
		if (fish == false) {
			UI.fishLabel.setVisible(false);
		}
		if (fish == true) {
			UI.fishLabel.setVisible(true);
		}
	}
}
/////////////////////////////////////////////////////////////////////////////
// This class contains the code that triggers for events in scene 1.
/////////////////////////////////////////////////////////////////////////////

package PointAndClick.events;

import java.io.FileWriter;
import java.io.IOException;

import PointAndClick.common.Player;
import PointAndClick.common.SceneChanger;
import PointAndClick.views.UI;

/**
 * This class contains the code that triggers for events in scene 1
 * Date:   May 2, 2023
 * @author Ethan Nowaczyk
 * @version 1.0
 */
public class Events01 {
	SceneChanger sChanger = new SceneChanger();
	
	// HOUSE
	public void inspectHouse() {
		if (Player.lantern == false) {
			UI.mainTextArea.setText("You inspect the house. It is an old seemingly abandoned shelter you have been hiding in since your arrival.");
		} else if (Player.armor == false) {
			UI.mainTextArea.setText("You use your newfound lantern to inspect the inside of the house better. In a previously dark corner of the house, you see an empty space below the floor. After prying the loose floorboards away with your hands and dagger, you find some chest armor.\n(Armor acquired)");
			Player.armor = true;
			Player.playerDefense++;
			Player.updatePlayerUI();
		} else {
			UI.mainTextArea.setText("You find nothing else in the house.");
		}
	}
	public void restHouse() {
		if (Player.playerLife != Player.playerMaxLife) {
			UI.mainTextArea.setText("You choose to rest in the house a bit.\n(Your health has increased by 1)");
			Player.playerLife++;
			Player.updatePlayerUI();
		} else {
			UI.mainTextArea.setText("You choose to rest in the house a bit.");
		}
	}
	public void saveHouse() {
		try {
			FileWriter write = new FileWriter("saveData.txt");
			write.write(Player.playerLife +"\n"+ Player.playerDamage +"\n"+ Player.playerDefense +"\n"+ Player.lantern +"\n"+ Player.dagger +"\n"+ Player.armor);
			write.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
		    e.printStackTrace();
		}
		UI.mainTextArea.setText("You have saved the game. It is now safe to quit.");
	}
	
	// CHEST
	public void inspectChest() {
		UI.mainTextArea.setText("You inspect the chest. It appears to be a rusty chest that has been sitting here for who knows how long. It doesn't appear to have a lock of any kind.");
	}
	public void kickChest() {
		if (Player.dagger == false && Player.playerLife != 1) {
			UI.mainTextArea.setText("You kick the chest. You heard a slight rattle as if something slid around but now your foot hurts.\n(Your health has decreased by 1)");
			Player.playerLife--;
			Player.updatePlayerUI();
		} else if (Player.playerLife != 1) {
			UI.mainTextArea.setText("You kick the chest.\n(Your health has decreased by 1)");
			Player.playerLife--;
			Player.updatePlayerUI();
		} else {
			UI.mainTextArea.setText("After kicking the chest again, the pain is so extreme that you pass out and some wandering creatures find you, ending your life.");
			Player.playerLife--;
			Player.updatePlayerUI();
			sChanger.showGameOverScreen(1);
		}
	}
	public void openChest() {
		if (Player.dagger == false) {
			UI.mainTextArea.setText("You open the chest and find a dagger. It's not in the greatest of conditions, but it's better than nothing.\n(Dagger acquired)");
			Player.dagger = true;
			Player.playerDamage++;
			Player.updatePlayerUI();
		} else {
			UI.mainTextArea.setText("You open the chest, but find nothing else.");
		}
	}
}
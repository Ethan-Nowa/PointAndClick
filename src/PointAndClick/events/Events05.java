/////////////////////////////////////////////////////////////////////////////
// This class contains the code that triggers for events in scene 2.
/////////////////////////////////////////////////////////////////////////////

package PointAndClick.events;

import PointAndClick.common.Player;
import PointAndClick.common.SceneChanger;
import PointAndClick.views.UI;

/**
 * This class contains the code that triggers for events in scene 5.
 * Date:   June 20, 2024
 * @author Ethan Nowaczyk
 * @version 1.0
 */
public class Events05 {
	SceneChanger sChanger = new SceneChanger();
	
	// POND
	public void inspectPond() {
		UI.mainTextArea.setText("You inspect the pond. It looks murky and is filled with algae. You notice a single fish swimming around.");
	}
	public void drinkPond() {
		if (Player.playerLife != 1) {
			UI.mainTextArea.setText("You drink the water and immediately feel sick.\n(Your health has decreased by 1)");
			Player.playerLife--;
			Player.updatePlayerUI();
		} else {
			UI.mainTextArea.setText("You drink the water and pass out. Some wandering creatures find you, ending your life.");
			Player.playerLife--;
			Player.updatePlayerUI();
			sChanger.showGameOverScreen(5);
		}
	}
	public void fishPond() {
		if (Player.rod == true && Player.fish == false) {
			UI.mainTextArea.setText("You find a worm and attach it to the hook of the fishing rod before casting it in, it doesn't take long for the fish to fall for your trap.\n(Fish acquired)");
			Player.fish = true;
			Player.updatePlayerUI();
		} else if (Player.fish == true) {
			UI.mainTextArea.setText("There are no more fish in the pond.");
		} else {
			UI.mainTextArea.setText("You try to catch the fish, but it's simply too quick for you.");
		}
	}
}

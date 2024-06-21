/////////////////////////////////////////////////////////////////////////////
// This class contains the code that triggers for events in scene 2.
/////////////////////////////////////////////////////////////////////////////

package PointAndClick.events;

import PointAndClick.common.Player;
import PointAndClick.views.UI;

/**
 * This class contains the code that triggers for events in scene 2.
 * Date:   May 2, 2023
 * @author Ethan Nowaczyk
 * @version 1.0
 */
public class Events02 {
	// GROUND
	public void inspectGround() {
		UI.mainTextArea.setText("You inspect the ground. You notice a patch of thick, wood-like ivy covering a hole.");
	}
	public void kickGround() {
		if (Player.lantern == false) {
			UI.mainTextArea.setText("You kick the ivy. It doesn't budge, so you try to pull, but it's just too thick. You'll need something to cut it.");
		} else {
			UI.mainTextArea.setText("You kick the ground. Nothing happens.");
		}
	}
	public void cutGround() {
		if (Player.dagger == false) {
			UI.mainTextArea.setText("You have nothing to cut the ivy with.");
		} else if (Player.dagger == true && Player.lantern == false){
			UI.mainTextArea.setText("You cut the ivy with the dagger you found. Peering into the hole, you find a lantern in workable condition. This should be good enough to light up the cave.\n(Lantern acquired)");
			Player.lantern = true;
			Player.updatePlayerUI();
		} else {
			UI.mainTextArea.setText("You attempt to cut the ground. All you do is shift some dirt around.");
		}
	}
}

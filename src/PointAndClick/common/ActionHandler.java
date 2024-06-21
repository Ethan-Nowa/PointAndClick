
package PointAndClick.common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import PointAndClick.events.*;

/**
 * This class handles the actions performed by the player and causes the appropriate event to play.
 * Date:   May 2, 2023
 * @author Ethan Nowaczyk
 * @version 1.0
 */
public class ActionHandler implements ActionListener{
	/**
	 * Handles the action and causes the appropriate event to play.
	 */
	public void actionPerformed(ActionEvent e) {
		Events01 ev1 = new Events01();
		Events02 ev2 = new Events02();
		SceneChanger sChanger = new SceneChanger();
		Player play = new Player();
		Combat combat = new Combat();
		
		String chosenAction = e.getActionCommand();
		
		switch (chosenAction) {
		// Restart
		case "restart": sChanger.firstScene1(); break;
		// Load Game
		case "load": try {play.loadPlayerStatus(); sChanger.showScene1();} catch (FileNotFoundException e1) {e1.printStackTrace();} break;
		// Change Scenes
		case "goScene1": sChanger.showScene1(); break;
		case "goScene2": sChanger.showScene2(); break;
		case "goScene3": sChanger.showScene3(); break;
		// HOUSE
		case "inspectHouse": ev1.inspectHouse(); break;
		case "restHouse": ev1.restHouse(); break;
		case "saveHouse": ev1.saveHouse(); break;
		// CHEST
		case "inspectChest": ev1.inspectChest(); break;
		case "kickChest": ev1.kickChest(); break;
		case "openChest": ev1.openChest(); break;
		// GROUND
		case "inspectGround": ev2.inspectGround(); break;
		case "kickGround": ev2.kickGround(); break;
		case "cutGround": ev2.cutGround(); break;
		// Attack
		case "attack": combat.attack(); break;
		// Defend
		case "defend": combat.defend(); break;
		}
	}
}

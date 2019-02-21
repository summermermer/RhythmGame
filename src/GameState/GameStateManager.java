package GameState;

import java.awt.Graphics2D;

public class GameStateManager {

	private GameState gameStates[];
	private int currentState;
	
	public static final int STARTMENUSTATE = 0;
	public static final int MENUSTATE = 1;
	public static final int SIMPLEPLAN_STATE = 2;
	public static final int MOMOLAND_STATE = 3;
	public static final int TWICE_STATE = 4;
	public static final int AILEE_STATE = 5;
	
	public GameStateManager() {
		
		gameStates = new GameState[5];
		
		currentState = 0;
		loadState(currentState);
		
	}
	
	public void loadState(int state) {
		if(state == STARTMENUSTATE)
			gameStates[state] = new StartMenuState(this);
		if(state == MENUSTATE) {
			if (gameStates[state] == null)
				gameStates[state] = new MenuState(this);
		}
		if(state == SIMPLEPLAN_STATE)
			gameStates[state] = new GameSimplePlan(this);
		if(state == TWICE_STATE)
			gameStates[state] = new GameTwice(this);
		if (state == MOMOLAND_STATE)
			gameStates[state] = new GameMoMo(this);
	}
	
	public void unloadState(int state) {
		gameStates[state] = null;
	}
	
	
	public void setState(int state) {
		
		if (currentState != MENUSTATE) {
			unloadState(currentState);
		}
		
		currentState = state;
		loadState(state);
	}
	
	public int getState() {
		return currentState ;
	}
	
	public void setStateInit(int state) {
		try {
			gameStates[state].init();
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public void update() {
		try {
			gameStates[currentState].update();
		} catch (Exception e) {}
	}
	
	public void draw(Graphics2D g) {
		try {
			gameStates[currentState].draw(g);
		} catch (Exception e) {}
	}
	
	public void keyPressed(int k) {
		try {
			gameStates[currentState].keyPressed(k);
		} catch (Exception e) {}
	}
	public void keyReleased(int k) {
		try {
			gameStates[currentState].keyReleased(k);
		} catch (Exception e) {}
	}
}

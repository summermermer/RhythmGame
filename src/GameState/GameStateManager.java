
package GameState;

import java.awt.Graphics2D;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GameStateManager {

	private GameState gameStates[];
	private int currentState;
	
	public static final int STARTMENUSTATE = 0;
	public static final int MENUSTATE = 1;
	public static final int BLUEMING_STATE = 2;
	public static final int WERIDE_STATE = 3;
	public static final int DYNAMITE_STATE = 4;
	//public static final int AILEE_STATE = 5;

	//ÃÑ ÄÚÀÎ ¼ö
	private int totalCoin = 0;
	public CoinBank c = new CoinBank();
	
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
		if(state == BLUEMING_STATE)
			gameStates[state] = new GameBlueming(this);
		if(state == WERIDE_STATE)
			gameStates[state] = new GameWeRide(this);
		if (state == DYNAMITE_STATE)
			gameStates[state] = new GameDynamite(this);
	}
	

	
	public void setState(int state) {
		
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
	public void buyByCoin(int p) {
		totalCoin-=p;
		c.coinLogging(totalCoin);
	}
	
	public int getToTalCoin() {
		totalCoin = c.coinLoading();
		return totalCoin;
	}
	public void addTotalCoin(int coin) {
		totalCoin += coin;
		c.coinLogging(totalCoin);
	}
}

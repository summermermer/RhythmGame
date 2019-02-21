package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;

import GameElement.InterfaceBackground;
import GameEnvironment.Music;

public class GameTwice extends GameState {

	private InterfaceBackground bg;
	
	private Music gameMusic;
	
	public GameTwice(GameStateManager gsm) {
		
		this.gsm = gsm;
		
		init();
	}
	
	@Override
	public void init() {
		bg = new InterfaceBackground("/image/likey.jpg");
		gameMusic = new Music("Likey.mp3", false);
		gameMusic.start();
	}
	
	@Override
	public void update() {
		
	}

	@Override
	public void draw(Graphics2D g) {
		bg.draw(g);
		g.setColor(Color.WHITE);
		g.setRenderingHint(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font("Elephant", Font.BOLD, 30));
		g.drawString("Twice - Likey", 20, 700);
		
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Elephant", Font.BOLD, 30));
		g.drawString("000000", 565, 700);
		g.drawString("Easy", 1180, 700);
	}

	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ESCAPE) {
			gameMusic.close();
			gsm.setState(GameStateManager.MENUSTATE); 
		}
		else if (k == KeyEvent.VK_S) {
			bg.pressS();
		} 
		else if (k == KeyEvent.VK_D) {
			bg.pressD();
		} 
		else if (k == KeyEvent.VK_F) {
			bg.pressF();
		} 
		else if (k == KeyEvent.VK_SPACE) {
			bg.pressSpace();
		} 
		else if (k == KeyEvent.VK_J) {
			bg.pressJ();
		} 
		else if (k == KeyEvent.VK_K) {
			bg.pressK();
		} 
		else if (k == KeyEvent.VK_L) {
			bg.pressL();
		}
	}

	@Override
	public void keyReleased(int k) {
		if (k == KeyEvent.VK_S) {
			bg.releaseS();
		} 
		else if (k == KeyEvent.VK_D) {
			bg.releaseD();;
		} 
		else if (k == KeyEvent.VK_F) {
			bg.releaseF();
		} 
		else if (k == KeyEvent.VK_SPACE) {
			bg.releaseSpace();
		} 
		else if (k == KeyEvent.VK_J) {
			bg.releaseJ();
		} 
		else if (k == KeyEvent.VK_K) {
			bg.releaseK();
		} 
		else if (k == KeyEvent.VK_L) {
			bg.releaseL();
		}
	}
	
}

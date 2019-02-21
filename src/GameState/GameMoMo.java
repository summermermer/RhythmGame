package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import GameElement.Beat;
import GameElement.EffectAnimation;
import GameElement.InterfaceBackground;
import GameElement.Note;
import GameElement.ResultBackground;
import GameEnvironment.Music;

public class GameMoMo extends GameState
                   implements Runnable {

	// 배경, 음악, 비트
	private InterfaceBackground bg;
	private Music gameMusic;
	private Beat[] beats = null;
	
	// 노트 배열
	private ArrayList<Note> notes = new ArrayList<Note>();
	
	// 게임 스레드
	private Thread game;
	
	// 점수, 콤보
	private int score;
	private int combo;
	
	// effect motion
	private EffectAnimation effect;
	
	// 곡 시작시간
	private int startTime;
	
	// result 화면 여부
	private boolean displayResult;
	private boolean showingResult;
	private ResultBackground rbg;
	
	// 게임 도중 exit
	private boolean exit;
	
	// beat
	private Music beatSound;
	
	public GameMoMo(GameStateManager gsm) {
		this.gsm = gsm;
		
		init();
	}
	
	@Override
	public void init() {
		
		// 점수 초기화
		score = 0; combo = 0;
		
		// 결과 화면
		displayResult = false;
		showingResult = false;
		rbg = new ResultBackground();
		
		// 배경 
		bg = new InterfaceBackground("/image/tropicana.png");
		
		// 악보 세팅
		setBeat();
		
		// 게임 음악
		gameMusic = new Music("Tropicana.mp3", false);
		gameMusic.start();
		
		// effect
		effect = new EffectAnimation();
		
		// 게임 시작
		game = new Thread(this);
		game.start();
		
		// exit
		exit = false;
		
		// beat
		beatSound = new Music("beat.mp3", false);
		
	}
	
	public void setBeat() {
		startTime = 1333; 
		// 처음 비트가 떨어지는 시간
		int gap = (int)(60.0 / 360 * 1000);/* 박자 계산용 ex) 1/8박자 = 125/1000 */
		beats = new Beat[] {
				new Beat(startTime + gap * 4, "Space"),
				new Beat(startTime + gap * 7, "L"),
				new Beat(startTime + gap * 8, "D"),
				new Beat(startTime + gap * 11, "F"),
				new Beat(startTime + gap * 12, "F"),
				new Beat(startTime + gap * 14, "F"), 
				new Beat(startTime + gap * 18, "S"), 
				new Beat(startTime + gap * 19, "D"),
				new Beat(startTime + gap * 20, "F"),             
				new Beat(startTime + gap * 21, "D"),
				new Beat(startTime + gap * 22, "S"), 
				new Beat(startTime + gap * 25, "F"),
				new Beat(startTime + gap * 26, "K"),
				new Beat(startTime + gap * 29, "L"),
				new Beat(startTime + gap * 30, "L"),
				new Beat(startTime + gap * 32, "L"), // 사과 톡톡톡 
				new Beat(startTime + gap * 36, "K"),
				new Beat(startTime + gap * 37, "J"),
				new Beat(startTime + gap * 38, "D"),
				new Beat(startTime + gap * 39, "S"),
				new Beat(startTime + gap * 40, "Space"), // 트로피카나
				new Beat(startTime + gap * 42, "D"),
				new Beat(startTime + gap * 44, "S"),
				new Beat(startTime + gap * 45, "D"),
				new Beat(startTime + gap * 47, "F"),
				new Beat(startTime + gap * 48, "F"),
				new Beat(startTime + gap * 51, "J"),
				new Beat(startTime + gap * 53, "L"), // hey지점
				new Beat(startTime + gap * 54, "S"), 
				new Beat(startTime + gap * 55, "D"),
				new Beat(startTime + gap * 56, "F"),              
				new Beat(startTime + gap * 57, "J"),
				new Beat(startTime + gap * 58, "K"),  
				new Beat(startTime + gap * 59, "L"),
				new Beat(startTime + gap * 60, "S"),  
				new Beat(startTime + gap * 61, "D"),
				new Beat(startTime + gap * 62, "F"), // 트로피카나 스파클링
				new Beat(startTime + gap * 66, "S"), 
				new Beat(startTime + gap * 67, "D"),
				new Beat(startTime + gap * 70, "D"),
				new Beat(startTime + gap * 71, "D"),
				new Beat(startTime + gap * 72, "D"),
				new Beat(startTime + gap * 77, "S"),
				new Beat(startTime + gap * 78, "J"), 
				new Beat(startTime + gap * 79, "F"),             
				new Beat(startTime + gap * 80, "D"),
				new Beat(startTime + gap * 81, "S"), 
				new Beat(startTime + gap * 84, "S"),
				new Beat(startTime + gap * 85, "D"),
				new Beat(startTime + gap * 88, "S"),
				new Beat(startTime + gap * 89, "D"),
				new Beat(startTime + gap * 91, "S"),  
				new Beat(startTime + gap * 95, "S"),
				new Beat(startTime + gap * 96, "K"),
				new Beat(startTime + gap * 97, "F"),
				new Beat(startTime + gap * 98, "D"),
				new Beat(startTime + gap * 99, "S"), 
				new Beat(startTime + gap * 102, "F"),
				new Beat(startTime + gap * 103, "D"),
				new Beat(startTime + gap * 106, "S"),
				new Beat(startTime + gap * 107, "D"),
				new Beat(startTime + gap * 109, "F"), 
				new Beat(startTime + gap * 110, "D"),
				new Beat(startTime + gap * 111, "J"), 
				new Beat(startTime + gap * 112, "S"), 
				new Beat(startTime + gap * 113, "S"),
				new Beat(startTime + gap * 114, "D"),              
				new Beat(startTime + gap * 115, "J"),
				new Beat(startTime + gap * 116, "K"),  
				new Beat(startTime + gap * 117, "L"),
				new Beat(startTime + gap * 118, "D"), 
				new Beat(startTime + gap * 119, "K"),
				new Beat(startTime + gap * 120, "L"), 
				new Beat(startTime + gap * 123, "Space"), 
				new Beat(startTime + gap * 124, "Space"),
				new Beat(startTime + gap * 125, "Space"),
				new Beat(startTime + gap * 126, "Space"), // 리얼FRUIT
				new Beat(startTime + gap * 128, "F"),
				new Beat(startTime + gap * 129, "D"),
				new Beat(startTime + gap * 130, "F"), 
				new Beat(startTime + gap * 131, "K"), 
				new Beat(startTime + gap * 132, "K"), // 리어스파클링
				new Beat(startTime + gap * 134, "D"),
				new Beat(startTime + gap * 135, "S"),              
				new Beat(startTime + gap * 136, "D"),
				new Beat(startTime + gap * 137, "F"),  
				new Beat(startTime + gap * 138, "D"), 
				new Beat(startTime + gap * 140, "K"), 
				new Beat(startTime + gap * 141, "J"),
				new Beat(startTime + gap * 142, "K"), 
				new Beat(startTime + gap * 143, "Space"),// 스파클링
		};
	}
	
	@Override
	public void update() {
		
		// note 위치
		for (int i = 0; i < notes.size(); i++) {
			Note note = notes.get(i);
			note.update();
			if(note.isProceeded() == false ) {
				notes.remove(i);
				i--;
			}
		}
		
		// effect
		effect.update();
		
		// result
		if(displayResult) {
			if(!showingResult) {
				showingResult = true;
				rbg.playBgm();
				rbg.takeMusicTitle("Momoland  -  Tropicana");
				rbg.takeScore(score);
				rbg.writeScore();
			}
			rbg.update();
		}
		
	}

	@Override
	public void draw(Graphics2D g) {
		
		// 배경, 인터페이스 그래픽
		bg.draw(g);
		
		// 곡이름 그래픽
		g.setColor(Color.WHITE);
		g.setRenderingHint(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font("Elephant", Font.BOLD, 30));
		g.drawString("Momoland - Tropicana", 20, 700);
		
		// 점수판, 난이도 그래픽
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Elephant", Font.BOLD, 30));
		g.drawString(String.valueOf(score), 600, 700);
		g.drawString("Easy", 1180, 700);
		
		// 각 노트 그래픽
		for (int i = 0; i < notes.size(); i++) {
			Note note = notes.get(i);
			note.draw(g);
		}
		
		// effect
		effect.draw(g);
		
		// result
		if(displayResult) {
			rbg.draw(g);
		}
		
	}

	@Override
	public void keyPressed(int k) {
		
		if(k == KeyEvent.VK_ESCAPE) {
			
			exit = true;
			
			// 음악 종료
			gameMusic.close();
			rbg.closeBgm();
			
			// 게임 종료
			for (int i = 0; i < notes.size(); i++) {
				Note note = notes.get(i);
				note.setProceeded(false);
			}
			game.interrupt();
			
			// 화면 이동
			gsm.setStateInit(GameStateManager.MENUSTATE);
			gsm.setState(GameStateManager.MENUSTATE); 
			
		}
		
		// 게임 시작
		if(!displayResult) {
			
			beatSound = new Music("beat.mp3", false);
			beatSound.start();
			
			if (k == KeyEvent.VK_S) {
				judge("S");
				bg.pressS();
			} 
			else if (k == KeyEvent.VK_D) {
				judge("D");
				bg.pressD();
			} 
			else if (k == KeyEvent.VK_F) {
				judge("F");
				bg.pressF();
			} 
			else if (k == KeyEvent.VK_SPACE) {
				judge("Space");
				bg.pressSpace();
			} 
			else if (k == KeyEvent.VK_J) {
				judge("J");
				bg.pressJ();
			} 
			else if (k == KeyEvent.VK_K) {
				judge("K");
				bg.pressK();
			} 
			else if (k == KeyEvent.VK_L) {
				judge("L");
				bg.pressL();
			}
		}
		
		else if(displayResult) {
			if(k == KeyEvent.VK_ENTER && rbg.getNextGame()) {
				gsm.setStateInit(GameStateManager.MENUSTATE);
				gsm.setState(GameStateManager.MENUSTATE); 
			}
			
		}
		
	}

	@Override
	public void keyReleased(int k) {
		if (k == KeyEvent.VK_S) bg.releaseS();
		else if (k == KeyEvent.VK_D) bg.releaseD();
		else if (k == KeyEvent.VK_F) bg.releaseF();
		else if (k == KeyEvent.VK_SPACE) bg.releaseSpace();
		else if (k == KeyEvent.VK_J) bg.releaseJ();
		else if (k == KeyEvent.VK_K) bg.releaseK();
		else if (k == KeyEvent.VK_L) bg.releaseL();
	}

	// 순차탐색을 돌며 해당 노트 판단 
	public void judge(String input)
	{
		for (int i = 0; i < notes.size(); i++) {
			Note note = notes.get(i);
			if(input.equals(note.getNoteType()))
			{
				if (note.judge().equals("Miss")) {
					combo = 0;
				}
				if (note.judge().equals("Perfect")){
					effect.setCombo("Perfect", combo); 
					effect.setPosition(note.getNoteType());
					effect.setEffect(); 
					combo++;
					score += 80 + combo * 5;
				}
				if (note.judge().equals("Great")){
					effect.setCombo("Great", combo); 
					effect.setPosition(note.getNoteType());
					effect.setEffect(); 
					combo++;
					score += 80 + combo * 5;
				}
				if (note.judge().equals("Good")){
					effect.setCombo("Good", combo); 
					effect.setPosition(note.getNoteType());
					effect.setEffect(); 
					combo++;
					score += 80 + combo * 5;
				}
				break;
			}
		}
	}
	
	
	@Override
	public void run() {
		
		int i =0;

		// 악보가 끝날 때까지 run
			while (i < beats.length && !game.isInterrupted()) {

				// 해당 비트가 drop할 시간이 됐을 때 노트 생성
				if (beats[i].getTime() <= gameMusic.getTime()) {
					Note note = new Note(beats[i].getNoteName());
					notes.add(note);
					i++;
				}
			}
			
		// ESC버튼 누르지 않았다면
		if (!exit) {
			// result 화면
			try {
				Thread.sleep(5000);
			} catch (Exception e) {e.printStackTrace();}
			rbg.calRank();
			displayResult = true;
		}

	}
	

}

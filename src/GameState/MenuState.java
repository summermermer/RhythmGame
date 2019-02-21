package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import GameEnvironment.Music;
import GameEnvironment.Track;


public class MenuState extends GameState{

	// image
	private BufferedImage imageMenuBackground; 
	private BufferedImage imageClickNote;
	private BufferedImage imageBasicNote;
	private BufferedImage imageGrayStar;
	private BufferedImage imageYellowStar;
	private BufferedImage imageMenu;
	
	// track
	private int currentChoice;
	private ArrayList<Track> tracks;
	
	// track value
	public static final int AILEE_MUSIC = 0;
	public static final int Tropicana_MUSIC = 1;
	public static final int TWICE_MUSIC  = 2;
	
	// selected music
	private Music selectedMusic;
	
	// 개발중
	private boolean developing = false;
	private int alpha = 0;
	private int delayAlpha = 0;
	
	
	public MenuState(GameStateManager gsm) {
		
		this.gsm = gsm;
		currentChoice = 0;
		
		tracks = new ArrayList<Track>();

		tracks.add(new Track("Momoland", "Tropicana", 1));
		tracks.add(new Track("Twice", "Likey", 2));
		tracks.add(new Track("Simple plan", "Take my hand", 4));
		tracks.add(new Track("Ailee", "Evening Sky", 2));
		
		try {
			imageMenuBackground = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/menuBackground.jpg")
					);
			imageClickNote = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/clickNote.png")
					);
			imageBasicNote = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/basicNote.png")
					);
			imageGrayStar = ImageIO.read( 
					getClass().getResourceAsStream(
							"/image/grayStar.png")
					);
			imageYellowStar = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/yellowStar.png")
					);
			imageMenu = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/menubar.png")
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		init();
	}
	
	@Override
	public void init() {
		
		playMusic(tracks.get(currentChoice). 
				getTitleName()+"_hightlight.mp3"
				);
		
	}
	
	@Override
	public void update() {
		if (developing) {
			if (alpha >= 145) {
				developing = false;
				alpha = 0;
				delayAlpha = 0;
			}
		}

		if(developing) {
			delayAlpha += 5;
			if (delayAlpha >= 150)
				alpha += 5;
		}
			
	}

	@Override
	public void draw(Graphics2D g) {
		
		// background
		g.drawImage(imageMenuBackground, 0, 0, null);
		
		// Indication to select music
		for (int i = 0; i < tracks.size(); i++) {
			double dif = 200 * Math.sin(i * 124 * Math.PI / 180);
			
			// selected music
			if(i == currentChoice) {
				g.drawImage(
						imageClickNote, 
						40+ i * 300, 
						220 - (int) dif,
						null
						);
				
				g.setColor(Color.white);
				g.setFont(new Font(
						"HYHeadLine", 
						Font.BOLD, 
						25));
				g.drawString(
						tracks.get(i).getSingerName(),
						200+ i * 300,
						380 - (int) dif
						);
				g.drawString(
						tracks.get(i).getTitleName(),
						200+ i * 300,
						420 - (int) dif
						);

				g.drawLine(
						200+ i * 300,
						393 - (int) dif,
						350+ i * 300, 
						393 - (int) dif
						);
				
				// 난이도 표시
				int j;
				for (j = 0; j < tracks.get(i).getDifficulty(); j++) {
					g.drawImage(
							imageYellowStar, 
							200+ i * 300 + j * 20,
							440 - (int) dif,
							null);
				}
				for (int k = 0; k < 5 - tracks.get(i).getDifficulty(); k++) {
				g.drawImage(
						imageGrayStar, 
						200+ i * 300 + j * 20 + k * 20,  
						440 - (int) dif,
						null);
				}
			}
			
			// not selected music
			else {
				g.drawImage(
						imageBasicNote, 
						40+ i * 300,
						220 - (int) dif,
						null
						);
				
				g.setColor(Color.gray);
				g.setFont(new Font(
						"HYHeadLine", 
						Font.BOLD, 
						25));
				g.drawString(
						tracks.get(i).getSingerName(),
						200+ i * 300,
						380 - (int) dif
						);
				g.drawString(
						tracks.get(i).getTitleName(),
						200+ i * 300,
						420 - (int) dif
						);

				g.drawLine(
						200+ i * 300 ,
						393 - (int) dif,
						350+ i * 300, 
						393 - (int) dif
						);
			
			} 
		}
		
		// 위 아래 라인
		g.drawImage(imageMenu, 0, -10,null);
		g.drawImage(imageMenu, 0, 670,null);
		
		
		// 개발 중 문구
		if(developing) {
			g.setColor(new Color(
					0, 0, 0, 
					150 - alpha
					));
			g.fillRect(0, 330, 1280, 70);
			g.setColor(new Color(
					200,
					255,
					200, 
					255 - alpha
					));
			g.setFont(new Font(
					"Elephant", 
					Font.BOLD,
					25
					));
			g.drawString(
					"Developing games.. ",
					520, 
					370
					);
		}
		
		
		
		
		
	}

	public void playMusic(String music) { 
		if(selectedMusic != null)
			selectedMusic.close();
		selectedMusic = new Music(music, true);
		selectedMusic.start();
	}
	
	public void select() {
		if (currentChoice == 0) {
			gsm.setState(GameStateManager.MOMOLAND_STATE);
		}
		if (currentChoice == 1) {
			developing = true;
		}
		if (currentChoice == 2) {
			developing = true;
		}
		if (currentChoice == 3) {
			developing = true;
		}
	}
	
	@Override
	public void keyPressed(int k) {
		
		if(k == KeyEvent.VK_Y) {
			System.exit(0);
		}
		
		if(k == KeyEvent.VK_ENTER) {
			if (selectedMusic != null)
				selectedMusic.close();
			select();
		}
		
		if(k == KeyEvent.VK_LEFT) {
			currentChoice--;
			if(currentChoice == -1)
				currentChoice = tracks.size()- 1 ;
			
			if (selectedMusic != null)
				selectedMusic.close();
			
			playMusic(tracks.get(currentChoice). 
					getTitleName()+"_hightlight.mp3"
					);
		}
		
		if(k == KeyEvent.VK_RIGHT) {
			currentChoice++;
			if(currentChoice == tracks.size())  
				currentChoice = 0;
			
			if (selectedMusic != null)
				selectedMusic.close();
			
			playMusic(tracks.get(currentChoice).
					getTitleName()+"_hightlight.mp3"
					);
		}
	}

	@Override
	public void keyReleased(int k) {}
	
}

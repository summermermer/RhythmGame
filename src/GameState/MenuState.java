

package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import GameEnvironment.Music;
import GameEnvironment.Track;

public class MenuState extends GameState{

	// image
	private BufferedImage imageMenuBackground; 
	//private BufferedImage imageClickNote;
	private BufferedImage imageBasicNote;
	private BufferedImage imagePointer; 
	private BufferedImage imagePointer2; 
	private BufferedImage imageGrayStar;
	private BufferedImage imageYellowStar;
	private BufferedImage imageMenu;
	
	// track
	private int currentChoice;
	private ArrayList<Track> tracks;
	
	// track value
	public static final int IU_MUSIC = 0;
	public static final int BraveGirls_MUSIC = 1;
	public static final int BTS_MUSIC  = 2;
	
	// selected music
	private Music selectedMusic;
	
	// 잠김
	private boolean locked = false;
	private int alpha = 100;
	private int delayAlpha = 0;
	private boolean lwOpened = false;
	private boolean yesOrno = false;
	private boolean noEntered = false;
	private boolean notEnough = false;
	private int i = 0;
	
	public boolean checkLocked(String title) {
		String sc = null;
		for(int i = 0; i < 3 ; i ++) {
			try {
				
				File file = new File(title+"_Locked.txt");
				Scanner scan = new Scanner(file);
				while(scan.hasNextLine()) {
					sc = scan.nextLine();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(sc.equals("true")) return true;
		else return false;
	}
	
	public MenuState(GameStateManager gsm) {
		
		this.gsm = gsm;
		currentChoice = 0;
		
		tracks = new ArrayList<Track>();

		tracks.add(new Track("IU", "Blueming", 1, checkLocked("Blueming"), 0));
		tracks.add(new Track("BraveGirls", "We Ride", 2, checkLocked("We Ride"), 2000));
		tracks.add(new Track("BTS", "Dynamite", 4, checkLocked("Dynamite"), 5000));
		
		try {
			imageMenuBackground = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/menuBackground.png")
					);
			/*ImageIcon ii = new ImageIcon(
					getClass().getResourceAsStream(
					"/image/note0.gif")
					);*/

			imagePointer = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/pointer.png")
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
							"/image/bar.png")
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
		locked = tracks.get(currentChoice).getLocked();
		if(locked) {
			
		}
		else {
			lwOpened = false;
		}
		/*if (locked) {
			if (alpha >= 145) {
				locked = false;
				alpha = 0;
				delayAlpha = 0;
			}
		}

		if(locked) {
			delayAlpha += 5;
			if (delayAlpha >= 150)
				alpha += 5;
		}*/
			
	}

	@Override
	public void draw(Graphics2D g) {
		int x=80, y= 420, ix= 400, iy= 100;

		// background
		g.drawImage(imageMenuBackground, 0, 0, null);
		g.setColor(Color.black);
		g.setFont(new Font(
				"Elephant",
				Font.BOLD, 
				50
				));
		g.drawString( String.valueOf(gsm.getToTalCoin()), 245, 106);
		
		// Indication to select music
		for (int i = 0; i < tracks.size(); i++) {
			//double dif = 150 * Math.sin(i * 124 * Math.PI / 180);
			
			try {
				imageBasicNote = ImageIO.read(
						getClass().getResourceAsStream(
								"/image/basicNote"+i+".png")
						);
				imagePointer2 = ImageIO.read(
						getClass().getResourceAsStream(
								"/image/menu"+i+".png")
						);
			}catch(Exception e) {
				e.printStackTrace();
			}
			// selected music
			if(i == currentChoice) {

				g.drawImage(
						imagePointer2, 
						x-40 + i * ix + 50, 
						y -70- (i%2) * iy - 50,
						null
						);
				g.drawImage(
						imageBasicNote, 
						x+50+ i * ix, 
						y-160 - (i%2) * iy,
						null
						);
				g.drawImage(
						imagePointer, 
						x+50 + i * ix + 50, 
						y-160 - (i%2) * iy - 50,
						null
						);
				
				g.setColor(Color.white);
				g.setFont(new Font(
						"HYHeadLine", 
						Font.BOLD, 
						25));
				g.drawString(
						tracks.get(i).getSingerName(),
						x+ i * ix,
						y - (i%2) * iy
						);
				g.drawString(
						tracks.get(i).getTitleName(),
						x+ i * ix,
						y+40 - (i%2) * iy
						);

				g.drawLine(
						x+ i * ix,
						y+ 13 - (i%2) * iy,
						300+ i * ix, 
						y+ 13 - (i%2) * iy
						);
				
				// 난이도 표시
				int j;
				for (j = 0; j < tracks.get(i).getDifficulty(); j++) {
					g.drawImage(
							imageYellowStar, 
							x+ i * ix + j * 20,
							y+ 60 - (i%2) * iy,
							null);
				}
				for (int k = 0; k < 5 - tracks.get(i).getDifficulty(); k++) {
				g.drawImage(
						imageGrayStar, 
						x+ i * ix + j * 20 + k * 20,  
						y+ 60 - (i%2) * iy,
						null);
				}
			}
			
			// not selected music
			else {
				g.drawImage(
						imageBasicNote, 
						x+ 50+ i * ix,
						y- 160 - (i%2) * iy,
						null
						);
				
				g.setColor(Color.gray);
				g.setFont(new Font(
						"HYHeadLine", 
						Font.BOLD, 
						25));
				g.drawString(
						tracks.get(i).getSingerName(),
						x+ i * ix,
						y - (i%2) * iy
						);
				g.drawString(
						tracks.get(i).getTitleName(),
						x+ i * ix,
						y+ 40 - (i%2) * iy
						);

				g.drawLine(
						x+ i * ix ,
						y+ 13 - (i%2) * iy,
						300+ i * ix, 
						y+ 13 - (i%2) * iy
						);
			
			} 
		}
		
		// 위 아래 라인
		//g.drawImage(imageMenu, 0, -10,null);
		g.drawImage(imageMenu, 0, 650, null);
		
		
		// lock 문구
		if(locked && (!noEntered)) {
			lwOpened = true;
			g.setColor(new Color(
					0, 0, 0, 
					150
					));
			g.fillRect(0, 300, 1280, 200);
			g.setColor(new Color(
					200,
					255,
					200, 
					255
					));
			g.setFont(new Font(
					"Elephant", 
					Font.BOLD,
					25
					));
			g.drawString(
					"Locked game",
					520, 
					370
					);
			g.drawString(
					"Buy? ( "+tracks.get(currentChoice).getPrice()+"coins )",
					520, 
					410
					);
			if(yesOrno) {
				g.drawString(
						"YES",
						550, 
						450
						);
				g.setColor(new Color(
						200-alpha,
						255-alpha,
						200-alpha, 
						255 - alpha
						));
				g.drawString(
						"NO",
						650, 
						450
						);
			}
			else {
				g.setColor(new Color(
						200-alpha,
						255-alpha,
						200-alpha, 
						255 - alpha
						));
				g.drawString(
						"YES",
						550, 
						450
						);
				g.setColor(new Color(
						200,
						255,
						200, 
						255
						));
				g.drawString(
						"NO",
						650, 
						450
						);
			}
			
		}
		
		if(notEnough) {
			g.setColor(new Color(
					200,
					200,
					200, 
					255
					));
			g.drawString(
					"Don't have enough coin.",
					480, 
					480
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
			gsm.setState(GameStateManager.BLUEMING_STATE);
		}
		if (currentChoice == 1) {
			gsm.setState(GameStateManager.WERIDE_STATE);
		}
		if (currentChoice == 2) {
			gsm.setState(GameStateManager.DYNAMITE_STATE);
		}
		
	}
	
	@Override
	public void keyPressed(int k) {
		
		if(k == KeyEvent.VK_Y) {
			System.exit(0);
		}
		
		if(k == KeyEvent.VK_ENTER) {
			if(lwOpened) {
				if(yesOrno) {
					if(gsm.c.coinLoading() >= tracks.get(currentChoice).getPrice()) { 
						tracks.get(currentChoice).setLocked(false); 
						gsm.buyByCoin(tracks.get(currentChoice).getPrice());
					}
					else {
						if(i == 1) {
							System.out.println("hihi");
							i = 0;
							notEnough = false;
						}
						else { notEnough = true; i++;}
						Graphics2D g3 = null;
						lwOpened = false;
						gsm.draw(g3);
					}
				}
				else {
					Graphics2D g2 = null;
					lwOpened = false;
					notEnough = false;
					noEntered =  true;
					gsm.draw(g2);
				}
			}
			else {
				if (selectedMusic != null)
					selectedMusic.close();
				select();
			}
		}
		
		if(k == KeyEvent.VK_LEFT) {
			noEntered = false;
			if(lwOpened) {
				yesOrno = !yesOrno;
			}
			else {
				currentChoice--;
				if(currentChoice == -1)
					currentChoice = tracks.size()- 1 ;
				
				if (selectedMusic != null)
					selectedMusic.close();
				
				playMusic(tracks.get(currentChoice). 
						getTitleName()+"_hightlight.mp3"
						);
			}
		}
		
		if(k == KeyEvent.VK_RIGHT) {
			noEntered = false;
			if(lwOpened) {
				yesOrno = !yesOrno;
			}
			else {
				currentChoice++;
				if(currentChoice == tracks.size())  
					currentChoice = 0;
				
				if (selectedMusic != null) {
					selectedMusic.close();
					
					playMusic(tracks.get(currentChoice).
							getTitleName()+"_hightlight.mp3"
							);
				}
			}
			
		}
		
		if(k == KeyEvent.VK_ESCAPE) 
			System.exit(0);
		
	}

	@Override
	public void keyReleased(int k) {}
	
}

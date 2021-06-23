package GameElement;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


public class InterfaceBackground {
	
	// image
	private BufferedImage imagBackground; 
	private Image gameInfoImage;
	private Image judgmentLineImage;
	private Image noteRouteLineImage;
	private Image noteRoute1Image;
	private Image noteRoute2Image;
	private Image noteRoute3Image;
	private Image noteRoute4Image;
	private Image noteRoute5Image;
	
	private Image ch;
	
	//ch의 위치
	private int position = 3;
	//noteLine 의 y 좌표
	private int y = 0;
	private String chr;
	public InterfaceBackground(String background, int state) {
		switch(state) {
			case 2: 
				chr = "chick";
				break;
			case 3:
				chr = "frog";
				break;
			case 4:
				chr = "kitty";
				break;
		}
		try {
			imagBackground = ImageIO.read(
					getClass().getResourceAsStream(
							background
							));
			gameInfoImage = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/bar.png"
							));
			judgmentLineImage = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/"+chr+"_bar.png"
							));
			noteRouteLineImage = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/noteRouteLine.png"
							));
			noteRoute1Image = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/noteRoute.png"
							));
			noteRoute2Image = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/noteRoute.png"
							));
			noteRoute3Image = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/noteRoute.png"
							));
			
	         noteRoute4Image = ImageIO.read(
	                 getClass().getResourceAsStream(
	                       "/image/noteRoute.png"
	                       ));
	         noteRoute5Image = ImageIO.read(
	                 getClass().getResourceAsStream(
	                       "/image/noteRoute.png"
	                       ));

   	       ch = ImageIO.read(
 					getClass().getResourceAsStream(
 							"/image/"+chr+".png"
 							));
	       
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void draw(Graphics2D g) {
		
		// 백그라운드
		g.drawImage(imagBackground, 0, 0, null);
		
		// 노트 루트
		g.drawImage(noteRoute1Image, 228, y, null);
		g.drawImage(noteRoute2Image, 332, y, null);
		g.drawImage(noteRoute3Image, 436, y, null);
		g.drawImage(noteRoute4Image, 540, y, null);
		g.drawImage(noteRoute5Image, 644, y, null); 
		g.drawImage(noteRouteLineImage, 224, y, null);
		g.drawImage(noteRouteLineImage, 328, y, null);
		g.drawImage(noteRouteLineImage, 432, y, null);
		g.drawImage(noteRouteLineImage, 536, y, null);
		g.drawImage(noteRouteLineImage, 640, y, null);
		g.drawImage(noteRouteLineImage, 744, y, null);

		
		// 라인
		g.drawImage(judgmentLineImage, 0, 600, null);
		g.drawImage(gameInfoImage, 0, 670, null);
		
		//캐릭터
		g.drawImage(ch, 126 + 104 * position, 530, null);
		
		// 루트 단축키
		g.setColor(Color.DARK_GRAY);
		g.setRenderingHint(
				RenderingHints.KEY_TEXT_ANTIALIASING, 
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON
				);
		g.setFont(new Font("Arial", Font.PLAIN, 30));
		/*g.drawString("1", 270, 609);
		g.drawString("2", 374, 609);
		g.drawString("3", 478, 609);
		g.drawString("4", 580, 609);
		g.drawString("5", 682, 609);*/

		
		
	}
	
	public void press1() {
		try {
			noteRoute1Image = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/noteRoutePressed.png"
							));
			position = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void release1() {
		try {
			noteRoute1Image = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/noteRoute.png"
							));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void press2() {
		try {
			noteRoute2Image = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/noteRoutePressed.png"
							));
			position = 2;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void release2() {
		try {
			noteRoute2Image = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/noteRoute.png"
							));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void press3() {
		try {
			noteRoute3Image = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/noteRoutePressed.png"
							));
			position = 3;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void release3() {
		try {
			noteRoute3Image = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/noteRoute.png"
							));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void press4() {
	      try {
	         noteRoute4Image = ImageIO.read(
	               getClass().getResourceAsStream(
	                     "/image/noteRoutePressed.png"
	                     ));
	         position = 4;
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }

	public void release4() {
	      try {
	         noteRoute4Image = ImageIO.read(
	               getClass().getResourceAsStream(
	                     "/image/noteRoute.png"
	                     ));
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }

	public void press5() {
		try {
			noteRoute5Image = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/noteRoutePressed.png"
							));
			position = 5;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void release5() {
		try {
			noteRoute5Image = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/noteRoute.png"
							));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	
	
	
	
}

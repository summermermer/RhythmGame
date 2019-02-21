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
	private Image noteRouteSImage;
	private Image noteRouteDImage;
	private Image noteRouteFImage;
	private Image noteRouteSpaceImage;
	private Image noteRouteJImage;
	private Image noteRouteKImage;
	private Image noteRouteLImage;
	
	public InterfaceBackground(String background) {
		
		try {
			imagBackground = ImageIO.read(
					getClass().getResourceAsStream(
							background
							));
			gameInfoImage = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/gameInfo.png"
							));
			judgmentLineImage = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/judgementLine.png"
							));
			noteRouteLineImage = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/noteRouteLine.png"
							));
			noteRouteSImage = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/noteRoute.png"
							));
			noteRouteDImage = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/noteRoute.png"
							));
			noteRouteFImage = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/noteRoute.png"
							));
			noteRouteSpaceImage = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/noteRoute.png"
							));
			noteRouteJImage = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/noteRoute.png"
							));
			noteRouteKImage = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/noteRoute.png"
							));
			noteRouteLImage = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/noteRoute.png"
							));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void draw(Graphics2D g) {
		
		// 백그라운드
		g.drawImage(imagBackground, 0, 0, null);
		
		// 노트 루트
		g.drawImage(noteRouteSImage, 228, 30, null);
		g.drawImage(noteRouteDImage, 332, 30, null);
		g.drawImage(noteRouteFImage, 436, 30, null);
		g.drawImage(noteRouteSpaceImage, 540, 30, null);
		g.drawImage(noteRouteSpaceImage, 640, 30, null); 
		g.drawImage(noteRouteJImage, 744, 30, null);
		g.drawImage(noteRouteKImage, 848, 30, null);
		g.drawImage(noteRouteLImage, 952, 30, null);
		g.drawImage(noteRouteLineImage, 224, 0, null);
		g.drawImage(noteRouteLineImage, 328, 0, null);
		g.drawImage(noteRouteLineImage, 432, 0, null);
		g.drawImage(noteRouteLineImage, 536, 0, null);
		g.drawImage(noteRouteLineImage, 740, 0, null);
		g.drawImage(noteRouteLineImage, 844, 0, null);
		g.drawImage(noteRouteLineImage, 948, 0, null);
		g.drawImage(noteRouteLineImage, 1052, 0, null);
		
		// 라인
		g.drawImage(judgmentLineImage, 0, 580, null);
		g.drawImage(gameInfoImage, 0, 660, null);
		
		// 루트 단축키
		g.setColor(Color.DARK_GRAY);
		g.setRenderingHint(
				RenderingHints.KEY_TEXT_ANTIALIASING, 
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON
				);
		g.setFont(new Font("Arial", Font.PLAIN, 30));
		g.drawString("S", 270, 609);
		g.drawString("D", 374, 609);
		g.drawString("F", 478, 609);
		g.drawString("Space Bar", 580, 609);
		g.drawString("J", 784, 609);
		g.drawString("K", 889, 609);
		g.drawString("L", 993, 609);
		
		
	}
	
	public void pressS() {
		try {
			noteRouteSImage = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/noteRoutePressed.png"
							));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void releaseS() {
		try {
			noteRouteSImage = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/noteRoute.png"
							));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void pressD() {
		try {
			noteRouteDImage = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/noteRoutePressed.png"
							));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void releaseD() {
		try {
			noteRouteDImage = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/noteRoute.png"
							));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pressF() {
		try {
			noteRouteFImage = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/noteRoutePressed.png"
							));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void releaseF() {
		try {
			noteRouteFImage = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/noteRoute.png"
							));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pressSpace() {
		try {
			noteRouteSpaceImage = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/noteRoutePressed.png"
							));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void releaseSpace() {
		try {
			noteRouteSpaceImage = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/noteRoute.png"
							));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pressJ() {
		try {
			noteRouteJImage = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/noteRoutePressed.png"
							));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void releaseJ() {
		try {
			noteRouteJImage = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/noteRoute.png"
							));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pressK() {
		try {
			noteRouteKImage = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/noteRoutePressed.png"
							));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void releaseK() {
		try {
			noteRouteKImage = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/noteRoute.png"
							));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pressL() {
		try {
			noteRouteLImage = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/noteRoutePressed.png"
							));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void releaseL() {
		try {
			noteRouteLImage = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/noteRoute.png"
							));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

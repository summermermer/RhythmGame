package GameElement;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


public class EffectAnimation {
	
	// effect ����
	private boolean hitEffect;
	private boolean comboEffect;
	private boolean comboColor;
	
	// effect �ð��� ��Ʈ��
	private int hitAlpha;
	private int comboAlpha;
	private int comboColorAlpha;

    // combo
 	private int comboCount; 
	private String comboMotion;
	
	// hit effect index
	private int index;
	private BufferedImage peongImage;
	
	public EffectAnimation() {
		
		// effect �ʱ�ȭ
		hitEffect = false;	
		comboEffect = false;
		comboColor = false;
	
		// effect ��Ʈ�� �ʱ�ȭ
		hitAlpha = 0;
		comboAlpha = 0;
		comboColorAlpha = 0;
		
		// hit ��ġ ��Ʈ�� �ʱ�ȭ
		index = 0;
		
		
		try {
			peongImage = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/peong.png"
							));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setPosition(int noteType) {
		if(noteType == 1) index = 0;
		else if(noteType == 2) index = 1;
		else if(noteType == 3) index = 2;
		else if(noteType == 4) index = 3;
		else if(noteType == 5) index = 4;
		//else if(noteType.equals("K")) index = 6;
		//else if(noteType.equals("L")) index = 7;
	}
	
	public void update() {
		
		// effect�� �����ٸ� false��Ŵ
		if(hitEffect) {
			if (hitAlpha >= 250) hitEffect = false;
		}
		if(comboEffect) {
			if (comboAlpha >= 100) comboEffect = false;
		}
		if(comboColor) {
			if (comboColorAlpha >= 250) comboColor = false;
		}
		
		
		
		// effect�ߵ��� �ʱ�ȭ
		if (hitEffect) {
			hitAlpha += 10;
		}
		if (comboEffect) {
			comboAlpha += 3;
		}
		if (comboColor) {
			comboColorAlpha += 15;
		}
		if (comboAlpha >= 60) {
			comboColor = true;
		}
		
	}
	
	public void draw(Graphics2D g) {
		
		// hit ȿ��
		if (hitEffect) {
			g.setColor(new Color(
					hitAlpha, 
					hitAlpha, 
					hitAlpha));
				g.drawImage(
						peongImage, 
						228 + index * 104, 
						500,
						null
						);
		}
		
		g.setRenderingHint(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		// �޺� ��� ȿ��
		 if (comboEffect) {
	         g.setColor(new Color(
	               200, 
	               255,
	               200,
	               255  - comboColorAlpha
	               ));
	         g.setFont(new Font("Elephant", Font.BOLD, 30));
	         g.drawString(
	               comboMotion, 
	               520, 
	               400 + (int)comboAlpha
	               );   
	         /*g.drawString(
	               "X "+String.valueOf(comboCount), 
	               700,
	               400 + (int)comboAlpha
	               ); */
	      }

	}
	
	public void setCombo(String comboMotion, int combo) {
		this.comboMotion = comboMotion;
		comboCount = combo;
	}
	

	public void setEffect() {
		
		hitAlpha = 0;
		comboAlpha = 0;
		comboColorAlpha = 0;
		
		hitEffect = true; 
		comboEffect = true;
	}
	
}





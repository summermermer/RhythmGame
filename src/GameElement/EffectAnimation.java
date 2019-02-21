package GameElement;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;


public class EffectAnimation {

	// effect 여부
	private boolean hitEffect;
	private boolean comboEffect;
	private boolean comboColor;
	
	// effect 시간별 컨트롤
	private int hitAlpha; 
	private int comboAlpha;
	private int comboColorAlpha;

    // combo
 	private int comboCount; 
	private String comboMotion;
	
	// hit effect index
	private int index;
	
	
	public EffectAnimation() {
		
		// effect 초기화
		hitEffect = false;	
		comboEffect = false;
		comboColor = false;
	
		// effect 컨트롤 초기화
		hitAlpha = 0;
		comboAlpha = 0;
		comboColorAlpha = 0;
		
		// hit 위치 컨트롤 초기화
		index = 0;
	}
	
	public void setPosition(String noteType) {
		if(noteType.equals("S")) index = 0;
		else if(noteType.equals("D")) index = 1;
		else if(noteType.equals("f")) index = 2;
		else if(noteType.equals("Space")) index = 3;
		else if(noteType.equals("J")) index = 5;
		else if(noteType.equals("K")) index = 6;
		else if(noteType.equals("L")) index = 7;
	}
	
	public void update() {
		
		// effect가 끝났다면 false시킴
		if(hitEffect) {
			if (hitAlpha >= 250) hitEffect = false;
		}
		if(comboEffect) {
			if (comboAlpha >= 100) comboEffect = false;
		}
		if(comboColor) {
			if (comboColorAlpha >= 250) comboColor = false;
		}
		
		
		
		// effect발동시 초기화
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
		
		// hit 효과
		if (hitEffect) {
			g.setColor(new Color(
					hitAlpha, 
					hitAlpha, 
					hitAlpha));
			if(index == 3) {
				g.fillRect(
						228 + index * 104, 
						580,
						104,
						40
						);
				g.fillRect(
						228 + (index + 1) * 104, 
						580,
						100,
						40
						);
			}
			if(index != 3) {
				g.fillRect(
						228 + index * 104, 
						580,
						100,
						40
						);
			}
			
		}
		
		g.setRenderingHint(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		// 콤보 모션 효과
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
			g.drawString(
					String.valueOf(comboCount), 
					700,
					400 + (int)comboAlpha
					); 
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



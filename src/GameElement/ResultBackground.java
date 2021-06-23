package GameElement;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import GameEnvironment.Music;

public class ResultBackground {

	// ����, ����, ��ũ, ���� ����
	private String musicTitle;
	private int score;
	private int grade;
	private String rank;
	private int coin;
	
	// �ܰ躰 ��Ʈ�� ����
	private boolean step1On;
	private boolean step2On;
	private boolean step3On;
	private boolean step4On;
	private boolean nextgame;
	private int angle;

	// BGM
	private Music bgm;
	
	
	boolean isPlayed = false;
	
	public ResultBackground() {
		
		// �ܰ躰 ��Ʈ�� 
		step1On = false;
		step2On = false;
		step3On = false;
		step4On = false;
		nextgame = false;
		angle = 155;
		
		// BGM
		bgm = new Music("resultBgm.mp3", false);
		
	}
	
	// �������
	public void calRank() {
		if(grade == 3) rank = "C"; 
		else if(grade == 2) rank = "B"; 
		else if(grade == 1) rank = "A"; 
		else if(grade == 0) rank = "S"; 
	}
	
	public void writeScore() {
			
		// write�� �ð�
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
		String dateStr = sdf.format(cal.getTime());
		
		// score ����
		String scoreStr = String.valueOf(score);
		
		// �� �̸� ����
		String titleStr = musicTitle;
		
		// FileWriter
		FileWriter fw;
		BufferedWriter writer;
		
		try {
			
			fw = new FileWriter(
					new File(
							"ScoreLog.txt"),
					         true
					         );
			writer = new BufferedWriter(fw);
				
			
			writer.write(dateStr + "\t\t");
			writer.write(titleStr + "\t\t");
			writer.write(scoreStr);
			writer.newLine();
			writer.flush();
			fw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void update() {
		
		// 'next game Enter' info control
		angle += 3;
		
		// ���� �������� �̵� ����
		if(bgm.getTime() >= 6500) {
			nextgame = true;
		}
		
	} 
	
	public void draw(Graphics2D g) {
		
		// result ���
		g.setColor(new Color(0, 0, 0, 150));
		g.fillRect(
				400, 
				240,
				480, 
				300
				);
		
		g.setRenderingHint(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		// �ܰ躰�� ȭ�����
		// 1�ܰ� - ���̸�
		if(bgm.getTime() >= 3000 || step1On == true) {
			step1On = true;
			g.setColor(new Color(200, 255, 200));
			g.setFont(new Font(
					"Elephant", 
					Font.BOLD, 
					25
					));
			g.drawString(musicTitle, 440, 300);
		}
		
		// 2�ܰ� - ������ ���� ����
		if(bgm.getTime() >= 4000 || step2On == true) {
			step2On = true;
			g.setColor(new Color(200, 255, 200));
			g.setFont(new Font(
					"Elephant", 
					Font.BOLD,
					25
					));
			g.drawString(
					"Score : "+String.valueOf(score), 
					440, 
					370
					);
			g.drawString(
					"Obtained coin : "+String.valueOf(coin), 
					440, 
					440
					);
		}
		
		// 3�ܰ� - ��ũ
		if(bgm.getTime() >= 5500 || step3On == true) {
			step3On = true;
			g.setColor(Color.green);
			g.setFont(new Font(
					"Elephant",
					Font.BOLD, 
					100
					));
			g.drawString(rank, 760, 390);
		}
		
		// 4�ܰ� - ���� ����
		if(bgm.getTime() >= 6500 || step4On == true) {
			step4On = true;
			double alpha = 255 * Math.sin(angle * Math.PI / 180);	
			if (angle >= 175) angle = 0;
			g.setColor(new Color(255, 255, 100, (int)alpha));
			g.setFont(new Font(
					"Elephant", 
					Font.BOLD,
					27
					));
			g.drawString(
					"next game ENTER", 
					500, 
					500
					);
		}
	
	}
	
	public void playBgm() {
		isPlayed = true;
		bgm.start();
	}
	
	public void closeBgm() {
		if(!isPlayed) return;
		bgm.close();
	}
	
	public boolean getNextGame() { return nextgame; }
	public void takeMusicTitle(String title){ musicTitle = title; }
	public void takeScore(int score,int grade) { this.score = score; this.grade=grade; }
	public void takeCoin(int coin) { this.coin = coin; }
	
}

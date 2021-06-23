package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import GameElement.Beat;
import GameElement.EffectAnimation;
import GameElement.InterfaceBackground;
import GameElement.Note;
import GameElement.ResultBackground;
import GameEnvironment.Music;

public class GameWeRide extends GameState
                   implements Runnable {

   // ���, ����, ��Ʈ
   private InterfaceBackground bg;
   private Music gameMusic;
   private Beat[] beats = null;
   private ArrayList<Beat> beats_final = new ArrayList<Beat>();
   
   //ĳ���� �ʵ�
   
   // ��Ʈ �迭
   private ArrayList<Note> notes = new ArrayList<Note>();
   
   // ���� ������
   private Thread game;
   
   // ����, �޺�, ����
   private int score;
   private int grade;
   private int combo;
   private int coin;
   
   //ĳ������ ��ġ ����
   private int position;
   
   
   // effect motion
   private EffectAnimation effect;
   
   // �� ���۽ð�
   private int startTime;
   
   // result ȭ�� ����
   private boolean displayResult;
   private boolean showingResult;
   private ResultBackground rbg;
   
   // ���� ���� exit
   private boolean exit;
   
   private boolean failure;
   
   // beat
   private Music beatSound;
   private Music coinSound;
   
   public GameWeRide(GameStateManager gsm) {
      this.gsm = gsm;
      
      init();
   }
   
   @Override
   public void init() {
      
      // ���� �ʱ�ȭ
      score = 0; combo = 0;
      
      // ��� ȭ��
      displayResult = false;
      showingResult = false;
      rbg = new ResultBackground();
      
      // ��� 
      bg = new InterfaceBackground("/image/frog_background.png",gsm.getState());
      
      
      // �Ǻ� ����
      setBeat();
      
      // ���� ����
      gameMusic = new Music("We Ride.mp3", false);
      gameMusic.start();
      
      // effect
      effect = new EffectAnimation();
      
      // ���� ����
      game = new Thread(this);
      game.start();
      
      // exit
      exit = false;
      
      failure = false;
      
      // beat
      beatSound = new Music("beat.mp3", false);
      
      //ĳ������ ��ġ
      position = 3;
      
      //���� ���� �� �ʱ�ȭ
      coin = 0;
      
   }
   
   public void setBeat() {      //�������� beat �ϳ��ϳ� ����
      startTime = 111; 
      // ó�� ��Ʈ�� �������� �ð�
      int gap = 500; // 0.5��
      beats = new Beat[] {
            new Beat(startTime + gap * 1, 2),
            new Beat(startTime + gap * 2, 3),
            new Beat(startTime + gap * 5, 5),
            new Beat(startTime + gap * 6, 3),
            new Beat(startTime + gap * 9, 1),
            new Beat(startTime + gap * 10, 3), 
            new Beat(startTime + gap * 14, 4), 
            new Beat(startTime + gap * 15, true, 5),
            new Beat(startTime + gap * 16, true, 2), 
            new Beat(startTime + gap * 17, true, 3),            
            new Beat(startTime + gap * 19, 2), // �̰� �Ҷ� coin�� ���� position �տ� true�� ���ָ� �˴ϴ�.
            new Beat(startTime + gap * 20, 4), 
            new Beat(startTime + gap * 23, true, 3),
            new Beat(startTime + gap * 24,true, 1),
            new Beat(startTime + gap * 27, 3),
            new Beat(startTime + gap * 28, 1),
            new Beat(startTime + gap * 33, true, 5), // ��� ������ 
            new Beat(startTime + gap * 34, 5),
            new Beat(startTime + gap * 35, 3)
         };
            
            //Stage2 ����
            
            for(int i = 0; i < beats.length; i++) {      //beats �迭���� arraylist �� ���� -> ��� beat ���� ����� ����
               Beat beat = new Beat(beats[i].getTime(), beats[i].getCoin(), beats[i].getNoteName());
               beats_final.add(beat);
            }
            
            for(int i = 0; i < beats.length; i++) {      //stage 2 (1.2���)
               
               //startTime + (stage 1 ����) + ���� gap * 0.8
               Beat beat = new Beat(startTime + 20000 + ((beats_final.get(i).getTime() - startTime) * 0.8), 
                              beats_final.get(i).getCoin(), beats_final.get(i).getNoteName());
               
               beats_final.add(beat);
            }
            for(int i = 0; i < beats.length; i++) {      //stage 3 (1.4���)
               
               //startTime + (stage 1, 2 ����) + ���� gap * 0.6
               Beat beat = new Beat(startTime + 36000 + ((beats_final.get(i).getTime() - startTime) * 0.6), 
                              beats_final.get(i).getCoin(), beats_final.get(i).getNoteName());
               
               beats_final.add(beat);
            }
   }
   
   @Override
   public void update() {
      
      // note ��ġ
      for (int i = 0; i < notes.size(); i++) {
         Note note = notes.get(i);
         note.update();
         if(note.isProceeded() == false ) {
            notes.remove(i);
            i--;
         }
      }
      
      
      //���� ������Ʈ
      if(failure == false) {
    	  judge(position);
      }
      
      
      // effect
      effect.update();
      

      
      // result
      if(displayResult || failure == true) {
         if(!showingResult) {
            showingResult = true;
            gameMusic.close();
            rbg.playBgm();
            rbg.takeMusicTitle("Brave Girls  -  We Ride");
            if(score < 1400) grade=3;
            else if(score < 3700) grade=2;
            else if(score < 6700) grade=1;
            else grade=0;
            rbg.takeScore(score,grade);
            rbg.takeCoin(coin);
            rbg.writeScore();
            gsm.addTotalCoin(coin); // ���ӵ��� ��Ҵ� coin���� �߰��Ѵ�.
         }
         rbg.update();
      }
      
   }

   @Override
   public void draw(Graphics2D g) {
      
      // ���, �������̽� �׷���
      bg.draw(g);
      
      
      
      // ���̸� �׷���
      g.setColor(Color.WHITE);
      g.setRenderingHint(
            RenderingHints.KEY_TEXT_ANTIALIASING,
            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      g.setFont(new Font("Elephant", Font.BOLD, 30));
      g.drawString("Brave Girls - We Ride", 20, 700);
      
      // ������, ���̵� �׷���
      g.setColor(Color.LIGHT_GRAY);
      g.setFont(new Font("Elephant", Font.BOLD, 30));
      g.drawString("Normal", 1145, 700);
      g.setColor(Color.black);
      g.setFont(new Font("Elephant", Font.BOLD, 40));
      g.drawString(String.valueOf(score), 1055, 130);
      g.drawString(String.valueOf(coin), 1015, 200);
      
      // �� ��Ʈ �׷���
      for (int i = 0; i < notes.size(); i++) {
         Note note = notes.get(i);
         note.draw(g);
      }
      
      // effect
      effect.draw(g);
      
      
      
      
      g.setFont(new Font("Elephant", Font.BOLD, 40));
      if(failure == true) {
          g.setColor(new Color(0, 0, 0, 150));
          g.fillRect(
 					225, 
 					120,
 					523,
 					100);
          g.setColor(new Color(225,90,90));
          g.drawString("Game Over. . .", 380, 180);
       }
      else if(gameMusic.getTime() >= 10 && gameMusic.getTime() <= 1500) {
          g.setColor(new Color(0, 0, 0, 150));
          g.fillRect(
    					225, 
    					120,
    					523,
    					100);
          g.setColor(Color.white);
         g.drawString("Stage 1 Start!!", 360, 180);
         
      }
      else if(gameMusic.getTime() >= 20000 && gameMusic.getTime() <= 22000) {
          g.setColor(new Color(0, 0, 0, 150));
          g.fillRect(
    					225, 
    					120,
    					523,
    					100);
          g.setColor(Color.white);
         g.drawString("Stage 2 Start!!", 360, 180);
         
      }else if(gameMusic.getTime() >= 36000 && gameMusic.getTime() <= 38000) {
          g.setColor(new Color(0, 0, 0, 150));
          g.fillRect(
    					225, 
    					120,
    					523,
    					100);
          g.setColor(Color.white);
         g.drawString("Stage 3 Start!!", 360, 180);
      }
      
      
      
      
      
      // result
      if(displayResult) {
         rbg.draw(g);
      }
      
   }

   @Override
   public void keyPressed(int k) {      //Ű ���� ��
      
      if(k == KeyEvent.VK_ESCAPE) {
         
         exit = true;
         
         // ���� ����
         gameMusic.close();
         rbg.closeBgm();
         
         // ���� ����
         for (int i = 0; i < notes.size(); i++) {
            Note note = notes.get(i);
            note.setProceeded(false);
         }
         game.interrupt();
         
         // ȭ�� �̵�
         gsm.setStateInit(GameStateManager.MENUSTATE);
         gsm.setState(GameStateManager.MENUSTATE); 
         
      }
      
      // ���� ����
      if(!displayResult) {
         
         if(k == KeyEvent.VK_RIGHT)
         {
            if (position == 5 ) position = 5;
            else position += 1;
         }
         else if(k == KeyEvent.VK_LEFT)
         {
            if (position == 1 ) position = 1;
            else position -= 1;
         }
         
         
         if (position == 1) {
            bg.press1();
         } 
         else if (position == 2) {
            bg.press2();
         } 
         else if (position == 3) {
            bg.press3();
         } 
         else if (position == 4) {
            bg.press4();
         } 
         else if (position == 5) {
            bg.press5();
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
   public void keyReleased(int k) {      //Ű �� ��
      if (position == 1) bg.release1();
      else if (position == 2) bg.release2();
      else if (position == 3) bg.release3();
      else if (position == 4) bg.release4();
      else if (position == 5) bg.release5();
   }

   // ����Ž���� ���� �ش� ��Ʈ �Ǵ� 
   public void judge(int input)
   {
	   String clear="";
	   Random rand = new Random();
      for (int i = 0; i < notes.size(); i++) {         
    	  Note note = notes.get(i);
	      switch(rand.nextInt(3)) {
	      case 0:
	     	 clear="Perfect";
	      	break;
	      case 1:
	     	 clear="Lovely";
	      	break;
	      case 2:
	     	 clear="Amazing";
	      	break;
	  }
         if(note.getCheck() == false && note.getY() >= 530)      //judge ���� �ʾҰ� chick ���� ������ note �� �Ǵ��ؾ���.
         {
            
            if(note.judge(input).equals("clear")) {      //�� ���� ���
               System.out.println("clear");

               if(note.getCoin()==false) {
                   beatSound = new Music("beat.mp3", false);
                   beatSound.start();
            	   effect.setCombo(clear, combo); 
            	   score += 80 + combo * 5;
                   combo++;
               }
               else  {
                   coinSound = new Music("coinbeat.mp3", false);
                   coinSound.start();
            	   effect.setCombo("Coin +100 ",0); 
            	   coin += 100;
               }
               
               effect.setPosition(note.getNoteType());
               effect.setEffect(); 
               note.setCheck(true);
               
               
            }else if(note.judge(input).equals("before")){      //���� �� ���������� �ִ� note
               continue;
               
            }else if(note.judge(input).equals("failure")) {      //���� -> ���ӿ���
               failure = true;
               note.setCheck(true);
               
            }else if(note.judge(input).equals("coin")) {
               note.setCheck(true);
            }
            
            break;
         }
      }
   }
   
   
   @Override
   public void run() {
      
      int i =0;

      // �Ǻ��� ���� ������ run
      while(gameMusic.getTime() <= 50999 && failure == false && !game.isInterrupted()) {      //�뷡�ð����� && �ޱ⼺�� -> ���� ��� ����
         
         if(i == beats_final.size()) {   //������ note �����ð��� �뷡 ����ð� ���̿� ������ �����ϱ� ������ ������ �ʿ�. 
            continue;
         }
         if (beats_final.get(i).getTime() <= gameMusic.getTime() && i < beats_final.size()) {
            Note note = new Note(beats_final.get(i).getCoin(), beats_final.get(i).getNoteName(),1);
            notes.add(note);
            i++;
         }
         
         /*
         if(i == 22) {
            System.out.println(i);
            try {
               Thread.sleep(2000);
            } catch (InterruptedException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }*/
      }
         
      // ESC��ư ������ �ʾҴٸ�
      if (!exit) {
         // result ȭ��
         try {
            Thread.sleep(2000);
         } catch (Exception e) {e.printStackTrace();}
         rbg.calRank();
         displayResult = true;
      }

   }
   

}
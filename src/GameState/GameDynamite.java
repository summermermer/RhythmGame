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

public class GameDynamite extends GameState
                   implements Runnable {

   // 배경, 음악, 비트
   private InterfaceBackground bg;
   private Music gameMusic;
   private Beat[] beats = null;
   private ArrayList<Beat> beats_final = new ArrayList<Beat>();
   
   //캐릭터 필드
   
   // 노트 배열
   private ArrayList<Note> notes = new ArrayList<Note>();
   
   // 게임 스레드
   private Thread game;
   
   // 점수, 콤보, 코인
   private int score;
   private int grade;
   private int combo;
   private int coin;
   
   //캐릭터의 위치 변수
   private int position;
   
   
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
   
   private boolean failure;
   
   // beat
   private Music beatSound;
   private Music coinSound;
   public GameDynamite(GameStateManager gsm) {
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
      bg = new InterfaceBackground("/image/kitty_background.png",gsm.getState());
      
      
      // 악보 세팅
      setBeat();
      
      // 게임 음악
      gameMusic = new Music("Dynamite.mp3", false);
      gameMusic.start();
      
      // effect
      effect = new EffectAnimation();
      
      // 게임 시작
      game = new Thread(this);
      game.start();
      
      // exit
      exit = false;
      
      failure = false;
      
      // beat
      beatSound = new Music("beat.mp3", false);
      coinSound = new Music("coinbeat.mp3", false);
      
      //캐릭터의 위치
      position = 3;
      
      //얻은 코인 값 초기화
      coin = 0;
      
   }
   
   public void setBeat() {      //떨어지는 beat 하나하나 설정
      startTime = 111; 
      // 처음 비트가 떨어지는 시간
      int gap = 333; // 0.3초
      beats = new Beat[] {
            new Beat(startTime + gap * 2, 2),
            new Beat(startTime + gap * 4, 3),
            new Beat(startTime + gap * 6, 4),
            new Beat(startTime + gap * 8, 3),
            new Beat(startTime + gap * 9, 4),
            new Beat(startTime + gap * 11, 2),
            new Beat(startTime + gap * 13, 1),
            new Beat(startTime + gap * 15, 3),
            new Beat(startTime + gap * 17, 4), 
            new Beat(startTime + gap * 18, 5),
            new Beat(startTime + gap * 21, true, 5),
            new Beat(startTime + gap * 24, true, 2), 
            new Beat(startTime + gap * 27, true, 3),            
            new Beat(startTime + gap * 30, 2), // 이거 할때 coin인 경우는 position 앞에 true만 써주면 됩니다.
            new Beat(startTime + gap * 33, 4), 
            new Beat(startTime + gap * 35, true, 3),
            new Beat(startTime + gap * 36,true, 1),
            new Beat(startTime + gap * 37, 3),
            new Beat(startTime + gap * 39, 4), 
            new Beat(startTime + gap * 40, true, 3),
            new Beat(startTime + gap * 45, 1),
            new Beat(startTime + gap * 48, 3),
            new Beat(startTime + gap * 51, 1),
            new Beat(startTime + gap * 53, 3),
            new Beat(startTime + gap * 55, 1),
            new Beat(startTime + gap * 56, 2),
            new Beat(startTime + gap * 59, 3),
            new Beat(startTime + gap * 62, 5),
            new Beat(startTime + gap * 65, 3),
            new Beat(startTime + gap * 67, 5),
            new Beat(startTime + gap * 68, 4),
            new Beat(startTime + gap * 69, 5),
            new Beat(startTime + gap * 71, true, 4),
            new Beat(startTime + gap * 74, true, 2), 
            new Beat(startTime + gap * 77, true, 3),
            new Beat(startTime + gap * 80, true, 5),
            new Beat(startTime + gap * 83, 3),
            new Beat(startTime + gap * 86, 4),
            new Beat(startTime + gap * 89, 2),
            new Beat(startTime + gap * 92, 4),
            new Beat(startTime + gap * 94, true, 1),
            new Beat(startTime + gap * 95,true, 3),
            new Beat(startTime + gap * 96,true, 2),

      };
      
      for(int i = 0; i < beats.length; i++) {      //beats 배열에서 arraylist 로 복사 -> 배속 beat 쉽게 만들기 위해
         Beat beat = new Beat(beats[i].getTime(), beats[i].getCoin(), beats[i].getNoteName());
         beats_final.add(beat);
      }
      
      for(int i = 0; i < beats.length; i++) {      //stage 2 (1.2배속)
         
         //startTime + (stage 1 길이) + 기존 gap * 0.8
         Beat beat = new Beat(startTime + 33000 + ((beats_final.get(i).getTime() - startTime) * 0.8), 
                        beats_final.get(i).getCoin(), beats_final.get(i).getNoteName());
         
         beats_final.add(beat);
      }
      for(int i = 0; i < beats.length; i++) {      //stage 3 (1.4배속)
         
         //startTime + (stage 1, 2 길이) + 기존 gap * 0.6
         Beat beat = new Beat(startTime + 62000 + ((beats_final.get(i).getTime() - startTime) * 0.6), 
                        beats_final.get(i).getCoin(), beats_final.get(i).getNoteName());
         
         beats_final.add(beat);
      }
      
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
      
      
      //점수 업데이트
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
            rbg.takeMusicTitle("BTS  -  Dynamic");
            if(score < 4300) grade=3;
            else if(score < 13000) grade=2;
            else if(score < 27000) grade=1;
            else grade=0;
            rbg.takeScore(score,grade);
            rbg.takeCoin(coin);
            rbg.writeScore();
            gsm.addTotalCoin(coin); // 게임동안 모았던 coin값을 추가한다.
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
      g.drawString("BTS - Dynamic", 20, 700);
      
      // 점수판, 난이도 그래픽
      g.setColor(Color.LIGHT_GRAY);
      g.setFont(new Font("Elephant", Font.BOLD, 30));
      g.drawString("Hard", 1180, 700);
      g.setColor(Color.black);
      g.setFont(new Font("Elephant", Font.BOLD, 40));
      g.drawString(String.valueOf(score), 1055, 130);
      g.drawString(String.valueOf(coin), 1015, 200);
      
      // 각 노트 그래픽
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
      else if(gameMusic.getTime() >= 32000 && gameMusic.getTime() <= 34000) {
          g.setColor(new Color(0, 0, 0, 150));
          g.fillRect(
    					225, 
    					120,
    					523,
    					100);
          g.setColor(Color.white);
         g.drawString("Stage 2 Start!!", 360, 180);
         
      }else if(gameMusic.getTime() >= 62000 && gameMusic.getTime() <= 64000) {
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
   public void keyPressed(int k) {      //키 누를 때
      
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
   public void keyReleased(int k) {      //키 뗄 때
      if (position == 1) bg.release1();
      else if (position == 2) bg.release2();
      else if (position == 3) bg.release3();
      else if (position == 4) bg.release4();
      else if (position == 5) bg.release5();
   }

   // 순차탐색을 돌며 해당 노트 판단 
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
         if(note.getCheck() == false && note.getY() >= 530)      //judge 하지 않았고 chick 까지 내려온 note 만 판단해야함.
         {
            
            if(note.judge(input).equals("clear")) {      //잘 받은 경우
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
               
            }else if(note.judge(input).equals("before")){      //아직 더 떨어질곳이 있는 note
               continue;
               
            }else if(note.judge(input).equals("failure")) {      //실패 -> 게임오버
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

      // 악보가 끝날 때까지 run
      while(gameMusic.getTime() <= 86999 && failure == false && !game.isInterrupted()) {      //노래시간남음 && 받기성공 -> 게임 계속 진행
         
         if(i == beats_final.size()) {   //마지막 note 생성시간과 노래 종료시간 사이에 간격이 존재하기 때문에 딜레이 필요. 
            continue;
         }
         if (beats_final.get(i).getTime() <= gameMusic.getTime() && i < beats_final.size()) {
            Note note = new Note(beats_final.get(i).getCoin(), beats_final.get(i).getNoteName(),2);
            notes.add(note);
            i++;
         }
      }
         
      // ESC버튼 누르지 않았다면
      if (!exit) {
         // result 화면
         try {
            Thread.sleep(2000);
         } catch (Exception e) {e.printStackTrace();}
         rbg.calRank();
         displayResult = true;
      }

   }
   

}
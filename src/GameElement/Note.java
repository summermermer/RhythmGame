package GameElement;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

import Main.Game;


public class Note {
   
   // note image
   private BufferedImage noteBasicImage;
   
   // note ��ǥ
   private int x;
   private int y = 580 - 
         (1000 / Game.SLEEP_TIME * Game.NOTE_SPEED);
                          

   // note type, proceeded
   private int noteType;
   private boolean proceeded = true;
   
   //judge�� �ߴ��� ���ߴ��� check
   private boolean check = false;
   
   //note�� �������� �ƴ���
   private boolean coin = false;

   private int stage;

   public Note(int noteType) {
      
      this.noteType = noteType;
   
      init();
   }
   
   public Note(boolean coin, int noteType, int stage) {
      
      this.coin = coin;
      this.noteType = noteType;
      this.stage = stage;
      init();
   }

   public void init() {
      
      try {
         if(coin == true )
         {
            noteBasicImage = ImageIO.read(
                  getClass().getResourceAsStream(
                        "/image/coin.png"
                        ));
         }
         
         else {
        	 Random rand= new Random();
        	 String name="";
        	 switch(stage) {
	        	 case 0:
	        		 name="egg";
	        		 break;
	        	 case 1:
	        		 name="lotus";
	        		 break;
	        	 case 2:
	        		 name="foot";
	        		 break;
        	 }
            noteBasicImage = ImageIO.read(
               getClass().getResourceAsStream(
                     "/image/"+name+Integer.toString(rand.nextInt(2))+".png"
                     ));
         }
         
         
      } catch (Exception e) {   e.printStackTrace(); };   
      

      if (noteType == 1) x = 228;
      else if (noteType == 2) x = 332;
      else if (noteType == 3) x = 436;
      else if (noteType == 4) x = 540;
      else if (noteType == 5) x = 644;
      
   }

   public void update() {
      y += Game.NOTE_SPEED;
      /*
      if (y > 620)          
         close();
         */
   }
   
   public void draw(Graphics2D g) {      
      g.drawImage(noteBasicImage, x, y, null);
   }


   public String judge(int position) {      //���� ���� �Ǵ�

      
      if(y <= 620 && position == this.noteType) {      //chick ��ġ ����, y <= 620 �̸� ����.
         close();
         return "clear";
         
      }else if(y <= 620 && position != this.noteType) {//������ ��ġ �ٸ���, ���ɼ��� ����.
         return "before";
         
      }else {                                 //����
         if(this.coin == true) {      //���ν��д� ���ӳ���������
            close();
            return "coin";
            
         }else {
            close();
            return "failure";
         }
      }
      
      
   }
   
   public void close() { proceeded = false;}      //close -> proceeded = false
   public int getY() { return y; }
   public int getNoteType() { return noteType; }
   public void setProceeded(boolean proceeded) { this.proceeded = proceeded; }
   public boolean isProceeded() { return proceeded; }
   public boolean getCheck() { return check; }
   public void setCheck(boolean a) { check = a; }
   public boolean getCoin() { return coin; }

}
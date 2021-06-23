package Main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;


import javax.swing.JFrame;

import GameState.GameStateManager;

@SuppressWarnings("serial")
public class GameFrame extends JFrame implements
             Runnable, KeyListener{
   
   
   // game frame ( FPS = 60 )
   private int fps = 60; 
   private long MilliSecondPerFrame = 1000 / fps; 
   private Thread thread;
   private boolean running = false;
   
   //image
   private BufferedImage image;
   private Graphics2D g;
   
   // game state manager
   private GameStateManager gsm;
   
   public GameFrame() {
      
      // ������ ����
      thread = new Thread(this);
      thread.start();
      
      setUndecorated(true); /* �⺻ Ʋ ���� */
      setTitle("Rhythm Game");
      setSize(Game.WIDTH, Game.HEIGHT);
      setResizable(false); /* ���� ȭ�� ũ�� ���� - ����ڰ� ���Ƿ� �����Ұ� */
      setLocationRelativeTo(null); /* ����� ����â �߾����� ���� */
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //setBackground(new Color(0, 0, 0, 0));
      
      setShape(new RoundRectangle2D.Double(
            0,0,Game.WIDTH,Game.HEIGHT,100,100));  /* �ձ۰� �ձ۰� */
      setVisible(true);
      setLayout(null);
      addKeyListener(this);
      
   
      
   }
   
   public void init()
   {
      image = new BufferedImage(
            Game.WIDTH, Game.HEIGHT, 
            BufferedImage.TYPE_INT_RGB
            );
      g = (Graphics2D) image.getGraphics();
      
      gsm = new GameStateManager();
      
      running = true;

   }
   
   
   public void update() {
      gsm.update();
   }
   

   @Override
   public void keyPressed(KeyEvent k) {
      gsm.keyPressed(k.getKeyCode());
   }

   @Override
   public void keyReleased(KeyEvent k) {
      gsm.keyReleased(k.getKeyCode());
   }

   @Override
   public void keyTyped(KeyEvent k) {}
   
   public void draw() {
      gsm.draw(g);
   }

   public void drawToScreen() {
      
      Graphics g2 = getGraphics();
      g2.drawImage(image, 0, 0, Game.WIDTH, Game.HEIGHT,  null);
      g2.dispose();
      
   }

   
   @Override
   public void run() {
      
      init();

      long start;
      long elapsed;
      long wait;
      
      
      // game loop
      while(running) {
         
         // ù start �ð� ���� �߻� - Ȯ�� ��
         start = System.nanoTime(); /* ���� �ð� ��������� */
         
         update();
         draw();
         drawToScreen();
         
         elapsed = System.nanoTime() - start;
         
         wait = MilliSecondPerFrame - elapsed / 1000000;
         
         if(wait < 0 ) wait = 0;
         try {
            Thread.sleep(wait);
         } catch (Exception e) {
            e.printStackTrace();
         }
         
         //frameCount();
      }
      
   }
}
package GameEnvironment;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;


import javazoom.jl.player.Player;

public class Music extends Thread{

	// music player
	private Player player;
	private boolean isloop; /* 한 번만 재생하는가 or 무한 반복인가 */
	
	// music file 처리
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public Music(String name, boolean isloop) {
		
		try {
			this.isloop = isloop;
			file = new File(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/* 실행 중인 음악의 위치 반환 */
	public int getTime()
	{
		if(player == null)
			return 0;
		return player.getPosition(); /* 3분짜리 영상- > 0:10라면 10000반환 */
	}
	
	public void close()
	{
		try {
			isloop = false;
			player.close();
			this.interrupt(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void run()
	{
		try {
			do {
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis); /* 버퍼에 담음 */
				player = new Player(bis); /* 해당 음악 파일을 담음 */
				player.play();
			} while (isloop);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

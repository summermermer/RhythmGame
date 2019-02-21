package GameElement;

import java.awt.image.BufferedImage;
import java.util.Date;


public class Animation {

	private BufferedImage[] frames;
	private int currentFrame;
	
	private long startTime;
	private long delayTime;
	
	private boolean occuredEffectOnce;
	
	public Animation() {
		occuredEffectOnce = false;
	}
	
	public void setFrames(BufferedImage[] frames) {
		this.frames = frames;
		currentFrame = 0;
		startTime = new Date().getTime();
		occuredEffectOnce = false;
	}
	
	public void setDelay(long d) { delayTime = d; }
	public void setFrame(int index) { currentFrame = index; }
	
	public void update() {

		if(delayTime == - 1) return;
		
		long elapsed = new Date().getTime() - startTime;
		if(elapsed > delayTime) {
			currentFrame++;
			startTime = new Date().getTime();
		}
		if(currentFrame == frames.length) {
			currentFrame = 0;
			occuredEffectOnce = true;
		}
		
	}
	
	public int getFrame() { return currentFrame; }
	public BufferedImage getImage() { return frames[currentFrame]; }
	public boolean hasOccuredOnce( ) { return occuredEffectOnce; }
}

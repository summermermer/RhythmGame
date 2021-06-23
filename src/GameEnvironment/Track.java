package GameEnvironment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Track {

	private String singerName; 
	private String titleName;
	private int difficulty;
	private int Price;
	private boolean isLocked;
	
	public void saveLocked() {
		String str=null;
		if(isLocked) str="true";
		else str="false";
		FileWriter fw_new;
		BufferedWriter writer;
		try {
			
			fw_new = new FileWriter(
					new File(
							this.titleName+"_Locked.txt"),
					         false
					         );
			writer = new BufferedWriter(fw_new);
			writer.write(str);
			writer.flush();
			fw_new.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Track(String singerName, String titleName, int difficulty, boolean Locked, int price) {
		this.singerName = singerName;
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.isLocked = Locked;
		this.Price = price;
	}
	public String getSingerName() {
		return singerName;
	}
	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public boolean getLocked() {
		return isLocked;
	}
	public void setLocked(boolean Locked) {
		this.isLocked = Locked;
		saveLocked();
	}
	public int getPrice() {
		return this.Price;
	}

}

package GameState;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class CoinBank {
	//private int Coin;
	public CoinBank(){
	}
	int coinLoading() {
		int sc = 0;
		try {
			
			File file = new File("NowCoin.txt");
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine()) {
				sc = Integer.parseInt(scan.nextLine());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sc;
	}
	void coinLogging(int Coin) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
		String dateStr = sdf.format(cal.getTime());
		//저장 시간
		
		FileWriter fw,fw_new;
		BufferedWriter writer;
		// coinLog와 현재코인 파일에 저장
		try {
			
			fw = new FileWriter(
					new File(
							"CoinLog.txt"),
					         true
					         );
			writer = new BufferedWriter(fw);
				
			
			writer.write(dateStr + "\t\t");
			writer.write(Integer.toString(Coin));
			writer.newLine();
			writer.flush();
			fw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			
			fw_new = new FileWriter(
					new File(
							"NowCoin.txt"),
					         false
					         );
			writer = new BufferedWriter(fw_new);
			writer.write(Integer.toString(Coin));
			writer.flush();
			fw_new.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

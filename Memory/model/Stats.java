package Memory.model;
import java.util.ArrayList;

public class Stats {
	private ArrayList<Long> timeRecords = new ArrayList<Long>();
	private int gameCount = 0;
	
	public void setCT(long time){
		timeRecords.add(time);
	}
	
	public void incrementGC(){
		gameCount++;
	}

	public void decrementGC(){
		gameCount--;
	}

	public int getGameCount(){
		return gameCount;
	}

	public ArrayList<Long> getTimeRecords(){
		return timeRecords;
	}
}
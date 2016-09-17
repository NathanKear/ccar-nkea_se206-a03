package VoxspellPrototype;

import java.util.ArrayList;
import java.util.HashMap;

public class Level {

	private boolean _isUnlocked = false;
	private HashMap<String, int[]> _levelMap;
	private ArrayList<String> _currentlyFailedList = new ArrayList<String>();
	private String _levelName;
	
	public Level(String levelName, HashMap<String, int[]> levelMap) {
		_levelName = levelName;
		_levelMap = levelMap;
	}
	
	public void unlockLevel() {
		_isUnlocked = true;
	}
	
	public boolean isUnlocked() {
		return _isUnlocked;
	}
	
	public HashMap<String, int[]> getMap() {
		return _levelMap;
	}
	
	public String levelName() {
		return _levelName;
	}
	
	public void addToFailed(String word){
		_currentlyFailedList.add(word);
	}
	
	public void removeFromFailed(String word){
		_currentlyFailedList.remove(word);
	}
	
	public ArrayList<String> getFailedWords() {
		return _currentlyFailedList;
	}
	
}

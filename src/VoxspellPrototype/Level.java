package VoxspellPrototype;

import java.util.ArrayList;
import java.util.HashMap;

public class Level {

	private boolean _isUnlocked = false;
	private HashMap<String, int[]> _levelMap;
	private ArrayList<String> _currentlyFailedList = new ArrayList<String>();
	private ArrayList<String> _currentlyMasteredList = new ArrayList<String>();
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
	
	public int Size() {
		return _levelMap.size();
	}

	public String levelName() {
		return _levelName;
	}

	public void addToFailed(String word){
		if(!_currentlyFailedList.contains(word)) {
			_currentlyFailedList.add(word);
		}
	}

	public void removeFromFailed(String word){
		_currentlyFailedList.remove(word);
	}

	public ArrayList<String> getFailedWords() {
		return _currentlyFailedList;
	}
	
	public void addToMastered(String word){
		if(!_currentlyMasteredList.contains(word)) {
			_currentlyMasteredList.add(word);
		}
	}

	public void removeFromMastered(String word){
		_currentlyMasteredList.remove(word);
	}

	public ArrayList<String> getMasteredWords() {
		return _currentlyMasteredList;
	}

	public void ClearStats() {
		for (int[] stats : _levelMap.values()) {
			for (int i = 0; i < stats.length; i++) {
				stats[i] = 0;
			}
		}
	}
}

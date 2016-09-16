package VoxspellPrototype;

import java.util.HashMap;

public class Level {

	private boolean _isUnlocked = false;
	private HashMap<String, int[]> _levelMap;
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
	
}

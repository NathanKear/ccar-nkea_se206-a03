package VoxspellPrototype;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class WordList extends HashMap<String, HashMap<String, int[]>> {

	private static WordList _instance = null;
	
	private WordList() {
		super();
	}
	
	public static WordList GetWordList() {
		if (_instance == null) {
			_instance = initialiseNathansAwesomeDataStructure("NZCER-spelling-lists.txt");
		}
		
		return _instance;
	}
	
	public WordList ReloadWordList() {
		_instance = initialiseNathansAwesomeDataStructure("NZCER-spelling-lists.txt");
		
		return _instance;
	}
	
	private static WordList initialiseNathansAwesomeDataStructure(String fileName) {
		//Creating the file to read from
		File wordList = new File(fileName);

		String line;
		int lvlCounter = 1;
		String levelKey = "";
		
		//Initialising the data structures
		WordList nathansAwesomeDataStructure = new WordList();
		HashMap<String, int[]> levelHashMap = new HashMap<String, int[]>();
		
		try {
			//Creating the reader to loop through each line in the text file
			BufferedReader textFileReader = new BufferedReader(new FileReader(wordList));

			while((line = textFileReader.readLine()) != null) {
	
				//If the first char is % then its the name of the level
				if(line.charAt(0) == '%') {
					
					//Set the level name and increase the counter by 1
					levelKey = "level " + lvlCounter;
					lvlCounter++;
					
					//Create the hashmap for that level
					levelHashMap = new HashMap<String, int[]>();

				} else {
					
					//Hashing each word to the level hashmap
					levelHashMap.put(line, new int[3]);
					
					//Hashing the level hashmap to the overall hashmap
					nathansAwesomeDataStructure.put(levelKey, levelHashMap);
					
				}

			}
			textFileReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return nathansAwesomeDataStructure;
	}
}

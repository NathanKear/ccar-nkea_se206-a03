package VoxspellPrototype;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.List;

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

	public void clearWordList() {
		this.clear();

		_instance = initialiseNathansAwesomeDataStructure("NZCER-spelling-lists.txt");
	}

	public void saveWordListToDisk() {
		File f = new File("Word-Log");

		try {

			if(!f.exists()) {
				f.createNewFile();
			}

			BufferedWriter textFileWriter = new BufferedWriter(new FileWriter(f));

			for(int i = 0; i < this.size(); i++) {
				//Getting a level from the hash map
				HashMap<String, int[]> levelMap = this.get("level " + (i + 1));

				//Getting an iterator to go over all the words in the level hash map
				Iterator<Map.Entry<String, int[]>> wordIterator = levelMap.entrySet().iterator();

				//Looping through all the words in the level hash map using the iterator
				while(wordIterator.hasNext()) {
					Map.Entry<String, int[]> pair = (Map.Entry<String, int[]>) wordIterator.next();

					//Getting the stats array associated with the pair from the level hash map
					int[] stats = (int[]) pair.getValue();

					//Looping through the stats array
					for(int k = 0; k < 3; k++) {
						//If at any point a stat is not zero then the word has been tested so it should be saved to disk and 
						//the loop should be broken out of
						if(stats[k] != 0) {
							String word = (String) pair.getKey();
							textFileWriter.append("level " + i + " " + word + " " + stats[0] + " " + stats[1] + " " + stats[2]);
							break;
						}
					}
				}
			}
			textFileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static WordList loadStatsFromFile(WordList wordList) {
		File savedWords = new File("Word-Log");

		try {

			//Reading words from file if they exist
			if(savedWords.exists()) {
				BufferedReader statsReader = new BufferedReader(new FileReader(savedWords));

				String line = "";
				while((line = statsReader.readLine()) != null) {
					//Splitting each line by spaces
					String[] wordAndStats = line.split("\\s+"); 
					
					//Getting the key for the level hash map
					String levelKey = wordAndStats[0] + " " + wordAndStats[1];
					
					//Getting the word to use as a key in the level map
					String wordKey = wordAndStats[2];
					
					//Getting the level map
					HashMap<String, int[]> levelMap = wordList.get(levelKey);
					
					//Getting the stats paired with the word
					int[] stats = levelMap.get(wordKey);
					
					//Set each of the stats to be what they are from file
					stats[0] = Integer.parseInt(wordAndStats[3]);
					stats[1] = Integer.parseInt(wordAndStats[4]);
					stats[2] = Integer.parseInt(wordAndStats[5]);
					
					//Hash the stats and word back into the hashmap
					levelMap.put(wordKey, stats);
				}

				statsReader.close();
			}
		} catch (IOException e) {

		}


		return wordList;
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
					levelKey = "Level " + lvlCounter;
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

		nathansAwesomeDataStructure = loadStatsFromFile(nathansAwesomeDataStructure);
		return nathansAwesomeDataStructure;
	}
	
	/**
	 * Returns random selection of words from the wordlist specified.
	 * @param wordlistName Name of the list to select random words
	 * @param listCount Number of words to return
	 * @return
	 */
	public List<String> GetRandomWords(String wordlistName, int listCount) {
		// Get list of words from named wordlist
		Collection<String> wordset = this.get(wordlistName).keySet();
		List<String> wordlist = new ArrayList<String>(wordset);
		
		// Shuffle list
		java.util.Collections.shuffle(wordlist);
		
		// Ensure we don't try to return more elements than exist in the list
		listCount = Math.min(listCount, wordlist.size() - 1);
		
		// Return first n elements from shuffled list (essentially n random elements)
		return wordlist.subList(0, listCount);
	}
}

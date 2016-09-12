package VoxspellPrototype;

import java.util.HashMap;

import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class StatisticsScreen extends Parent {
	
	private Window _window;
	
	public StatisticsScreen(Window window) {
		
		this._window = window;
		
		//Getting the number of tabs to create
		int numOfTabs = WordList.GetWordList().size();
		
		//This will be the name of each tab
		String tabName;
		
		//Creating the pane to store the tabs
		TabPane statsTabPane = new TabPane();
		
		//Looping through the number of levels and creating a tab for each one
		for(int i = 0; i < numOfTabs; i++) {
			int levelUpTo = i + 1;
			tabName = "Level " + levelUpTo;
			Tab t = new Tab(tabName);
			t.setClosable(false);
			statsTabPane.getTabs().add(t);
		}
		
		//Adding the statspane
		this.getChildren().add(statsTabPane);
		
		return;
		
	}

}

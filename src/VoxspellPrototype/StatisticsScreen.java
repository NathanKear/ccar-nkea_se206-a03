package VoxspellPrototype;


import java.util.HashMap;
import java.util.Set;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

public class StatisticsScreen extends Parent {
	
	private Window _window;
	
	private final String BTN_RETURN_TEXT = "Return";
	private final String BTN_COLOR = VoxspellPrototype.DARK_BLUE;
	private final String BTN_FONT_COLOR = VoxspellPrototype.WHITE;
	private final int BTN_FONT_SIZE = VoxspellPrototype.BTN_FONT_SIZE;
	private final double BTNWIDTH_SCREENWIDTH_RATIO = 1.00;
	private final int BTN_HEIGHT = 70;
	private final String BACK_COLOR = VoxspellPrototype.LIGHT_BLUE;
	
	public StatisticsScreen(Window window) {
		
		this._window = window;
		
		//Getting the WordList
		WordList wordlist = WordList.GetWordList();
		
		//Getting the number of tabs to create
		int numOfTabs = wordlist.size();
		
		//This will be the name of each tab
		String tabName;

		BorderPane bp = new BorderPane();
				
		//Creating the pane to store the tabs
		TabPane statsTabPane = new TabPane();

		bp.setCenter(statsTabPane);
		
		int tabWidth = _window.GetWidth()/(numOfTabs + 3);
		statsTabPane.setTabMinWidth(tabWidth);
		
		statsTabPane.setStyle("-fx-base: #89bdd3");
		
		//Looping through the number of levels and creating a tab for each one
		for(int i = 0; i < numOfTabs; i++) {
			tabName = wordlist.get(i).levelName();
			Tab t = new Tab(tabName);
			t.setClosable(false);
			statsTabPane.getTabs().add(t);

			populateStatsTable(wordlist.get(i), t, i + 1);
		}

		statsTabPane.setMinHeight(_window.GetHeight());
		statsTabPane.setMinWidth(_window.GetWidth());
		
		Button btnReturn;
		btnReturn = new Button(BTN_RETURN_TEXT);
		btnReturn.setPrefWidth(BTNWIDTH_SCREENWIDTH_RATIO * _window.GetWidth());
		btnReturn.setPrefHeight(BTN_HEIGHT);
		btnReturn.setAlignment(Pos.CENTER);
		btnReturn.setStyle("-fx-font: " + BTN_FONT_SIZE + " arial;" + 
				" -fx-base: " + BTN_COLOR + ";" + 
				" -fx-text-fill: " + BTN_FONT_COLOR + ";");	
		btnReturn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				_window.SetWindowScene(new Scene(new MainScreen(_window), _window.GetWidth(), _window.GetHeight()));
			}
		});
		
		VBox root = new VBox(btnReturn, statsTabPane);
		
		//Adding the statspane
		this.getChildren().add(root);
		
		root.setStyle("-fx-background-color: " + BACK_COLOR + ";");

		return;
	}

	private void populateStatsTable(Level level, Tab tab, int index) {
	

		HashMap<String, int[]> levelHashMap = level.getMap();

		TableColumn<HashMap.Entry<String, int[]>, String> statsWordCol = new TableColumn<>("Word");
		statsWordCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HashMap.Entry<String, int[]>, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<HashMap.Entry<String, int[]>, String> p) {
				// this callback returns property for just one cell, you can't use a loop here
				// for first column we use key
				return new SimpleStringProperty(p.getValue().getKey());
			}
		});

		TableColumn<HashMap.Entry<String, int[]>, String> statsFailedCol = new TableColumn<>("Failed");
		statsFailedCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HashMap.Entry<String, int[]>, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<HashMap.Entry<String, int[]>, String> p) {
				// this callback returns property for just one cell, you can't use a loop here
				// for first column we use key
				int[] statsArray = p.getValue().getValue();
				return new SimpleStringProperty("" + statsArray[0]);
			}
		});

		TableColumn<HashMap.Entry<String, int[]>, String> statsFaultedCol = new TableColumn<>("Faulted");
		statsFaultedCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HashMap.Entry<String, int[]>, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<HashMap.Entry<String, int[]>, String> p) {
				// this callback returns property for just one cell, you can't use a loop here
				// for first column we use key
				int[] statsArray = p.getValue().getValue();
				return new SimpleStringProperty("" + statsArray[1]);
			}
		});

		TableColumn<HashMap.Entry<String, int[]>, String> statsMasteredCol = new TableColumn<>("Mastered");
		statsMasteredCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HashMap.Entry<String, int[]>, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<HashMap.Entry<String, int[]>, String> p) {
				// this callback returns property for just one cell, you can't use a loop here
				// for first column we use key
				int[] statsArray = p.getValue().getValue();
				return new SimpleStringProperty("" + statsArray[2]);
			}
		});

		ObservableList<HashMap.Entry<String, int[]>> items = FXCollections.observableArrayList(levelHashMap.entrySet());
		final TableView<HashMap.Entry<String,int[]>> table = new TableView<>(items);
		
		table.getColumns().setAll(statsWordCol, statsFailedCol, statsFaultedCol, statsMasteredCol);

		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		tab.setContent(table);

	}

}

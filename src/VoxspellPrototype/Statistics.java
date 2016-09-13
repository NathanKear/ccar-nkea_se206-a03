package VoxspellPrototype;


import java.util.HashMap;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class Statistics extends Parent {

	public Statistics(HashMap<String, HashMap<String, int[]>> dataStruct) {

		//Getting the number of tabs to create
		int numOfTabs = dataStruct.size();

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

			populateStatsTable(dataStruct, t, i + 1);

		}

		//Adding the statspane
		this.getChildren().add(statsTabPane);

		return;

	}

	private void populateStatsTable(HashMap<String, HashMap<String, int[]>> dataStruct, Tab tab, int index) {

		System.out.println(index);
		HashMap<String, int[]> levelHashMap = dataStruct.get("level " + (index + 1));

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

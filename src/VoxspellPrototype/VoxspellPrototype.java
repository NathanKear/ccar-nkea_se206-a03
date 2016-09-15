package VoxspellPrototype;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VoxspellPrototype extends Application {

	private Window _window;
	
	private final String WINDOW_TITLE = "VoxSpell";
	private final int WINDOW_WIDTH = 800;
	private final int WINDOW_HEIGHT = 500;

	public VoxspellPrototype() {
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setResizable(false);
		_window = new Window(stage, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		_window.SetWindowTitle(WINDOW_TITLE);	
				
		_window.SetWindowScene(new Scene(new MainScreen(_window), _window.GetWidth(), _window.GetHeight()));
		
		_window.CenterOnScreen();
		_window.Show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void stop(){
	    //Save words to disk
		WordList wordList = WordList.GetWordList();
		wordList.saveWordListToDisk();
	}

	
}


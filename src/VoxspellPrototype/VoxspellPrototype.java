package VoxspellPrototype;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VoxspellPrototype extends Application {

	public static final String LIGHT_BLUE = "#9ad3de";
	public static final String DARK_BLUE = "#89bdd3";
	public static final String WHITE = "#e3e3e3";
	public static final String DARK = "#c9c9c9";
	public static final String MEDIA_SOURCE = "/home/nathan/workspace/ccar-nkea_se206-a03/media/bunny.mp4";
	public static final int BTN_FONT_SIZE = 22;
	public static final int TXT_FONT_SIZE = 30;
	public static final int TXT_FONT_SIZE_FINE = 18;
	public static final int QUIZ_LENGTH = 10;
	
	private Window _window;
	
	private final String WINDOW_TITLE = "VoxSpell";
	private final int WINDOW_WIDTH = 800;
	private final int WINDOW_HEIGHT = 500;

	public VoxspellPrototype() {
	}

	@Override
	public void start(Stage stage) throws Exception {
		Platform.setImplicitExit(false);
		
		stage.setResizable(false);
		_window = new Window(stage, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		_window.SetWindowTitle(WINDOW_TITLE);	
				
		_window.SetWindowScene(new Scene(new MainScreen(_window), _window.GetWidth(), _window.GetHeight()));
		
		_window.CenterOnScreen();
		_window.Show(true);
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


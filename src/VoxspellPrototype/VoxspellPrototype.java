package VoxspellPrototype;

import javafx.application.Application;
import javafx.stage.Stage;

public class VoxspellPrototype extends Application {

	private Stage _window;
	
	private final String _windowTitle = "VOXSPELL";
	
	public VoxspellPrototype() {
	}

	@Override
	public void start(Stage window) throws Exception {
		this._window = window;
		
		_window.setTitle(_windowTitle);
		_window.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}

package VoxspellPrototype;

import java.awt.Rectangle;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class Window {
	
	private int _width, _height;
	private Stage _window;
	
	public int GetWidth() {
		return _width;
	}
	
	public int GetHeight() {
		return _height;
	}
	
	public Window(Stage window, int width, int height) {
		this._window = window;

		this._height = height;
		this._width = width;
	}
	
	public void SetWindowScene(Scene scene) {
		_window.setScene(scene);
	}
	
	public void SetWindowTitle(String title) {
		_window.setTitle(title);
	}
	
	public void Show() {
		_window.show();
	}
	
	public void CenterOnScreen() {
		_window.centerOnScreen();
	}
	
	public Rectangle GetBounds() {
		return new Rectangle(
				(int)this._window.getX(),
				(int)this._window.getY(),
				(int)this._window.getWidth(),
				(int)this._window.getHeight());
	}
}

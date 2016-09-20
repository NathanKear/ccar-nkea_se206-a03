package VoxspellPrototype;

import java.awt.Rectangle;

import javafx.geometry.Bounds;
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
	
	public int GetPosX() {
		return (int)this._window.getX();
	}
	
	public int GetPosY() {
		return (int)this._window.getY();
	}
	
	public Stage GetWindowStage() {
		return _window;
	}
	
	public Window(Stage window, int width, int height) {
		this._window = window;

		this._height = height;
		this._width = width;
	}
	
	public void SetWindowScene(Scene scene) {
		_window.setScene(scene);
	}
	
	public void SetWindowPosition(int x, int y) {
		_window.setX(x);
		_window.setY(y);
	}
	
	public void SetWindowTitle(String title) {
		_window.setTitle(title);
	}
	
	public void Show(boolean show) {
		if (show) {
			_window.show();
		} else {
			_window.hide();
		}
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

package VoxspellPrototype;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class PopupWindow {
	private final static int TXT_FONT_SIZE = VoxspellPrototype.TXT_FONT_SIZE;
	private final static String TXT_FONT_COLOR = VoxspellPrototype.WHITE;
	private final static String BACK_COLOR = VoxspellPrototype.LIGHT_BLUE;
	private final static int POPWINDOW_WIDTH = 300;
	private final static int POPWINDOW_HEIGHT = 100;
	
	public static Stage DeployPopupWindow(String message) {
		Stage popupStage = new Stage();
		
		VBox root = new VBox();
		Text popupText = new Text(message);
		popupText.setTextAlignment(TextAlignment.CENTER);
		popupText.setStyle("-fx-font: " + TXT_FONT_SIZE + " arial;" +
				" -fx-fill: " + TXT_FONT_COLOR + ";");
		root.setAlignment(Pos.CENTER);
		root.getChildren().add(popupText);
		Scene popupScene = new Scene(root, POPWINDOW_WIDTH, POPWINDOW_HEIGHT);
		
		popupStage.setScene(popupScene);
		popupStage.show();
		popupStage.requestFocus();
		popupStage.toFront();
		
		// Set root node color
		root.setStyle("-fx-background-color: " + BACK_COLOR + ";");
		
		return popupStage;
	}
}


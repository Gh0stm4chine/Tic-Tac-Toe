package project6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
	static GridPane grid = new GridPane();

	public static void main(String[] args) {
//			Morpion.play(1,1);
//			Morpion.displayWorld();
//			System.out.println(Morpion.endGame());
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
		Scene scene = new Scene(root,695.0,606.0);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Morpion");
		primaryStage.show();
	}

}

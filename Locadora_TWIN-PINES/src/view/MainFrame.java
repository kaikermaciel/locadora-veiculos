package view;



import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFrame extends Application{
	
	public void start(Stage primaryStage) {
		Parent root;
		System.out.println("flu");
		try {
			root = FXMLLoader.load(getClass().getResource("layout.fxml"));
			Scene scene = new Scene(root);

			primaryStage.setTitle("Vendas");
			primaryStage.setScene(scene);
			primaryStage.show();
		
		} catch (IOException e) {
			System.out.println("FLU");
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
		}

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}


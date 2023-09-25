package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		View view = new View();
		view.start(primaryStage);
	}
	
	@Override
	public void stop(){
		// on stoppe le thread si jeu fini
	    controller.loose = true;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

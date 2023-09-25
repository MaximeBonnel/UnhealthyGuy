package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

class Position{
	public int positionX = 0;
	public int positionY = 0;
	
	public Position(int x, int y) {
		this.positionX = x;
		this.positionY = y;
	}
}

public class View extends Application{
	
	private int screenWidth = 1000;
	private int screenHeight = 600;
	private boolean resizable = false;
	
	// Display Table
	private Position[] displayTable = new Position[controller.module.getArrayLength() + 1];
	
	// Background
	private Image background = new Image("RueDessin.PNG");
	private ImageView backgroundView = new ImageView(this.background);
	private Image backgroundLoose = new Image("barJava.jpg");
	private ImageView backgroundLooseView = new ImageView(this.backgroundLoose);
	
	// Player
	private Image player = new Image("player.gif");
	private ImageView playerView = new ImageView(this.player);
	
	// Bottles
	private Image image = new Image("waterBottle.png");
	private Image image2 = new Image("cokeBottle.png");
	
	// Timer label
	private String timerText = "Temps : ";
	private Label timerLabel = new Label();
	
	// Score label
	private String scoreText = "Score : ";
	private Label scoreLabel = new Label();
	
	// Text label
	private Label textLabel = new Label("Perdu !!");
	
	public void setDisplayTable() {
		for (int i = 0; i < controller.module.getArraySize()[0]; i++) { // Y
			for (int j = 0; j < controller.module.getArraySize()[1]; j++) { // X
				displayTable[(i * controller.module.getArraySize()[1]) + j] = new Position((this.screenWidth / controller.module.getArraySize()[1]) * j, (this.screenHeight / controller.module.getArraySize()[0]) * i);
			}
		}
	}
		
	@Override
	public void start(Stage primaryStage) {
		try {
			// Tableau d'affichage
			setDisplayTable();
			
			//Parent sceneBuilder = FXMLLoader.load(getClass().getResource("scenebuilder.fxml"));
			
			Group root = new Group();
			Scene scene = new Scene(root, screenWidth, screenHeight);
			
			// Css
		 	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			// Icone 
			Image icon = new Image("troll-face-44.gif");
			primaryStage.getIcons().add(icon);
			primaryStage.setTitle("TheUnhealthy");
			
			// Dimensions de la fenetre :
			primaryStage.setWidth(screenWidth);
			primaryStage.setHeight(screenHeight+35);
			primaryStage.setResizable(resizable);
			
			// Background
			this.backgroundLooseView.setFitWidth(screenWidth);
			this.backgroundLooseView.setFitHeight(screenHeight);
			this.backgroundView.setFitWidth(screenWidth);
			this.backgroundView.setFitHeight(screenHeight);
				
			// Player
			this.playerView.setTranslateX(this.displayTable[controller.module.getStickmanPos()].positionX);
			this.playerView.setTranslateY(this.displayTable[controller.module.getStickmanPos()].positionY);
			this.playerView.setFitWidth(this.screenWidth / controller.module.getArraySize()[1]);
			this.playerView.setFitHeight(this.screenHeight / controller.module.getArraySize()[0]);
			
		    // Actualisation
			new AnimationTimer() {
				@SuppressWarnings("static-access")
				@Override
				public void handle(long arg0) {
					root.getChildren().clear(); // Supprime toutes les images
					
					if (controller.loose == true) {
						// Ajoute le fond
						root.getChildren().add(backgroundLooseView);
						
						// Ajoute le joueur
						playerView.setTranslateY(300);
						root.getChildren().add(playerView);
						
						// Affiche le texte
						textLabel.setTranslateX(270);
						textLabel.setTranslateY(230);
						textLabel.setTextFill(Color.web("#FFFFFF"));
						textLabel.setFont(new Font("Arial", 40));
						root.getChildren().add(textLabel);
						
						// Affiche le score
						scoreLabel.setTranslateX(600);
						scoreLabel.setTranslateY(230);
						scoreLabel.setTextFill(Color.web("#FFFFFF"));
						scoreLabel.setFont(new Font("Arial", 40));
						scoreLabel.setText(scoreText + controller.module.getScore());
						root.getChildren().add(scoreLabel);
					} else {
						root.getChildren().add(backgroundView); // Ajoute le fond
						playerView.setTranslateY(displayTable[controller.module.getStickmanPos()].positionY);
						root.getChildren().add(playerView); // Ajoute le joueur
						
						// Timer
						timerLabel.setTranslateX(230);
						timerLabel.setTranslateY(20);
						timerLabel.setFont(new Font("Arial", 25));
						timerLabel.setText(timerText + controller.timer.getSeconds());
						root.getChildren().add(timerLabel);
						
						// Score
						scoreLabel.setTranslateX(450);
						scoreLabel.setTranslateY(20);
						scoreLabel.setFont(new Font("Arial", 25));
						scoreLabel.setText(scoreText + controller.module.getScore());
						root.getChildren().add(scoreLabel);
						
						// Ajoute les bouteilles
						for (int i = 0; i < controller.module.getArrayLength(); i++) {
							if (controller.module.getArray()[i] == 2) {
								ImageView imageView = new ImageView(image);
								imageView.setTranslateX(displayTable[i].positionX);
								imageView.setTranslateY(displayTable[i].positionY);
								imageView.setFitWidth(screenWidth / controller.module.getArraySize()[1]);
								imageView.setFitHeight(screenHeight / controller.module.getArraySize()[0]);
								root.getChildren().add(imageView);
							}
							else if (controller.module.getArray()[i] == 3) {
								ImageView imageView2 = new ImageView(image2);
								imageView2.setTranslateX(displayTable[i].positionX);
								imageView2.setTranslateY(displayTable[i].positionY);
								imageView2.setFitWidth(screenWidth / controller.module.getArraySize()[1]);
								imageView2.setFitHeight(screenHeight / controller.module.getArraySize()[0]);
								root.getChildren().add(imageView2);
							}
						}
					}
				}
			}.start();
		 	
		 	// Player
		 	movePlayer(scene);
		 	
		    primaryStage.setScene(scene);
		    primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// mouvements via le clavier :
	
	public void movePlayer(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

		    @Override
		    public void handle(KeyEvent event) {

		        switch (event.getCode()) {

		        case Q:
		        	controller.Left();
		        	playerView.setTranslateX(displayTable[controller.module.getStickmanPos()].positionX);
		            break;
		        case D:
		        	controller.Right();
		        	playerView.setTranslateX(displayTable[controller.module.getStickmanPos()].positionX);
		            break;
				default:
					break;
		        }
		    }
		});
	}

	public int getScreenWidth() {
		return this.screenWidth;
	}
	
	public int getScreenHeight() {
		return this.screenHeight;
	}
}

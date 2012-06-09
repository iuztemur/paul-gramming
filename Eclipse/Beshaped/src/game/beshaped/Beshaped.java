package game.beshaped;

import java.io.File;
import java.util.Random;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Beshaped extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Group root = new Group();
		
		// show background
		ImageView background = new ImageView(new Image("images/background.png"));
		root.getChildren().add(background);
		
		// add logo 
		ImageView logo = new ImageView(new Image("images/logo.png"));
		root.getChildren().add(logo);
		logo.setTranslateX(343);
		logo.setTranslateY(-5);
		logo.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.exit(0);
			}
			
		});
		
		// load all the images
		File dir = new File("shapes");
		String[] shapes = dir.list();
		Image[] images = new Image[shapes.length];
		for (int i=0; i<shapes.length; i++) {
			images[i] = new Image("shapes/"+shapes[i]);
		}
		
		int row = 6, column = 6;
		ImageView[][] cells = new ImageView[row][column];
		Random random = new Random();
		for (int r=0; r<row; r++) {
			for (int c=0; c<column; c++) {
				int randomIndex = random.nextInt(images.length);
				Image randomImage = images[randomIndex];
				cells[r][c] = new ImageView(randomImage);
				root.getChildren().add(cells[r][c]);
				cells[r][c].setTranslateX(c*50+40);
				cells[r][c].setTranslateY(r*50+50);
			}
		}
		
		Scene scene = new Scene(root, 400, 400, Color.TRANSPARENT);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}

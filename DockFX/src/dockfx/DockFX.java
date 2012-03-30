/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dockfx;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author paul
 */
public class DockFX extends Application {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) {
		stage.setTitle("Hello World!");

		Group root = new Group();

		final ImageView dock = new ImageView(new Image("images/dock1.png"));
		dock.setTranslateX(100);
		dock.setTranslateY(200);
		dock.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent arg0) {
				if (arg0.getButton() == MouseButton.PRIMARY) {
					dock.setImage(new Image("images/dock1.png"));
				} else {
					dock.setImage(new Image("images/dock2.png"));
				}
			}
			
		});
		root.getChildren().add(dock);

		final String[] images = {
			"Browser-32.png", 
			"Internet-32.png",
			"Mail-32.png",
			"User-32.png"
		};

		HBox box = new HBox(20);
		for (int i = 0; i < images.length; i++) {
			BouncingIcon icon = new BouncingIcon(new Image("icons/"+images[i]));
			icon.setEffect(new Reflection());
			box.getChildren().add(icon);
		}

		box.setTranslateX(150);
		box.setTranslateY(220);
		root.getChildren().add(box);

		stage.setScene(new Scene(root, 550, 300));
		stage.show();
	}
}

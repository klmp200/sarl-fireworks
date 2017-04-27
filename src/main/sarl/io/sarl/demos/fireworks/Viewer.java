package io.sarl.demos.fireworks;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Viewer extends Application {
	
	private FXMLLoader loader;

	@Override
	public void start(Stage stage) throws Exception {
		URL location = getClass().getResource("viewer.fxml");
		loader = new FXMLLoader();
		loader.setLocation(location);
		loader.setBuilderFactory(new JavaFXBuilderFactory());
		Parent root = (Parent) loader.load(location.openStream());
		Scene scene = new Scene(root);
		stage.setTitle("Firewoks sarl animation");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		super.stop();
		((FXMLViewerController) loader.getController()).cleanExit();
	}
	
}
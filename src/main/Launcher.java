package main;

import java.net.URL;
import java.sql.Connection;

import controller.MainScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * GS Marketing Programming ExampleBy Trenton Goins
 * Purpose: To Encode, Decode, and manipulate DNA information to and from strings of ASCII characters.
 * Makes use of JavaFX for the front-end
 *  @author Trent
*/

public class Launcher extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 *  The program's start function. Will Initialize a JavaFX screen controlled by the MainScreenController. 
	 *  This screen will contain everything necessary to achieve the 5 goals listed on the assignment's documentation.
	 */
	@Override
	public void start(Stage stage) throws Exception {
		
		//Load the FXML file and set the MainScreenController as it's controller
		URL fxmlFile = this.getClass().getResource("/view/MainView.fxml");
		FXMLLoader loader = new FXMLLoader(fxmlFile);
		MainScreenController controller = new MainScreenController();
		loader.setController(controller);
		
		GridPane rootNode = loader.load();
		Scene scene = new Scene(rootNode, 800, 600);
		
		stage.setTitle("GS Marketing Programming Example by Trenton Goins");
		stage.setScene(scene);
		stage.show();
		
	}
	
	@Override
	public void init() throws Exception {
		super.init();
	}

}

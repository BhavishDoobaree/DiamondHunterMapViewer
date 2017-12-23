package com.neet.DiamondHunter.Main;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenuController {
	
	Stage primaryStage= new Stage();
	private static JFrame window;

	
	// Event Listener on Button.onAction
	@FXML
	public void rungame(ActionEvent event) {
		System.out.println("Run game clicked");
		window = new JFrame("Diamond Hunter");	
		
		window.add(new GamePanel());
		
		window.setResizable(false);
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

	}
	
	public static JFrame getWindow(){
		return window;
	}
	
	@FXML
	public void mapviewer() throws Exception{
		
		Parent root = FXMLLoader.load(getClass().getResource("../MapViewerController/GUILayout.fxml"));
        primaryStage.setTitle("Edit Map");        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
	
}

}

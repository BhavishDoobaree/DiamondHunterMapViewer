package com.neet.DiamondHunter.MapViewer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MapEditor extends Application{ //this is the main class. run me to get access to tile map editor

	public static void main (String[] args)
	{
		Application.launch();
	}
	
	
	@Override
	public void start(Stage runstage) throws Exception {
		// TODO Auto-generated method stub
		
		Parent app = FXMLLoader.load(getClass().getResource("GUILayout.fxml"));
		runstage.setTitle("Tile Map Editor");
		runstage.setScene(new Scene(app, 950, 650)); //review me set dimensions here
		runstage.show();
		runstage.setResizable(true);
	}
	
	
	
	

}

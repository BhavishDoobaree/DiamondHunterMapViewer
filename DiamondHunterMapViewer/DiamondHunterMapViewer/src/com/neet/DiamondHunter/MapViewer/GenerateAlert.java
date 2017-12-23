package com.neet.DiamondHunter.MapViewer;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GenerateAlert {

	   public static void show(String title, String message){
	        Stage alert = new Stage();

	        alert.initModality(Modality.APPLICATION_MODAL);
	        alert.setTitle(title);
	        alert.setMinWidth(300);
	        alert.setMinHeight(80);

	        Label label = new Label();
	        label.setText(message);
	        Button closeButton = new Button("Ok");
	        closeButton.setOnAction(e -> alert.close());

	        VBox layout = new VBox();
	        layout.getChildren().addAll(label,closeButton);
	        layout.setAlignment(Pos.CENTER);

	        Scene alert_scene = new Scene(layout);
	        alert.setScene(alert_scene);
	        alert.showAndWait();
	    }
	
}

package com.neet.DiamondHunter.MapViewer;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;


public class GUIController implements Initializable{
	
	// All Variables 
    private int axeX=26,axeY=37,boatX=12,boatY=4;
    public static int Xaxe=416,Yaxe=592,Xboat=192,Yboat=64;
    int select=0;
    boolean boolboat=true,boolaxe=true;

    private int[][] map;
    private int tileSize=16;
    private int numRows;
    private int numCols;

    private Image[][] tiles;
    private Image[] item;
    public Image image;
    private int numTilesAcross;

    @FXML
    public Canvas canvas;

    @FXML
    private TextField axex;

    @FXML
    private Button save;
    
    @FXML
    private Button back;

    @FXML
    private TextField axey;

    @FXML
    private TextField boatx;

    @FXML
    private TextField boaty;

    @FXML
    private Button boat;

    @FXML
    private Button axe;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        

       /* Alert saved = new Alert(Alert.AlertType.INFORMATION);
        saved.setTitle("Axe and Boat");
        saved.setHeaderText("Changes has been saved.");*/

        GraphicsContext mapini = canvas.getGraphicsContext2D();
        initT("/Tilesets/testtileset.gif");
        initI("/Sprites/items.gif");
        initM("/Maps/testmap.map");

        // Initial Map and Items Display
        draw(mapini);
        mapini.drawImage(
                item[0],
                Yboat,Xboat
        );
        mapini.drawImage(
                item[1],
                Yaxe,Xaxe
        );
        
        // default
        
        axey.setText(Integer.toString(Xaxe/16));  //review me
        axex.setText(Integer.toString(Yaxe/16));
        boaty.setText(Integer.toString(Xboat/16));
        boatx.setText(Integer.toString(Yboat/16));

        //Event handling and objects on GUI

        boat.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                select=1;
            }
        });

        axe.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                select=0;
            }
        });

        canvas.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent e) {
                GraphicsContext mapelement = canvas.getGraphicsContext2D();
                if(select==0){
                    draw(mapelement);
                    if(boolboat){
                        mapelement.drawImage(
                                item[0],
                                Yboat,Xboat
                        );
                    }
                    if(!boolboat){
                        mapelement.drawImage(
                                item[0],
                                boatX*16,boatY*16
                        );
                    }
                    axeX=(int)e.getX()/16;
                    axeY=(int)e.getY()/16;
                    axex.setText(Integer.toString(axeX));
                    axey.setText(Integer.toString(axeY));
                    boolaxe=false;
                    mapelement.drawImage(
                            item[1],
                            axeX*16,axeY*16
                    );
                }
                else{
                    draw(mapelement);
                    if(boolaxe){
                        mapelement.drawImage(
                                item[1],
                                Yaxe,Xaxe
                        );
                    }
                    if(!boolaxe) {
                        mapelement.drawImage(
                                item[1],
                                axeX * 16, axeY * 16
                        );
                    }
                    boatX=(int)e.getX()/16;
                    boatY=(int)e.getY()/16;
                    boatx.setText(Integer.toString(boatX));
                    boaty.setText(Integer.toString(boatY));
                    boolboat=false;
                    mapelement.drawImage(
                            item[0],
                            boatX*16,boatY*16
                    );
                }
            }
        });

        save.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
       
               try{
                   if(boolaxe==true && boolboat==true){
                       InvalidPlace(axeY,axeX,boatY,boatX);}
                   if(boolaxe==false && boolboat==false){
                       InvalidPlace(axeX,axeY,boatX,boatY);}
                   if(boolaxe==true && boolboat==false){
                       InvalidPlace(axeY,axeX,boatX,boatY);}
                   if(boolaxe==false && boolboat==true){
                       InvalidPlace(axeX,axeY,boatY,boatX);}

                   if(boolaxe == false){
                   Xaxe=axeY*16;
                   Yaxe=axeX*16;
                   }
                   if(boolboat == false){
                   Xboat=boatY*16;
                   Yboat=boatX*16;
                   }
                   saved.setContentText("Axe (x,y)  : "+Yaxe/16+" "+Xaxe/16+"\nBoat (x,y) : "+Yboat/16+" "+Xboat/16 +
                		   "\n\n Click on Exit if you are done editing the map. ");
                   saved.showAndWait();
               }catch(ExceptionHandling e){
                Alerts.display("Alert",e.exceptionmessage);
               }

            }
            
            //makes sure that items are not placed in invalid places

            private void InvalidPlace(int axeX, int axeY, int boatX, int boatY) throws ExceptionHandling {
                // TODO Auto-generated method stub
            	if((axeX*16)==Xaxe && (axeY*16)==Yaxe && (boatX*16)==Xboat && (boatY*16)==Yboat){
            		throw new ExceptionHandling("No changes made to map. Press Exit");
            	}
                if(map[axeY][axeX] == 20 || map[axeY][axeX]==21){
                    throw new ExceptionHandling("Invalid Axe Placement Detected. Item cannot be placed on tree.");
                }
                if(map[axeY][axeX]==22){
                    throw new ExceptionHandling("Invalid Axe Placement Detected. Item cannot be placed on water.");
                }
                if(map[boatY][boatX] == 20 || map[boatY][boatX]==21){
                    throw new ExceptionHandling("Invalid Boat Placement Detected. Item cannot be placed on tree.");
                }
                if(map[boatY][boatX]==22){
                    throw new ExceptionHandling("Invalid Boat Placement Detected. Item cannot be placed on water.");
                }
            }
        });
    }
    
    @FXML
    public void back() throws Exception{
        Scene scene = back.getScene();
        Stage currentscene = (Stage)scene.getWindow();
        currentscene.hide();
    }

    //////////////////////////////////////////////////
    // Methods to extract images from resources
    // also to load tiles and map, and draw images in respective position according to map.map
    public void initI(String s) {
        Image setTile = new Image(s);
        item = new Image[2];
        for (int col = 0; col < 2; col++) {
            item[col] = new WritableImage(
                    setTile.getPixelReader(),
                    col * tileSize,
                    16,

                    tileSize,
                    tileSize);
        }
    }
    public void initT(String s) {

        try {

            Image setTile=new Image(s);
            numTilesAcross = (int) setTile.getWidth() / tileSize;
            tiles = new Image[2][numTilesAcross];

            for(int col = 0; col < numTilesAcross; col++) {
                tiles[0][col] = new WritableImage(
                        setTile.getPixelReader(),
                        col * tileSize,
                        0,
                        tileSize,
                        tileSize);
                tiles[1][col] = new WritableImage(
                        setTile.getPixelReader(),
                        col * tileSize,
                        tileSize,
                        tileSize,
                        tileSize);
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
    public void initM(String s) {

        try {

            InputStream in = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(in)
            );

            numCols = Integer.parseInt(br.readLine());
            numRows = Integer.parseInt(br.readLine());
            map = new int[numRows][numCols];

            String delims = "\\s+";
            for(int row = 0; row < numRows; row++) {
                String line = br.readLine();
                String[] tokens = line.split(delims);
                for(int col = 0; col < numCols; col++) {
                    map[row][col] = Integer.parseInt(tokens[col]);
                }
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
    public void draw(GraphicsContext g) {

        for(int row = 0; row < 40; row++) {

            if(row >= numRows) break;

            for(int col = 0; col < 40; col++) {

                if(col >= numCols) break;
                if(map[row][col] == 0) continue;

                int rc = map[row][col];
                int r = rc / numTilesAcross;
                int c = rc % numTilesAcross;

                g.drawImage(
                        tiles[r][c],
                        col * tileSize,
                        row * tileSize
                );

            }

        }

    }

    //////////////////////////////////////////////////

    
}


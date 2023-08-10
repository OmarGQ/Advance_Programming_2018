/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author danie
 */
public class Examen1 extends Application {
    private boolean ban = false;
    public HBox hb, hb2;
    public Button btn, btn2;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Examen");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);//crea una tabla
        grid.setHgap(10); //separacion entre celdas horizontal
        grid.setVgap(10);//separacion de celdas en vertical
        grid.setPadding(new Insets(25,25,25,25));//separacion de tabla y otros elementos
        
        btn = new Button("ON");// crea boton
        btn.setId("on");//asigna id al objeto
        btn.setPrefSize(100, 50);
        hb = new HBox(10);hb.setAlignment(Pos.CENTER_LEFT);// es una caja que nos ayuda a posicionar  los objetos dentro de la tabla
        hb.getChildren().add(btn);// agrega el boton al box
        grid.add(hb, 0, 0);
        btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                //accion.setFill(Color.FIREBRICK);
                if(ban == false){
                    btn.setText("OFF");
                    btn.setId("off");
                    btn2.setText("ON");
                    btn2.setId("on");
                    ban = true;
                }else{
                    btn.setText("ON");
                    btn.setId("on");
                    btn2.setText("OFF");
                    btn2.setId("off");
                    ban = false;
                }
            }
            
        });
        
        btn2 = new Button("OFF");// crea boton
        btn2.setId("off");
        btn2.setPrefSize(100, 50);
        hb2 = new HBox(10);hb2.setAlignment(Pos.CENTER_RIGHT);// es una caja que nos ayuda a posicionar  los objetos dentro de la tabla
        hb2.getChildren().add(btn2);// agrega el boton al box
        grid.add(hb2, 1, 0);
        btn2.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                //accion.setFill(Color.FIREBRICK);
                if(ban == false){
                    btn.setText("OFF");
                    btn.setId("off");
                    btn2.setText("ON");
                    btn2.setId("on");
                    ban = true;
                }else{
                    btn.setText("ON");
                    btn.setId("on");
                    btn2.setText("OFF");
                    btn2.setId("off");
                    ban = false;
                }
            }
            
        });
        
        Scene scene = new Scene(grid, 250, 100);//Tamaño del panel
        primaryStage.setScene(scene);//agregar escena
        scene.getStylesheets().add(getClass().getResource("Frame.css").toExternalForm());//agragr formato de estilo
        primaryStage.show();//mostrar
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

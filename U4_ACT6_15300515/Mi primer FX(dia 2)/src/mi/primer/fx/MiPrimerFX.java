/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mi.primer.fx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Alumnos
 */
public class MiPrimerFX extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        /*
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        */
        primaryStage.setTitle("Mi primer FX");//Titulo del frame
        GridPane grid = new GridPane();
        grid.setId("pane");//asigna id al objeto
        grid.setAlignment(Pos.CENTER);//crea una tabla
        grid.setHgap(10); //separacion entre celdas horizontal
        grid.setVgap(10);//separacion de celdas en vertical
        grid.setPadding(new Insets(25,25,25,25));//separacion de tabla y otros elementos
        Text sceneTitle = new Text("Bienvenido");//etiqueta que no puede cambiar
        sceneTitle.setId("welcome");//asigna id al objeto
        //sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        HBox hb2 = new HBox(10);hb2.setAlignment(Pos.BOTTOM_CENTER);// es una caja que nos ayuda a posicionar  los objetos dentro de la tabla
        hb2.getChildren().add(sceneTitle);
        grid.add(hb2, 0, 0, 2, 1);//(objeto, columnina, fila, Numero de columnas, Numero filas)
        Label username = new Label("Usuario");
        grid.add(username, 0, 1);
        TextField usertext = new TextField();
        grid.add(usertext, 1, 1);
        Label password = new Label("Password");
        grid.add(password, 0, 2);
        TextField passtext = new TextField();
        grid.add(passtext, 1, 2);
        Button btn = new Button("Entrar");// crea boton
        HBox hb = new HBox(10);hb.setAlignment(Pos.BOTTOM_RIGHT);// es una caja que nos ayuda a posicionar  los objetos dentro de la tabla
        hb.getChildren().add(btn);// agrega el boton al box
        grid.add(hb, 1, 4);
        final Text accion= new Text();
        accion.setId("accion");
        grid.add(accion, 1, 6);
        btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                //accion.setFill(Color.FIREBRICK);
                accion.setText("Entraste");
            }
            
        });
        Scene scene = new Scene(grid, 300, 275);//Tamaño del panel
        primaryStage.setScene(scene);//agregar escena
        scene.getStylesheets().add(getClass().getResource("MiEstilo.css").toExternalForm());//agragr formato de estilo
        primaryStage.show();//mostrar
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

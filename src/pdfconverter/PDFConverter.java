/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfconverter;

import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author TT
 */
public class PDFConverter extends Application {
    
    @Override
    public void start(final Stage primaryStage) {
        
        Label label = new Label("Select the pdf file");
        label.setTranslateY(20);
        Font font = Font.font("Verdana", FontWeight.EXTRA_BOLD, 25);
        label.setFont(font);
        
        
        Button sel = new Button();
        sel.setText("Select a file");
        sel.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
                fileChooser.getExtensionFilters().add(extFilter);
                File selectedFile = fileChooser.showOpenDialog(null);
                if(selectedFile != null){
                    Convertor con = new Convertor();
                    String sb = con.readData(selectedFile.getAbsolutePath());
                    System.out.println(sb);
                    if(!sb.contains("\\S"))
                        Toast.show("select proper doc file", primaryStage);
                    else{
                        boolean res = con.writeData(selectedFile.getAbsolutePath(), sb);
                        if(res)
                            Toast.show("Doc file created", primaryStage);
                        else
                            Toast.show("Error in creating doc file", primaryStage);
                    }
                }
                else{
                    System.out.println("error");
                }
            }
        });
        sel.setTranslateY(40);
        
        Button exit = new Button("Exit");
        exit.setOnAction(e->System.exit(0));
        exit.setTranslateY(40);
        
        VBox root = new VBox();
        root.getChildren().addAll(label,sel,exit);
        root.setAlignment(Pos.TOP_CENTER);
        root.setSpacing(10.0);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("PDFConvertor");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

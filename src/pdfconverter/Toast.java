/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfconverter;

import java.awt.event.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author TT
 */
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Toast {
    
    private static int TOAST_TIMEOUT = 800;
    
    private static Popup createPopup(final String message) {
        final Popup popup = new Popup();
        popup.setAutoFix(true);
        Label label = new Label(message);
        Font font = Font.font("Verdana", 16);
        label.setFont(font);
        popup.getContent().add(label);
        return popup;
    }

    public static void show(final String message, final Stage stage) {
        
           
        final Popup popup = createPopup(message);
        popup.setOnShown(e -> {
            popup.setX(stage.getX() + stage.getWidth() / 2 - popup.getWidth() / 2);
            popup.setY(stage.getY() + stage.getHeight() / 1.2 - popup.getHeight() / 2);
            });
            popup.show(stage);

        new Timeline(new KeyFrame(
                Duration.millis(TOAST_TIMEOUT),
                ae -> popup.hide())).play();
        }
    }
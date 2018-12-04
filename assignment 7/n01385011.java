import java.util.*;
import javafx.application.Application;
import javafx.beans.binding.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class n01385011 extends Application {
   Text text = new Text(50, 50, "Ronald Shevchenko");
   
   protected BorderPane getPane() {
      HBox paneForButtons = new HBox(20);
      Button btUp = new Button("Up");
      Button btDown = new Button("Down");
      paneForButtons.getChildren().addAll(btUp, btDown);
      paneForButtons.setAlignment(Pos.CENTER);
      
      BorderPane pane = new BorderPane();
      pane.setBottom(paneForButtons);
            
      Pane paneForText = new Pane();
      paneForText.getChildren().add(text);
      GridPane textVideoPane = new GridPane();
      textVideoPane.add(paneForText, 0, 0);
      textVideoPane.setStyle("-fx-border-color: green");
      pane.setCenter(textVideoPane);
           
      btUp.setOnAction(e -> text.setY(text.getY() - 10));
      btDown.setOnAction(e -> text.setY(text.getY() + 10));
               
      return pane;
  }

   @Override
   public void start(Stage main){
      Scene scene = new Scene(getPane(), 450, 200);
      main.setTitle("Assignment 7");
      main.setScene(scene);
      main.show();
   }
}

class BoldItalic extends n01385011 {
   @Override
   protected BorderPane getPane(){
      BorderPane pane = super.getPane();
      Font fontBoldItalic = Font.font("Times New Roman",
         FontWeight.BOLD, FontPosture.ITALIC, 20);
      Font fontBold = Font.font("Times New Roman",
         FontWeight.BOLD, FontPosture.REGULAR, 20);
      Font fontItalic = Font.font("Times New Roman",
         FontWeight.NORMAL, FontPosture.ITALIC, 20);
      Font fontNormal = Font.font("Times New Roman",
         FontWeight.NORMAL, FontPosture.REGULAR, 20);
         
      text.setFont(fontNormal);
      
      VBox paneForBoxes = new VBox(20);
      paneForBoxes.setPadding(new Insets(5, 5, 5, 5));
      CheckBox chkBold = new CheckBox("Bold");
      CheckBox chkItalic = new CheckBox("Italic");
      paneForBoxes.getChildren().addAll(chkBold, chkItalic);
      pane.setLeft(paneForBoxes);
      
      EventHandler<ActionEvent> handler = e -> {
         if (chkBold.isSelected() && chkItalic.isSelected()) {
            text.setFont(fontBoldItalic); // Both check boxes checked
         }
         else if (chkBold.isSelected()) {
            text.setFont(fontBold); // The Bold check box checked
         }
         else if (chkItalic.isSelected()) {
            text.setFont(fontItalic); // The Italic check box checked
         }
         else {
            text.setFont(fontNormal); // Both check boxes unchecked
         }
      };
      
      chkBold.setOnAction(handler);
      chkItalic.setOnAction(handler);
       
      return pane;
   }
} 
       
import java.util.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.*;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

class NameButtons extends Application {
   protected Text text = new Text(10, 10, "Ronald Shevchenko");
   GridPane textVideoPane = new GridPane();
   
   protected BorderPane getPane() {
      HBox paneForButtons = new HBox(20);
      Button btUp = new Button("Up");
      Button btDown = new Button("Down");
      paneForButtons.getChildren().addAll(btUp, btDown);
      paneForButtons.setAlignment(Pos.CENTER);
      paneForButtons.setPadding(new Insets(10, 10, 10, 10));
      
      BorderPane pane = new BorderPane();
      pane.setBottom(paneForButtons);
                
      Pane paneForText = new Pane();
      paneForText.getChildren().add(text);
      
      paneForText.setPadding(new Insets(10, 10, 10, 10));
      textVideoPane.setPadding(new Insets(10, 10, 10, 10));
      textVideoPane.setHgap(10);
      
      textVideoPane.add(paneForText, 0, 0);
      pane.setCenter(textVideoPane);
           
      btUp.setOnAction(e -> text.setY(text.getY() - 10));
      btDown.setOnAction(e -> text.setY(text.getY() + 10));
               
      return pane;
  }
  
  @Override
   public void start(Stage main){
      Scene scene = new Scene(getPane(), 950, 530);
      main.setTitle("Assignment 7 | Ronald Shevchenko | n01385011");
      main.setScene(scene);
      main.show();
   }

}

class BoldItalics extends NameButtons {
   @Override
   protected BorderPane getPane(){
      BorderPane pane = super.getPane();
      System.out.println(Font.getFamilies());
      Font fontBoldItalic = Font.font("Comic Sans MS",
         FontWeight.BOLD, FontPosture.ITALIC, 20);
      Font fontBold = Font.font("Comic Sans MS",
         FontWeight.BOLD, FontPosture.REGULAR, 20);
      Font fontItalic = Font.font("Comic Sans MS",
         FontWeight.NORMAL, FontPosture.ITALIC, 20);
      Font fontNormal = Font.font("Comic Sans MS",
         FontWeight.NORMAL, FontPosture.REGULAR, 20);
         
      text.setFont(fontNormal);
      
      VBox paneForBoxes = new VBox(20);
      paneForBoxes.setPadding(new Insets(10, 10, 10, 10));
      paneForBoxes.setAlignment(Pos.TOP_CENTER);
      CheckBox chkBold = new CheckBox("Bold");
      CheckBox chkItalic = new CheckBox("Italic");
      paneForBoxes.getChildren().addAll(chkBold, chkItalic);
      pane.setLeft(paneForBoxes);
      
      EventHandler<ActionEvent> handler = e -> {
         if (chkBold.isSelected() && chkItalic.isSelected()) {
            text.setFont(fontBoldItalic);
         }
         else if (chkBold.isSelected()) {
            text.setFont(fontBold);
         }
         else if (chkItalic.isSelected()) {
            text.setFont(fontItalic);
         }
         else {
            text.setFont(fontNormal);
         }
      };
      
      chkBold.setOnAction(handler);
      chkItalic.setOnAction(handler);
       
      return pane;
   }
}

class ColorButtons extends BoldItalics{
   @Override
   protected BorderPane getPane(){
      BorderPane pane = super.getPane();
      
      VBox paneRadio = new VBox(20);
      paneRadio.setPadding(new Insets(10, 10, 10, 10));
      paneRadio.setAlignment(Pos.TOP_CENTER);
      
      RadioButton rdPink = new RadioButton("Pink");
      RadioButton rdLavender = new RadioButton("Lavender");
      RadioButton rdYellow = new RadioButton("Yellow");
      paneRadio.getChildren().addAll(rdPink, rdLavender, rdYellow);
      pane.setRight(paneRadio);
      
      ToggleGroup group = new ToggleGroup();
      rdPink.setToggleGroup(group);
      rdLavender.setToggleGroup(group);
      rdYellow.setToggleGroup(group);
      
      rdPink.setOnAction(e -> {
         if (rdPink.isSelected()) {
            text.setFill(Color.web("#ffb6c1"));
         }
      });
      
      rdLavender.setOnAction(e -> {
         if (rdLavender.isSelected()) {
            text.setFill(Color.web("#e6e6fa"));
         }
      });
      
      rdYellow.setOnAction(e -> {
         if (rdYellow.isSelected()) {
            text.setFill(Color.web("#fdfd96"));
         }
      });

      return pane;
   }
} 

class BouncingRectangle extends ColorButtons {
   @Override
   protected BorderPane getPane(){
      BorderPane pane = super.getPane();
      BallPane ballPane = new BallPane();
      Slider slSpeed = new Slider();
      slSpeed.setMax(20);
      ballPane.rateProperty().bind(slSpeed.valueProperty());
      
      BorderPane ballBorder = new BorderPane();
      ballPane.setPrefHeight(100);
      ballPane.setPrefWidth(100);
      ballBorder.setCenter(ballPane);
      ballBorder.setBottom(slSpeed);
      pane.setTop(ballBorder);
      
      return pane;
   }
}

class BallPane extends Pane {
   public final double radiusX = 20, radiusY = 30;
   private double x = radiusX, y = radiusY;
   private double dx = 1, dy = 1;
   private Rectangle rectangle = new Rectangle(20, 30, 20, 30);
   private Timeline animation;
   
   public BallPane() {
      rectangle.setFill(Color.RED);       
      getChildren().add(rectangle);
      
      animation = new Timeline(
         new KeyFrame(Duration.millis(50), e -> moveBall()));
         animation.setCycleCount(Timeline.INDEFINITE);
         animation.play();
      }
      
      public void play() {
         animation.play();
      }
      
      public void pause() {
         animation.pause();
      }
      
      public void increaseSpeed() {
         animation.setRate(animation.getRate() + 0.1);
      }
      
      public void decreaseSpeed() {
         animation.setRate(
         animation.getRate() > 0 ? animation.getRate() - 0.1 : 0);
      }
      
      public DoubleProperty rateProperty() {
         return animation.rateProperty();
      }
      
      protected void moveBall() {
         // Check boundaries
         if (x+20 < radiusX || x > getWidth() - radiusY) {
            dx *= -1; // Change ball move direction
         }
         if (y+30 < radiusY || y > getHeight() - radiusY) {
            dy *= -1; // Change ball move direction
         }
         
         // Adjust ball position
         x += dx;
         y += dy;
         rectangle.setX(x);
         rectangle.setY(y);
      }
}

public class n01385011 extends BouncingRectangle{
   private static final String MEDIA_URL = 
   "https://www.unf.edu/~n01385011/catvid.mp4";
   
   @Override
   protected BorderPane getPane(){
      BorderPane pane = super.getPane();
      Media media = new Media(MEDIA_URL);
      MediaPlayer mediaPlayer = new MediaPlayer(media);
      MediaView mediaView = new MediaView(mediaPlayer);
      
      Button playButton = new Button(">");
      playButton.setOnAction(e -> {
         if(playButton.getText().equals(">")){
            mediaPlayer.play();
            playButton.setText("||");
         } else{
            mediaPlayer.pause();
            playButton.setText(">");
         }
      });
      
      Button rewindButton = new Button("<<");
      rewindButton.setOnAction(e -> mediaPlayer.seek(Duration.ZERO));
      
      Slider slVolume = new Slider();
      slVolume.setPrefWidth(150);
      slVolume.setMaxWidth(Region.USE_PREF_SIZE);
      slVolume.setMinWidth(30);
      slVolume.setValue(50);
      mediaPlayer.volumeProperty().bind(
         slVolume.valueProperty().divide(100));
      
      HBox hBox = new HBox(10);
      hBox.setAlignment(Pos.CENTER);
      hBox.getChildren().addAll(playButton, rewindButton,
         new Label("Volume"), slVolume);
         
      BorderPane videoPane = new BorderPane();
      videoPane.setCenter(mediaView);
      videoPane.setBottom(hBox);
      
      textVideoPane.add(videoPane, 2, 0);
      
      return pane;
   }
   
   public static void main(String[] args) {
      launch(args);
   }  
}
       
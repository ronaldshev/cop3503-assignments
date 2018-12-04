import java.util.*;
import javafx.application.Application;
import javafx.beans.binding.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class n01385011 extends Application {
   @Override
   public void start(Stage main){
      ClockPane clock = new ClockPane();
      String timeString = clock.getHour() + ":" + clock.getMinute()
         + ":00";
      Label CurrentTime = new Label(timeString);

      BorderPane clocked = new BorderPane();
      clocked.setCenter(clock);
      clocked.setBottom(CurrentTime);
      clocked.setAlignment(CurrentTime, Pos.CENTER);
      
      Hangman hangman = new Hangman();
      Fans fans = new Fans();
      
      GridPane pane = new GridPane();
      pane.setMaxSize(720, 256);
      pane.setMinSize(720, 256);
      StackPane mainScene = new StackPane(pane);
      NumberBinding maxScale = Bindings.min(mainScene.widthProperty().divide(720),
      mainScene.heightProperty().divide(256));
      pane.scaleXProperty().bind(maxScale);
      pane.scaleYProperty().bind(maxScale);
      pane.add(hangman, 0, 0);
      pane.add(clocked, 1, 0);
      pane.add(fans, 2,0);

      Scene scene = new Scene(mainScene, 720, 256);
      main.setTitle("Ronald Shevchenko n01385011");
      main.setScene(scene);
      main.show();
   }
}

class Hangman extends Pane {
   //8 lines, one circle, one stand
   
   public Hangman(){
      drawLines();
      drawCircles();
   }
   
   public void drawLines(){
      Line post = new Line(65, 215, 65, 50);
      Line post2 = new Line(65, 50, 150, 50);
      Line noose = new Line(150, 50, 150, 80);
      Line body = new Line(150, 90, 150, 160);
      Line leftArm = new Line(150, 90, 110, 120);
      Line rightArm = new Line(150, 90, 190, 120);
      Line leftLeg = new Line(150, 160, 120, 190);
      Line rightLeg = new Line(150, 160, 180, 190);
      
      getChildren().addAll(post, post2, noose, body, leftArm, rightArm, leftLeg, rightLeg);
   }
   
   public void drawCircles(){ 
      Circle head = new Circle(150, 90, 16);
      head.setStroke(Color.BLACK);
      head.setFill(Color.WHITE);
      
      Arc stand = new Arc(65, 230, 40, 15, 0, 180);
      stand.setFill(Color.TRANSPARENT);
      stand.setStroke(Color.BLACK);
      stand.setType(ArcType.OPEN);
            
      getChildren().addAll(head, stand);

   }
}

class ClockPane extends Pane {
   private int hour;
   private int minute;

   private double w = 250, h = 250;
   
   public ClockPane() {
      setTime();
      paintClock();
   }
   
   public int getHour(){
      return hour;
   }
   
   public int getMinute(){
      return minute;
   }
    public void setTime(){
      Random random = new Random();
      this.hour = random.nextInt((12-1) + 1) + 1;
      this.minute = random.nextInt((60-1) + 1) + 1;
                  
      paintClock();
   }
   
   protected void paintClock(){
      double clockRadius = Math.min(w, h) * 0.8 * 0.5;
      double centerX = w / 2;
      double centerY = h / 2;
      
      Circle circle = new Circle(centerX, centerY, clockRadius);
      circle.setFill(Color.WHITE);
      circle.setStroke(Color.BLACK);
      Text t1 = new Text(centerX - 5, centerY - clockRadius + 12, "12");
      Text t2 = new Text(centerX - clockRadius + 3, centerY + 5, "9");
      Text t3 = new Text(centerX + clockRadius - 10, centerY + 3, "3");
      Text t4 = new Text(centerX - 3, centerY + clockRadius - 3, "6");
      
      double mLength = clockRadius * 0.65;
      double xMinute = centerX + mLength *
      Math.sin(minute * (2 * Math.PI / 60));
      double minuteY = centerY - mLength *
      Math.cos(minute * (2 * Math.PI / 60));
      Line mLine = new Line(centerX, centerY, xMinute, minuteY);
      mLine.setStroke(Color.BLUE);
      
      double hLength = clockRadius * 0.5;
      double hourX = centerX + hLength *
      Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
      double hourY = centerY - hLength *
      Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
      Line hLine = new Line(centerX, centerY, hourX, hourY);
      hLine.setStroke(Color.GREEN);
      
      Text name = new Text(centerX-45, centerY+4, "Ronald Shevchenko");
      
      getChildren().clear();
      getChildren().addAll(circle, t1, t2, t3, t4, mLine, hLine, name);
   }
}
   
class Fans extends Pane {

   public Fans(){
      drawCircles();
      drawArcs();
  }

      
   
  /* for (int i=0; i < 2; i++){
      for (int j = 0; j < 2; j++){
        Circle fan = new Circle();
        fan.setCenterX(x);
        fan.setCenterY(y);
        fan.setRadius(RADIUS);
        fan.setStroke(Color.BLACK);
        fan.setFill(Color.WHITE);
        fan.setStrokeWidth(3.0);

        fanPane.getChildren().add(fan);

        for (int k =30; k < 360; k+= 90){
          Arc arc = new Arc(x, y, RADIUS - 15, RADIUS - 15, k, 30);
          arc.setStroke(Color.BLACK);
          arc.setFill(Color.BLACK);
          arc.setType(ArcType.ROUND);
          fanPane.getChildren().add(arc);
        }
       x += RADIUS * 2 + GAP;
      }
      
      x = RADIUS + GAP;
      y += RADIUS * 2 + GAP;
    } */

   
   public void drawCircles(){
      Circle fan1 = new Circle(75, 65, 55, Color.WHITE);
      Circle fan2 = new Circle(200, 65, 55, Color.WHITE);
      Circle fan3 = new Circle(75, 185, 55, Color.WHITE);
      Circle fan4 = new Circle(200, 185, 55, Color.WHITE);
      fan1.setStroke(Color.BLACK);
      fan2.setStroke(Color.BLACK);
      fan3.setStroke(Color.BLACK);
      fan4.setStroke(Color.BLACK);
      getChildren().addAll(fan1, fan2, fan3, fan4);
   }
   
   public void drawArcs(){
      for (int j = 0; j < 4; j++){
         for(int k = 30; k < 360; k+= 90){
            Arc blade = new Arc(200, 65, 50, 50, k, 30);
            blade.setType(ArcType.ROUND);
            getChildren().add(blade);
         }
       }
       
       for (int j = 0; j < 4; j++){
         for(int k = 30; k < 360; k+= 90){
            Arc blade = new Arc(75, 65, 50, 50, k, 30);
            blade.setType(ArcType.ROUND);
            getChildren().add(blade);
         }
       }
       
       for (int j = 0; j < 4; j++){
         for(int k = 30; k < 360; k+= 90){
            Arc blade = new Arc(200, 185, 50, 50, k, 30);
            blade.setType(ArcType.ROUND);
            getChildren().add(blade);
         }
       }
       
       for (int j = 0; j < 4; j++){
         for(int k = 30; k < 360; k+= 90){
            Arc blade = new Arc(75, 185, 50, 50, k, 30);
            blade.setType(ArcType.ROUND);
            getChildren().add(blade);
         }
       }
   }
}
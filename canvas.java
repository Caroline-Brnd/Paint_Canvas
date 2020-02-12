import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Souris extends Application {
	private ComboBox <String> cbColor = new ComboBox <String> ();
	private ComboBox <String> cbShape = new ComboBox <String> ();
	private Color pinceau=Color.GREEN;
	int t= 30;
	private Spinner <Integer> cbSize = new Spinner<Integer>(1,100,t);

	public static void main (String[] args){
		launch (args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Paint TP7");
	    cbColor.getItems().addAll("Vert", "Bleu","Cyan","Rouge","Orange","Bordeaux");
	    cbColor.setValue("Vert");
	    cbShape.getItems().addAll("Rond","Carre","Etoile");
	    cbShape.setValue("Rond");
	    Canvas canvas = new Canvas(700,700);
	    GraphicsContext gc = canvas.getGraphicsContext2D();
	    gc.setFill(Color.GREEN);
	    
	    Image image = new Image("file:images-canvas.png");
	    ImageView iv = new ImageView(image);
	    iv.setImage(image);
	    iv.setFitHeight(700);
	    iv.setFitWidth(700);
	    Button saveBtn = new Button("Save");
		saveBtn.setOnAction(e -> saveToFile(image));

	    canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>(){
				@Override
	            public void handle(MouseEvent e) {            
	              Color c;
	              int value = (Integer) cbSize.getValue();
	              switch(cbColor.getValue ()){
	              case "Vert" : gc.setFill(Color.GREEN);
			      break;  
		          case "Bleu" : gc.setFill(Color.BLUE);
			      break;
		          case "Rouge" :  gc.setFill(Color.RED);
			      break;
		          case "Bordeaux" : gc.setFill(Color.BROWN);
		          break;
		          case "Cyan" : gc.setFill(Color.CYAN);
		          break;
		          default : gc.setFill(Color.ORANGE);
	              }
	              switch(cbShape.getValue ()){
	              case "Rond" : gc.fillOval(e.getX(), e.getY(), value, value);
			      break;
	              case "Carre" : gc.fillRect(e.getX(), e.getY(), value, value);
	              default :
	              }
				}
	    });

	    Group root = new Group();
	    HBox hbox = new HBox();
	    hbox.getChildren().addAll(cbShape, cbColor, cbSize, saveBtn);
	    BorderPane borderPane = new BorderPane();
	    borderPane.setTop(hbox);     
	    Pane pane = new Pane();
	    pane.getChildren().add(iv);
	    pane.getChildren().add(canvas);
	    borderPane.setCenter(pane);    
	    root.getChildren().add(borderPane);
	    
	    primaryStage.setScene(new Scene(root,700,700));
	    primaryStage.show();
	}
	
	public static void saveToFile(Image image) {
	    File outputFile = new File("C:/JavaFx/");
	    BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
	    try {
	      ImageIO.write(bImage, ".png", outputFile);
	    } catch (IOException e) {
	      throw new RuntimeException(e);
	    }
	  }
}
package application;
	
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;




public class Main extends Application {
    @FXML
    public TableColumn<Book, String> TitCol;
    @FXML
    public TableColumn<Book, String> AutCol;
    @FXML
    public TableColumn<Book, String> PgntCol;
    @FXML
    public TableColumn<Book, String> PubCol;
    @FXML
    public TableColumn<Book, String> GentCol;
    
	private Locale locale = new Locale("pl");
    @Override
    public void start(Stage primaryStage) throws Exception{
    	
        Parent root = FXMLLoader.load(getClass().getResource("View.fxml"),ResourceBundle.getBundle("bundles.MyBundle", locale));
        primaryStage.setTitle("PlecakSolver");
        primaryStage.setScene(new Scene(root, 660, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

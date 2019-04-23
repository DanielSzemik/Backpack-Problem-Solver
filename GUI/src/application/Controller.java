package application;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import lab1.BruteForce;
import lab1.Greedy;
import lab1.Instance;
import lab1.Item;
import lab1.Solution;

public class Controller implements Initializable{

    @FXML
    public String BruteForce,Greedy;
    @FXML
    public ComboBox<String> PickAlg;
    @FXML
    public Button DodajPrzedmiot, Execute;
    @FXML
    public TextField ItValue, ItWeight, MaxWeight;
    @FXML
    public Label ItemNumber, Time; //, Result;
    @FXML
    public TableView<Item> tableview;
    @FXML
    public TableColumn<Item, Integer> IDCol;
    @FXML
    public TableColumn<Item, Float> ValueCol;
    @FXML
    public TableColumn<Item, Integer> WeightCol;
    @FXML
    public ScrollPane scrollpane;
    @FXML
    public AnchorPane MainPane;
    @FXML
    public ImageView Polska, USA, UK;



    ArrayList<Item> Items = new ArrayList<Item>();
    Instance instance;
    int ItemNr = 1;
    String result = "";
    private ResourceBundle bundle;
    private Locale locale;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	bundle = resources;
    	PickAlg.setItems(FXCollections.observableArrayList(
    			bundle.getString("BruteForce"),
    			bundle.getString("GreedyAlg")));

        IDCol.setCellValueFactory(new PropertyValueFactory<Item, Integer>("nr"));
        ValueCol.setCellValueFactory(new PropertyValueFactory<Item, Float>("value"));
        WeightCol.setCellValueFactory(new PropertyValueFactory<Item, Integer>("weight"));
        scrollpane.setContent(new Text(bundle.getString("Result")));
        initClock();
        
    }

    public void DodajPrzedmiotClicked(MouseEvent mouseEvent) {
    	try {
    	Items.add(new Item (Float.parseFloat(ItValue.getText()),Integer.parseInt(ItWeight.getText()),ItemNr));
    	} catch (Exception e) {
	        e.printStackTrace();
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
    		alert.setHeaderText(bundle.getString("BadParam"));
    		alert.showAndWait();
    		return;
    	}
    	tableview.getItems().add(Items.get(Items.size() - 1));
    	ItemNumber.setText(bundle.getString("NrOfItems") + ItemNr);
    	ItemNr++;
   	} 
    public void ExecuteClicked(MouseEvent mouseEvent) {
    	try {
    		instance = new Instance(Items, Integer.parseInt(MaxWeight.getText()));
    	}catch (Exception e) {
	        e.printStackTrace();
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
    		alert.setHeaderText(bundle.getString("BadParam"));
    		alert.showAndWait();
    		return;
    	}
    	if(PickAlg.getValue().equals(bundle.getString("BruteForce")))
    	{
    		BruteForce BF = new BruteForce(instance.getlist(),instance.getpojemnosc());
    		Solution solution = new Solution(BF.getrozw());
    		result = solution.ResultPL(instance.getlist());
    		if(bundle.getString("Lang").equals("enUS") || bundle.getString("Lang").equals("enGB"))
    		{
    			result = result.replace("Ilość przedmiotów","Number of items");
    			result = result.replace("Suma wag","Total weight");
    			result = result.replace("Suma wartośći","Total value");
    			result = result.replace("Wartość","Value");
    			result = result.replace("Waga","Weight");
    			result = result.replace("Przedmiot","Item");
    		}
    			
    		scrollpane.setContent(new Text(result));
    	}
    	if(PickAlg.getValue().equals(bundle.getString("GreedyAlg")))
    	{
    		Greedy GR = new Greedy(instance.getlist(),instance.getpojemnosc());
    		Solution solution = new Solution(GR.getrozw());
    		result = solution.ResultPL(instance.getlist());
    		if(bundle.getString("Lang").equals("enUS") || bundle.getString("Lang").equals("enGB"))
    		{
    			result = result.replace("Ilość przedmiotów","Number of items");
    			result = result.replace("Suma wag","Total weight");
    			result = result.replace("Suma wartośći","Total value");
    			result = result.replace("Wartość","Value");
    			result = result.replace("Waga","Weight");
    			result = result.replace("Przedmiot","Item");
    		}
    		scrollpane.setContent(new Text(result));
    	}
    	
    }
    
    public void PolskaClicked(MouseEvent mouseEvent)
    {
    	locale = new Locale("PL");
    	Scene scene = MainPane.getScene();
    	try {
    		scene.setRoot(FXMLLoader.load(getClass().getResource("View.fxml"),ResourceBundle.getBundle("bundles.MyBundle", locale)));
    		return;
    	}catch (Exception e) {
    		e.printStackTrace();
    	}

    }
    
    public void USAClicked(MouseEvent mouseEvent)
    {
    	locale = new Locale("enUS");
    	Scene scene = MainPane.getScene();
    	try {
    		scene.setRoot(FXMLLoader.load(getClass().getResource("View.fxml"),ResourceBundle.getBundle("bundles.MyBundle", locale)));
    		return;
    	}catch (Exception e) {
    		e.printStackTrace();
    	}

    }
    
    public void UKClicked(MouseEvent mouseEvent)
    {
    	locale = new Locale("enGB");
    	Scene scene = MainPane.getScene();
    	try {
    		scene.setRoot(FXMLLoader.load(getClass().getResource("View.fxml"),ResourceBundle.getBundle("bundles.MyBundle", locale)));
    		return;
    	}catch (Exception e) {
    		e.printStackTrace();
    	}

    }
    
    
    
    private void initClock() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(bundle.getString("TimeFormat"));
            Time.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }


 }

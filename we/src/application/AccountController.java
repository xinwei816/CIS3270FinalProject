package application;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class AccountController implements Initializable {
	
	
	// this is from customer
	@FXML
	private TextField txtfid1;
	@FXML
	private Label status1;
	@FXML
	private TableView<Flight> tableflight1;
	@FXML
	private TableColumn<Flight,String> fi1;
	@FXML
	private TableColumn<Flight,String> or1;
	@FXML
	private TableColumn<Flight,String> des1;
	@FXML
	private TableColumn<Flight,String> dm1;
	@FXML
	private TableColumn<Flight,String> dd1;
	@FXML
	private TableColumn<Flight,String> dy1;
	@FXML
	private TableColumn<Flight,String> dt1;
	@FXML
	private TableColumn<Flight,String> ad1;
	@FXML
	private TableColumn<Flight,String> at1;
	@FXML
	private TableColumn<Flight,String> pn1;
	
	@FXML
	private Label userlabel4;
	String user2;
	
	PreparedStatement prep;
	 
	ObservableList<Flight> data= FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location,ResourceBundle resource) {
		
		fi1.setCellValueFactory(new PropertyValueFactory<>("flightId"));
		or1.setCellValueFactory(new PropertyValueFactory<>("origin"));
		des1.setCellValueFactory(new PropertyValueFactory<>("destination"));
		dm1.setCellValueFactory(new PropertyValueFactory<>("departingMonth"));
		dd1.setCellValueFactory(new PropertyValueFactory<>("departingDate"));
		dy1.setCellValueFactory(new PropertyValueFactory<>("departingYear"));
		dt1.setCellValueFactory(new PropertyValueFactory<>("departingTime"));
		ad1.setCellValueFactory(new PropertyValueFactory<>("arrivingDate"));
		at1.setCellValueFactory(new PropertyValueFactory<>("arrivingTime"));
		pn1.setCellValueFactory(new PropertyValueFactory<>("passenger"));
		
	}
	
	

	
	//this methods is for customer
public void checkFlightOnAccount(ActionEvent event) throws Exception {
		
	data.clear();
	
	
	
	Driver 	driver= new Driver();
	Connection connection= Driver.Connector();
		
	try {
		
		
		Statement statement = connection.createStatement();
			
			String sql="select * from flights, account where flights.flightId= account.flightId AND account.username='"+user2+"';";
			
			ResultSet resultSet= statement.executeQuery(sql);
			while(resultSet.next()) {
			
			data.add(new Flight(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(9),resultSet.getString(10),resultSet.getString(11)));
			}
			
		}
		catch(Exception e) {
			status1.setText("System Error");
		}
		
		
		tableflight1.setItems(data);
		
		
		}
	
public void Signup(ActionEvent event) throws Exception {
		
		try {
		Driver 	driver= new Driver();
		Connection connection= Driver.Connector();
		
		
		
		String sql="INSERT INTO account VALUES('"+user2+"','"+txtfid1.getText()+"')";
		
				
		Statement statement=connection.createStatement();
		statement.executeUpdate(sql);
		
		
		status1.setText("Reserve Success");
		}
		catch (Exception e) {
			status1.setText("Reserve Failed");
		}
					
		
		
	}

//this method is for customer account
	public void DeleteFlight (ActionEvent event) throws Exception {
		
		try {
		Driver 	driver= new Driver();
		Connection connection= Driver.Connector();
		
		
		
		Statement statement = connection.createStatement();
			
			String sql="DELETE from account where flightId='"+txtfid1.getText()+"' and username='"+user2+"' ;";
			
			int result= statement.executeUpdate(sql);
						
			status1.setText("Deleted Successfully");
			
		}
		catch(Exception e) {
			status1.setText("System Error");
		}
		
		
		tableflight1.setItems(data);
		
		
		}

	
	
	
	
	
	
	public void myFunction(String text) {
		userlabel4.setText(text);
		user2=userlabel4.getText();
	}
	
	public void backToMainmenu(ActionEvent event) throws Exception {
		
		
		
		FXMLLoader loader= new FXMLLoader(getClass().getResource("/application/Mainmenu.fxml"));
		Parent tableViewParent4 = loader.load();
		
		//* passing username to next scene
		
		MainmenuController m=loader.getController();
		m.myFunction(user2);
		
		Scene tableViewScene4 = new Scene(tableViewParent4);
		
		
		
		Stage window4= (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window4.setScene(tableViewScene4);
		window4.show();
		
	
	
	}
	
	

	
}
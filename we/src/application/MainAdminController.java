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


public class MainAdminController implements Initializable {
	
	@FXML
	private Label userlabel1;
	String user;
	@FXML
	private Label statuslabel;
	@FXML
	private TextField txtFid;
	@FXML
	private TextField origins;
	@FXML
	private TextField destin;
	@FXML
	private TextField depM;
	@FXML
	private TextField depD;
	@FXML
	private TextField depY;
	@FXML
	private TableView<Flight> tableFlight;
	@FXML
	private TableColumn<Flight,String> cFid;
	@FXML
	private TableColumn<Flight,String> cOrgins;
	@FXML
	private TableColumn<Flight,String> cDestin;
	@FXML
	private TableColumn<Flight,String> cDepM;
	@FXML
	private TableColumn<Flight,String> cDepD;
	@FXML
	private TableColumn<Flight,String> cDepY;
	@FXML
	private TableColumn<Flight,String> cDepT;
	@FXML
	private TableColumn<Flight,String> cArrD;
	@FXML
	private TableColumn<Flight,String> cArrT;
	@FXML
	private TableColumn<Flight,String> cPNum;
	
	PreparedStatement prep;
	 
	ObservableList<Flight> data= FXCollections.observableArrayList();
	
	
	@Override
	public void initialize(URL location,ResourceBundle resource) {
		
		
		try {
			Driver 	driver= new Driver();
			Connection connection= Driver.Connector();
			Statement statement = connection.createStatement();
			String sql="select * from flights";
			
			ResultSet resultSet= statement.executeQuery(sql);
			while(resultSet.next()) {
			
			data.add(new Flight(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(9),resultSet.getString(10),resultSet.getString(11)));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		cFid.setCellValueFactory(new PropertyValueFactory<>("flightId"));
		cOrgins.setCellValueFactory(new PropertyValueFactory<>("origin"));
		cDestin.setCellValueFactory(new PropertyValueFactory<>("destination"));
		cDepM.setCellValueFactory(new PropertyValueFactory<>("departingMonth"));
		cDepD.setCellValueFactory(new PropertyValueFactory<>("departingDate"));
		cDepY.setCellValueFactory(new PropertyValueFactory<>("departingYear"));
		cDepT.setCellValueFactory(new PropertyValueFactory<>("departingTime"));
		cArrD.setCellValueFactory(new PropertyValueFactory<>("arrivingDate"));
		cArrT.setCellValueFactory(new PropertyValueFactory<>("arrivingTime"));
		cPNum.setCellValueFactory(new PropertyValueFactory<>("passenger"));
		
		tableFlight.setItems(data);
		
		
	}
//* method to transfer object between scene
	public void myFunction(String text) {
		userlabel1.setText(text);
		user=userlabel1.getText();
		
	}
	
	
	
	
	
	public void Search(ActionEvent event) throws Exception {
		data.clear();
		
		boolean o=origins.getText().isEmpty();
		boolean d=destin.getText().isEmpty();
		boolean dm=depM.getText().isEmpty();
		boolean dd=depD.getText().isEmpty();
		boolean dy=depY.getText().isEmpty();
	
		
		
		
		Driver 	driver= new Driver();
		Connection connection= Driver.Connector();
		
		Statement statement = connection.createStatement();
		
		try {
		//* search if only enter origin and destin	
		if(dm && dd && dy) {
			prep = connection.prepareStatement("SELECT * FROM flights where origin = ? AND destination = ?");
			   prep.setString(1, origins.getText());
			   prep.setString(2, destin.getText());
			   

			  ResultSet resultSet = prep.executeQuery();
			 
			  while(resultSet.next()) {
			  data.add(new Flight(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(9),resultSet.getString(10),resultSet.getString(11)));
			  
			  }
			  
			}
		//* search all if not enter anything 
		if( o&&d && dm && dd && dy) {
			String sql="select * from flights";
			
			ResultSet resultSet= statement.executeQuery(sql);
			
			while(resultSet.next()) {
				
				data.add(new Flight(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(9),resultSet.getString(10),resultSet.getString(11)));
				}
		}
		else {
		
		
		prep = connection.prepareStatement("SELECT * FROM flights where origin = ? AND destination = ? AND departingMonth = ? AND departingDate = ? AND departingYear = ?");
		   prep.setString(1, origins.getText());
		   prep.setString(2, destin.getText());
		   prep.setString(3, depM.getText());
		   prep.setString(4, depD.getText());
		   prep.setString(5, depY.getText());

		  ResultSet resultSet = prep.executeQuery();
		 
		  while(resultSet.next()) {
		  data.add(new Flight(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(9),resultSet.getString(10),resultSet.getString(11)));
		  
		  		}
			}
		}
		catch(SQLException e) {
			
		}
		tableFlight.setItems(data);
	}
	
	public void Signup(ActionEvent event) throws Exception {
		
		try {
		Driver 	driver= new Driver();
		Connection connection= Driver.Connector();
		
		
		
		String sql="INSERT INTO account VALUES('"+user+"','"+txtFid.getText()+"')";
		
				
		Statement statement=connection.createStatement();
		statement.executeUpdate(sql);
		
		
		statuslabel.setText("Success");
		}
		catch (Exception e) {
			statuslabel.setText("Failed");
		}
		
		
			
		
		
		
	}
	
	public void Logout(ActionEvent event) throws Exception {
		
		
		Parent tableViewParent4 = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
		Scene tableViewScene4 = new Scene(tableViewParent4);
		
		Stage window4= (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window4.setScene(tableViewScene4);
		window4.show();
		
	
	
	}
	public void goToAccount(ActionEvent event) throws Exception {
		
		FXMLLoader loader= new FXMLLoader(getClass().getResource("/application/AccountAdmin.fxml"));
		Parent tableViewParent4 = loader.load();
		
		//* passing username to next scene
		
		AccountAdminController m=loader.getController();
		m.myFunctionAdmin(user);

		
		
		Scene tableViewScene4 = new Scene(tableViewParent4);
		
		Stage window4= (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window4.setScene(tableViewScene4);
		window4.show();
		
	
	
	}
public void goToManage(ActionEvent event) throws Exception {
		
		
		
		
		
		FXMLLoader loader= new FXMLLoader(getClass().getResource("/application/ManageFlight.fxml"));
		Parent tableViewParent4 = loader.load();
		
		//* passing username to next scene
		ManageFlightController m1=loader.getController();
		m1.myFunction(user);
		
		Scene tableViewScene4 = new Scene(tableViewParent4);
		
		Stage window4= (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window4.setScene(tableViewScene4);
		window4.show();
		
	
	
	}
}
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


public class AccountAdminController implements Initializable {
	// This is from AdminAccount
	@FXML
	private TextField txtfid;
	@FXML
	private Label status;
	@FXML
	private TableView<Flight> tableflight;
	@FXML
	private TableColumn<Flight,String> fi;
	@FXML
	private TableColumn<Flight,String> or;
	@FXML
	private TableColumn<Flight,String> des;
	@FXML
	private TableColumn<Flight,String> dm;
	@FXML
	private TableColumn<Flight,String> dd;
	@FXML
	private TableColumn<Flight,String> dy;
	@FXML
	private TableColumn<Flight,String> dt;
	@FXML
	private TableColumn<Flight,String> ad;
	@FXML
	private TableColumn<Flight,String> at;
	@FXML
	private TableColumn<Flight,String> pn;
	
	@FXML
	private Label userlabel3;
	String user1;
	
	
	
	PreparedStatement prep;
	 
	ObservableList<Flight> data= FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location,ResourceBundle resource) {
		
		fi.setCellValueFactory(new PropertyValueFactory<>("flightId"));
		or.setCellValueFactory(new PropertyValueFactory<>("origin"));
		des.setCellValueFactory(new PropertyValueFactory<>("destination"));
		dm.setCellValueFactory(new PropertyValueFactory<>("departingMonth"));
		dd.setCellValueFactory(new PropertyValueFactory<>("departingDate"));
		dy.setCellValueFactory(new PropertyValueFactory<>("departingYear"));
		dt.setCellValueFactory(new PropertyValueFactory<>("departingTime"));
		ad.setCellValueFactory(new PropertyValueFactory<>("arrivingDate"));
		at.setCellValueFactory(new PropertyValueFactory<>("arrivingTime"));
		pn.setCellValueFactory(new PropertyValueFactory<>("passenger"));
	}
	
	
// this method is for admin account
	public void checkFlightOnAccountAdmin(ActionEvent event) throws Exception {
		
		data.clear();
		
		
		
		try {
		Driver 	driver= new Driver();
		Connection connection= Driver.Connector();
		
		
		
		Statement statement = connection.createStatement();
			
			String sql="select * from flights, account where flights.flightId= account.flightId AND account.username='"+user1+"';";
			
			ResultSet resultSet= statement.executeQuery(sql);
			while(resultSet.next()) {
			
			data.add(new Flight(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(9),resultSet.getString(10),resultSet.getString(11)));
			}
			
		}
		catch(Exception e) {
			status.setText("System Error");
		}
		
		
		tableflight.setItems(data);
		
		
		}
	
	//this methods is for customer

	
public void Signup(ActionEvent event) throws Exception {
		
		try {
		Driver 	driver= new Driver();
		Connection connection= Driver.Connector();
		
		
		
		String sql="INSERT INTO account VALUES('"+user1+"','"+txtfid.getText()+"')";
		
				
		Statement statement=connection.createStatement();
		statement.executeUpdate(sql);
		
		
		status.setText("Reserve Success");
		}
		catch (Exception e) {
			status.setText("Reserve Failed");
		}
					
		
		
	}

//this method is for admin account
	public void DeleteFlightAdmin (ActionEvent event) throws Exception {
		
		try {
		Driver 	driver= new Driver();
		Connection connection= Driver.Connector();
		
		
		
		Statement statement = connection.createStatement();
			
			String sql="DELETE from account where flightId='"+txtfid.getText()+"' and username='"+user1+"' ;";
			
			int result= statement.executeUpdate(sql);
						
			status.setText("Deleted Successfully");
			
		}
		catch(Exception e) {
			status.setText("System Error");
		}
		
		
		tableflight.setItems(data);
		
		
		}

	
	
	
	
	
	public void myFunctionAdmin(String text) {
		userlabel3.setText(text);
		user1=userlabel3.getText();
	}
	
	

	
	

	
	public void backToMainmenuAdmin(ActionEvent event) throws Exception {
		
		
		FXMLLoader loader= new FXMLLoader(getClass().getResource("/application/MainmenuAdmin.fxml"));
		Parent tableViewParent4 = loader.load();
		
		//* passing username to next scene
		MainAdminController m=loader.getController();
		m.myFunction(user1);
		
		Scene tableViewScene4 = new Scene(tableViewParent4);
		
		Stage window4= (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window4.setScene(tableViewScene4);
		window4.show();
		
	
	
	}

}
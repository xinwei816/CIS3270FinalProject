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


public class ManageFlightController implements Initializable {
	
	@FXML
	private Label txtuser;
	@FXML
	private TextField txtnpw;
	@FXML
	private TextField txtsa;
	String user3;
	
	@FXML
	private Label status;
	@FXML
	private TextField fi;
	@FXML
	private TextField or;
	@FXML
	private TextField des;
	@FXML
	private TextField dm;
	@FXML
	private TextField dd;
	@FXML
	private TextField dy;
	@FXML
	private TextField dt;
	@FXML
	private TextField am;
	@FXML
	private TextField ad;
	@FXML
	private TextField at;
	@FXML
	private TextField pn;
	@FXML
	private TableView<Flight> tableFlight;
	@FXML
	private TableColumn<Flight,String> cfl;
	@FXML
	private TableColumn<Flight,String> cor;
	@FXML
	private TableColumn<Flight,String> cdes;
	@FXML
	private TableColumn<Flight,String> cdm;
	@FXML
	private TableColumn<Flight,String> cdd;
	@FXML
	private TableColumn<Flight,String> cdy;
	@FXML
	private TableColumn<Flight,String> cdt;
	@FXML
	private TableColumn<Flight,String> cad;
	@FXML
	private TableColumn<Flight,String> cat;
	@FXML
	private TableColumn<Flight,String> cpn;
	
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
			
			data.add(new Flight(resultSet.getString("flightId"),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(9),resultSet.getString(10),resultSet.getString(11)));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		cfl.setCellValueFactory(new PropertyValueFactory<>("flightId"));
		cor.setCellValueFactory(new PropertyValueFactory<>("origin"));
		cdes.setCellValueFactory(new PropertyValueFactory<>("destination"));
		cdm.setCellValueFactory(new PropertyValueFactory<>("departingMonth"));
		cdd.setCellValueFactory(new PropertyValueFactory<>("departingDate"));
		cdy.setCellValueFactory(new PropertyValueFactory<>("departingYear"));
		cdt.setCellValueFactory(new PropertyValueFactory<>("departingTime"));
		cad.setCellValueFactory(new PropertyValueFactory<>("arrivingDate"));
		cat.setCellValueFactory(new PropertyValueFactory<>("arrivingTime"));
		cpn.setCellValueFactory(new PropertyValueFactory<>("passenger"));
		
		tableFlight.setItems(data);
	}
	
	public void Search(ActionEvent event) throws Exception {
		data.clear();
		
		boolean f=fi.getText().isEmpty();
		boolean o=or.getText().isEmpty();
		boolean d=des.getText().isEmpty();
		boolean dM=dm.getText().isEmpty();
		boolean dD=dd.getText().isEmpty();
		boolean dY=dy.getText().isEmpty();
		Driver 	driver= new Driver();
		
		Connection connection= Driver.Connector();
		
		Statement statement = connection.createStatement();
		
		try {
			//*search all
		if( f && o && d && dM && dD && dY) {
				String sql="select * from flights";
				
				ResultSet resultSet= statement.executeQuery(sql);
				
				while(resultSet.next()) {
					
					data.add(new Flight(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(9),resultSet.getString(10),resultSet.getString(11)));
					}
		}
		//search by origins and destin
		if(dM && dD && dY) {
			
			
			

			prep = connection.prepareStatement("SELECT * FROM flights where  origin = ? AND destination = ? ");
			   
			   prep.setString(1, or.getText());
			   prep.setString(2, des.getText());
			   
			  ResultSet resultSet = prep.executeQuery();
			 
			  
			   
			 
			  while(resultSet.next()) {
			  data.add(new Flight(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(9),resultSet.getString(10),resultSet.getString(11)));
			  
			  
			  }
			
			
			tableFlight.setItems(data);
			
			
			}	
			
		//SEACH BY FLIGHT ID 
		if(o&& d && dM && dD && dY) {
		
		
		
		prep = connection.prepareStatement("SELECT * FROM flights where flightId = ? ");
		   prep.setString(1, fi.getText());
		  
		

		  ResultSet resultSet = prep.executeQuery();
		 
		  
		   
		 
		  while(resultSet.next()) {
		  data.add(new Flight(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(9),resultSet.getString(10),resultSet.getString(11)));
		  
		  
		  }
		
		
		tableFlight.setItems(data);
		
		
		}
	//*SEARCH FLIGHT BY ALL INFO
	else {
		
			
			
			prep = connection.prepareStatement("SELECT * FROM flights where flightId= ? AND origin = ? AND destination = ? AND departingMonth = ? AND departingDate = ? AND departingYear = ?");
			   prep.setString(1, fi.getText());
			   prep.setString(2, or.getText());
			   prep.setString(3, des.getText());
			   prep.setString(4, dm.getText());
			   prep.setString(5, dd.getText());
			   prep.setString(6, dy.getText());
			  ResultSet resultSet = prep.executeQuery();
			
			  while (resultSet.next()) {
			  data.add(new Flight(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(9),resultSet.getString(10),resultSet.getString(11)));
			  
			  
			  }
			
			
			tableFlight.setItems(data);
			}
		}
		
		catch(SQLException e) {
			
		}
	}
	
	public void Add(ActionEvent event) throws Exception {
		data.clear();
		
		Driver 	driver= new Driver();
		Connection connection= Driver.Connector();
		
		Statement statement = connection.createStatement();
		
		try{
		
		
		   prep = connection.prepareStatement("INSERT INTO flights (flightId,origin, destination, departingMonth,departingDate,departingYear, departingTime, arrivingMonth,arrivingDate,arrivingTime,passenger) Values (?,?,?,?,?,?,?,?,?,?,?)");
		   prep.setString(1, fi.getText());
		   prep.setString(2, or.getText());
		   prep.setString(3, des.getText());
		   prep.setString(4, dm.getText());
		   prep.setString(5, dd.getText());
		   prep.setString(6, dy.getText());
		   prep.setString(7, dt.getText());
		   prep.setString(8, am.getText());
		   prep.setString(9, ad.getText());
		   prep.setString(10, at.getText());
		   prep.setString(11, pn.getText());
		  
		   
		   int result = prep.executeUpdate();
		   
		   Statement statement2 = connection.createStatement();
			String sql="select * from flights";
			
			ResultSet resultSet= statement.executeQuery(sql);
			while(resultSet.next()) {
			
			data.add(new Flight(resultSet.getString("flightId"),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(9),resultSet.getString(10),resultSet.getString(11)));
			}
		   
		  
		  
		  status.setText("Successfully Added");
		
		  tableFlight.setItems(data);
		}
		catch(Exception e) {
			status.setText("Add Failed");
		}
		
		
		}
	
	public void delete(ActionEvent event) throws Exception {
		data.clear();
		
		Driver 	driver= new Driver();
		Connection connection= Driver.Connector();
		
		Statement statement = connection.createStatement();
		
		try {
		
		
		   prep = connection.prepareStatement("Delete From flights where flightId=?");
		   prep.setString(1, fi.getText());
		 
		   
		   int result = prep.executeUpdate();
		   
		   Statement statement2 = connection.createStatement();
			String sql="select * from flights";
			
			ResultSet resultSet= statement.executeQuery(sql);
			while(resultSet.next()) {
			
			data.add(new Flight(resultSet.getString("flightId"),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(9),resultSet.getString(10),resultSet.getString(11)));
			}
		   
		  
		  
		  status.setText("Successfully Deleted");
		
		  tableFlight.setItems(data);
		}
		catch(Exception e) {
			status.setText("Delete Failed");
		}
		
		
		}
	
	public void update(ActionEvent event) throws Exception {
		data.clear();
		
		
		Driver 	driver= new Driver();
		Connection connection= Driver.Connector();
		
		Statement statement = connection.createStatement();
		
		try {
		   prep = connection.prepareStatement("Update flights set origin=? ,destination =?, departingMonth =? ,departingDate=? ,departingYear=? ,departingTime=? ,arrivingMonth=? ,arrivingDate=? ,arrivingTime=? ,passenger=?  where flightId=?");
		   
		   prep.setString(1, or.getText());
		   prep.setString(2, des.getText());
		   prep.setString(3, dm.getText());
		   prep.setString(4, dd.getText());
		   prep.setString(5, dy.getText());
		   prep.setString(6, dt.getText());
		   prep.setString(7, am.getText());
		   prep.setString(8, ad.getText());
		   prep.setString(9, at.getText());
		   prep.setString(10, pn.getText());
		   prep.setString(11, fi.getText());
		   
		   int result = prep.executeUpdate();
		   
		   Statement statement2 = connection.createStatement();
			String sql="select * from flights";
			
			ResultSet resultSet= statement.executeQuery(sql);
			while(resultSet.next()) {
			
			data.add(new Flight(resultSet.getString("flightId"),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(9),resultSet.getString(10),resultSet.getString(11)));
			}
		   
		  
		  
		  status.setText("Successfully Updated");
		
		  tableFlight.setItems(data);
		}
		catch(Exception e) {
			status.setText("Updated Failed");
		}
		
		
		}
	
	//* method to transfer object between scene
	
	
	public void myFunction(String text) {
		txtuser.setText(text);
		user3=txtuser.getText();
		
	}
	public void backToAdminmenu(ActionEvent event) throws Exception {
		
		
		FXMLLoader loader= new FXMLLoader(getClass().getResource("/application/MainmenuAdmin.fxml"));
		Parent tableViewParent4 = loader.load();
		
		//* passing username to next scene
		MainAdminController m=loader.getController();
		m.myFunction(user3);
		
		Scene tableViewScene4 = new Scene(tableViewParent4);
		
		Stage window4= (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window4.setScene(tableViewScene4);
		window4.show();
		
	
	
	}

}
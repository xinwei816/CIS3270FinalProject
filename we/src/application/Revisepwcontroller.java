package application;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class Revisepwcontroller {
	
	@FXML
	private TextField txtuser;
	@FXML
	private TextField txtnpw;
	@FXML
	private TextField txtsa;
	@FXML
	private Label labstats;
	
	Driver 	driver= new Driver();
	Connection connection= Driver.Connector();
	
public void ConfirmButton(ActionEvent event) throws Exception {
		
	
		
		try{
			Statement statement= connection.createStatement();
			String sql= "SELECT * FROM  users Where username='"+txtuser.getText()+"' AND securityanswer = '"+txtsa.getText()+"';";
			
			ResultSet resultSet= statement.executeQuery(sql);
			
			
			if( resultSet.next()) {
		
				Statement statement2= connection.createStatement();
			
				String sql2 = "UPDATE users SET passwords= '"+txtnpw.getText()+"' WHERE username='"+txtuser.getText()+"' ;";
				
				int resultSet2= statement.executeUpdate(sql2);
			
				labstats.setText("Changed Successfully");
			}
			else {
			labstats.setText("Changed Failed");}
			
		}
		catch (SQLException e){
			labstats.setText("System Error");
			e.printStackTrace();
		}
	}

	public void backToLogin2(ActionEvent event) throws Exception {
		
		
		Parent tableViewParent4 = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
		Scene tableViewScene4 = new Scene(tableViewParent4);
		
		Stage window4= (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window4.setScene(tableViewScene4);
		window4.show();
		
	
	
	}

}

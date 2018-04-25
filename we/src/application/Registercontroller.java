package application;



import java.sql.Connection;

import java.sql.Statement;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.IOException;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Registercontroller {
	@FXML
	private Label signstatus;
	@FXML
	private TextField fName;
	@FXML
	private TextField lName;
	@FXML
	private TextField address;
	@FXML
	private TextField state;
	@FXML
	private TextField zip;
	@FXML
	private TextField uName;
	@FXML
	private TextField pWord;
	@FXML
	private TextField eMail;
	@FXML
	private TextField city;
	@FXML
	private TextField ssn;
	@FXML
	private TextField sAnswer;
	@FXML
	private TextField adminCode;
	
	Admin newuser= new Admin();
	
	;
	
	
	public void Signup(ActionEvent event) throws Exception {
		try {
		Driver 	driver= new Driver();
		Connection connection= Driver.Connector();
		
		if(adminCode.getText().equals("SA08")) {
		
		String sql="INSERT INTO users VALUES('"+fName.getText()+"','"+lName.getText()+"','"+address.getText()+"','"+city.getText()+"','"+state.getText()+"','"+zip.getText()+"','"+uName.getText()+"','"+pWord.getText()+"','"+eMail.getText()+"','"+ssn.getText()+"','"+sAnswer.getText()+"','YES');";
		
		Statement statement=connection.createStatement();
		statement.executeUpdate(sql);
		
		
		}
		else {
		String sql="INSERT INTO users VALUES('"+fName.getText()+"','"+lName.getText()+"','"+address.getText()+"','"+city.getText()+"','"+state.getText()+"','"+zip.getText()+"','"+uName.getText()+"','"+pWord.getText()+"','"+eMail.getText()+"','"+ssn.getText()+"','"+sAnswer.getText()+"','NO');";
		Statement statement=connection.createStatement();
		statement.executeUpdate(sql);		
			
		}
		
		
			signstatus.setText("Success");
		}
		catch(Exception e) {
			signstatus.setText("Failed");
		}
		
		
		
	}
	
	
	
	
	public void backToLogin(ActionEvent event) throws Exception {
		
			
			Parent tableViewParent = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
			Scene tableViewScene = new Scene(tableViewParent);
			
			Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
			
			window.setScene(tableViewScene);
			window.show();
			
		
		
	}
	
	
}
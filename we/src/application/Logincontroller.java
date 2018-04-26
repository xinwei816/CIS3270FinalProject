package application;

import java.sql.*;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Driver;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Logincontroller implements Initializable {
	@FXML
	private Label txtStatus;
	@FXML
	private TextField txtUser;
	@FXML
	private TextField txtPw;
	@FXML
	private AnchorPane root;
	
	
	Driver 	driver= new Driver();
	Connection connection= Driver.Connector();
	
	@Override
	public void initialize(URL location,ResourceBundle resource) {
		//* check if spashscreen is loaded or not 
		if(!Main.isSplashLoader) {
		loadSplashScreen();
		}
		
	}
	
	public void Login(ActionEvent event) throws Exception {
		
	
		//* find the user has matched username and password in database
		try{
			Statement statement= connection.createStatement();
			String sql= "SELECT * FROM  users Where username='"+txtUser.getText()+"' AND passwords = '"+txtPw.getText()+"';";
			
			ResultSet resultSet= statement.executeQuery(sql);
			
			
			if( resultSet.next()) {
		
			txtStatus.setText("Login Success");
			
			Statement statement1 = connection.createStatement();
			String sql1 = "SELECT * FROM  users Where username='"+txtUser.getText()+"'";
			
			ResultSet resultSet1= statement1.executeQuery(sql1);
			resultSet1.next();
			//* Find the adminstatus cloumn
			
			String s= resultSet1.getString("adminstatus");
			
			Admin user= new Admin();
			
			//* check whether user is admin
				if(user.isAdmin(s)) {
					/**
					 * When this method is called, it will change the scene to main menu.
					 */
					
					FXMLLoader loader= new FXMLLoader(getClass().getResource("/application/MainmenuAdmin.fxml"));
					Parent tableViewParent = loader.load();
					//* passing username to next scene
					MainAdminController mainadmincontroller=loader.getController();
					mainadmincontroller.myFunction(txtUser.getText());
					
					
					
					
					Scene tableViewScene = new Scene(tableViewParent);
			
					Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
			
					window.setScene(tableViewScene);
					window.show();}
				
				else {
					FXMLLoader loader= new FXMLLoader(getClass().getResource("/application/Mainmenu.fxml"));
					Parent tableViewParent = loader.load();
					
					//* passing username to next scene
					
					MainmenuController mainmenucontroller=loader.getController();
					mainmenucontroller.myFunction(txtUser.getText());
							
					Scene tableViewScene = new Scene(tableViewParent);
			
					Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
			
					window.setScene(tableViewScene);
					window.show();
					
				}
					
			}
			
			
			else {
			txtStatus.setText("Login Failed");}
			
		}
		catch (SQLException e){
			txtStatus.setText("System error");
			e.printStackTrace();
		}
	}
	
	public void loadSplashScreen() {
		
		try {
			// after initialze,set it to ture, which means  splash screen will be load only once
			Main.isSplashLoader = true;
			
			StackPane pane =  FXMLLoader.load(getClass().getResource("/application/SplashScreen.fxml"));
			root.getChildren().setAll(pane);
			
			
			FadeTransition fadeIn= new FadeTransition(Duration.seconds(2),pane);
			fadeIn.setFromValue(0);
			fadeIn.setToValue(1);
			fadeIn.setCycleCount(1);
			
			FadeTransition fadeOut= new FadeTransition(Duration.seconds(2),pane);
			fadeOut.setFromValue(0);
			fadeOut.setToValue(1);
			fadeOut.setCycleCount(1);
			
			fadeIn.play();
			
			fadeIn.setOnFinished((e)->{
				fadeOut.play();
				
				
			});
			fadeOut.setOnFinished((e)->{
				try {
					AnchorPane parent=FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
					root.getChildren().setAll(parent);
					
				}
				catch(Exception ex) {
					
				}
			});
			
			
			
		}
		catch(Exception e) {
			
		}
	}
	
	
	
	public void registerPage(ActionEvent event) throws Exception {
		
		//* go to register page
		
		Parent tableViewParent2 = FXMLLoader.load(getClass().getResource("/application/RegisterMenu.fxml"));
		Scene tableViewScene2 = new Scene(tableViewParent2);
		
		Stage window2= (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window2.setScene(tableViewScene2);
		window2.show();
		
	
	
	}
	public void revisePassowrd (ActionEvent event) throws Exception {
		//* go to changing password page
		
		Parent tableViewParent3 = FXMLLoader.load(getClass().getResource("/application/RevisepwMenu.fxml"));
		Scene tableViewScene3 = new Scene(tableViewParent3);
		
		Stage window3= (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window3.setScene(tableViewScene3);
		window3.show();
		
	
	
	}
	
	
	
}

package application;

public abstract class User {

		//registration required info	
		
		private String username;
		private String password;
		
	
		
			
		
			//get and set methods for each required info
			
			
			public void setUserName(String username){
				this.username = username;
			}
			
			public String getUserName(){
				return username;
			}
			
			public void setPassword(String password){
				this.password = password;
			}
			
			public String getPassword(){
				return password;
			}
			
}
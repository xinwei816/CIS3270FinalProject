package application;

public class Customer extends User {
	private String fName;
	private String lName;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String email;
	private String ssn;
	private String sAnswer;
	private boolean isAdmin;
	
	public void setFirstName(String fName){
		this.fName = fName;
	}
	
	public String getFirstName(){
		return fName;
	}
	
	public void setLastName(String lName){
		this.lName = lName;
	}
	
	public String getLastName(){
		return lName;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	
	public String getCity(){
		return city;
	}
	
	public void setCity(String city){
		this.city = city;
	}
	public String getAddress(){
		return address;
	}
	
	public void setZip(String zip){
		this.zip = zip;
	}
	
	public String getZip(){
		return zip;
	}
	
	public void setState(String state){
		this.state = state;
	}
	
	public String getState(){
		return state;
	}
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setSsn(String ssn){
		this.ssn = ssn;
	}
	
	public String getSsn(){
		return ssn;
	}
	
	public void setSecurityAnswer(String sAnswer){
		this.sAnswer = sAnswer;
	}
	
	public String getSecurityAnswer(){
		return sAnswer;
	}
	
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin= isAdmin;
		
	}
	public boolean getIsAdmin(){
		return isAdmin;
	}
	
	public boolean isAdmin(String admin) {
		if(admin.equalsIgnoreCase("NO") ) {
			return false;
		}
		else
			return true;
	
	}
	
	
}

package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Flight {
	
	private final StringProperty flightId;
	private final StringProperty origin;
	private final StringProperty destination;
	private final StringProperty departingMonth;
	private final StringProperty departingDate;
	private final StringProperty departingYear;
	private final StringProperty departingTime;
	private final StringProperty arrivingDate;
	private final StringProperty arrivingTime;
	private final StringProperty passenger;
	
	
	public Flight( String flightId,String origin, String destination, String departingMonth, String departingDate,
			String departingYear, String departingTime,  String arrivingDate, String arrivingTime, String passenger) {
		
		this.flightId = new SimpleStringProperty (flightId);
		this.origin = new SimpleStringProperty (origin);
		this.destination = new SimpleStringProperty (destination);
		this.departingMonth = new SimpleStringProperty (departingMonth);
		this.departingDate = new SimpleStringProperty (departingDate);
		this.departingYear = new SimpleStringProperty (departingYear);
		this.departingTime =new SimpleStringProperty  (departingTime);
		this.arrivingDate =new SimpleStringProperty  (arrivingDate);
		this.arrivingTime =new SimpleStringProperty  (arrivingTime);
		this.passenger=new SimpleStringProperty (passenger);
		
	}


	public String getFlightId() {
		return flightId.get();
	}


	public String getOrigin() {
		return origin.get();
	}


	public String getDestination() {
		return destination.get();
	}


	public String getDepartingMonth() {
		return departingMonth.get();
	}


	public String getDepartingDate() {
		return departingDate.get();
	}


	public String getDepartingYear() {
		return departingYear.get();
	}


	public String getDepartingTime() {
		return departingTime.get();
	}


	public String getArrivingDate() {
		return arrivingDate.get();
	}


	public String getArrivingTime() {
		return arrivingTime.get();
	}


	public String getPassenger() {
		return passenger.get();
	}
	
}
	
	
	
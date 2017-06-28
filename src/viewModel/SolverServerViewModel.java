package viewModel;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class SolverServerViewModel implements Observer{

	private StringProperty port;
	private Model model;
	private IntegerProperty numOfConnectedUsers;
	
	
	public SolverServerViewModel(Model model) {
		
		numOfConnectedUsers = new SimpleIntegerProperty(0);
		port = new SimpleStringProperty(" ");
		this.model = model;
	}
	
	public void stopServer(){
		
		model.stopServer();
		port.set(" ");
		
	}
	
	public void startServer(String port){
		
		int portNum;
		
		try {
			portNum = Integer.parseInt(port);
		} catch (NumberFormatException e) {
			return;
		}
		
		this.port.set(port);
		model.startServer(portNum);
		
	}
	

	public StringProperty getPort() {
		return port;
	}

	
	@Override
	public void update(Observable o, Object arg) {
		
		@SuppressWarnings("unchecked")
		LinkedList<String> params = (LinkedList<String>) arg;
		String commandKey = params.removeFirst();
		
		if(commandKey == "USERAMOUNTCHANGED")
			numOfConnectedUsers.set(model.getNumOfUsersHandled());

		
	}

	public IntegerProperty getNumOfConnectedUsers() {
		return numOfConnectedUsers;
	}
	
	


}

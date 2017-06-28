package view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import viewModel.SolverServerViewModel;

public class MainWindowController implements View {

	@FXML
	private TextField textField;
	@FXML
	private Text currentPort;
	@FXML
	private Text NumOfconnectedUsers;

	@FXML
	private void initialize() {
		
	}

	public TextField getTextField() {
		return textField;
	}

	public void setTextField(TextField textField) {
		this.textField = textField;
	}

	public Text getCurrentPort() {
		return currentPort;
	}

	public void setCurrentPort(Text currentPort) {
		this.currentPort = currentPort;
	}

	private SolverServerViewModel viewModel;

	public void setViewModel(SolverServerViewModel viewModel) {
		this.viewModel = viewModel;
		currentPort.textProperty().bind(viewModel.getPort());
		NumOfconnectedUsers.textProperty().bind(viewModel.getNumOfConnectedUsers().asString());
	}

	public MainWindowController() {

	}

	@FXML
	public void stopServer() {
		viewModel.stopServer();
	}

	@FXML
	public void startServer() {
	
		viewModel.startServer(textField.getText());
	}
	
	
	@FXML
	public void about(){
		
		Platform.runLater(new Runnable() {
			@Override
			public void run() {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("About");
				alert.setHeaderText(null);
				alert.setContentText(
						"Welcome to Sokomon solver server!\nThe server handles requests for sokoban level solutions.");
				alert.showAndWait();
			}
		});
		
	}
		
		
	

}

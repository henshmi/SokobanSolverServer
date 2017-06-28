package model.clientHandlers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Observable;

import model.solver.sokobanSolver;

public class SokobanSolutionClientHandler extends Observable implements ClientHandler{


	Socket client;
	
	public SokobanSolutionClientHandler(Socket client) {
		this.client = client;
	}
	
	@Override
	public void handleClient() {
		try {
			PrintWriter writer = new PrintWriter(client.getOutputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			
			String compressedMap = reader.readLine();
			
			// At this moment, checking whether the server contains the solution.
			// If we have received a negative answer, the following will be committed
			
			sokobanSolver solver = new sokobanSolver();
			String solution = solver.solve(compressedMap);
			
			writer.println(solution); // Sending the solution to the client.
			writer.flush();
			//Updating the web service
			
			client.close();
			
			LinkedList<String> notificationToObservers = new LinkedList<>();
			notificationToObservers.add("USERDISCONNECTED");
			setChanged();
			notifyObservers(notificationToObservers);

		} catch (Exception e) {
			e.printStackTrace();
		} 

	}




}

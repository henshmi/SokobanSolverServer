package model.clientHandlers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Observable;

import model.solver.sokobanSolver;

public class SokobanSolutionClientHandler extends Observable implements ClientHandler {

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
//			String solution = getSolutionFromService(compressedMap);
//			
//					if(solution.toLowerCase().equals("not found")){
						sokobanSolver solver = new sokobanSolver();
						String solution = solver.solve(compressedMap);
//						saveSolutionToService(compressedMap,solution);
//					}
					
		    writer.println(solution); 
			writer.flush();
			
			client.close();
		

			LinkedList<String> notificationToObservers = new LinkedList<>();
			notificationToObservers.add("USERDISCONNECTED");
			notificationToObservers.add(client.getInetAddress().getHostAddress());
			notificationToObservers.add(String.valueOf(client.getPort()));
			setChanged();
			notifyObservers(notificationToObservers);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//	private String getSolutionFromService(String map) {
//		String url = "http://localhost:8080/sokoservice/webapi/searchsolution/" + map;
//		Client client = ClientBuilder.newClient();
//		WebTarget webTarget = client.target(url);
//		Response response = webTarget.request(MediaType.TEXT_PLAIN).get(Response.class);
//		if (response.getStatus() == 200) {
//			String solution = response.readEntity(new GenericType<String>() {
//			});
//			return solution;
//		} else {
//			System.out.println(response.getHeaderString("errorResponse"));
//		}
//		return null;
//	} 
//	
//
//
//
//	public void saveSolutionToService(String map, String solution){
//		Client client = ClientBuilder.newClient();
//		WebTarget webTarget = client.target("http://localhost:8080/sokoservice/webapi/addSolution?levelMap=" + map);
//		Entity<String> entity = Entity.text(solution);
//		Response response = webTarget.request(MediaType.TEXT_PLAIN).post(entity);
//		if (response.getStatus() == 200) {
//		} else {
//			System.out.println(response.getHeaderString("errorResponse"));
//		}
//	}
//	
	
	
	
}

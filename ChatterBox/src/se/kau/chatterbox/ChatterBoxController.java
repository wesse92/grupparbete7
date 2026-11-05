package se.kau.chatterbox;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDateTime;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ChatterBoxController{
	private User[] users;
	private User loggedInUser;
	private ArrayList<Message> messages;
	private int nextId;
	
	
	public ChatterBoxController() {
		users = new User[2];
		users[0] = new User("Samuel", "ForshagaIF");
		users[1] = new User("Simon", "ForshagaIF2");
		messages = new ArrayList<>();
		nextId = 1;
		loadMessages();
		
	}
	
	public boolean login(String username, String password) {
		for(User u: users) {
			if(u.getUsername().equals(username) && u.getPassword().equals(password)) {
				this.loggedInUser = u;
				return true;
			}
		}
		return false;	

	}
	public void logout() {
		loggedInUser = null;
	}
	
	public boolean isLoggedIn() {
		return loggedInUser != null;
	}
	public User getLoggedInUser() {
		return loggedInUser;
	}
	public void postMessage(String text) {
		Message m = new Message(nextId, text, new Date(), loggedInUser);
		messages.add(m);
		nextId++;
		saveMessages();
	}
	public ArrayList<Message> getAllMessages(){
		return messages;
	}
	public ArrayList<Message> getMyMessages(){
		ArrayList<Message> mine = new ArrayList<>();
		for(Message m : messages) {
			if(m.getAuthor() == loggedInUser) {
				mine.add(m);
			}
		}
		return mine;
	}
	public boolean deleteMessage(int id) {
		for(Message m : messages) {
			if(m.getId() == id && m.getAuthor() == loggedInUser) {
				messages.remove(m);
				saveMessages();
				return true;
			}
		}
		return false;
	}
	public void saveMessages() {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new FileWriter("messages.csv"));
			
			for(Message m : messages) {
				writer.println(m.getId() + ";" + m.getAuthor().getUsername() + ";" + m.getText() + ";" + m.getTimestamp()); // Kolla med mohammed "så sparas ett tal som är enkelt att läsa tillbaka. Det sparar oss bekymmer i nästa steg, där vi läser in filen."
			}
			writer.close();
		}catch(IOException e) {
			System.out.println("Could not save message to file");
		}
	}
	public void loadMessages() {
		try {
			Scanner reader = new Scanner(new File("messages.csv"));
			
			while(reader.hasNextLine()) {
				String line = reader.nextLine();
				
				String[] parts = line.split(";");
				
				int id = Integer.parseInt(parts[0]);
				
				User author = null;
				for(User u : users) {
					if(u.getUsername().equals(parts[1])){
						author = u;
					}
				}
				Date timestamp = new Date(Long.parseLong(parts[3]));
				messages.add(new Message(id, parts[2], timestamp, author));
				
				if(id >= nextId) {
					nextId = id + 1;
				}
			}
			reader.close();
		} catch(FileNotFoundException e) {
			
		}
	}
}	

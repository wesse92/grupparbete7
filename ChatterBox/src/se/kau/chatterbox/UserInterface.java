package se.kau.chatterbox;
import java.util.Scanner;
import java.util.ArrayList;

public class UserInterface {
		private ChatterBoxController controller;
		private Scanner scanner;
		
		public UserInterface(ChatterBoxController controller, Scanner scanner){
		this.controller = controller;
		this.scanner = scanner;
		}
		public boolean showLogin() {
			System.out.println("---Enter--");
			System.out.print("Username");
			String username = scanner.nextLine();
			System.out.print("Password: ");
			String password = scanner.nextLine();
			
			if(controller.login(username, password)) {
				System.out.println("Welcome, " + controller.getLoggedInUser().getUsername() + "!");
				return true;
			}else {
				System.out.println("Wrong username or password");
				return false;
			}
			
		}
		public void showUserMenu() {
			boolean running = true;
			while(running){
				System.out.println("--- User Menu ---");
				System.out.println("1. Write a message");
				System.out.println("2. Delete message");
				System.out.println("3. View my messages");
				System.out.println("4. View all messages");
				System.out.println("0. Logout");
				System.out.print("Enter choice: ");
				String choice = scanner.nextLine();
				
				switch(choice) {
				case "1":
					System.out.print("Write your message: ");
					String message = scanner.nextLine();
					controller.postMessage(message);
					System.out.println("Message is posted!");
					break;
					
				case "3":
					ArrayList<Message> mine = controller.getMyMessages();
					if(mine.isEmpty()) {
						System.out.println("You have no messages yet");
					}
					for(Message m : mine) {
						System.out.println(m.getId() + ": " + m.getAuthor().getUsername() + ": " + m.getText() + " (" + m.getTimestamp() + ")");
					}
					break;
				
				case "2":
					ArrayList<Message> mine2 = controller.getMyMessages();
					if(mine2.isEmpty()) {
						System.out.println("You have no messages to delete");
						break;
					}
					for(Message m : mine2) {
						System.out.println(m.getId() + ": " + m.getText());
					}
					System.out.print("Enter a ID that you want to delete: ");
					int id = Integer.parseInt(scanner.nextLine());
					if(controller.deleteMessage(id)) {
						System.out.println("Message deleted!");
					}else {
						System.out.println("There are no messages with that ID!");
					}
					break;
				case "4":
					ArrayList<Message> all = controller.getAllMessages();
					if(all.isEmpty()) {
						System.out.println("No messages has been stored yet!");
					}
					for(Message m : all) {
						System.out.println(m.getId() + ": " + m.getAuthor().getUsername() + ": " + m.getText() + " (" + m.getTimestamp() + ")");
					}
					break;
					
					
					
				case "0":
					controller.logout();
					running = false;
					break;
				default:
					System.out.println("Invalid choice");
					break;
				}
			}
			
		}
}

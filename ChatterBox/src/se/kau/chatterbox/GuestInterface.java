package se.kau.chatterbox;
import java.util.ArrayList;
import java.util.Scanner;

public class GuestInterface {
	private ChatterBoxController controller;
	private Scanner scanner;
	
	public GuestInterface(ChatterBoxController controller, Scanner scanner) {
		this.controller = controller;
		this.scanner = scanner;
	}
	public void showGuestMenu() {
		boolean running = true;
		
		while(running) {
			System.out.println("--- Guest Menu ---");
			System.out.println("1. View all messages");
			System.out.println("0. Return");
			System.out.println("Enter your choice: ");
			String choice = scanner.nextLine();
			
			switch(choice) {
			
			case "1":
				ArrayList<Message> all = controller.getAllMessages();
				
				if(all.isEmpty()) {
					System.out.println("No messages has ben stored yet");
				}
				for(Message m : all) {
					System.out.println(m.getId() + ": " + m.getAuthor().getUsername() + ": " + m.getText() + " (" + m.getTimestamp() + ")");
				}
				break;
			
			case "0":
				running = false;
				break;
			
			default:
				System.out.println("Invalid choice");
			}
		}
	}
}

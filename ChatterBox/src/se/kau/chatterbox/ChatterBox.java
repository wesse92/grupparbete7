package se.kau.chatterbox;
import java.util.Scanner;

public class ChatterBox {
		public static void main(String[] args) {
			Scanner scanner = new Scanner(System.in);
			ChatterBoxController c = new ChatterBoxController();
			UserInterface ui = new UserInterface(c, scanner);
			
			boolean running = true;
			while(running) {
				System.out.println("--- ChatterBox ---");
				System.out.println("1. Login");
				System.out.println("2. Guest");
				System.out.println("0. Exit");
				System.out.println("Enter choice: ");
				String choice = scanner.nextLine();
				
				switch(choice) {
				case "1":
					if(ui.showLogin()) {
						ui.showUserMenu();
					}
					break;
				case "2":
					GuestInterface guest = new GuestInterface(c, scanner);
					guest.showGuestMenu();
					break;
				case "0":
					running = false;
					break;
				default:
					System.out.println("Invalid choice");
					break;
				}
			}
			scanner.close();
		}
	}



package cit_207_finalProject;
import java.util.*;

public class Final_java_project {
	static Scanner sc = new Scanner(System.in);
    static final String STAFF_USERNAME = "admin";
    static final String STAFF_PASSWORD = "admin123";

    static ArrayList<MenuItem> menu = new ArrayList<>();
    static ArrayList<Table> tables = new ArrayList<>();

    public static void main(String[] args) {
        MenuManager.loadDefaultMenu();
        TableManager.loadDefaultTables();

        while (true) {
            System.out.println("\n========================================");
            System.out.println("     RESTAURANT RESERVATION & BILLING");
            System.out.println("========================================");
            System.out.println("1. Check Menu");
            System.out.println("2. Check Tables");
            System.out.println("3. Make Reservation");
            System.out.println("4. Cancel Reservation");
            System.out.println("5. Staff Management");
            System.out.println("6. Exit");

            int choice = InputHelper.readInt("Choose: ");

            if (choice == 1)
                MenuManager.showMenu();
            else if (choice == 2)
                TableManager.checkTables();
            else if (choice == 3)
                ReservationManager.makeReservation();
            else if (choice == 4)
                ReservationManager.cancelReservation();
            else if (choice == 5) {
                if (StaffManager.login())
                    StaffManager.management();
            }
            else if (choice == 6) {
                System.out.println("\nThank you for using the system!");
                break;
            }
            else
                System.out.println("Invalid choice.");
        }

        sc.close();
    }
}

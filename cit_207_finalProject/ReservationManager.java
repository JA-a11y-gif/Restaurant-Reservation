package cit_207_finalProject;

public class ReservationManager {

	public static void makeReservation() {

        if (Final_java_project.tables.size() == 0) {
            System.out.println("There are no tables available.");
            return;
        }

        System.out.println("\n================================");
        System.out.println("        MAKE RESERVATION");
        System.out.println("================================");

        String name = InputHelper.readNonEmptyLine("Customer name: ");

        int guests;

        while (true) {

            guests = InputHelper.readInt("Number of guests: ");

            if (guests <= 0) {
                System.out.println("Number of guests must be greater than 0.");
                continue;
            }

            // Check if ANY available table can fit this guest capacity
            boolean suitableTableExists = false;
            for (Table t : Final_java_project.tables) {
                if (!t.reserved && t.capacity >= guests) {
                    suitableTableExists = true;
                    break;
                }
            }

            if (!suitableTableExists) {
                System.out.println("\n[!] No available table can accommodate " + guests + " guests.");
                System.out.println("1. Try a different number of guests");
                System.out.println("0. Back to Main Menu");

                int option = InputHelper.readInt("Choose option: ");

                if (option == 0) {
                    System.out.println("Returning to main menu...");
                    return;
                }
                continue; 
            }

            break;
        }

        String date = InputHelper.readDate("Date (MM/DD/YYYY): ");
        String time = InputHelper.readTime("Time (example: 7:30 PM): ");

        TableManager.checkTables();

        Table selected;

        while (true) {

            int number = InputHelper.readInt("\nChoose table number: ");

            selected = TableManager.findTable(number);

            if (selected == null) {
                System.out.println("There is no table with that number. Try again.");
                continue;
            }

            if (selected.reserved) {
                System.out.println("Table is already reserved. Choose another.");
                continue;
            }

            // ONLY show the option to enter '0' to exit if capacity is exceeded
            if (guests > selected.capacity) {
                System.out.println("This table can only accommodate " + selected.capacity + " guests.");
                int choice = InputHelper.readInt("Enter 1 to choose another table, or 0 to go back to Main Menu: ");
                
                if (choice == 0) {
                    System.out.println("Returning to main menu...");
                    return;
                }
                continue;
            }

            break;
        }

        selected.reserved = true;
        selected.customer = name;
        selected.date = date;
        selected.time = time;

        System.out.println("\n================================");
        System.out.println("     RESERVATION CONFIRMED");
        System.out.println("================================");
        System.out.println("Customer: " + name);
        System.out.println("Table: " + selected.number);
        System.out.println("Location: " + selected.location);
        System.out.println("Guests: " + guests);
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);

        OrderManager.order(
            name,
            selected.number,
            date,
            time
        );
    }

    public static void cancelReservation() {

        if (Final_java_project.tables.size() == 0) {
            System.out.println("There are no tables.");
            return;
        }

        System.out.println("\n================================");
        System.out.println("       CANCEL RESERVATION");
        System.out.println("================================");

        TableManager.checkTables();

        int number = InputHelper.readInt("\nEnter table number: ");

        Table table = TableManager.findTable(number);

        if (table == null) {
            System.out.println("Invalid table.");
            return;
        }

        if (!table.reserved) {
            System.out.println("There is no reservation on this table.");
            return;
        }

        System.out.println("\nReservation Details");
        System.out.println("Customer: " + table.customer);
        System.out.println("Date: " + table.date);
        System.out.println("Time: " + table.time);

        System.out.print("Cancel reservation? (Y/N): ");

        String confirm = Final_java_project.sc.nextLine();

        if (confirm.equalsIgnoreCase("Y")) {

            table.reserved = false;
            table.customer = null;
            table.date = null;
            table.time = null;

            System.out.println("Reservation cancelled successfully.");
        }
        else {
            System.out.println("Cancellation stopped.");
        }
    }
}
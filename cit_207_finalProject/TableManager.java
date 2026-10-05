package cit_207_finalProject;

public class TableManager {
	 public static void loadDefaultTables() {

	        Final_java_project.tables.add(
	            new Table(1, 4, "Window")
	        );

	        Final_java_project.tables.add(
	            new Table(2, 2, "Center")
	        );

	        Final_java_project.tables.add(
	            new Table(3, 6, "Patio")
	        );
	    }

	    public static void checkTables() {

	        System.out.println("\n================================");
	        System.out.println("          TABLE STATUS");
	        System.out.println("================================");

	        if (Final_java_project.tables.size() == 0) {
	            System.out.println("No tables available.");
	            return;
	        }

	        for (Table table : Final_java_project.tables) {

	            System.out.println(
	                "Table " + table.number +
	                " | Capacity: " + table.capacity +
	                " | Location: " + table.location +
	                " | " +
	                (table.reserved ? "RESERVED" : "AVAILABLE")
	            );

	            if (table.reserved) {

	                System.out.println(
	                    "   Customer: " + table.customer
	                );

	                System.out.println(
	                    "   Date: " + table.date
	                );

	                System.out.println(
	                    "   Time: " + table.time
	                );
	            }
	        }
	    }

	    public static Table findTable(int number) {

	        for (Table table : Final_java_project.tables) {

	            if (table.number == number)
	                return table;
	        }

	        return null;
	    }

	    public static void addTable() {

	        System.out.println("\n===== ADD TABLE =====");

	        int newNumber = 1;

	        for (Table table : Final_java_project.tables) {

	            if (table.number >= newNumber)
	                newNumber = table.number + 1;
	        }

	        System.out.println(
	            "New table number: " + newNumber
	        );

	        int capacity;

	        while (true) {

	            capacity =
	                InputHelper.readInt("Table capacity: ");

	            if (capacity > 0)
	                break;

	            System.out.println(
	                "Capacity must be greater than 0."
	            );
	        }

	        String location =
	            InputHelper.readNonEmptyLine(
	                "Table location: "
	            );

	        Final_java_project.tables.add(
	            new Table(
	                newNumber,
	                capacity,
	                location
	            )
	        );

	        System.out.println(
	            "Table " + newNumber +
	            " added successfully."
	        );
	    }

	    public static void removeTable() {

	        if (Final_java_project.tables.size() == 0) {
	            System.out.println("No tables available.");
	            return;
	        }

	        checkTables();

	        int number =
	            InputHelper.readInt(
	                "\nEnter table number to remove: "
	            );

	        Table table = findTable(number);

	        if (table == null) {
	            System.out.println("Invalid table.");
	            return;
	        }

	        if (table.reserved) {
	            System.out.println(
	                "Cannot remove a reserved table."
	            );
	            return;
	        }

	        System.out.println(
	            "Table " + table.number +
	            " | Capacity: " + table.capacity +
	            " | Location: " + table.location
	        );

	        System.out.print("Remove this table? (Y/N): ");

	        String confirm =
	            Final_java_project.sc.nextLine();

	        if (confirm.equalsIgnoreCase("Y")) {

	            Final_java_project.tables.remove(table);

	            System.out.println(
	                "Table removed successfully."
	            );
	        }
	        else {
	            System.out.println("Removal cancelled.");
	        }
	    }

	    public static void changeTableCapacity() {

	        if (Final_java_project.tables.size() == 0) {
	            System.out.println("No tables available.");
	            return;
	        }

	        checkTables();

	        int number =
	            InputHelper.readInt(
	                "\nEnter table number: "
	            );

	        Table table = findTable(number);

	        if (table == null) {
	            System.out.println("Invalid table.");
	            return;
	        }

	        System.out.println(
	            "Current capacity: " + table.capacity
	        );

	        int newCapacity;

	        while (true) {

	            newCapacity =
	                InputHelper.readInt("New capacity: ");

	            if (newCapacity > 0)
	                break;

	            System.out.println(
	                "Capacity must be greater than 0."
	            );
	        }

	        table.capacity = newCapacity;

	        System.out.println(
	            "Table " + table.number +
	            " capacity changed to " +
	            newCapacity + "."
	        );
	    }
	}
package cit_207_finalProject;

public class MenuManager {
	 public static void loadDefaultMenu() {
	        Final_java_project.menu.add(
	            new MenuItem("Adobo", "Food", 150, 10)
	        );

	        Final_java_project.menu.add(
	            new MenuItem("Sinigang", "Food", 200, 5)
	        );

	        Final_java_project.menu.add(
	            new MenuItem("Halo-Halo", "Beverage", 120, 8)
	        );

	        Final_java_project.menu.add(
	            new MenuItem("Iced Tea", "Beverage", 50, 20)
	        );
	    }

	    public static void showMenu() {

	        while (true) {
	            System.out.println("\n================================");
	            System.out.println("             MENU");
	            System.out.println("================================");
	            System.out.println("1. Food");
	            System.out.println("2. Beverages");
	            System.out.println("3. Show All");
	            System.out.println("4. Back");

	            int choice = InputHelper.readInt("Choose category: ");

	            if (choice == 1)
	                displayCategory("Food");

	            else if (choice == 2)
	                displayCategory("Beverage");

	            else if (choice == 3)
	                displayAllMenu();

	            else if (choice == 4)
	                return;

	            else
	                System.out.println("Invalid choice.");
	        }
	    }

	    public static void displayCategory(String category) {

	        System.out.println("\n===== " + category.toUpperCase() + " =====");

	        boolean found = false;

	        for (int i = 0; i < Final_java_project.menu.size(); i++) {

	            MenuItem item = Final_java_project.menu.get(i);

	            if (item.category.equals(category)) {
	                found = true;

	                System.out.println(
	                    (i + 1) + ". " + item.name +
	                    " | PHP " + item.price +
	                    " | Stock: " + item.stock
	                );
	            }
	        }

	        if (!found)
	            System.out.println("No items available.");
	    }

	    public static void displayAllMenu() {

	        System.out.println("\n========== FOOD ==========");

	        boolean foodFound = false;

	        for (int i = 0; i < Final_java_project.menu.size(); i++) {

	            MenuItem item = Final_java_project.menu.get(i);

	            if (item.category.equals("Food")) {

	                foodFound = true;

	                System.out.println(
	                    (i + 1) + ". " + item.name +
	                    " | PHP " + item.price +
	                    " | Stock: " + item.stock
	                );
	            }
	        }

	        if (!foodFound)
	            System.out.println("No food available.");

	        System.out.println("\n======= BEVERAGES =======");

	        boolean beverageFound = false;

	        for (int i = 0; i < Final_java_project.menu.size(); i++) {

	            MenuItem item = Final_java_project.menu.get(i);

	            if (item.category.equals("Beverage")) {

	                beverageFound = true;

	                System.out.println(
	                    (i + 1) + ". " + item.name +
	                    " | PHP " + item.price +
	                    " | Stock: " + item.stock
	                );
	            }
	        }

	        if (!beverageFound)
	            System.out.println("No beverages available.");
	    }

	    public static void addMenuItem() {

	        System.out.println("\n===== ADD MENU ITEM =====");

	        String name =
	            InputHelper.readNonEmptyLine("Item name: ");

	        System.out.println("1. Food");
	        System.out.println("2. Beverage");

	        String category;

	        while (true) {

	            int choice =
	                InputHelper.readInt("Category: ");

	            if (choice == 1) {
	                category = "Food";
	                break;
	            }

	            if (choice == 2) {
	                category = "Beverage";
	                break;
	            }

	            System.out.println(
	                "Invalid category. Choose 1 or 2."
	            );
	        }

	        double price;

	        while (true) {

	            price =
	                InputHelper.readDouble("Price: PHP ");

	            if (price > 0)
	                break;

	            System.out.println(
	                "Price must be greater than 0."
	            );
	        }

	        int stock;

	        while (true) {

	            stock =
	                InputHelper.readInt("Quantity/Stock: ");

	            if (stock >= 0)
	                break;

	            System.out.println(
	                "Stock cannot be negative."
	            );
	        }

	        Final_java_project.menu.add(
	            new MenuItem(name, category, price, stock)
	        );

	        System.out.println(
	            "Menu item added successfully."
	        );
	    }

	    public static void removeMenuItem() {

	        if (Final_java_project.menu.size() == 0) {
	            System.out.println("No menu items available.");
	            return;
	        }

	        displayAllMenu();

	        int number =
	            InputHelper.readInt(
	                "\nEnter menu number to remove: "
	            );

	        if (number < 1 ||
	            number > Final_java_project.menu.size()) {

	            System.out.println("Invalid menu number.");
	            return;
	        }

	        MenuItem item =
	            Final_java_project.menu.get(number - 1);

	        System.out.println("Selected: " + item.name);

	        System.out.print("Remove this item? (Y/N): ");

	        String confirm =
	            Final_java_project.sc.nextLine();

	        if (confirm.equalsIgnoreCase("Y")) {

	            Final_java_project.menu.remove(number - 1);

	            System.out.println(
	                "Menu item removed successfully."
	            );
	        }
	        else {
	            System.out.println("Removal cancelled.");
	        }
	    }

	    public static void updateMenuItem() {

	        if (Final_java_project.menu.size() == 0) {
	            System.out.println("No menu items available.");
	            return;
	        }

	        displayAllMenu();

	        int number =
	            InputHelper.readInt(
	                "\nEnter menu number to update: "
	            );

	        if (number < 1 ||
	            number > Final_java_project.menu.size()) {

	            System.out.println("Invalid menu number.");
	            return;
	        }

	        MenuItem item =
	            Final_java_project.menu.get(number - 1);

	        System.out.println("\nCurrent Item: " + item.name);
	        System.out.println("Current Price: PHP " + item.price);
	        System.out.println("Current Stock: " + item.stock);

	        double newPrice;

	        while (true) {

	            newPrice =
	                InputHelper.readDouble(
	                    "\nNew price: PHP "
	                );

	            if (newPrice > 0)
	                break;

	            System.out.println(
	                "Price must be greater than 0."
	            );
	        }

	        int newStock;

	        while (true) {

	            newStock =
	                InputHelper.readInt("New stock: ");

	            if (newStock >= 0)
	                break;

	            System.out.println(
	                "Stock cannot be negative."
	            );
	        }

	        item.price = newPrice;
	        item.stock = newStock;

	        System.out.println(
	            "Menu item updated successfully."
	        );
	    }
	}
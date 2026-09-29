import java.util.ArrayList;
import java.util.Scanner;

public class Java_project {

    static Scanner sc = new Scanner(System.in);

    static class MenuItem {
        String name, category;
        double price;
        int stock;
        MenuItem(String name, String category, double price, int stock) {
            this.name = name; this.category = category; this.price = price; this.stock = stock;
        }
    }
    static ArrayList<MenuItem> menu = new ArrayList<>();

    static class Table {
        int number, capacity;
        String location, customer, date, time;
        boolean reserved;
        Table(int number, int capacity, String location) {
            this.number = number; this.capacity = capacity; this.location = location; this.reserved = false;
        }
    }
    static ArrayList<Table> tables = new ArrayList<>();

    public static void main(String[] args) {
        loadDefaultMenu();
        loadDefaultTables();

        while (true) {
            System.out.println("\n========================================");
            System.out.println("   RESTAURANT RESERVATION & BILLING");
            System.out.println("========================================");
            System.out.println("1. Check Menu\n2. Check Tables\n3. Make Reservation\n4. Cancel Reservation\n5. Staff Management\n6. Exit");
            int choice = readInt("Choose: ");

            if (choice == 1) showMenu();
            else if (choice == 2) checkTables();
            else if (choice == 3) makeReservation();
            else if (choice == 4) cancelReservation();
            else if (choice == 5) staffManagement();
            else if (choice == 6) { System.out.println("\nThank you for using the system!"); break; }
            else System.out.println("Invalid choice.");
        }
        sc.close();
    }

    static void loadDefaultMenu() {
        menu.add(new MenuItem("Adobo", "Food", 150, 10));
        menu.add(new MenuItem("Sinigang", "Food", 200, 5));
        menu.add(new MenuItem("Halo-Halo", "Beverage", 120, 8));
        menu.add(new MenuItem("Iced Tea", "Beverage", 50, 20));
    }

    static void loadDefaultTables() {
        tables.add(new Table(1, 4, "Window"));
        tables.add(new Table(2, 2, "Center"));
        tables.add(new Table(3, 6, "Patio"));
    }

    static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    static String readNonEmptyLine(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            if (!line.isEmpty()) return line;
            System.out.println("This field cannot be empty.");
        }
    }

    static boolean isValidDate(String date) {
        return date != null && date.matches("(0[1-9]|1[0-2])/(0[1-9]|[12]\\d|3[01])/\\d{4}");
    }

    static String readDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            String date = sc.nextLine().trim();
            if (isValidDate(date)) return date;
            System.out.println("Invalid date. Please use MM/DD/YYYY (e.g., 09/28/2026).");
        }
    }

    static boolean isValidTime(String time) {
        return time != null && time.matches("(1[0-2]|0?[1-9]):[0-5]\\d\\s?(?i)(AM|PM)");
    }

    static String readTime(String prompt) {
        while (true) {
            System.out.print(prompt);
            String time = sc.nextLine().trim();
            if (isValidTime(time)) return time;
            System.out.println("Invalid time. Please use H:MM AM/PM (e.g., 7:30 PM).");
        }
    }

    static void showMenu() {
        while (true) {
            System.out.println("\n================================\n             MENU\n================================");
            System.out.println("1. Food\n2. Beverages\n3. Show All\n4. Back");
            int choice = readInt("Choose category: ");

            if (choice == 1) displayCategory("Food");
            else if (choice == 2) displayCategory("Beverage");
            else if (choice == 3) displayAllMenu();
            else if (choice == 4) return;
            else System.out.println("Invalid choice.");
        }
    }

    static void displayCategory(String category) {
        System.out.println("\n===== " + category.toUpperCase() + " =====");
        boolean found = false;
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.get(i);
            if (item.category.equals(category)) {
                found = true;
                System.out.println((i + 1) + ". " + item.name + " | PHP " + item.price + " | Stock: " + item.stock);
            }
        }
        if (!found) System.out.println("No items available.");
    }

    static void displayAllMenu() {
        System.out.println("\n========== FOOD ==========");
        boolean foodFound = false;
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.get(i);
            if (item.category.equals("Food")) {
                foodFound = true;
                System.out.println((i + 1) + ". " + item.name + " | PHP " + item.price + " | Stock: " + item.stock);
            }
        }
        if (!foodFound) System.out.println("No food available.");

        System.out.println("\n======= BEVERAGES =======");
        boolean beverageFound = false;
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.get(i);
            if (item.category.equals("Beverage")) {
                beverageFound = true;
                System.out.println((i + 1) + ". " + item.name + " | PHP " + item.price + " | Stock: " + item.stock);
            }
        }
        if (!beverageFound) System.out.println("No beverages available.");
    }

    static void checkTables() {
        System.out.println("\n================================\n          TABLE STATUS\n================================");
        if (tables.size() == 0) { System.out.println("No tables available."); return; }

        for (Table table : tables) {
            System.out.println("Table " + table.number + " | Capacity: " + table.capacity + " | Location: " + table.location
                + " | " + (table.reserved ? "RESERVED" : "AVAILABLE"));
            if (table.reserved) {
                System.out.println("   Customer: " + table.customer);
                System.out.println("   Date: " + table.date);
                System.out.println("   Time: " + table.time);
            }
        }
    }

    static void makeReservation() {
        if (tables.size() == 0) { System.out.println("There are no tables available."); return; }

        System.out.println("\n================================\n        MAKE RESERVATION\n================================");
        String name = readNonEmptyLine("Customer name: ");

        int guests;
        while (true) {
            guests = readInt("Number of guests: ");
            if (guests > 0) break;
            System.out.println("Number of guests must be greater than 0.");
        }

        String date = readDate("Date (MM/DD/YYYY): ");
        String time = readTime("Time (example: 7:30 PM): ");

        checkTables();

        Table selected;
        while (true) {
            int number = readInt("\nChoose table number: ");
            selected = findTable(number);
            if (selected == null) {
                System.out.println("There is no table with that number. Try again.");
                continue;
            }
            if (selected.reserved) {
                System.out.println("Table is already reserved. Choose another.");
                continue;
            }
            if (guests > selected.capacity) {
                System.out.println("This table can only accommodate " + selected.capacity + " guests. Choose another.");
                continue;
            }
            break;
        }

        selected.reserved = true;
        selected.customer = name;
        selected.date = date;
        selected.time = time;

        System.out.println("\n================================\n     RESERVATION CONFIRMED\n================================");
        System.out.println("Customer: " + name);
        System.out.println("Table: " + selected.number);
        System.out.println("Location: " + selected.location);
        System.out.println("Guests: " + guests);
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);

        order(name, selected.number, date, time);
    }

    static void cancelReservation() {
        if (tables.size() == 0) { System.out.println("There are no tables."); return; }

        System.out.println("\n================================\n       CANCEL RESERVATION\n================================");
        checkTables();

        int number = readInt("\nEnter table number: ");
        Table table = findTable(number);
        if (table == null) { System.out.println("Invalid table."); return; }
        if (!table.reserved) { System.out.println("There is no reservation on this table."); return; }

        System.out.println("\nReservation Details");
        System.out.println("Customer: " + table.customer);
        System.out.println("Date: " + table.date);
        System.out.println("Time: " + table.time);

        System.out.print("Cancel reservation? (Y/N): ");
        String confirm = sc.nextLine();

        if (confirm.equalsIgnoreCase("Y")) {
            table.reserved = false;
            table.customer = null;
            table.date = null;
            table.time = null;
            System.out.println("Reservation cancelled successfully.");
        } else {
            System.out.println("Cancellation stopped.");
        }
    }

    static Table findTable(int number) {
        for (Table table : tables) if (table.number == number) return table;
        return null;
    }

    static void order(String name, int tableNumber, String date, String time) {
        int[] orderQty = new int[menu.size()];

        while (true) {
            System.out.println("\n================================\n          ORDER SYSTEM\n================================");
            System.out.println("Customer: " + name);
            System.out.println("Table: " + tableNumber);
            System.out.println("--------------------------------");
            System.out.println("1. Food\n2. Beverages\n3. View Current Order\n4. Cancel Order\n5. Checkout\n6. Finish");
            int choice = readInt("Choose: ");

            if (choice == 1) chooseItem("Food", orderQty);
            else if (choice == 2) chooseItem("Beverage", orderQty);
            else if (choice == 3) showCurrentOrder(orderQty);
            else if (choice == 4) cancelOrder(orderQty);
            else if (choice == 5) { if (checkout(orderQty, name, tableNumber, date, time)) return; }
            else if (choice == 6) { System.out.println("Returning to main menu."); return; }
            else System.out.println("Invalid choice.");
        }
    }

    static void chooseItem(String selectedCategory, int[] orderQty) {
        System.out.println("\n===== " + selectedCategory.toUpperCase() + " =====");

        ArrayList<Integer> itemNumbers = new ArrayList<>();
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.get(i);
            if (item.category.equals(selectedCategory)) {
                itemNumbers.add(i);
                System.out.println(itemNumbers.size() + ". " + item.name + " | PHP " + item.price + " | Stock: " + item.stock);
            }
        }
        if (itemNumbers.size() == 0) { System.out.println("No items available."); return; }

        int choice;
        while (true) {
            choice = readInt("\nChoose item number: ");
            if (choice >= 1 && choice <= itemNumbers.size()) break;
            System.out.println("Invalid item. Try again.");
        }

        int index = itemNumbers.get(choice - 1);
        MenuItem item = menu.get(index);
        if (item.stock <= 0) { System.out.println("This item is out of stock."); return; }

        int quantity;
        while (true) {
            quantity = readInt("Enter quantity: ");
            if (quantity <= 0) { System.out.println("Quantity must be greater than 0."); continue; }
            if (quantity > item.stock) { System.out.println("Not enough stock. Available: " + item.stock); continue; }
            break;
        }

        orderQty[index] += quantity;
        item.stock -= quantity;
        System.out.println(quantity + " " + item.name + " added to your order.");
    }

    static void showCurrentOrder(int[] orderQty) {
        System.out.println("\n===== CURRENT ORDER =====");
        boolean empty = true;
        double total = 0;

        for (int i = 0; i < menu.size(); i++) {
            if (orderQty[i] > 0) {
                empty = false;
                MenuItem item = menu.get(i);
                double itemTotal = orderQty[i] * item.price;
                total += itemTotal;
                System.out.println(item.name + " x" + orderQty[i] + " @ PHP " + item.price + " = PHP " + itemTotal);
            }
        }

        if (empty) System.out.println("No items ordered.");
        else {
            System.out.println("----------------------------");
            System.out.println("TOTAL: PHP " + total);
        }
    }

    static void cancelOrder(int[] orderQty) {
        boolean hasOrder = false;
        for (int i = 0; i < menu.size(); i++) {
            if (orderQty[i] > 0) {
                MenuItem item = menu.get(i);
                item.stock += orderQty[i];
                orderQty[i] = 0;
                hasOrder = true;
            }
        }
        if (hasOrder) {
            System.out.println("Order cancelled successfully.");
            System.out.println("Items returned to stock.");
        } else {
            System.out.println("There is no order to cancel.");
        }
    }

    static boolean checkout(int[] orderQty, String name, int tableNumber, String date, String time) {
        double total = 0;
        for (int i = 0; i < menu.size(); i++) total += orderQty[i] * menu.get(i).price;
        if (total == 0) { System.out.println("You have not ordered anything."); return false; }

        System.out.println("\n================================\n              BILL\n================================");
        System.out.println("Customer : " + name);
        System.out.println("Table    : " + tableNumber);
        System.out.println("Date     : " + date);
        System.out.println("Time     : " + time);
        System.out.println("--------------------------------\nORDERED ITEMS\n--------------------------------");

        for (int i = 0; i < menu.size(); i++) {
            if (orderQty[i] > 0) {
                MenuItem item = menu.get(i);
                double itemTotal = orderQty[i] * item.price;
                System.out.println(item.name + " x" + orderQty[i] + " @ PHP " + item.price + " = PHP " + itemTotal);
            }
        }

        System.out.println("--------------------------------");
        System.out.println("TOTAL: PHP " + total);

        double payment;
        while (true) {
            payment = readDouble("Payment: PHP ");
            if (payment >= total) break;
            System.out.println("Insufficient payment. You still owe PHP " + (total - payment) + ".");
        }

        double change = payment - total;
        System.out.println("Payment: PHP " + payment);
        System.out.println("Change: PHP " + change);
        System.out.println("================================\n          THANK YOU!\n================================");
        return true;
    }

    static void staffManagement() {
        while (true) {
            System.out.println("\n================================\n       STAFF MANAGEMENT\n================================");
            System.out.println("1. Add Menu Item\n2. Remove Menu Item\n3. Update Menu Item\n4. Add Table\n5. Remove Table\n6. Change Table Capacity\n7. View Menu\n8. View Tables\n9. Back");
            int choice = readInt("Choose: ");

            if (choice == 1) addMenuItem();
            else if (choice == 2) removeMenuItem();
            else if (choice == 3) updateMenuItem();
            else if (choice == 4) addTable();
            else if (choice == 5) removeTable();
            else if (choice == 6) changeTableCapacity();
            else if (choice == 7) displayAllMenu();
            else if (choice == 8) checkTables();
            else if (choice == 9) return;
            else System.out.println("Invalid choice.");
        }
    }

    static void addMenuItem() {
        System.out.println("\n===== ADD MENU ITEM =====");
        String name = readNonEmptyLine("Item name: ");

        System.out.println("1. Food\n2. Beverage");
        String category;
        while (true) {
            int categoryChoice = readInt("Category: ");
            if (categoryChoice == 1) { category = "Food"; break; }
            if (categoryChoice == 2) { category = "Beverage"; break; }
            System.out.println("Invalid category. Choose 1 or 2.");
        }

        double price;
        while (true) {
            price = readDouble("Price: PHP ");
            if (price > 0) break;
            System.out.println("Price must be greater than 0.");
        }

        int stock;
        while (true) {
            stock = readInt("Quantity/Stock: ");
            if (stock >= 0) break;
            System.out.println("Stock cannot be negative.");
        }

        menu.add(new MenuItem(name, category, price, stock));
        System.out.println("Menu item added successfully.");
    }

    static void removeMenuItem() {
        if (menu.size() == 0) { System.out.println("No menu items available."); return; }

        displayAllMenu();
        int number = readInt("\nEnter menu number to remove: ");
        if (number < 1 || number > menu.size()) { System.out.println("Invalid menu number."); return; }

        MenuItem item = menu.get(number - 1);
        System.out.println("Selected: " + item.name);
        System.out.print("Remove this item? (Y/N): ");
        String confirm = sc.nextLine();

        if (confirm.equalsIgnoreCase("Y")) {
            menu.remove(number - 1);
            System.out.println("Menu item removed successfully.");
        } else {
            System.out.println("Removal cancelled.");
        }
    }

    static void updateMenuItem() {
        if (menu.size() == 0) { System.out.println("No menu items available."); return; }

        displayAllMenu();
        int number = readInt("\nEnter menu number to update: ");
        if (number < 1 || number > menu.size()) { System.out.println("Invalid menu number."); return; }

        MenuItem item = menu.get(number - 1);
        System.out.println("\nCurrent Item: " + item.name);
        System.out.println("Current Price: PHP " + item.price);
        System.out.println("Current Stock: " + item.stock);

        double newPrice;
        while (true) {
            newPrice = readDouble("\nNew price: PHP ");
            if (newPrice > 0) break;
            System.out.println("Price must be greater than 0.");
        }

        int newStock;
        while (true) {
            newStock = readInt("New stock: ");
            if (newStock >= 0) break;
            System.out.println("Stock cannot be negative.");
        }

        item.price = newPrice;
        item.stock = newStock;
        System.out.println("Menu item updated successfully.");
    }

    static void addTable() {
        System.out.println("\n===== ADD TABLE =====");

        int newNumber = 1;
        for (Table table : tables) if (table.number >= newNumber) newNumber = table.number + 1;
        System.out.println("New table number: " + newNumber);

        int capacity;
        while (true) {
            capacity = readInt("Table capacity: ");
            if (capacity > 0) break;
            System.out.println("Capacity must be greater than 0.");
        }

        String location = readNonEmptyLine("Table location: ");

        tables.add(new Table(newNumber, capacity, location));
        System.out.println("Table " + newNumber + " added successfully.");
    }

    static void removeTable() {
        if (tables.size() == 0) { System.out.println("No tables available."); return; }

        checkTables();
        int number = readInt("\nEnter table number to remove: ");

        Table table = findTable(number);
        if (table == null) { System.out.println("Invalid table."); return; }
        if (table.reserved) { System.out.println("Cannot remove a reserved table."); return; }

        System.out.println("Table " + table.number + " | Capacity: " + table.capacity + " | Location: " + table.location);
        System.out.print("Remove this table? (Y/N): ");
        String confirm = sc.nextLine();

        if (confirm.equalsIgnoreCase("Y")) {
            tables.remove(table);
            System.out.println("Table removed successfully.");
        } else {
            System.out.println("Removal cancelled.");
        }
    }

    static void changeTableCapacity() {
        if (tables.size() == 0) { System.out.println("No tables available."); return; }

        checkTables();
        int number = readInt("\nEnter table number: ");

        Table table = findTable(number);
        if (table == null) { System.out.println("Invalid table."); return; }

        System.out.println("Current capacity: " + table.capacity);
        int newCapacity;
        while (true) {
            newCapacity = readInt("New capacity: ");
            if (newCapacity > 0) break;
            System.out.println("Capacity must be greater than 0.");
        }

        table.capacity = newCapacity;
        System.out.println("Table " + table.number + " capacity changed to " + newCapacity + ".");
    }
}
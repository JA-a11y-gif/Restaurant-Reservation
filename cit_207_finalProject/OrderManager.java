package cit_207_finalProject;
import java.util.ArrayList;
public class OrderManager {
	public static void order(
	        String name,
	        int tableNumber,
	        String date,
	        String time
	    ) {

	        int[] orderQty =
	            new int[Final_java_project.menu.size()];

	        while (true) {

	            System.out.println("\n================================");
	            System.out.println("          ORDER SYSTEM");
	            System.out.println("================================");
	            System.out.println("Customer: " + name);
	            System.out.println("Table: " + tableNumber);
	            System.out.println("--------------------------------");
	            System.out.println("1. Food");
	            System.out.println("2. Beverages");
	            System.out.println("3. View Current Order");
	            System.out.println("4. Cancel Order");
	            System.out.println("5. Checkout");
	            System.out.println("6. Finish");

	            int choice =
	                InputHelper.readInt("Choose: ");

	            if (choice == 1)
	                chooseItem("Food", orderQty);

	            else if (choice == 2)
	                chooseItem("Beverage", orderQty);

	            else if (choice == 3)
	                showCurrentOrder(orderQty);

	            else if (choice == 4)
	                cancelOrder(orderQty);

	            else if (choice == 5) {

	                if (CheckoutManager.checkout(
	                    orderQty,
	                    name,
	                    tableNumber,
	                    date,
	                    time
	                ))
	                    return;
	            }

	            else if (choice == 6) {
	                System.out.println(
	                    "Returning to main menu."
	                );
	                return;
	            }

	            else
	                System.out.println("Invalid choice.");
	        }
	    }

	    public static void chooseItem(
	        String selectedCategory,
	        int[] orderQty
	    ) {

	        System.out.println(
	            "\n===== " +
	            selectedCategory.toUpperCase() +
	            " ====="
	        );

	        ArrayList<Integer> itemNumbers =
	            new ArrayList<>();

	        for (int i = 0;
	             i < Final_java_project.menu.size();
	             i++) {

	            MenuItem item =
	                Final_java_project.menu.get(i);

	            if (item.category.equals(selectedCategory)) {

	                itemNumbers.add(i);

	                System.out.println(
	                    itemNumbers.size() +
	                    ". " + item.name +
	                    " | PHP " + item.price +
	                    " | Stock: " + item.stock
	                );
	            }
	        }

	        if (itemNumbers.size() == 0) {
	            System.out.println("No items available.");
	            return;
	        }

	        int choice;

	        while (true) {

	            choice =
	                InputHelper.readInt(
	                    "\nChoose item number: "
	                );

	            if (choice >= 1 &&
	                choice <= itemNumbers.size())
	                break;

	            System.out.println(
	                "Invalid item. Try again."
	            );
	        }

	        int index =
	            itemNumbers.get(choice - 1);

	        MenuItem item =
	            Final_java_project.menu.get(index);

	        if (item.stock <= 0) {
	            System.out.println(
	                "This item is out of stock."
	            );
	            return;
	        }

	        int quantity;

	        while (true) {

	            quantity =
	                InputHelper.readInt(
	                    "Enter quantity: "
	                );

	            if (quantity <= 0) {
	                System.out.println(
	                    "Quantity must be greater than 0."
	                );
	                continue;
	            }

	            if (quantity > item.stock) {
	                System.out.println(
	                    "Not enough stock. Available: " +
	                    item.stock
	                );
	                continue;
	            }

	            break;
	        }

	        orderQty[index] += quantity;
	        item.stock -= quantity;

	        System.out.println(
	            quantity + " " +
	            item.name +
	            " added to your order."
	        );
	    }

	    public static void showCurrentOrder(
	        int[] orderQty
	    ) {

	        System.out.println(
	            "\n===== CURRENT ORDER ====="
	        );

	        boolean empty = true;
	        double total = 0;

	        for (int i = 0;
	             i < Final_java_project.menu.size();
	             i++) {

	            if (orderQty[i] > 0) {

	                empty = false;

	                MenuItem item =
	                    Final_java_project.menu.get(i);

	                double itemTotal =
	                    orderQty[i] * item.price;

	                total += itemTotal;

	                System.out.printf(
	                    "%-20s x%-3d PHP %8.2f%n",
	                    item.name,
	                    orderQty[i],
	                    itemTotal
	                );
	            }
	        }

	        if (empty) {
	            System.out.println(
	                "No items ordered."
	            );
	        }
	        else {

	            System.out.println(
	                "----------------------------"
	            );

	            System.out.printf(
	                "TOTAL: PHP %.2f%n",
	                total
	            );
	        }
	    }

	    public static void cancelOrder(
	        int[] orderQty
	    ) {

	        boolean hasOrder = false;

	        for (int i = 0;
	             i < Final_java_project.menu.size();
	             i++) {

	            if (orderQty[i] > 0) {

	                MenuItem item =
	                    Final_java_project.menu.get(i);

	                item.stock += orderQty[i];
	                orderQty[i] = 0;

	                hasOrder = true;
	            }
	        }

	        if (hasOrder) {

	            System.out.println(
	                "Order cancelled successfully."
	            );

	            System.out.println(
	                "Items returned to stock."
	            );
	        }
	        else {

	            System.out.println(
	                "There is no order to cancel."
	            );
	        }
	    }
	}

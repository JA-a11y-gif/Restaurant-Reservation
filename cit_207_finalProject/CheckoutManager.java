package cit_207_finalProject;

public class CheckoutManager {
	public static boolean checkout(
	        int[] orderQty,
	        String name,
	        int tableNumber,
	        String date,
	        String time
	    ) {

	        double total = 0;

	        for (int i = 0;
	             i < Final_java_project.menu.size();
	             i++) {

	            total +=
	                orderQty[i] *
	                Final_java_project.menu.get(i).price;
	        }

	        if (total <= 0) {

	            System.out.println(
	                "\nYou have not ordered anything."
	            );

	            return false;
	        }

	        double payment;

	        while (true) {

	            System.out.println(
	                "\n========================================"
	            );

	            System.out.println(
	                "              PAYMENT"
	            );

	            System.out.println(
	                "========================================"
	            );

	            System.out.printf(
	                "TOTAL AMOUNT : PHP %.2f%n",
	                total
	            );

	            payment =
	                InputHelper.readDouble(
	                    "ENTER PAYMENT: PHP "
	                );

	            if (payment < 0) {

	                System.out.println(
	                    "Payment cannot be negative."
	                );

	                continue;
	            }

	            if (payment < total) {

	                System.out.printf(
	                    "INSUFFICIENT PAYMENT! " +
	                    "YOU STILL OWE: PHP %.2f%n",
	                    total - payment
	                );

	                System.out.println(
	                    "Please enter a sufficient amount."
	                );

	                continue;
	            }

	            break;
	        }

	        double change = payment - total;

	        System.out.println(
	            "\n\n========================================"
	        );

	        System.out.println(
	            "          RESTAURANT RECEIPT"
	        );

	        System.out.println(
	            "========================================"
	        );

	        System.out.println(
	            "Customer : " + name
	        );

	        System.out.println(
	            "Table    : " + tableNumber
	        );

	        System.out.println(
	            "Date     : " + date
	        );

	        System.out.println(
	            "Time     : " + time
	        );

	        System.out.println(
	            "----------------------------------------"
	        );

	        System.out.printf(
	            "%-20s %-5s %10s%n",
	            "ITEM",
	            "QTY",
	            "AMOUNT"
	        );

	        System.out.println(
	            "----------------------------------------"
	        );

	        for (int i = 0;
	             i < Final_java_project.menu.size();
	             i++) {

	            if (orderQty[i] > 0) {

	                MenuItem item =
	                    Final_java_project.menu.get(i);

	                double itemTotal =
	                    orderQty[i] * item.price;

	                System.out.printf(
	                    "%-20s %-5d PHP %8.2f%n",
	                    item.name,
	                    orderQty[i],
	                    itemTotal
	                );
	            }
	        }

	        System.out.println(
	            "----------------------------------------"
	        );

	        System.out.printf(
	            "%-27s PHP %8.2f%n",
	            "TOTAL:",
	            total
	        );

	        System.out.printf(
	            "%-27s PHP %8.2f%n",
	            "PAYMENT:",
	            payment
	        );

	        System.out.printf(
	            "%-27s PHP %8.2f%n",
	            "CHANGE:",
	            change
	        );

	        System.out.println(
	            "========================================"
	        );

	        System.out.println(
	            "          PAYMENT SUCCESSFUL"
	        );

	        System.out.println(
	            "          THANK YOU!"
	        );

	        System.out.println(
	            "========================================"
	        );

	        return true;
	    }
	}

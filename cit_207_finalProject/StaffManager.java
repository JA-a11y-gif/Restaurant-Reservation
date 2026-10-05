package cit_207_finalProject;

public class StaffManager {
	  public static boolean login() {

	        System.out.println(
	            "\n========================================"
	        );

	        System.out.println(
	            "             STAFF LOGIN"
	        );

	        System.out.println(
	            "========================================"
	        );

	        int attempts = 3;

	        while (attempts > 0) {

	            String username =
	                InputHelper.readNonEmptyLine(
	                    "Username: "
	                );

	            String password =
	                InputHelper.readNonEmptyLine(
	                    "Password: "
	                );

	            if (username.equals(
	                    Final_java_project.STAFF_USERNAME)
	                &&
	                password.equals(
	                    Final_java_project.STAFF_PASSWORD)) {

	                System.out.println(
	                    "\n========================================"
	                );

	                System.out.println(
	                    "          LOGIN SUCCESSFUL"
	                );

	                System.out.println(
	                    "========================================"
	                );

	                System.out.println(
	                    "Welcome, " + username + "!"
	                );

	                return true;
	            }

	            attempts--;

	            System.out.println(
	                "\nInvalid username or password."
	            );

	            if (attempts > 0)
	                System.out.println(
	                    "Attempts remaining: " + attempts
	                );
	        }

	        System.out.println(
	            "\n========================================"
	        );

	        System.out.println(
	            "          ACCESS DENIED"
	        );

	        System.out.println(
	            "========================================"
	        );

	        System.out.println(
	            "Too many failed login attempts."
	        );

	        System.out.println(
	            "Returning to main menu..."
	        );

	        return false;
	    }

	    public static void management() {

	        while (true) {

	            System.out.println(
	                "\n================================"
	            );

	            System.out.println(
	                "       STAFF MANAGEMENT"
	            );

	            System.out.println(
	                "================================"
	            );

	            System.out.println("1. Add Menu Item");
	            System.out.println("2. Remove Menu Item");
	            System.out.println("3. Update Menu Item");
	            System.out.println("4. Add Table");
	            System.out.println("5. Remove Table");
	            System.out.println("6. Change Table Capacity");
	            System.out.println("7. View Menu");
	            System.out.println("8. View Tables");
	            System.out.println("9. Logout");

	            int choice =
	                InputHelper.readInt("Choose: ");

	            if (choice == 1)
	                MenuManager.addMenuItem();

	            else if (choice == 2)
	                MenuManager.removeMenuItem();

	            else if (choice == 3)
	                MenuManager.updateMenuItem();

	            else if (choice == 4)
	                TableManager.addTable();

	            else if (choice == 5)
	                TableManager.removeTable();

	            else if (choice == 6)
	                TableManager.changeTableCapacity();

	            else if (choice == 7)
	                MenuManager.displayAllMenu();

	            else if (choice == 8)
	                TableManager.checkTables();

	            else if (choice == 9) {

	                System.out.println(
	                    "\nStaff logged out successfully."
	                );

	                return;
	            }

	            else {
	                System.out.println(
	                    "Invalid choice."
	                );
	            }
	        }
	    }
	}

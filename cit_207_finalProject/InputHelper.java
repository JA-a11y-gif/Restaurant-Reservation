package cit_207_finalProject;

public class InputHelper {
	
	    public static int readInt(String prompt) {

	        while (true) {
	            System.out.print(prompt);
	            String line = Final_java_project.sc.nextLine().trim();

	            try {
	                return Integer.parseInt(line);
	            }
	            catch (NumberFormatException e) {
	                System.out.println(
	                    "Invalid input. Please enter a whole number."
	                );
	            }
	        }
	    }

	    public static double readDouble(String prompt) {

	        while (true) {
	            System.out.print(prompt);
	            String line = Final_java_project.sc.nextLine().trim();

	            try {
	                return Double.parseDouble(line);
	            }
	            catch (NumberFormatException e) {
	                System.out.println(
	                    "Invalid input. Please enter a number."
	                );
	            }
	        }
	    }

	    public static String readNonEmptyLine(String prompt) {

	        while (true) {
	            System.out.print(prompt);
	            String line = Final_java_project.sc.nextLine().trim();

	            if (!line.isEmpty())
	                return line;

	            System.out.println("This field cannot be empty.");
	        }
	    }

	    public static boolean isValidDate(String date) {

	        return date != null &&
	               date.matches(
	                   "(0[1-9]|1[0-2])/(0[1-9]|[12]\\d|3[01])/\\d{4}"
	               );
	    }

	    public static String readDate(String prompt) {

	        while (true) {
	            System.out.print(prompt);
	            String date = Final_java_project.sc.nextLine().trim();

	            if (isValidDate(date))
	                return date;

	            System.out.println(
	                "Invalid date. Please use MM/DD/YYYY."
	            );
	        }
	    }

	    public static boolean isValidTime(String time) {

	        return time != null &&
	               time.matches(
	                   "(1[0-2]|0?[1-9]):[0-5]\\d\\s?(?i)(AM|PM)"
	               );
	    }

	    public static String readTime(String prompt) {

	        while (true) {
	            System.out.print(prompt);
	            String time = Final_java_project.sc.nextLine().trim();

	            if (isValidTime(time))
	                return time;

	            System.out.println(
	                "Invalid time. Please use H:MM AM/PM."
	            );
	        }
	    }
	}

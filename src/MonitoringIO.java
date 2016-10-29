/**
 * Created by Cree on 29/10/2016.
 */
import java.util.Scanner;
import java.util.regex.Pattern;

public class MonitoringIO {
    private static Scanner scanner = new Scanner(System.in);
    private static Monitoring monitor = new Monitoring();

    public static void main(String[] args) {

        boolean finished = false;

        while(!finished) {
            int current = mainMenu();
            if (current == 1) {
                addObservatoryMenu();
            }
            if (current == 2) {
                addObservatoryMenu();
            }
            if (current == 3) {
                addObservatoryMenu();
            }
            if (current == 4) {
                print("Exiting. Goodbye!");
                finished = true;
            }
        }

    }



    private static int mainMenu() {
        println("========================================");
        println("========EarthquakeMonitor==V0.1=========");
        println("========================================");
        println("=Select one of the following options by=");
        println("=typing the number and pressing enter.==");
        println("========================================");
        println("");
        println("1. Enter Observatory data.");
        println("2. Enter Earthquake data.");
        println("3. See Monitoring statistics.");
        println("4. Exit");
        println("");
        println("========================================");
        int input = intPrompt();
        return input;
    }

    private static void addObservatoryMenu() {
        Observatory newObservatory;
        String name = scanner.nextLine();
        String countryName = null;
        int yearStarted = 0;
        int areaCovered = 0;
        boolean valid = false;

        println("========================================");
        println("========Enter the Observatory name======");
        println("========================================");

        while (!valid) {
            name = stringPrompt().toLowerCase();
            valid = stringValidate(name);
        }
        valid = false;

        println("========================================");
        println("=====Enter the Observatory Country======");
        println("========================================");
        while (!valid) {
            countryName = stringPrompt().toLowerCase();
            valid = stringValidate(countryName);
        }
        valid = false;

        println("========================================");
        println("===Enter the year observations began====");
        println("========================================");
        while (!valid) {
            yearStarted = Integer.parseInt(stringPrompt());
            valid = yearValidate(yearStarted);
        }
        valid = false;

        println("========================================");
        println("=Finally, enter the area covered in km2=");
        println("========================================");
        while (!valid) {
            areaCovered = Integer.parseInt(stringPrompt());
            valid = areaValidate(areaCovered);
        }

        newObservatory = new Observatory(name, countryName, yearStarted, areaCovered);
        monitor.addObservatory(newObservatory);
        println("========================================");
        println("You added the " + name + " observatory in");
        println(countryName +" which started monitoring");
        println("in " + yearStarted + ". It covers " + areaCovered + " square kilometers.");
        println("Press enter to return to the menu.");
        stringPrompt();
    }

    private static boolean areaValidate(int number) {
        if (number < 1 || number > 15000) {
            println("Valid area is between 1 and 15000km2. Please try again.");
            return false;
        } else {
            println("Successfully entered area.");
            return true;
        }
    }

    private static boolean yearValidate(int number) {
        if (number >= 2016 || number <= 1800) {
            println("Valid years are between 1800 and 2016. Please try again.");
            return false;
        } else {
            println("Successfully entered year.");
            return true;
        }
    }

    private static boolean stringValidate(String message) {
        if (!Pattern.matches("^[a-zA-Z]+$", message) && message != null) {
            println("Please try again. Empty input is not allowed.");
            println("Non-Alphabet characters not allowed.");
            return false;
        } else {
            println("Success.");
            return true;
        }
    }

    private static void println(String message) {
        System.out.println(message);
    }
    private static void print(String message) {
        System.out.print(message);
    }

    private static String stringPrompt() {
        print("> ");
        String newString = scanner.nextLine();
        return newString;
    }
    private static int intPrompt() {
        print("> ");
        int newInt = scanner.nextInt();
        return newInt;
    }
    private static double doublePrompt() {
        print("> ");
        double newDouble = scanner.nextDouble();
        return newDouble;
    }


}

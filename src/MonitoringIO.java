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

            int selection = mainMenu();
            if (selection == 1) {
                addObservatoryMenu();
            }
            if (selection == 2) {
                addEarthquakeMenu();
            }
            if (selection == 3) {
                try {
                    print("" + monitor.getLargestEverEarthquake().getMagnitude());
                } catch (Exception e){
                    println(e.toString());
                    returnToMenu();
                }
            }
            if (selection == 4) {
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
        String input = prompt();
        return parseInt(input);
    }
    private static void addObservatoryMenu() {
        Observatory newObservatory;
        String name = null;
        String countryName = null;
        int yearStarted = 0;
        int areaCovered = 0;
        boolean valid = false;

        println("========================================");
        println("========Enter the Observatory name======");
        println("========================================");
        while (!valid) {
            name = prompt();
            valid = stringValidate(name);
        }
        valid = false;

        println("========================================");
        println("=====Enter the Observatory Country======");
        println("========================================");
        while (!valid) {
            countryName = prompt();
            valid = stringValidate(countryName);
        }
        valid = false;

        println("========================================");
        println("===Enter the year observations began====");
        println("========================================");
        while (!valid) {
            yearStarted = parseInt(prompt());
            valid = yearValidate(yearStarted);
        }
        valid = false;

        println("========================================");
        println("=Finally, enter the area covered in km2=");
        println("========================================");
        while (!valid) {
            areaCovered = parseInt(prompt());
            valid = areaValidate(areaCovered);
        }

        newObservatory = new Observatory(name, countryName, yearStarted, areaCovered);
        monitor.addObservatory(newObservatory);

        println("========================================");
        println("You added the " + capitalise(name) + " Observatory in");
        println(capitalise(countryName) +" which started monitoring");
        println("in " + yearStarted + ". It covers " + areaCovered + " square kilometers.");

        returnToMenu();
    }
    private static void addEarthquakeMenu() {
        String name = null;
        double magnitude = 0;
        double latitude = 0;
        double longitude = 0;
        int year = 0;
        boolean valid = false;

        println("========================================");
        println("========Enter the Observatory name======");
        println("========================================");
        println("Type the name of the observatory");
        println("that you want to add an Earthquake");
        println("to from the following list: ");
        println(allPossibleObservatoryChoices() + ".");
        while (!valid) {
            name = prompt();
            valid = observatoryNameValidate(name);
        }
        valid = false;

        println("========================================");
        println("=======Enter earthquake magnitude=======");
        println("========================================");
        println("Values between 1.0 to 9.9 allowed.");
        while (!valid) {
            magnitude = parseMagnitude(prompt());
            valid = magnitudeValidate(magnitude);
        }
        valid = false;

        println("========================================");
        println("=======Enter earthquake latitude========");
        println("========================================");
        println("Values between 1.0 to 9.9 allowed.");
        while (!valid) {
            magnitude = parseMagnitude(prompt());
            valid = magnitudeValidate(magnitude);
        }
        valid = false;

        returnToMenu();
    }
    private static String allPossibleObservatoryChoices() {
        String answer = "";
        if (monitor.getObservatoryNames().isEmpty()) {
            return "None listed";
        } else {
            for(String key : monitor.getObservatoryNames()) {
                answer += (capitalise(key) + ", ");
            }
        }
        return answer.substring(0,answer.length() - 2);
    }

    //Validators
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
    private static boolean stringValidate(String input) {
        if (!Pattern.matches("^[a-zA-Z]+$", input) && input != null) {
            println("Please try again. Empty input is not allowed.");
            println("Non-Alphabet characters not allowed.");
            return false;
        } else {
            println("Success.");
            return true;
        }
    }
    private static boolean observatoryNameValidate(String choice) {
        if (monitor.getObservatoryNames().contains(choice)) {
            println("Success. Earthquake being added to " + capitalise(choice) + ".");
            return true;
        } else {
            println("Choose an observatory from the list.");
            return false;
        }
    }
    private static boolean magnitudeValidate(Double magnitude) {
        if(magnitude > 9.9 || magnitude < 1.0) {
            println("Enter a number between 1.0 and 9.9.");
            return false;
        } else if (magnitude.toString().length() > 3) {
            println("Too many decimal places. Enter number between 1.0 and 9.9.");
            return false;
        } else {
            return true;
        }
    }

    //Prompts and input components
    private static String capitalise(String word) {
        return word.substring(0,1).toUpperCase() + word.substring(1);
    }
    private static String prompt() {
        print("> ");
        String newString = scanner.nextLine().toLowerCase();
        return newString;
    }
    private static int parseInt(String toInt) {
        return Integer.parseInt(toInt);
    }
    private static double parseMagnitude(String toDouble) {
        if (Pattern.matches("^[0-9]\\.?[0-9]?$",toDouble)) {
            return Double.parseDouble(toDouble);
        } else {
            return 0;
        }
    }
    private static void returnToMenu() {
        println("Press enter to return to the menu.");
        prompt();
    }
    private static void println(String message) {
        System.out.println(message);
    }
    private static void print(String message) {
        System.out.print(message);
    }





}

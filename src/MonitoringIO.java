/**
 * M
 * @author Matthew Cree
 * @version October 2016
 */
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class MonitoringIO {

    private static Scanner scanner = new Scanner(System.in);
    private static Monitoring monitor = new Monitoring();

    public static void main(String[] args) {
        MainMenu.show();
    }

    // Menus

    private static class MainMenu {
        private static final int ADD_OBSERVATORY = 1;
        private static final int ADD_EARTHQUAKE = 2;
        private static final int VIEW_STATS = 3;
        private static final int EXIT = 4;

        public static void show() {
            boolean finished = false;

            while(!finished) {

                switch(MainMenu.promptUserForMenuSelection()) {
                    case MainMenu.ADD_OBSERVATORY:
                        ObservatoryMenu.show();
                        break;
                    case MainMenu.ADD_EARTHQUAKE:
                        EarthquakeMenu.show();
                        break;
                    case MainMenu.VIEW_STATS:
                        StatsMenu.show();
                        break;
                    case MainMenu.EXIT:
                        print("Exiting. Goodbye!");
                        finished = true;
                        break;
                    default:
                        println("Invalid selection!");
                        continuationPrompt();
                }

            }
        }

        // View Helpers

        private static int promptUserForMenuSelection() {
            println("========Earthquake Monitor 1.0==========");
            println("=Select one of the following options by=");
            println("=typing the number and pressing enter.==");
            println("========================================");
            println("");
            println("1. Enter Observatory data.");
            println("2. Enter Earthquake data.");
            println("3. See Monitoring statistics.");
            println("4. Exit");
            println("");
            String input = prompt();
            return parseInt(input);
        }
    }

    private static class ObservatoryMenu {

        private static void show() {
            // Prompt user for Observatory fields

            String name = promptForObservatoryName("Enter the Observatory Name");
            String countryName = promptForString("Enter the Observatory Country");
            int yearStarted = promptForYear("Enter the year observations began");
            double areaCovered = promptForArea("Finally, enter the area covered in km2");
            // Create new Observatory
            Observatory newObservatory = new Observatory(name, countryName, yearStarted, areaCovered);
            // Add Observatory to Monitor
            monitor.addObservatory(newObservatory);
            // Show Summary of New Observatory
            showSummary(newObservatory);
        }

        // View Helpers

        private static void showSummary(Observatory observatory) {
            println("You added the " + observatory.getName() + " Observatory.");
            println("- Located in " + observatory.getCountry() + ".");
            println("- Monitoring began in " + observatory.getStartYear() + ".");
            println("- It covers " + observatory.getArea() + " square kilometers.");
            continuationPrompt();
        }

        // User Input Prompts

        private static String promptForString(String userPrompt) {
            boolean valid = false;
            println(userPrompt + ":");
            String inputString = "";
            while (!valid) {
                inputString = prompt();
                valid = stringValidate(inputString);
            }
            return inputString;
        }

        private static String promptForObservatoryName(String userPrompt) {
            boolean valid = false;
            println(userPrompt + ":");
            String inputString = "";
            while (!valid) {
                inputString = prompt();
                valid = observatoryNameValidate(inputString);
            }
            return inputString;
        }

        private static int promptForYear(String userPrompt) {
            println(userPrompt + ":");
            boolean valid = false;
            int year = 0;
            while (!valid) {
                year = parseInt(prompt());
                valid = yearValidate(year);
            }
            return year;
        }

        private static double promptForArea(String userPrompt) {
            println(userPrompt + ":");
            boolean valid = false;
            double area = 0;
            while (!valid) {
                area = parseDouble(prompt());
                valid = areaValidate(area);
            }
            return area;
        }

        // Validation

        private static boolean areaValidate(double number) {
            if (number < 1 || number > 15000) {
                println("Valid area is between 1 and 15000km2. Please try again.");
                return false;
            } else {
                return true;
            }
        }

        private static boolean observatoryNameValidate(String choice) { // Ensures no duplicate Observatory names
            if(choice == null || choice.equals("")) {
                println("Please try again. Empty input is not allowed.");
                return false;
            } else if (monitor.getObservatoryNames().contains(choice)) {
                println("An observatory called " + choice + " already exists.");
                return false;
            }

            return stringValidate(choice);
        }
    }

    private static class EarthquakeMenu {

        public static void show() {
            // Prompt user for Observatory / Earthquake fields
            if (allPossibleObservatoryChoices().equals("none")) {
                println("Add an observatory first.");
                continuationPrompt();
            } else {
                String name = promptForObservatoryName();
                double magnitude = promptForMagnitude();
                double latitude =  promptForLatitude();
                double longitude =  promptForLongitude();
                int year = promptForYear();
                // Create new Observatory
                Earthquake earthquake = new Earthquake(magnitude, latitude, longitude, year);
                // Record Earthquake
                monitor.addEarthquake(name, earthquake);
                // Show Summary of New Earthquake
                showSummary(name, earthquake);
            }

        }

        // View Helpers

        private static void showSummary(String name, Earthquake earthquake) {
            println("You added a " + earthquake.getMagnitude() + " magnitude earthquake.");
            println("- Recorded by the " + name + " Observatory in " + earthquake.getYear() + ".");
            println("- Occurred at " + earthquake.getLatitude() + " degrees latitude and "
                    + earthquake.getLongitude() + " degrees longitude.");
            continuationPrompt();
        }

        // User Input Prompts

        private static String promptForObservatoryName() {
            println("Enter the Observatory name from the following list \""
                    + allPossibleObservatoryChoices() + "\":");
            boolean valid = false;
            String name = "";
            while (!valid) {
                name = prompt();
                valid = observatoryNameValidate(name);
            }
            return name;
        }

        private static int promptForYear() {
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            println("Enter year of Earthquake (Values between 1800 to " + currentYear + " allowed):");
            boolean valid = false;
            int year = 0;
            while (!valid) {
                year = parseInt(prompt());
                valid = yearValidate(year);
            }
            return year;
        }

        private static double promptForLongitude() {
            println("Enter earthquake longitude (Values between -180 to 180 allowed):");
            boolean valid = false;
            double longitude = 0;
            while (!valid) {
                longitude = longitudeValidate(prompt());
                valid = !Double.isNaN(longitude);
            }
            return longitude;
        }

        private static double promptForLatitude() {
            println("Enter earthquake latitude (Values between -90.0 to 90 allowed):");
            boolean valid = false;
            double latitude = 0;
            while (!valid) {
                latitude = latitudeValidate(prompt());
                valid = !Double.isNaN(latitude);
            }
            return latitude;
        }



        // Validation

        private static boolean observatoryNameValidate(String choice) {
            if (monitor.getObservatoryNames().contains(choice)) {
                println("Earthquake being added to " + choice + ".");
                return true;
            } else {
                println("Observatory not found, select from list.");
                return false;
            }
        }



        private static double longitudeValidate(String input) {
            try {
                double longitude = Double.parseDouble(input);
                // Check range
                if(longitude > 180 || longitude < -180) {
                    println("Enter a number between -180.0 and 180.0.");
                    return Double.NaN;
                }
                return longitude;
            } catch(NumberFormatException ex){
                println("Enter a number between -180.0 and 180.0.");
                return Double.NaN;
            }
        }

        private static double latitudeValidate(String input) {
            try {
                double latitude = Double.parseDouble(input);
                // Check range
                if(latitude > 90 || latitude < -90) {
                    println("Enter a number between -90 and 90.");
                    return Double.NaN;
                }
                return latitude;
            } catch(NumberFormatException ex){
                println("Enter a number between -90.0 and 90.0.");
                return Double.NaN;
            }
        }


        // Helpers

        private static String allPossibleObservatoryChoices() {
            Set<String> observatoryNames = monitor.getObservatoryNames();
            if (observatoryNames.isEmpty()) {
                return "none";
            }

            return  String.join(", ", monitor.getObservatoryNames());
        }
    }

    private static class StatsMenu {
        private static final int GET_LARGEST_RECORDED_EARTHQUAKE = 1;
        private static final int GET_OBSERVATORY_WITH_LARGEST_AVERAGE = 2;
        private static final int GET_ALL_QUAKES_LARGER_THAN_GIVEN_NUMBER = 3;
        private static final int BACK_TO_MAIN = 4;

        public static void show() {
            boolean finished = false;

            while(!finished) {

                switch(StatsMenu.promptUserForMenuSelection()) {
                    case StatsMenu.GET_LARGEST_RECORDED_EARTHQUAKE:
                        showLargestRecordedEarthquake();
                        break;
                    case StatsMenu.GET_OBSERVATORY_WITH_LARGEST_AVERAGE:
                        showObservatoryWithLargestAverage();
                        break;
                    case StatsMenu.GET_ALL_QUAKES_LARGER_THAN_GIVEN_NUMBER:
                        showAllQuakesLargerThanGivenNumberMenu();
                        break;
                    case StatsMenu.BACK_TO_MAIN:
                        finished = true;
                        break;
                    default:
                        println("Invalid selection!");
                        continuationPrompt();
                }
            }
        }

        private static void showLargestRecordedEarthquake() {
            Earthquake eq = null;
            try {
                 eq = monitor.getLargestRecordedEarthquake();
            } catch(Exception e) {}
            if(eq != null){
                // Print summary of earthquake
                println("The largest Earthquake observed so far was: " + eq.getMagnitude() + " magnitude.");
                println("- Observed in " + eq.getYear() + ".");
                println("- Exact location was " + eq.getLatitude() + " degrees latitude and " + eq.getLongitude() + " degrees longitude.");
                continuationPrompt();
            } else {
                println("No earthquakes recorded.");
                continuationPrompt();
            }
        }

        private static void showObservatoryWithLargestAverage() {
            Observatory ob = null;
            DecimalFormat format = new DecimalFormat("#.#");
            try {
                ob = monitor.getObservatoryWithLargestAverageMagnitude();
            } catch(Exception e) {}

            if(ob != null){
                // Print summary of Observatory
                println("The " + ob.getName() + " Observatory in " + ob.getCountry() + " has recorded the highest average earthquake magnitude.");
                println("- Started recording in " + ob.getStartYear() + ".");
                println("- Covers " + ob.getArea() + " kilometers squared.");
                println("- Average recorded magnitude: " + format.format(ob.getAverageEarthquakeMagnitude()) + ".");
                printInfoAboutAllEarthquakes(ob.getEarthquakes());
                continuationPrompt();
            } else {
                println("No earthquakes recorded.");
                continuationPrompt();
            }
        }

        private static void showAllQuakesLargerThanGivenNumberMenu() {
            ArrayList<Earthquake> earthquakes = null;
            Observatory ob = null;

            try {
                ob = monitor.getObservatoryWithLargestAverageMagnitude();
            } catch(Exception e) {}

            if (ob == null) {
                println("No earthquakes recorded");
            } else {
                try {
                    print("Enter a magnitude to view all Earthquakes which had greater magnitudes: ");
                    earthquakes = monitor.getAllEarthQuakesLargerThanGivenNumber(promptForMagnitude());
                } catch (Exception e) {}
            }

            if (earthquakes != null) {
                printInfoAboutAllEarthquakes(earthquakes);
            }
            continuationPrompt();
        }

        // View Helpers

        private static int promptUserForMenuSelection() {
            println("=========Monitoring Statistics==========");
            println("========================================");
            println("");
            println("1. Show Largest Earthquake.");
            println("2. Observatory with Largest Average");
            println("3. Show Earthquakes Larger Than Given Number");
            println("4. Back to Main Menu");
            println("");
            String input = prompt();
            return parseInt(input);
        }

        private static void printInfoAboutAllEarthquakes(ArrayList<Earthquake> earthquakes) {
            println("Detailed info about all earthquakes follows:");
            for(Earthquake earthquake : earthquakes) {
                println("- " + earthquake.getYear() + ": "  + earthquake.getMagnitude() + " magnitude recorded at " + earthquake.getLatitude() + " degrees latitude");
                println("and " + earthquake.getLongitude() + " degrees longitude.");
                println("");
            }
        }
    }

    // IO Helpers

    private static String prompt() {
        print("> ");
        String newString = scanner.nextLine();
        return newString;
    }

    private static void continuationPrompt() {
        println("Press enter to continue...");
        prompt();
    }

    private static void println(String message) {
        System.out.println(message);
    }

    private static void print(String message) {
        System.out.print(message);
    }

    // Input Validation

    private static int parseInt(String string) {
        if (Pattern.matches("^[0-9]+$", string)) {
            return Integer.parseInt(string);
        } else {
            return 0;
        }
    }

    private static double parseDouble(String string) {
        if (Pattern.matches("^[0-9]+\\.*[0-9]*$", string)) {
            return Double.parseDouble(string);
        } else {
            return 0;
        }
    }

    private static boolean yearValidate(int number) {
        if (number >= 2016 || number <= 1800) {
            println("Valid years are between 1800 and 2016. Please try again.");
            return false;
        } else {
            return true;
        }
    }

    private static boolean stringValidate(String input) {
        if (Pattern.matches("^[a-zA-Z ]+$", input)) {
            return true;
        } else {
            println("Please try again. Empty input is not allowed.");
            println("Non-Alphabet characters not allowed.");
            return false;
        }
    }

    private static double magnitudeValidate(String input) {
        try {
            double magnitude = Double.parseDouble(input);
            // Check range between 9.9 and 0
            if(magnitude > 9.9 || magnitude <= 0) {
                println("Enter a number between 0 and 9.9.");
                return Double.NaN;
            }
            // Valid inputs must have 2 or less dp
            if (input.length() > 3) {
                println("Too many decimal places. Enter number between 0 and 9.9.");
                return Double.NaN;
            }
            return magnitude;
        } catch(NumberFormatException ex){
            println("Enter number between 0 and 9.9.");
            return Double.NaN;
        }
    }

    private static double promptForMagnitude() {
        println("Enter earthquake magnitude (Values between 0 to 9.9 allowed):");
        boolean valid = false;
        double magnitude = 0;
        while (!valid) {
            magnitude = magnitudeValidate(prompt());
            valid = !Double.isNaN(magnitude);
        }
        return magnitude;
    }

}

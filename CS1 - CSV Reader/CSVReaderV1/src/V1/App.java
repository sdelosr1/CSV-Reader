package V1; /**
 * Reads CS1 one-liner and
 * Stores data into HashMap
 * Reads user input for Scene#
 * Outputs desired scene with data
 */

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {

        File propFile = new File("");

        Scanner scanner = new Scanner(propFile);
        HashMap<String, String[]> propMap = new HashMap<>(442);

        while (scanner.hasNextLine()) {

            String[] rows = scanner.nextLine().split(",");
            if (rows.length > 0) { // Checks if rows[] is initialized

                // Checks if line is a scene row
                if (!rows[0].contains("SCENE") && !rows[0].contains("STORY") &&
                        !rows[0].contains("EPISODE") && !rows[0].contains("Total") &&
                        !rows[0].contains("EPISIDE")) {
                    System.out.println(rows[0]); // prints scene#
                    propMap.put(rows[0], rows); // puts row into propMap
                }

            }
        }

        System.out.println("\nProp Map is Successfully Complete"); // Prop Map done

        scanner = new Scanner(System.in); // Switches Scanner to read input from System.in
        String userChoice; // user input

        do {
            // Menu
            System.out.println("\nMenu Enter Choice:" +
                    "\n\tEnter Props to view props" +
                    "\n\tEnter Quit to quit");

            userChoice = scanner.nextLine(); // read user input

            /**
             * Everything Below can be updated
             *
             * For Next Version
             * Make it read an array of Scene#
             * Make it write to a file the output
             */
            if (userChoice.equalsIgnoreCase("Props")) {

                System.out.println("Enter Scene#");
                String sceneKey = scanner.nextLine();

                if(propMap.containsKey(sceneKey)) {
                    String[] propList = propMap.get(sceneKey);
                    for (String s : propList) {
                        System.out.println(s);
                    }
                } else {
                    System.out.println("Key does not Exists");
                }


            } else if(userChoice.equalsIgnoreCase("Quit")){
                System.out.println("Goodbye!");
            } else {
                System.out.println("Input Unknown");
            }

        } while (!userChoice.equalsIgnoreCase("Quit"));

        scanner.close();
    }

}

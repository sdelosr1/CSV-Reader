package V2; /**
 * Reads CS1 one-liner and
 * Stores data into HashMap
 * Reads user input for Scene Numbers
 * Writes desired scenes with data to txt file
 */

import java.io.*;
import java.util.*;

public class AppV2 {

    public static void mapWriter(HashMap<String, String[]> map, List<String> keys, String day) throws Exception{

        File desFile = new File("done.txt");
        if(!desFile.exists()) {
            desFile.createNewFile();
        }

        FileWriter fr = null;
        BufferedWriter br = null;
        PrintWriter pr = null;

        try {
            fr = new FileWriter(desFile, false);
            br = new BufferedWriter(fr);
            pr = new PrintWriter(br);

            // write to file here
            pr.println("Scenes Day " + day);
            pr.println(keys);
            pr.println();

            // Writes Scenes
            for (String key: keys) {
                String[] value = map.get(key);
                for (String data: value) {
                    if (data.equals(key)) {
                       pr.println(data);
                    } else if (!data.equals("")) {
                        pr.println("\t"+data);
                    }
                }
                pr.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                pr.close();
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {

        File propFile = new File("");

        Scanner scanner = new Scanner(propFile);

        // set to 442 because that's how many entries
        HashMap<String, String[]> propMap = new HashMap<>(442);
        while (scanner.hasNextLine()) {

            String[] rows = scanner.nextLine().split(",");
            if (rows.length > 0) { // Checks if rows[] is initialized

                // Checks if line is a scene row
                if (!rows[0].contains("SCENE") && !rows[0].contains("STORY") &&
                        !rows[0].contains("EPISODE") && !rows[0].contains("Total") &&
                        !rows[0].contains("EPISIDE")) {
                    //System.out.println(rows[0]); // prints scene#
                    propMap.put(rows[0], rows); // puts row into propMap
                }

            }
        }

        System.out.println("\nProp Map is Successfully Complete"); // Prop Map done

        scanner = new Scanner(System.in); // Switches Scanner to read input from System.in
        List<String> sceneKeyList = new ArrayList<>();
        List<String> invalidKeyList = new ArrayList<>();

        System.out.println("\nEnter Day#");
        String day = scanner.nextLine();

        System.out.println("Enter Scene# Array");
        String[] sceneNumberArray = scanner.nextLine().split(",");

        for (String sceneKey : sceneNumberArray) {
            if (propMap.containsKey(sceneKey)) {
                sceneKeyList.add(sceneKey);
            } else {
                invalidKeyList.add(sceneKey);
            }
        }

        mapWriter(propMap, sceneKeyList, day);

        if (!invalidKeyList.isEmpty()) {
            System.out.println("Invalid Keys: ");
            for (String key : invalidKeyList) {
                System.out.println(key);
            }
         }

        scanner.close();
    }

}

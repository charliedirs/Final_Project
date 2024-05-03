import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Forest {
    private static ArrayList <Tree> allTrees = new ArrayList<>();

    private static String forestName;
    private static Random random = new Random();
    private static Scanner scanner = new Scanner(System.in);

    public static TreeSpecies stringToEnum(String STR) {

        switch (STR) {
            case "Maple": return TreeSpecies.MAPLE;
            case "Birch": return TreeSpecies.BIRCH;
            case "Fir": return TreeSpecies.FIR;
        }
        return null;
    }

    public void test() {
        System.out.println("Hello");
    }

    public static Forest readForestFromCSV(String fileName) {

        forestName = fileName;
        String fileNameExt;

        allTrees.clear();
        try {
            BufferedReader fromBufferedReader;
            String oneLine;

            String [] data;

            if (fileName.contains(".db")){
                fileNameExt = fileName;
            }
            else {
                fileNameExt = fileName + ".csv";
            }
            fromBufferedReader = new BufferedReader(new FileReader(fileNameExt));

            oneLine = fromBufferedReader.readLine();
            while (oneLine != null) {

                data = oneLine.split(",");

                Tree newTree = new Tree(stringToEnum(data[0]), Integer.parseInt(data[1]), Double.parseDouble(data[2]) , Double.parseDouble(data[3]) );
                allTrees.add(newTree);

                oneLine = fromBufferedReader.readLine();
            }
            fromBufferedReader.close();
        } catch (IOException e) {
            System.out.println("Error opening/reading " + forestName);
            System.out.println("Old forest retained");
        }
        System.out.println();
        return new Forest();
    }

    public static int countTrees() {
        return allTrees.size();
    }

    public static void growTrees() {

        for (Tree tree : allTrees) {
            double newHeight = (1 + (tree.getGrowthRate()/100)) * tree.getHeight();
            tree.setHeight(newHeight);
        }

    }

    public static void printTrees() {

        double totalHeight = 0;
        double averageHeight = 0;
        String fileName;

        System.out.println();
        System.out.println("Forest name: " + forestName.replace(".db"," "));


        for (int i=0; i< allTrees.size(); i++) {

            System.out.print(i+" ");
            System.out.println(allTrees.get(i).toString());

            for (Tree tree : allTrees) {
                totalHeight += tree.getHeight();
            }

        }

        averageHeight = totalHeight/(countTrees()*countTrees());

        System.out.println("There are " + countTrees()  + " trees, with an average height of " + String.format("%.2f", averageHeight) + "'");
        System.out.println();

    }

    public static void saveTrees() {

        try {

            FileWriter fileWrite = new FileWriter(forestName + ".db");
            for (int i=0; i< allTrees.size(); i++) {
                fileWrite.write(allTrees.get(i).toCSV());

            }
            fileWrite.close();

        } catch (Exception e) {



        }

    }

    public static Tree generateTrees() {

        TreeSpecies[] speciesValues = TreeSpecies.values();
        int randomSpeciesIndex = random.nextInt(speciesValues.length);
        TreeSpecies randomSpecies = speciesValues[randomSpeciesIndex];

        int randomYear = random.nextInt(25) + 2000; // Random year between 2000 and 2024
        double randomHeight = random.nextDouble() * 10 + 10; // Random height between 10 and 20
        double randomGrowthRate = random.nextDouble() * 10 + 10; // Random growth rate between 10 and 20

        return new Tree(randomSpecies, randomYear, randomHeight, randomGrowthRate);

    }

    public static boolean isANumber(String str) {

        try{
            Double.valueOf(str);
        } catch (NumberFormatException e) {
            //not an integer
            return false;
        }
        return true;
    }



    public static void cutTrees() {

        Scanner keyboard = new Scanner(System.in);
        int treeNumber;
        String input;



        do {

            System.out.println("Tree number to cut down: " );
            input = keyboard.next();

            if (!isANumber(input)) {

                System.out.println("That is not an integer");

            }


        } while (!isANumber(input));

        treeNumber= Integer.parseInt(input);

        if (treeNumber >= 0 && treeNumber < allTrees.size()) {
            Tree removedTree = allTrees.remove(treeNumber);
        } else {
            System.out.println("Tree number " + treeNumber + " does not exist");
        }

    }

    public static void reapTrees() {


        Scanner keyboard = new Scanner(System.in);
        double minHeight;

        String input;
        do {

            System.out.print("Enter the minimum height for trees to be reaped: ");
            input = keyboard.next();

            if (!isANumber(input)) {

                System.out.println("That is not an integer");

            }

        } while (!isANumber(input));

        minHeight = Double.parseDouble(input);

        for (int i = 0; i < allTrees.size(); i++) {
            Tree tree = allTrees.get(i);
            if (tree.getHeight() > minHeight) {

                System.out.println("Reaping the tall tree  " + tree);
                allTrees.remove(i);
                Tree newTree = generateTrees();
                allTrees.add(i, generateTrees());
                System.out.println("Replaced with new tree " + newTree);
            }
        }

    }

    public static void addTrees() {

        TreeSpecies[] speciesValues = TreeSpecies.values();
        int randomSpeciesIndex = random.nextInt(speciesValues.length);
        TreeSpecies randomSpecies = speciesValues[randomSpeciesIndex];

        int randomYear = random.nextInt(25) + 2000; // Random year between 2000 and 2024
        double randomHeight = random.nextDouble() * 10 + 10; // Random height between 10 and 20
        double randomGrowthRate = random.nextDouble() * 10 + 10; // Random growth rate between 10 and 20

        Tree newTree = new Tree(randomSpecies, randomYear, randomHeight, randomGrowthRate);
        allTrees.add(newTree);

    }

}

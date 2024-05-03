import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Forestry {

    public static void main(String[] args) {
        Forest myForest;
        int argIndex;
        boolean doNext;
        File testFile;
//----Welcome message
        System.out.println("Welcome to the Forestry Simulation");
        System.out.println("----------------------------------");
        doNext = true;

        for (argIndex = 0; doNext && argIndex < args.length; argIndex++) {
            doNext = false;
            System.out.println("Initializing from " + args[argIndex]);

            testFile = new File(args[argIndex] + ".csv");
            if (!testFile.exists()){
                System.out.println("Error opening/reading " + args[argIndex] + ".csv");
                doNext = true;
                continue;
            }

            if ((myForest = Forest.readForestFromCSV(args[argIndex])) != null) {
                doNext = menu(myForest);
            }
        }
//----Farewell message
        System.out.println();
        System.out.println("Exiting the Forestry Simulation");

    } // end of main method

    public static boolean menu(Forest myForest) {

        Scanner keyboard = new Scanner(System.in);
        char option;
        while (true) {

            System.out.println("(P)rint, (A)dd, (C)ut, (G)row, (R)eap, (S)ave, (L)oad, (N)ext, e(X)it : ");
            option = keyboard.next().charAt(0);

            switch (Character.toUpperCase(option)) {

                case 'P': myForest.printTrees(); break;

                case 'A': myForest.addTrees(); break;

                case 'C': myForest.cutTrees(); break;

                case 'G': myForest.growTrees(); break;

                case 'R': myForest.reapTrees(); break;

                case 'S': myForest.saveTrees(); break;

                case 'L': loadTrees(myForest); break;

                case 'N': return true;

                case 'X':
                    System.out.println("Exiting program. Have a great performance!");
                    return true;
                default:
                    System.out.println("Invalid menu option, try again");
            }

        }

    }
    public static void loadTrees(Forest myForest) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter forest name: ");
        String fileName = keyboard.next();
        myForest = Forest.readForestFromCSV(fileName + ".db");

    }

}
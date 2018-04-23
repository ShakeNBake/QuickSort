import java.io.*;
import java.util.Scanner;

/**
 * Console app that takes integer array as command line argument and sorts it using one of two types of QuickSort.
 * QuickSort type is passed as first command line argument, input file as second. Input file is expected to have one
 * integer number per line.
 * The sorted array is saved in "output.dat".
 *
 * @author Yan Vinokur
 */
public class QuickSortApp {
    /**
     * Program entry point
     * @param args Command line arguments.
     *             1. QuickSort Pivot Selection Type: "normal" or "median-of-3".
     *             2. Path to input data file.
     */
    public static void main(String[] args){
        int[] input;
        QuickSort qs = new QuickSort();

        if (args.length < 2) {
            System.out.println("Usage: pivotType dataFilePath");
            System.exit(1);
        }
        final String type = args[0];
        final File inputFile = new File(args[1]);
        final File output = new File("output.dat");

        input = loadSimData(inputFile);
        qs.quickSort(input, 0, input.length-1, type);
        writeOutput(arrayToString(input), output);

        System.out.println("Input: " + inputFile + "\n"
                         + "Input size (N): " + input.length + "\n"
                         + "Total Number of Comparisons: " + qs.getComparisonCounter() + "\n"
                         + "Sorted array has been saved in \"output.dat\"");
    }

    /**
     * Utility method to create tab-delimited string of elements from an array of integers.
     * @param a integer array to be converted to a String.
     * @return a String representation of an array of integers, tab-delimited.
     */
    public static String arrayToString(int[] a){
        StringBuilder str = new StringBuilder();
        for (int item : a){
            str.append(item + "\t");
        }
        str.append("\n");
        return str.toString();
    }

    /**
     * Utility to load array of numbers from a file.
     * @param fInput File object of data file.
     * @return Array of integers.
     */
    private static int[] loadSimData (File fInput) {
        int[] numbers;
        int count = countItems(fInput);
        numbers = new int[count];
        try (Scanner input = new Scanner(new FileReader(fInput)))
        {
            for (int i = 0; i < numbers.length; i++)
            {
                numbers[i] = input.nextInt();
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found: " + e);
            System.exit(1);
        }
        return numbers;
    }

    /**
     * Utility to count lines in the input file.
     * @param fInput a File to count line in.
     * @return number of items in a file
     */
    private static int countItems(File fInput) {
        int cnt = 0;
        try (Scanner input = new Scanner(new FileReader(fInput))) {
            while (input.hasNext()) {
                input.next();
                cnt++;
            }
        }
        catch (IOException ioe) {
            cnt = -1;
        }
        return cnt;
    }

    /**
     * Utility to append a String to a text file
     * @param str String to be appended to the file
     * @param fName File object to be written to.
     */
    private static void writeOutput(String str, File fName) {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(
                new FileWriter(fName, true)))) {
            pw.println(str);
        }
        catch (IOException ioe) {
            System.out.println("IO Exception.\n" + ioe.getMessage());
        }
    }
}

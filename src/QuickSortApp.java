import java.io.*;
import java.util.Scanner;

public class QuickSortApp {
    public static void main(String[] args){
        if (args.length < 2) {
            System.out.println("Usage: pivotType dataFilePath");
            System.exit(1);
        }
        final File inputFile = new File(args[1]);
        final String type = args[0];
        final File output = new File("data/output.dat");

        System.out.println(inputFile);

        int[] input = loadSimData(inputFile);
        QuickSort qs = new QuickSort();
//        printArray(input);
        qs.rQuickSort(input, 0, input.length-1, type);

        System.out.println("Input size: " + input.length);
        System.out.println("Total Number of Comparisons: " + qs.getComparisonCounter());
        System.out.println("Total Number of Exchanges: " + qs.getExchangeCounter());

        writeOutput(arrayToString(input), output);
    }

    public static String arrayToString(int[] a){
        StringBuilder str = new StringBuilder();
        for (int item : a){
            str.append(item + "\t");
        }
        str.append("\n");
        return str.toString();
    }

    public static void printArray(int[] a){
        for (int item : a){
            System.out.print(item + "\t");
        }
        System.out.println();
    }

    /**
     * Load array of numbers from a file.
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
     * Utility to count lines in the input file. Assumes
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
     * Routine to append a String to a text file
     * @param str String to be appended to the file
     * @param fName File object to be written to.
     */
    private static void writeOutput(String str, File fName) {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(
                new FileWriter(fName, true)))) {
            pw.println(str);
        }
        catch (IOException ioe) {
            System.out.println("IO Exception.");
            System.out.println(ioe.getMessage());
        }
    }

}

import java.util.Stack;

/**
 * Class containing QuickSort related methods.
 *
 * @author Yan Vinokur
 */
public class QuickSort {
    private int comparisonCounter;

    /**
     * Iterative QuickSort.
     * @param numbers array of integers to be sorted
     * @param i start index
     * @param k end index
     * @param type Can be "normal" or "median-of-3".
     */
    public void quickSort(int numbers[], int i, int k, String type) {
        int q;
        Stack stack = new Stack();
        stack.push(i);
        stack.push(k);

        while (!stack.isEmpty()) {
            int end = (int) stack.pop();
            int start = (int) stack.pop();

            if (end - start < 2) {
                continue;
            }

            q = partition(numbers, start, end, type);

            stack.push(q + 1);
            stack.push(end);
            stack.push(start);
            stack.push(q - 1);
        }
    }

    /**
     * Partition array such that all items less than or equal to 'pivot' are to the left of pivot
     * and the rest are to the right. Pivot is selected based on type parameter.
     * @param numbers Array of numbers to partition
     * @param i Start index (anything below is ignored)
     * @param k End index (anything above is ignored)
     * @param type Can be "normal" or "median-of-3".
     * @return location of first item in high partition.
     */
    private int partition(int numbers [], int i, int k, String type) {
        int l = i - 1;
        int pivotIndex = k;
        int pivot;

        if (type.equals("median-of-3")) pivotIndex = findMedian(numbers, i, k);

        pivot = numbers[pivotIndex];

        for (int j = i; j <= k; j++) {
            if (j == pivotIndex) continue;
            if (numbers[j] <= pivot) {
                ++l;
                if (l == pivotIndex) pivotIndex = j;
                exchange(numbers, l, j);
            }
            comparisonCounter++;
        }
        exchange(numbers, l + 1, pivotIndex);
        return l + 1;
    }

    /**
     * Utility that finds the index of the median of first, last, and midpoint elements in an integer array.
     * @param numbers Array of integers
     * @param i the index of the first element of the sub-array of interest
     * @param k the index of the last element of the sub-array of interest
     * @return the index of the median-of-three
     */
    private int findMedian(int[] numbers, int i, int k) {
        int midpoint = (i + k) / 2;
        int[] medianOf3 = new int[3];
        medianOf3[0] = numbers[i];
        medianOf3[1] = numbers[midpoint];
        medianOf3[2] = numbers[k];
        InsertionSort.insertionSort(medianOf3, 3);
        if (medianOf3[1] == numbers[i]) {
            return i;
        }
        else if (medianOf3[1] == numbers[k]) {
            return k;
        }
        else {
            return midpoint;
        }
    }

    private void exchange(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

    public int getComparisonCounter() {
        return comparisonCounter;
    }

    /**
     * Recursive QuickSort method. May cause StackOverflow in worst-case scenarios.
     * @param numbers array of integers to be sorted
     * @param i start index
     * @param k end index
     * @param type Can be "normal" or "median-of-3".
     */
    public void recursiveQuickSort(int numbers[], int i, int k, String type) {
        if (i < k) {
            int q = partition(numbers, i, k, type);
            recursiveQuickSort(numbers, i, q - 1, type);
            recursiveQuickSort(numbers, q + 1, k, type);
        }
    }
}

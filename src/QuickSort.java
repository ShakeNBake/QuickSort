/**
 * Class containing 4 QuickSort methods:
 *  1. Select the first item of the partition as the pivot.
 *     Treat partitions of size one and two as stopping cases.
 *  2. Select the first item of the partition as the pivot.
 *     Treat a partition of size 100 as a stopping case. Use an insertion sort to finish.
 *  3. Select the first item of the partition as the pivot.
 *     Treat a partition of size 50 as a stopping case. Use an insertion sort to finish.
 *  4. Select the median-of-three as the pivot.
 *     Treat partitions of size one and two as stopping cases.
 *
 * @author Yan Vinokur
 */
public class QuickSort {
    /**
     * Partition array such that all items less than or equal to 'pivot' are
     * to the left of pivot and the rest are to the right. Pivot is selected
     * based on type parameter.
     * @param numbers Array of numbers to partition
     * @param i Start index (anything below is ignored)
     * @param k End index (anything above is ignored)
     * @param type Specifies how pivot is selected see class description.
     * @return location of last item in low partition.
     */
    private int partition(int numbers [], int i, int k, String type) {
        int l;
        int j;
        int pivotIndex = k;
        int pivot = numbers[pivotIndex];
        int temp;
        boolean done = false;

        if (type.equals("median-of-3")) {
            int midpoint = (i + k) / 2;
            int[] medianOf3 = new int[3];
            medianOf3[0] = numbers[i];
            medianOf3[1] = numbers[midpoint];
            medianOf3[2] = numbers[k];
            InsertionSort.insertionSort(medianOf3, 3);
            if (medianOf3[1] == numbers[i]) {
                pivotIndex = i;
            }
                else if (medianOf3[1] == numbers[k]) {
                    pivotIndex = k;
                }
                    else {
                        pivotIndex = midpoint;
                    }
            pivot = numbers[pivotIndex];
        }

        l = i - 1;
//        j = i;
        System.out.println("Pivot Index: " + pivotIndex);
        System.out.println("Pivot: " + pivot);
        exchange(numbers, k, pivotIndex);
        for (j = i; j < k; j++) {
            if (numbers[j] <= pivot) {
                ++l;
                exchange(numbers, l, j);
            }
        }
        exchange(numbers, l + 1, k);
        QuickSortApp.printArray(numbers);
        System.out.println("l: " + l);
        return l + 1;
    }

    private void exchange(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

//    /**
//     * Iterative implementation of QuickSort sorting algorithm.
//     * @param numbers Array of numbers to be sorted.
//     * @param type type of QuickSort variation.
//     *             Accepted values are: "First", "Stop50", "Stop100", "MedianOf3".
//     */
//    public void quickSort(int[] numbers, String type) {
//        int j = 0;
//        Stack stack = new Stack();
//        stack.push(0);
//        stack.push(numbers.length - 1);
//
//        while (!stack.isEmpty()) {
//            int end = stack.pop();
//            int start = stack.pop();
//
//            if (end - start < 2) {
//                continue;
//            }
//
//            switch (type) {
//                case "Stop100": {
//                    if (end - start <= 100) {
//                        InsertionSort.insertionSort(numbers, end - start + 1);
//                        continue;
//                    }
//                    break;
//                }
//                case "Stop50": {
//                    if (end - start <= 50) {
//                        InsertionSort.insertionSort(numbers, end - start + 1);
//                        continue;
//                    }
//                    break;
//                }
//            }
//
//            /* Partition the data within the array. Value j returned
//               from partitioning is location of last item in low partition. */
//            j = partition(numbers, start, end, type);
//
//            stack.push(j + 1);
//            stack.push(end);
//
//            stack.push(start);
//            stack.push(j);
//        }
//    }

    /**
     * Recursive QuickSort method.
     * @param numbers array of integers to be sorted
     * @param i start index
     * @param k end index
     * @param type type of QuickSort variation.
     *             Accepted values are: "normal" and "median-of-3".
     */
    public void rQuickSort(int numbers[], int i, int k, String type) {
        if (i < k) {
            int q = partition(numbers, i, k, type);
            rQuickSort(numbers, i, q - 1, type);
            rQuickSort(numbers, q + 1, k, type);
        }
    }
}

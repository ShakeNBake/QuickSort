public class QuickSortApp {
    public static void main(String[] args){
        int[] input = {7,6,9,2,5,3,1,4,8,0};
        QuickSort qs = new QuickSort();
        printArray(input);
        qs.rQuickSort(input, 0, input.length-1, "normal");
        System.out.println();
        printArray(input);
    }

    public static void printArray(int[] a){
        for (int item : a){
            System.out.print(item + "\t");
        }
        System.out.println();
    }
}

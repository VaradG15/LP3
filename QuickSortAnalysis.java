import java.util.Arrays;
import java.util.Random;

public class QuickSortAnalysis {

    // Deterministic Quick Sort (using last element as pivot)
    public static void deterministicQuickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = deterministicPartition(arr, low, high);
            deterministicQuickSort(arr, low, pi - 1);
            deterministicQuickSort(arr, pi + 1, high);
        }
    }

    private static int deterministicPartition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    // Randomized Quick Sort
    public static void randomizedQuickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = randomizedPartition(arr, low, high);
            randomizedQuickSort(arr, low, pi - 1);
            randomizedQuickSort(arr, pi + 1, high);
        }
    }

    private static int randomizedPartition(int[] arr, int low, int high) {
        Random rand = new Random();
        int pivotIndex = low + rand.nextInt(high - low + 1);
        swap(arr, pivotIndex, high);
        return deterministicPartition(arr, low, high);
    }

    // Utility function to swap elements
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Function to analyze and print execution time of both variants
    public static void analyzeQuickSort(int[] arr) {
        int[] deterministicArr = Arrays.copyOf(arr, arr.length);
        int[] randomizedArr = Arrays.copyOf(arr, arr.length);

        // Deterministic Quick Sort
        long startTime = System.nanoTime();
        deterministicQuickSort(deterministicArr, 0, deterministicArr.length - 1);
        long endTime = System.nanoTime();
        long deterministicTime = endTime - startTime;
        System.out.println("Deterministic Quick Sort time: " + deterministicTime + " ns");

        // Randomized Quick Sort
        startTime = System.nanoTime();
        randomizedQuickSort(randomizedArr, 0, randomizedArr.length - 1);
        endTime = System.nanoTime();
        long randomizedTime = endTime - startTime;
        System.out.println("Randomized Quick Sort time: " + randomizedTime + " ns");

    }

    public static void main(String[] args) {
        int[] arr = {10, 7, 8, 9, 1, 5};

        System.out.println("Original Array: " + Arrays.toString(arr));

        // Analyze and compare both sorting algorithms
        analyzeQuickSort(arr);
    }
}

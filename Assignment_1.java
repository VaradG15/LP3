import java.util.Arrays;

public class Assignment_1 {

    public static int fibonacciIterative(int n) {
        if (n <= 1) {
            return n;
        }
        int a = 0, b = 1, c = 1;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    public static int fibonacciRecursive(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    public static int[] fibonacciIterativeArray(int n) {
        int[] fibArray = new int[n + 1]; // Array to store Fibonacci numbers
        if (n >= 0) fibArray[0] = 0;     // First Fibonacci number
        if (n >= 1) fibArray[1] = 1;     // Second Fibonacci number

        for (int i = 2; i <= n; i++) {
            fibArray[i] = fibArray[i - 1] + fibArray[i - 2];
        }
        return fibArray;
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println(fibonacciIterative(n));
        System.out.println(fibonacciRecursive(n));
        System.out.println(Arrays.toString(fibonacciIterativeArray(n)));
    }
}

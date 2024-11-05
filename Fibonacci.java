import java.util.ArrayList;
import java.util.Scanner;

public class Fibonacci {

    // Iteratively using memoization
    public static int iStepFibonacci(int n) {
        ArrayList<Integer> f = new ArrayList<>();
        f.add(0);
        f.add(1);

        int cnt = 2;
        for (int i = 2; i < n; i++) {
            cnt++;
            f.add(f.get(i - 1) + f.get(i - 2));
        }
        for(int j=0; j<f.size(); j++) {
            System.out.print(f.get(j) + " ");
        }
        return cnt;
    }

    static int rSteps = 0;

    // Recursively
    public static int rStepFibonacci(int n) {
        rSteps++;
        if (n < 0) return 0;
        if (n == 1 || n == 0) return 1;
        return rStepFibonacci(n - 1) + rStepFibonacci(n - 2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();

        System.out.println("Fibonacci Value: " + rStepFibonacci(n));
        System.out.println("Steps required using Iteration: " + iStepFibonacci(n));
        System.out.println("Steps required using Recursion: " + rSteps);

        scanner.close();
    }
}



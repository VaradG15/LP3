public class NQueens {
    private int n;
    private int[][] board;

    // Constructor initializes the board and sets the size n
    public NQueens(int n) {
        this.n = n;
        board = new int[n][n];
    }

    // Function to check if a queen can be safely placed at board[row][col]
    private boolean isSafe(int row, int col) {
        // Check the column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) {
                return false;
            }
        }

        // Check the upper left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // Check the upper right diagonal
        for (int i = row, j = col; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    // Function to solve the N-Queens problem using backtracking
    private boolean solveNQueens(int row) {
        // If all queens are placed, return true
        if (row == n) {
            return true;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(row, col)) {
                // Place queen
                board[row][col] = 1;

                // Recursively place the next queen
                if (solveNQueens(row + 1)) {
                    return true;
                }

                // Backtrack: remove queen if no solution is found
                board[row][col] = 0;
            }
        }

        return false; // No solution found
    }

    // Function to initialize the first queen and solve the rest
    public boolean solveWithFirstQueen(int firstRow, int firstCol) {
        // Place the first queen
        board[firstRow][firstCol] = 1;

        // Start placing the remaining queens from the next row
        return solveNQueens(firstRow + 1);
    }

    // Function to print the solution
    public void printSolution() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print((board[i][j] == 1 ? "Q " : ". "));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int n = 8; // Size of the board
        int firstRow = 0, firstCol = 0; // Initial position of the first queen

        NQueens queenProblem = new NQueens(n);

        if (queenProblem.solveWithFirstQueen(firstRow, firstCol)) {
            System.out.println("Solution found:");
            queenProblem.printSolution();
        } else {
            System.out.println("No solution exists.");
        }
    }
}

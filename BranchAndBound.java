import java.util.PriorityQueue;

class Item {
    int weight, value;
    double valuePerWeight;

    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
        this.valuePerWeight = (double)value / weight;
    }
}

class Node implements Comparable<Node> {
    int level, profit, weight;
    double bound;

    public Node(int level, int profit, int weight, double bound) {
        this.level = level;
        this.profit = profit;
        this.weight = weight;
        this.bound = bound;
    }

    // Comparison function for priority queue (descending order)
    @Override
    public int compareTo(Node other) {
        return Double.compare(other.bound, this.bound);
    }
}

public class BranchAndBound {

    // Function to calculate upper bound of profit in subtree rooted with 'node'
    private static double bound(Node node, int n, int maxWeight, Item[] items) {
        if (node.weight >= maxWeight) return 0;

        double profitBound = node.profit;
        int j = node.level + 1;
        int totalWeight = node.weight;

        // Greedily add items as long as weight is within the limit
        while (j < n && totalWeight + items[j].weight <= maxWeight) {
            totalWeight += items[j].weight;
            profitBound += items[j].value;
            j++;
        }

        // If there's still space for a fraction of the next item
        if (j < n) {
            profitBound += (maxWeight - totalWeight) * items[j].valuePerWeight;
        }
        return profitBound;
    }

    // Branch and Bound function to solve Knapsack problem
    public static int knapsack(Item[] items, int maxWeight) {
        int n = items.length;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Node u, v;

        // Create a dummy node to start the tree
        u = new Node(-1, 0, 0, 0);
        u.bound = bound(u, n, maxWeight, items);
        pq.add(u);

        int maxProfit = 0;
        while (!pq.isEmpty()) {
            u = pq.poll();

            // If the node is at the leaf level, continue
            if (u.level == n - 1) continue;

            // Generate the "taking the item" branch
            v = new Node(u.level + 1, u.profit + items[u.level + 1].value, u.weight + items[u.level + 1].weight, 0);
            if (v.weight <= maxWeight && v.profit > maxProfit) {
                maxProfit = v.profit;
            }
            v.bound = bound(v, n, maxWeight, items);
            if (v.bound > maxProfit) pq.add(v);

            // Generate the "not taking the item" branch
            v = new Node(u.level + 1, u.profit, u.weight, 0);
            v.bound = bound(v, n, maxWeight, items);
            if (v.bound > maxProfit) pq.add(v);
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int maxWeight = 40;
        Item[] items = {
                new Item(10, 60),
                new Item(20, 100),
                new Item(30, 120)
        };

        System.out.println("Maximum profit is: " + knapsack(items, maxWeight));
    }
}

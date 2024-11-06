import java.util.*;

class BranchBound {
    static class Item {
        int weight, value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    static class Node {
        int level, profit, bound, weight;

        public Node() {}

        public Node(Node copy) {
            this.level = copy.level;
            this.profit = copy.profit;
            this.bound = copy.bound;
            this.weight = copy.weight;
        }
    }

    public static int bound(Node u, int n, int W, Item[] items) {
        if (u.weight >= W) return 0;

        int profit_bound = u.profit;
        int j = u.level + 1;
        int totweight = u.weight;

        while ((j < n) && (totweight + items[j].weight <= W)) {
            totweight += items[j].weight;
            profit_bound += items[j].value;
            j++;
        }

        if (j < n) profit_bound += (W - totweight) * items[j].value / items[j].weight;

        return profit_bound;
    }

    public static int knapsackBranchBound(int W, Item[] items, int n) {
        Arrays.sort(items, (a, b) -> b.value / b.weight - a.value / a.weight);

        Queue<Node> Q = new LinkedList<>();
        Node u = new Node();
        Node v = new Node();

        Q.add(u);
        int maxProfit = 0;

        while (!Q.isEmpty()) {
            u = Q.poll();

            if (u.level == -1) v.level = 0;

            if (u.level == n - 1) continue;

            v.level = u.level + 1;

            v.weight = u.weight + items[v.level].weight;
            v.profit = u.profit + items[v.level].value;

            if (v.weight <= W && v.profit > maxProfit) maxProfit = v.profit;

            v.bound = bound(v, n, W, items);

            if (v.bound > maxProfit) Q.add(new Node(v));

            v.weight = u.weight;
            v.profit = u.profit;
            v.bound = bound(v, n, W, items);

            if (v.bound > maxProfit) Q.add(new Node(v));
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int W = 50;
        int n = values.length;

        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(weights[i], values[i]);
        }

        System.out.println("Maximum value in Knapsack (Branch & Bound): " + knapsackBranchBound(W, items, n));
    }
}

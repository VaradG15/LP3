import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class HuffmanNode {
    char character;
    int frequency;

    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }
}

class HuffmanComparator implements Comparator<HuffmanNode> {

    @Override
    public int compare(HuffmanNode node1, HuffmanNode node2) {
        return Integer.compare(node1.frequency, node2.frequency);
    }
}

public class HuffmanEncoding {

    public static void generateCodes(HuffmanNode root, String code, Map<Character, String> huffmanCodes) {

        if(root == null) return;

        if(root.left == null && root.right == null) {
            huffmanCodes.put(root.character, code);
        }

        generateCodes(root.left, code + "0", huffmanCodes);
        generateCodes(root.right, code + "1", huffmanCodes);
    }

    public static Map<Character, String> buildHuffmanTree(Map<Character, Integer> frequencyMap) {

        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>(new HuffmanComparator());

        for(Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            priorityQueue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while(priorityQueue.size()>1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();

            HuffmanNode sum = new HuffmanNode('-', left.frequency + right.frequency);
            sum.left = left;
            sum.right = right;

            priorityQueue.add(sum);
        }

        HuffmanNode root = priorityQueue.poll();

        Map<Character, String> huffmanCodes =new HashMap<>();
        generateCodes(root, "", huffmanCodes);
        return huffmanCodes;
    }

    public static void main(String[] args) {

        String text = "Hello I am Varad Gadaokar";

        Map<Character, Integer> frequencyMap = new HashMap<>();
        for(char c : text.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        Map<Character, String> huffmanCodes = buildHuffmanTree(frequencyMap);
        System.out.println("Character\tHuffman Codes");
        for(Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        StringBuilder encodedText = new StringBuilder();
        for(char c : text.toCharArray()) {
            encodedText.append(huffmanCodes.get(c));
        }
        System.out.println("\nEncoded text: " + encodedText.toString());
    }
}

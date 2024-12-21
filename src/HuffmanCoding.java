import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Map;

class Node implements Comparable<Node> {
    int frequency;
    char character;
    Node left;
    Node right;

    Node(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        left = null;
        right = null;
    }
    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.frequency, other.frequency);
    }
}

        public class HuffmanCoding {


            public Node buildHuffmanTree(Map<Character, Integer> charFreqMap) {
                PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

                for (Map.Entry<Character, Integer> entry : charFreqMap.entrySet()) {
                    priorityQueue.add(new Node(entry.getKey(), entry.getValue()));
                }

                while (priorityQueue.size() > 1) {
                    Node left = priorityQueue.poll();
                    Node right = priorityQueue.poll();
                    Node parent = new Node('\0', left.frequency + right.frequency);
                    parent.left = left;
                    parent.right = right;
                    priorityQueue.add(parent);
                }

                return priorityQueue.poll();
            }


            public void generateHuffmanCodes(Node root, String code, Map<Character, String> huffmanCodeMap) {
                if (root == null) {
                    return;
                }

                if (root.left == null && root.right == null) {
                    huffmanCodeMap.put(root.character, code);
                }

                generateHuffmanCodes(root.left, code + "0", huffmanCodeMap);
                generateHuffmanCodes(root.right, code + "1", huffmanCodeMap);
            }


            public String compress(String text) {
                Map<Character, Integer> charFreqMap = new HashMap<>();
                for (char character : text.toCharArray()) {
                    charFreqMap.put(character, charFreqMap.getOrDefault(character, 0) + 1);
                }

                Node root = buildHuffmanTree(charFreqMap);

                Map<Character, String> huffmanCodeMap = new HashMap<>();
                generateHuffmanCodes(root, "", huffmanCodeMap);


                StringBuilder compressedText = new StringBuilder();
                for (char character : text.toCharArray()) {
                    compressedText.append(huffmanCodeMap.get(character));
                }

                return compressedText.toString();
            }

            public static void main(String[] args) {
                HuffmanCoding huffmanCoding = new HuffmanCoding();
                String text = "hello world";
                String compressedText = huffmanCoding.compress(text);

                System.out.println("Оригинальный текст: " + text);
                System.out.println("Сжатый текст: " + compressedText);
            }
        }


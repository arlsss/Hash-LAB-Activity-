import java.util.LinkedList;

class HashTable<K, V> {
    private class Node {
        K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Node>[] buckets;
    private int size;
    private final int DEFAULT_CAPACITY = 10;

    public HashTable() {
        buckets = new LinkedList[DEFAULT_CAPACITY];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % buckets.length);
    }

    public void put(K key, V value) {
        int index = hash(key);
        LinkedList<Node> bucket = buckets[index];

        for (Node node : bucket) {
            if (node.key.equals(key)) {
                node.value = value; // Update value if key exists
                return;
            }
        }

        bucket.add(new Node(key, value)); // Add new key-value pair
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        LinkedList<Node> bucket = buckets[index];

        for (Node node : bucket) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }

        return null; // Key not found
    }

    public void remove(K key) {
        int index = hash(key);
        LinkedList<Node> bucket = buckets[index];

        for (Node node : bucket) {
            if (node.key.equals(key)) {
                bucket.remove(node);
                size--;
                return;
            }
        }
    }

    public boolean containsKey(K key) {
        int index = hash(key);
        LinkedList<Node> bucket = buckets[index];

        for (Node node : bucket) {
            if (node.key.equals(key)) {
                return true;
            }
        }

        return false;
    }

    public int size() {
        return size;
    }

    public void display() {
        for (int i = 0; i < buckets.length; i++) {
            System.out.print("Bucket " + i + ": ");
            for (Node node : buckets[i]) {
                System.out.print("(" + node.key + ", " + node.value + ") ");
            }
            System.out.println();
        }
    }
}

public class HashTableDemo {
    public static void main(String[] args) {
        HashTable<String, Integer> hashTable = new HashTable<>();

        // Insert key-value pairs
        hashTable.put("Alice", 25);
        hashTable.put("Bob", 30);
        hashTable.put("Charlie", 35);

        // Retrieve values
        System.out.println("Alice's age: " + hashTable.get("Alice"));
        System.out.println("Bob's age: " + hashTable.get("Bob"));

        // Remove a key-value pair
        hashTable.remove("Bob");
        System.out.println("After removing Bob:");
        hashTable.display();

        // Check for a key
        System.out.println("Contains Charlie? " + hashTable.containsKey("Charlie"));
        System.out.println("Contains Bob? " + hashTable.containsKey("Bob"));

        // Display the entire hash table
        System.out.println("Hash Table contents:");
        hashTable.display();
    }
}
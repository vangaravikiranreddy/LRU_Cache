import java.util.HashMap;

public class LRUCache {
    private HashMap<Integer, DoubleListNode> nodesList = new HashMap<>();
    private int capacity;
    private DoubleListNode head;
    private DoubleListNode tail;

    public void deleteHeadNode() {
        head = head.next;
    }
    public void deleteMiddleNode(DoubleListNode node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
        node.next = null;
        node.prev = null;
    }
    public void updateCache(int key) {
        if (head == tail || nodesList.get(key).next == null) return;
        if (head == nodesList.get(key)) {
           deleteHeadNode();
        } else {
           deleteMiddleNode(nodesList.get(key));
        }

        DoubleListNode node = nodesList.get(key);
        node.next = null;
        node.prev = null;
        tail.next = node;
        node.prev = tail;
        tail = tail.next;
    }
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (nodesList.containsKey(key)) {
            updateCache(key);
        }
        return (!nodesList.containsKey(key)) ? -1 : tail.value;
    }
    public void put(int key, int value) {
        if (nodesList.containsKey(key)) {
            nodesList.get(key).value = value;
           updateCache(key);
        } else if (nodesList.size() < capacity) {
            DoubleListNode node = new DoubleListNode(value, key);
            if (head == null) {
                head = tail = node;
            } else {
                tail.next = node;
                node.prev = tail;
                tail = tail.next;
            }
            nodesList.put(key, node);
        } else {
            nodesList.remove(head.key);
            deleteHeadNode();
            if (head != null) {
                DoubleListNode node = head.prev;
                node.next = null;
            }
            put(key, value);
        }
    }
}

public class LRUCacheImpl {
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.put(3, 5);
        lruCache.put(1, 2);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(4));
        lruCache.put(4, 5);
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.get(3));

    }
}
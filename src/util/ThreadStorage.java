package util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadStorage {

    private final Map<String, Thread> storage;

    public ThreadStorage() {
        this.storage = new ConcurrentHashMap<>();
    }

    public Map<String, Thread> getStorage() {
        return storage;
    }

    public boolean addThread(String name, Thread thread) {
        if (this.storage.get(name) != null) {
            return false;
        }
        this.storage.put(name, thread);
        return true;
    }

    public Thread get(String name) {
        return this.storage.get(name);
    }
}

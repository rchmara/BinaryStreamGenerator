package pracownia.badawcza;

/**
 * Created by Krzysztof Chowański on 2017-05-18.
 */
public class Bucket {
    private long timestamp;
    private int size;

    public Bucket(long timestamp) {
        size = 1;
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isExpired(long expiryTime) {
        return timestamp == expiryTime;
    }

    @Override
    public String toString() {
        return "" + size;
    }
}

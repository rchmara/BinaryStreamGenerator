package pracownia.badawcza;

import java.util.ArrayList;

/**
 * Created by Krzysztof Chowański on 2017-05-18.
 */
public class ExponentialHistogram {

    public static final int WINDOW_SIZE = 75;    // window size
    private double epsilon = 0.5;
    private double k = Math.ceil(1 / epsilon);
    private double kDividedByTwo = Math.ceil(k / 2);

    private int last = 1;   // size of the last bucket
    private int total;  // total size of buckets
    private ArrayList<Bucket> buckets;
    private long arrivalTime = 1;
    private long expiryTime = 1 - WINDOW_SIZE;

    public ExponentialHistogram() {
        buckets = new ArrayList<Bucket>();
    }

    public void add(int value) {

        // 1
        // Calculate new expiry time
        expiryTime++;
        arrivalTime++;

        Bucket lastBucket = null;

        if (!buckets.isEmpty()) {
            // Check if timestamp of the last bucket indicates expiry
            lastBucket = buckets.get(buckets.size() - 1);
            if (lastBucket.isExpired(expiryTime)) {
                buckets.remove(lastBucket);
                // Update the counter LAST - containing size of the last bucket
                last = buckets.isEmpty() ? 0 : buckets.get(buckets.size() - 1).getSize();
                // Update TOTAL - size of all buckets
                total -= lastBucket.getSize();
            }
        }

        // 2
        // new element is 0 then ignore it
        if (value == 0) return;

        // if element is 1, create new bucket with size 1 and the current timestamp, increment TOTAL
        buckets.add(0, new Bucket(arrivalTime - 1));
        total++;

        // 3
        // traverse the list of buckets in order of increasing sizes
        int onesLimit = (int) k + 2;

        if (buckets.size() >= onesLimit) {
            int otherLimit = (int) (k / 2) + 2;

            int limit = onesLimit;

            int mergeExponent = 0;
            int mergerBucketSize = (int) Math.pow(2, mergeExponent);

            ArrayList<Bucket> bucketsToMerge = new ArrayList<Bucket>();

            while (true) {

                for (Bucket bucket : buckets) {
                    if (bucket.getSize() < mergerBucketSize) continue;

                    if (bucket.getSize() > mergerBucketSize) break;

                    if (bucket.getSize() == mergerBucketSize) {
                        bucketsToMerge.add(bucket);
                    } else {
                        break;
                    }
                }

                if (bucketsToMerge.size() != limit) {
                    break;
                }

                // MERGE
                mergeExponent++;
                mergerBucketSize = (int) Math.pow(2, mergeExponent);
                limit = otherLimit;

                // Take two oldest buckets
                // we iterate from recent to oldest so take last 2 buckets from bucketsToMerge
                Bucket lastBucketToMerge = bucketsToMerge.get(bucketsToMerge.size() - 1);
                Bucket oneBeforeLastBucketToMerge = bucketsToMerge.get(bucketsToMerge.size() - 2);

                // If last bucket is being merged, update LAST counter
                if (oneBeforeLastBucketToMerge.getSize() == lastBucket.getSize()) {
                    last = oneBeforeLastBucketToMerge.getSize() * 2;
                }

                // Update size of the most recent bucket with double size
                oneBeforeLastBucketToMerge.setSize(oneBeforeLastBucketToMerge.getSize() * 2);
                // remove last bucket
                buckets.remove(lastBucketToMerge);

                // Clear temp buckets
                bucketsToMerge = new ArrayList<Bucket>();
            }

        }
    }


    @Override
    public String toString() {
        return "ExponentialHistogram{" +
                "last=" + last +
                ", total=" + total +
                ", buckets=" + buckets +
                '}';
    }

    public int getTotal() {
        return total;
    }

    public int getLastBucketSize() {
        return last;
    }
}

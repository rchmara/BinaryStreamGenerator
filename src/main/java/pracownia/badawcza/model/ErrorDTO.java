package pracownia.badawcza.model;

/**
 * Created by tomas on 21.05.2017.
 */
public class ErrorDTO {
    private int id;
    private final int step;
    private final int streamId;
    private final int originalCount;
    private final int histogramCount;
    private int lastBucketSize;

    public ErrorDTO(int step, int streamId, int originalCount, int histogramCount) {
        this.step = step;
        this.streamId = streamId;
        this.originalCount = originalCount;
        this.histogramCount = histogramCount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getStep() {
        return step;
    }

    public int getStreamId() {
        return streamId;
    }

    public int getOriginalCount() {
        return originalCount;
    }

    public int getHistogramCount() {
        return histogramCount;
    }

    public int getLastBucketSize() {
        return lastBucketSize;
    }

    public void setLastBucketSize(int lastBucketSize) {
        this.lastBucketSize = lastBucketSize;
    }
}

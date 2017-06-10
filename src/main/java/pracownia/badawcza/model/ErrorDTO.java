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
    private int estimate;

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

    public int getEstimate() {
        return estimate;
    }

    public void setEstimate(int estimate) {
        this.estimate = estimate;
    }
}

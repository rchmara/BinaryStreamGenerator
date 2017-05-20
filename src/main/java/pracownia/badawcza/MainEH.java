package pracownia.badawcza;

/**
 * Created by Krzysztof Chowański on 2017-05-18.
 */
public class MainEH {
    public static void main(String[] args) {
        int[] input = new BinaryStreamReader("output").readAsIntArray();

        ExponentialHistogram histogram = new ExponentialHistogram();

        for (int value : input) {
            histogram.add(value);
        }
    }
}

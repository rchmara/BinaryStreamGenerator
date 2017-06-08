package pracownia.badawcza;

import pracownia.badawcza.model.BinaryStreamDTO;
import pracownia.badawcza.model.ErrorDTO;
import pracownia.badawcza.model.GlobalDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Krzysztof Chowański on 2017-05-18.
 */
public class MainEH {
    public static void main(String[] args) {
//        int[] input = new BinaryStreamReader("output").readAsIntArray();

        GlobalDAO globalDAO = new GlobalDAO();
        int streamId = 1;
        BinaryStreamDTO binaryStreamDTO = globalDAO.getBinaryStream(streamId);
        String[] splittedStream = binaryStreamDTO.getStream().split("");
        int[] r4 = Stream.of(splittedStream).mapToInt(Integer::parseInt).toArray();

        ExponentialHistogram histogram = new ExponentialHistogram();

        Window slidingWindow = new Window(ExponentialHistogram.WINDOW_SIZE);


        int step = 0;
        long start = System.currentTimeMillis();
        List<ErrorDTO> errors = new ArrayList<>();
        for (int value : r4) {
            histogram.add(value);
            slidingWindow.add(value);
            int histogramTotal = histogram.getTotal();
            int windowTotal = slidingWindow.getTotal();
            errors.add(new ErrorDTO(step++, streamId, windowTotal, histogramTotal));
        }

        System.out.println(String.format("Generating histogram finished. Time: %d ms", System.currentTimeMillis() - start));
        System.out.println("Saving errors to database...");
        globalDAO.addErrors(errors);
        System.out.println(String.format("Saving finished. Total time: %d ms", System.currentTimeMillis() - start));
    }

}

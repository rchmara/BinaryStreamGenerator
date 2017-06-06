package pracownia.badawcza;

import pracownia.badawcza.model.BinaryStreamDTO;
import pracownia.badawcza.model.GlobalDAO;

import java.util.stream.Stream;

/**
 * Created by Krzysztof Chowański on 2017-05-18.
 */
public class MainEH {
    public static void main(String[] args) {
//        int[] input = new BinaryStreamReader("output").readAsIntArray();

        GlobalDAO globalDAO = new GlobalDAO();
        BinaryStreamDTO binaryStreamDTO = globalDAO.getBinaryStream(3);
        String[] splittedStream = binaryStreamDTO.getStream().split("");
        int[] r4 = Stream.of(splittedStream).mapToInt(Integer::parseInt).toArray();

        ExponentialHistogram histogram = new ExponentialHistogram();

        for (int value : r4) {
            histogram.add(value);
        }
    }
}

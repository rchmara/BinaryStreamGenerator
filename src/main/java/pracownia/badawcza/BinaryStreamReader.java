package pracownia.badawcza;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by Krzysztof Chowański on 2017-05-20.
 */
public class BinaryStreamReader {

    private final String fileName;

    public BinaryStreamReader(String fileName) {
        this.fileName = fileName;
    }

    public int[] readAsIntArray() {
        File file = new File(String.format("%s.txt", fileName));
        try {
            String stringStream = FileUtils.readFileToString(file, "UTF-8");
            char[] characters = stringStream.toCharArray();
            int[] result = new int[stringStream.length()];

            for (int i = 0; i < stringStream.length(); i++) {
                result[i] = Character.digit(characters[i], 10);
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

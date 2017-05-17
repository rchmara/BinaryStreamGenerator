package pracownia.badawcza;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinaryStreamGenerator {

	private final Long onesCount;
	private final Boolean randomMode;
	private final Long length;
	private final String outputFilename;

	public BinaryStreamGenerator(Long onesCount, Boolean randomMode, Long length, String outputFilename) {
		this.onesCount = onesCount;
		this.randomMode = randomMode;
		this.length = length;
		this.outputFilename = outputFilename;
	}

	public void generate() {
		String stream = getStream();
		File outputFile = new File(String.format("%s.txt", outputFilename));
		try {
			FileUtils.writeStringToFile(outputFile, stream, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getStream() {
		List streamList = new ArrayList<Character>();
		for(long i = 0; i < onesCount; i++) {
			streamList.add('1');
		}
		for(long i = onesCount; i < length; i++) {
			streamList.add('0');
		}
		if(randomMode) {
			Collections.shuffle(streamList);
		}
		return getStringRepresentation(streamList);
	}

	private String getStringRepresentation(List<Character> list)
	{
		StringBuilder builder = new StringBuilder(list.size());
		for(Character ch: list)
		{
			builder.append(ch);
		}
		return builder.toString();
	}
}

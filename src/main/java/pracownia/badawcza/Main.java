package pracownia.badawcza;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {

    public static void main(String[] args) {

        Options options = getOptions();

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("bingenerator", options);
            System.exit(1);
            return;
        }

        if(cmd.hasOption("help")){
            formatter.printHelp("bingenerator", options);
            System.exit(0);
            return;
        }

        String onesCount = cmd.getOptionValue("density");
        Boolean randomMode = cmd.hasOption("randomly");
        String streamLength = cmd.getOptionValue("length");
        long onesCountL = Long.parseLong(onesCount, 10);
        long streamLengthL = Long.parseLong(streamLength, 10);

        System.out.println(String.format("Generating stream: %d elements, randomMode: %s, number of ones: %d",
                streamLengthL, randomMode, onesCountL));

        long start = System.currentTimeMillis();
        BinaryStreamGenerator generator = new BinaryStreamGenerator(onesCountL, randomMode, streamLengthL, "output");
        generator.generate();
        long end = System.currentTimeMillis();

        System.out.println(String.format("Finished. Time: %d ms", end - start));
    }

    private static Options getOptions() {
        Options options = new Options();

        Option help = new Option( "help", "print this message" );
        Option input = new Option("d", "density", true,
                "number of ones in binary stream");
        input.setRequired(true);
        Option randomly = new Option("r", "randomly", false,
                "ones distributed randomly");
        randomly.setRequired(false);
        Option length = new Option("l", "length", true,
                "length of the stream");
        length.setRequired(true);


        options.addOption(help);
        options.addOption(input);
        options.addOption(randomly);
        options.addOption(length);

        return options;
    }


}

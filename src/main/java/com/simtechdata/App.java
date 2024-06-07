package com.simtechdata;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

/**
 * JavaFX App
 */
public class App {

    public static void main(String[] args) {
        if (args.length == 0) {
            Extract.fromClipboard();
        }
        else {
            for (String arg : args) {
                switch (arg) {
                    case "out" -> Extract.setOutput();
                    case "/?", "?", "--help", "-help", "help" -> {
                        showHelp();
                        System.exit(0);
                    }
                    case "--version", "-version", "version", "v" -> {
                        showVersion();
                        System.exit(0);
                    }
                }
                if (arg.startsWith("http")) {
                    Extract.fromURL(arg);
                    System.exit(0);
                }
            }
            Extract.fromClipboard();
            System.exit(0);
        }
    }


    private static void showHelp() {
        String help = """

                 Magnet Extractor
                 ----------------
                 Extracts magnet links from html or URL
                                \s
                 If you use the developer mode from your web browser you can copy the pages HTML code
                 into your clipboard. Then just run magnet:
                                \s
                     magnet
                                \s
                 If you want to give it the URL and let magnet grab the html code:
                                \s
                     magnet https://somserver.com/some/link/
                                \s
                 If you would like the link output to a file (for batch operations etc.) you can do it like this:
                                \s
                     magnet out > C:\\MyFile.txt
                     magnet out https://somserver.com/some/link/ > C:\\MyFile.txt
                                \s
                \s""";
        System.out.println(help);
    }

    private static void showVersion() {
        Properties prop = new Properties();
        try (InputStream input = App.class.getClassLoader().getResourceAsStream("version.properties")) {
            if (input == null) {
                System.out.println("Could not determine current version");
            }
            else {
                prop.load(input);
                System.out.println(prop.getProperty("version"));
            }
        }
        catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            throw new RuntimeException(e);
        }
    }

}

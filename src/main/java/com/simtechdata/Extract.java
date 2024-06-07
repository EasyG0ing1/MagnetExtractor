package com.simtechdata;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Extract {

    private static final String NL = System.lineSeparator();
    private static boolean out = false;


    public static void setOutput() {
        out = true;
    }

    public static void fromClipboard() {
        String clipText = getClipboard().strip();
        if (clipText.toLowerCase().startsWith("http")) {
            fromURL(clipText);
        }
        else {
            fromHTML(clipText);
        }
        System.exit(0);
    }

    public static void fromURL(String url) {
        fromHTML(getHTML(url.strip()));
    }


    private static void fromHTML(String html) {
        String magnet = getMagnet(html.strip());
        output(magnet);
    }

    public static void output(String magnet) {
        if (out) {
            System.out.println(magnet);
            return;
        }
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection contents = new StringSelection(magnet);
        clipboard.setContents(contents, null);
        System.out.println(STR."\{NL}\tMagnet link copied to clipboard");
    }

    private static String getMagnet(String html) {
        String[] parts = html.split("\"");
        for (String part : parts) {
            if (part.startsWith("magnet")) {
                return part.strip();
            }
        }
        return "";
    }

    private static String getClipboard() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contents = clipboard.getContents(null);
        String text = "";
        try {
            if (contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                text = (String) contents.getTransferData(DataFlavor.stringFlavor);
            }
            else {
                System.out.println("Content in clipboard is not a String");
                System.exit(0);
            }
        }
        catch (IOException | UnsupportedFlavorException e) {
            throw new RuntimeException(e);
        }
        return text.strip();
    }

    private static String getHTML(String urlString) {
        StringBuilder sb = new StringBuilder();
        if(!urlString.endsWith("/"))
            urlString += "/";
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append(NL);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString().strip();
    }

}

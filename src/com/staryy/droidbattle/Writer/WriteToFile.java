package com.staryy.droidbattle.Writer;

import java.io.*;

public class WriteToFile {

    private static FileWriter myWriter;
    private static final String PATH = "replay.txt";
    public static boolean writeToFile = false;

    public WriteToFile() {
        try {
            myWriter = new FileWriter(PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeAndShow(String string) {
        System.out.println(string);
        if (writeToFile) {
            try {
                myWriter.write(string + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeInputValue(String value) {
        if (writeToFile) {
            try {
                myWriter.write(value + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void readFromFile() {

        try {
            FileInputStream fStream = new FileInputStream(PATH);
            BufferedReader br = new BufferedReader(new InputStreamReader(fStream));

            String strLine;

            while ((strLine = br.readLine()) != null) {
                System.out.println(strLine);
            }
            fStream.close();
        } catch (IOException e) {
            System.out.println("The wrong way to file!");
            e.printStackTrace();
        }
    }

    public void closeWriter() {
        try {
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

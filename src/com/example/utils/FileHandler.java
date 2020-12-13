package com.example.utils;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class FileHandler {

    /**
     * Reading the line of a file
     *
     * @param fileName of the file to read contents
     * @return A list of lines of the file
     * @throws FileNotFoundException if the file specified does not exists on the system
     */
    public static List<String> getFileContent(String fileName) throws IOException {

        File file = new File(fileName);

        createFileIfNotThere(file);

        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(file)
        );

        return bufferedReader.lines().collect(Collectors.toList());
    }

    /**
     * Appends content to a file
     *
     * @param fileName the file to append to
     * @param text     the line to write
     * @throws IOException if an error occurs in the I/O
     */
    public static void appendToFile(String fileName, String text) throws IOException {

        File file = new File(fileName);

        createFileIfNotThere(file);

        try (PrintWriter printWriter = new PrintWriter(
                new BufferedWriter(
                        new FileWriter(fileName, true)
                )
        )) {
            printWriter.println(text);
        }


    }

    public static void createFileIfNotThere(File file) throws IOException {

        //Check if the file exist and create it if not
        if (!file.exists())
            if (file.createNewFile())
                System.out.println(file.getName() + " created");
    }
}

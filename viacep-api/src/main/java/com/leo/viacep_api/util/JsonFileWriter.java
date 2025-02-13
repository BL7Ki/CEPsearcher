package com.leo.viacep_api.util;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leo.viacep_api.model.CepResponse;

public class JsonFileWriter {

    private static final String DIRECTORY = "data/"; // Directory where JSON files will be saved

    public static void saveToFile(CepResponse cepResponse) {
        try {
            // Create the directory if it does not exist
            File directory = new File(DIRECTORY);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Define the filename based on the CEP
            String fileName = DIRECTORY + cepResponse.getCep() + ".json";
            File file = new File(fileName);

            // Create an ObjectMapper to convert the object into a JSON file
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, cepResponse);

            System.out.println("File saved: " + fileName);
        } catch (IOException e) {
            // Print an error message if the file cannot be saved
            System.err.println("Error saving JSON file: " + e.getMessage());
        }
    }
}

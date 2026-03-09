package com.AmanPundir;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;

public class OutputWriter {

    public static void write(List<APIResult> results) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        // create output folder if not exists
        File outputDir = new File("output");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        File outputFile = new File("output/apis.json");

        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(outputFile, results);

        System.out.println("Results saved to output/apis.json");
    }
}
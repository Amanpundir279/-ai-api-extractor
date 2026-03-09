package com.AmanPundir;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.*;

public class OpenAPIWriter {

    public static void write(List<APIResult> results) throws Exception {

        Map<String, Object> root = new LinkedHashMap<>();

        root.put("openapi", "3.0.0");

        Map<String, String> info = new HashMap<>();
        info.put("title", "Auto Generated API Docs");
        info.put("version", "1.0.0");

        root.put("info", info);

        Map<String, Object> paths = new LinkedHashMap<>();

        for (APIResult api : results) {

            Map<String, Object> method = new HashMap<>();

            Map<String, Object> response = new HashMap<>();
            response.put("description", "Success");

            Map<String, Object> responses = new HashMap<>();
            responses.put("200", response);

            method.put("responses", responses);

            Map<String, Object> pathItem =
                    (Map<String, Object>) paths.getOrDefault(api.getPath(), new HashMap<>());

            pathItem.put(api.getMethod().toLowerCase(), method);

            paths.put(api.getPath(), pathItem);
        }

        root.put("paths", paths);

        ObjectMapper mapper = new ObjectMapper();

        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File("output/openapi.json"), root);

        System.out.println("OpenAPI file generated: output/openapi.json");
    }
}
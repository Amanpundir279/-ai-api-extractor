package com.AmanPundir;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class APIExtractor {

    private static final Pattern API_PATTERN =
            Pattern.compile("(router|app)\\.(get|post|put|delete|patch)\\s*\\(\\s*['\"](.*?)['\"]");

    public static List<APIEndpoint> extractEndpoints(File jsFile) {

        List<APIEndpoint> endpoints = new ArrayList<>();

        try {

            String content = new String(Files.readAllBytes(jsFile.toPath()));

            Matcher matcher = API_PATTERN.matcher(content);

            while (matcher.find()) {

                String method = matcher.group(2).toUpperCase();
                String path = matcher.group(3);

                int start = Math.max(0, matcher.start() - 200);
                int end = Math.min(content.length(), matcher.end() + 300);

                String snippet = content.substring(start, end);

                endpoints.add(new APIEndpoint(method, path, snippet));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return endpoints;
    }
}
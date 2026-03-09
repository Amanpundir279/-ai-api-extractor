package com.AmanPundir;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {

            System.out.println("Enter GitHub Repository URL:");
            String repoUrl = scanner.nextLine();

            // Step 1: Clone repo
            String repoPath = RepoCloner.cloneRepo(repoUrl);

            System.out.println("Scanning repository for JS files...");

            // Step 2: Find JS/TS files
            List<File> jsFiles = FileScanner.findJSFiles(repoPath);

            List<APIEndpoint> endpoints = new ArrayList<>();

            // Step 3: Extract APIs
            for (File file : jsFiles) {

                System.out.println("Scanning file: " + file.getName());

                List<APIEndpoint> extracted = APIExtractor.extractEndpoints(file);

                endpoints.addAll(extracted);
            }

            // Remove duplicates
            Set<String> uniqueApis = new HashSet<>();
            List<APIEndpoint> uniqueEndpoints = new ArrayList<>();

            for (APIEndpoint api : endpoints) {

                String key = api.getMethod() + ":" + api.getPath();

                if (!uniqueApis.contains(key)) {
                    uniqueApis.add(key);
                    uniqueEndpoints.add(api);
                }
            }

            System.out.println("\nUnique APIs extracted: " + uniqueEndpoints.size());

            // Final results
            List<APIResult> results = new ArrayList<>();

            ObjectMapper mapper = new ObjectMapper();

            int count = 0;

            System.out.println("\nTesting first 20 APIs with LLM...\n");

            for (APIEndpoint api : uniqueEndpoints) {

                if (count >= 20) break;

                System.out.println("API: " + api.getMethod() + " " + api.getPath());

                try {

                    // Call LLM
                    String schema = LLMService.inferSchema(api.getCodeSnippet());

                    System.out.println("AI Schema:");
                    System.out.println(schema);

                    Map<String, Object> requestSchema = new HashMap<>();
                    Map<String, Object> responseSchema = new HashMap<>();

                    try {

                        Map<String, Object> parsed =
                                mapper.readValue(schema, Map.class);

                        if (parsed.get("requestSchema") != null) {
                            requestSchema =
                                    (Map<String, Object>) parsed.get("requestSchema");
                        }

                        if (parsed.get("responseSchema") != null) {
                            responseSchema =
                                    (Map<String, Object>) parsed.get("responseSchema");
                        }

                    } catch (Exception parseError) {

                        System.out.println("Schema parsing failed, using empty schema.");
                    }

                    results.add(new APIResult(
                            api.getMethod(),
                            api.getPath(),
                            requestSchema,
                            responseSchema
                    ));

                } catch (Exception e) {

                    System.out.println("LLM call failed");
                    e.printStackTrace();
                }

                System.out.println("--------------------------------------");

                count++;

                // Avoid rate limit
                Thread.sleep(1000);
            }

            // Step 4: Write JSON output
            OutputWriter.write(results);

            OpenAPIWriter.write(results);

            System.out.println("\nResults saved to output/apis.json");

            System.out.println("\nTest completed.");

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            scanner.close();
        }
    }
}
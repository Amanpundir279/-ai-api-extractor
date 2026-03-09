package com.AmanPundir;

import okhttp3.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;

public class LLMService {

    private static final String API_KEY = System.getenv("OPENAI_API_KEY");

    private static final String OPENAI_URL =
            "https://api.openai.com/v1/chat/completions";

    private static final OkHttpClient client = new OkHttpClient();

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String inferSchema(String codeSnippet) throws IOException {

        String prompt =
                "You are an API documentation generator.\n" +
                        "Given a Node.js Express API handler code, infer:\n" +
                        "1. request JSON schema\n" +
                        "2. response JSON schema\n\n" +
                        "Return ONLY valid JSON like this:\n" +
                        "{ \"requestSchema\":{}, \"responseSchema\":{} }\n\n" +
                        "Code:\n" + codeSnippet;

        // Build message
        Map<String, String> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", prompt);

        // Build request body
        Map<String, Object> requestBodyMap = new HashMap<>();
        requestBodyMap.put("model", "gpt-4.1-mini");
        requestBodyMap.put("messages", List.of(message));

        String jsonBody = mapper.writeValueAsString(requestBodyMap);

        RequestBody body =
                RequestBody.create(jsonBody, MediaType.parse("application/json"));

        Request request =
                new Request.Builder()
                        .url(OPENAI_URL)
                        .post(body)
                        .addHeader("Authorization", "Bearer " + API_KEY)
                        .addHeader("Content-Type", "application/json")
                        .build();

        Response response = client.newCall(request).execute();

        String responseBody = response.body().string();

        // Parse OpenAI response
        Map<String, Object> json = mapper.readValue(responseBody, Map.class);

        List choices = (List) json.get("choices");

        if (choices == null || choices.isEmpty()) {
            return "{}";
        }

        Map choice0 = (Map) choices.get(0);

        Map messageResponse = (Map) choice0.get("message");

        String content = (String) messageResponse.get("content");

        return content;
    }
}
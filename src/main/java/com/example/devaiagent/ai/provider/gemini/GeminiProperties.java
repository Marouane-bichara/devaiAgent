package com.example.devaiagent.ai.provider.gemini;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.ai.gemini")
public class GeminiProperties {
    public String apiKey;
    public String model;
    public String baseUrl;
}


package org.redhat.validator;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import dev.langchain4j.model.openai.OpenAiChatModel;

@ApplicationScoped
public class OpenAiModelProducer {

    @Produces
    @ApplicationScoped
    public OpenAiChatModel produceModel() {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .modelName("gpt-4.1-mini")
                .build();
    }
}
package org.redhat.test;

import io.quarkus.test.InjectMock;
import org.junit.jupiter.api.Test;
import dev.langchain4j.model.openai.OpenAiChatModel;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;


class ResourceValidatorTest {

    @InjectMock
    OpenAiChatModel mockModel;  // replaces the real bean

    @Test
    void testValidateYaml() {
        String fakeResponse = "YAML is valid. No issues found.";
        when(mockModel.chat(org.mockito.ArgumentMatchers.anyString()))
                .thenReturn(fakeResponse);

        given()
            .body("apiVersion: v1\nkind: Pod") // sample YAML
            .when().post("/validate")
            .then()
            .statusCode(200)
            .body("validation", is(fakeResponse));
    }
}
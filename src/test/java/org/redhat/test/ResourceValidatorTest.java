package org.redhat.test;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.InjectMock;
import org.junit.jupiter.api.Test;
import dev.langchain4j.model.openai.OpenAiChatModel;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@QuarkusTest
class ResourceValidatorTest {

    @InjectMock
    OpenAiChatModel mockModel;

    @Test
    void testValidateYaml() {
        String fakeResponse = "YAML is valid. No issues found.";
        when(mockModel.chat(org.mockito.ArgumentMatchers.anyString()))
                .thenReturn(fakeResponse);

        given()
            .contentType("text/plain") 
            .body("apiVersion: v1\nkind: Pod")
            .when().post("/validate")
            .then()
            .statusCode(200)
            .body(equalTo("{validation=YAML is valid. No issues found.}"));
    }
}
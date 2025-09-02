package org.redhat.validator;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import dev.langchain4j.model.openai.OpenAiChatModel;
import java.util.Map;

@Path("/validate")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.APPLICATION_JSON)
public class ResourceValidator {

    @Inject
    OpenAiChatModel model;

    @POST
    public Response validateYaml(String yaml) {
        String response = model.chat("Validate this OpenShift YAML for correctness. If correct, sumarize the YAML in a markdown table. Limits your answer to only suggest specific changes to fix it. If the YALM structure is correct don't suggest any change:\n" + yaml);
        return Response.ok(Map.of("validation", response)).build();
    }
}
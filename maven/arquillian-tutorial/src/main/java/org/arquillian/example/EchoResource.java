package org.arquillian.example;

import java.util.HashMap;
import java.util.Map;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("echo")
public class EchoResource {
    @Inject
    MessageService messageService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String get(@QueryParam("message") String message) {
        return messageService.format(message);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> post(Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", messageService.format((String) request.get("message")));
        return response;
    }
}

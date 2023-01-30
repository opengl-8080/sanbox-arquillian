package org.arquillian.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@ApplicationScoped
@Path("message")
public class MessageResource {
    @Inject
    MessageService messageService;

    @GET
    @Produces(value = MediaType.TEXT_PLAIN)
    public String hello(@QueryParam("word") @DefaultValue("World") String word) {
        return messageService.format(word);
    }
}

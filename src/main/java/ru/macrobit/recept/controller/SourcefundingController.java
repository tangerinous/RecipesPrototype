package ru.macrobit.recept.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.macrobit.recept.commons.Recept;
import ru.macrobit.recept.pojo.Sourcefunding;
import ru.macrobit.recept.security.ContextService;
import ru.macrobit.recept.service.SourcefundingService;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by david on 7/8/16
 */
@Path("/sourcefunding")
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({"ADMIN", "MIAC"})
public class SourcefundingController {
    private static final Logger log = LoggerFactory.getLogger(SourcefundingController.class);

    @Inject
    private SourcefundingService sourcefundingService;
    @EJB
    private ContextService ctx;

    @GET
    @Path("/{id}")
    public Sourcefunding getById(@PathParam("id") Long id) {
        return sourcefundingService.findById(id, ctx.getCurrentUser());
    }

    @GET
    @Path("/")
    public Object getByQuery(@QueryParam("query") String jsonQuery,
                             @QueryParam("count") String count, @QueryParam("skip") Integer skip,
                             @QueryParam("limit") Integer limit, @QueryParam("sort") String sortProperties,
                             @QueryParam("direction") String sortDirection) throws IOException {
        return sourcefundingService.findAll(jsonQuery == null ? null : Recept.MAPPER.readValue(jsonQuery, JsonNode.class), skip, limit, count, sortProperties, sortDirection, ctx.getCurrentUser());
    }

    @POST
    public Sourcefunding post(Sourcefunding sourcefunding) throws Exception {
        sourcefundingService.insert(sourcefunding);
        return sourcefunding;
    }

    @PUT
    @Path("/{id}")
    public Sourcefunding put(JsonNode sourcefunding, @PathParam("id") Long id) throws Exception {
        return sourcefundingService.update(id, sourcefunding, ctx.getCurrentUser());
    }

    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") Long id) throws Exception {
        sourcefundingService.deleteById(id);
    }
}

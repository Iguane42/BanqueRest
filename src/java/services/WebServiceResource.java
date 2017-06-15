/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dal.Compte;
import java.util.List;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import outils.Utilitaire;
import session.CompteFacade;

/**
 * REST Web Service
 *
 * @author Epulapp
 */
@Path("webservice")
public class WebServiceResource {

    @EJB
    CompteFacade compteF;
    
    /**
     * Creates a new instance of WebServiceResource
     */
    public WebServiceResource() {
    }
    
    @GET
    @Path("autoriserPaiement/{id}/{montant}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response autoriserPaiement(@PathParam("id") int id, @PathParam("montant") int montant) throws Exception{
        Response response = null;
        try {
            Boolean paiementAutorise = compteF.paiement(id, montant);

        JsonObject GEPaiement = Json.createObjectBuilder().add("Autorisation", paiementAutorise).build();
        response = Response.status(Response.Status.OK).entity(GEPaiement).build();
        } catch (Exception e) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utilitaire.getExceptionCause(e)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }

}

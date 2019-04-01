package edu.asupoly.ser422.lab3;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.asupoly.ser422.lab3.exceptions.*;

@Path("/pbook")
@Produces({MediaType.APPLICATION_JSON})
public class PhoneBookResource {
	
	private static PhoneBook pbook = PhoneBook.getInstance();
	
	@Context
	private UriInfo _uriInfo;
	
	/**
     * @apiDefine BadRequestError
     * @apiError (Error 4xx) {400} BadRequest Bad Request Encountered
     */
	/**
     * @apiDefine NotFoundError
     * @apiError (Error 4xx) {404} NotFound Resource Not Found
     */
	
	/**
	 * @api {put} /pbook Move Phone Entry to new Phonebook
     * @apiName moveEntry
     * @apiGroup Phonebook
	 *
	 * @apiUse BadRequestError
     * @apiUse NotFoundError
	 *
	 * @apiSuccessExample {json} Success-Response:
	 * 	HTTP/1.1 204 NO_CONTENT
	 */
	@PUT
	@Consumes("application/json")
	public Response moveEntry(PhoneEntry pe) {
		
		try {
			pbook.moveEntry(pe);
			return Response.status(Response.Status.NO_CONTENT).build();
		} catch (BadRequestException bre) {
			return Response.status(Response.Status.BAD_REQUEST).entity(
				"{ \"message\" : \"" + bre.toString() + "\"}").build();
		} catch (NotFoundException nfe) {
			return Response.status(Response.Status.NOT_FOUND).entity(
				"{ \"message\" : \"" + nfe.toString() + "\"}").build();
		}
	}
	/*
		_uriInfo.getAbsolutePath().toString() == http://localhost:8080/lab3/rest/pbook
		
		_uriInfo.getBaseUri().toString() == http://localhost:8080/lab3/rest
		
		_uriInfo.getPath().toString() == pbook
	*/
}
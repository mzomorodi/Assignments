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
     * @apiDefine ConflictError
     * @apiError (Error 4xx) {409} Conflict Conflict Error
     */
	/**
     * @apiDefine InternalServerError
     * @apiError (Error 5xx) {500} InternalServer Undefined Error
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
	 * @apiParam {String} phone 10-digit phone number
	 * @apiParam {String} bookid New phonebook id ("" to unlist)
	 *
	 * @apiParamExample {json} Relocate:
	 *	{
	 *		"phone" : "9876543210",
	 *		"bookid" : "3"
	 *	}
	 *
	 * @apiParamExample {json} Unlist:
	 *	{
	 *		"phone" : "9876543210",
	 *		"bookid" : ""
	 *	}
	 *
	 * @apiUse BadRequestError
	 * @apiUse InternalServerError
	 * @apiUse NotFoundError
	 *
	 * @apiSuccessExample {header} Success-Response:
	 * 	HTTP/1.1 204 NO_CONTENT
	 *	{
	 *		"Location" : "http://localhost:8080/lab3/rest/pentry/9876543210"
	 *	}
	 */
	@PUT
	@Consumes("application/json")
	public Response moveEntry(PhoneEntry pe) {
		
		try {
			pbook.moveEntry(pe);
			if (pe.getBookID().isEmpty()) {
				return Response.status(Response.Status.NO_CONTENT).build();
			} else {
				return Response.status(Response.Status.NO_CONTENT).
				header("Location", String.format("%s/pentry/%s",
				_uriInfo.getBaseUri().toString(), pe.getPhone())).build();
			}
		} catch (BadRequestException bre) {
			return Response.status(Response.Status.BAD_REQUEST).entity(
				"{ \"message\" : \"" + bre.toString() + "\"}").build();
		} catch (NotFoundException nfe) {
			return Response.status(Response.Status.NOT_FOUND).entity(
				"{ \"message\" : \"" + nfe.toString() + "\"}").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
				"{ \"message\" : \"" + e.toString() + "\"}").build();
		}
	}
	
	@GET
	@Path("/{bid}")
	public Response getPhoneBook(@PathParam("bid") String bid,
		@QueryParam("lname") String lname, @QueryParam("area") String area) {
		
		List<PhoneEntry> entries;
		try {
			entries = pbook.filter(bid, area, lname);
		} catch (BadRequestException bre) {
			return Response.status(Response.Status.BAD_REQUEST).entity(
				"{ \"message\" : \"" + bre.toString() + "\"}").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
				"{ \"message\" : \"" + e.toString() + "\"}").build();
		}
		
	}
	/*
		_uriInfo.getAbsolutePath().toString() == http://localhost:8080/lab3/rest/pbook
		
		_uriInfo.getBaseUri().toString() == http://localhost:8080/lab3/rest
		
		_uriInfo.getPath().toString() == pbook
	*/
}
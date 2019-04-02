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
	 * @api {put} Unlist Entry
     * @apiName unlistEntry
     * @apiGroup Phonebook
	 *
	 * @apiParam {String} phone 10-digit phone number of entry
	 *
	 * @apiParamExample {json} Unlist Entry:
	 *	{
	 *		"phone" : "9876543210"
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
	public Response unlistEntry(PhoneEntry pe) {
		//TODO: refactor unlist
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
	
	/**
	 * @api {put} /:bid Move Entry to specified Phonebook
     * @apiName moveEntry
     * @apiGroup Phonebook
	 *
	 * @apiParam {String} bid ID of destination Phonebook
	 * @apiParam {String} phone 10-digit phone number of Entry
	 *
	 * @apiParamExample {json} Move Entry:
	 * http://localhost:8080/lab3/rest/pbook/3
	 *	{
	 *		"phone" : "9876543210"
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
	@Path("/{bid}")
	@Consumes("application/json")
	public Response moveEntry(@PathParam("bid") String bid, PhoneEntry pe) {
		//TODO: refactor move (unlist if {bid} is empty/null)
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
	
	/**
	 * @api {get} /pbook:bid Retrieve specific Phonebook
     * @apiName getPhoneBook
     * @apiGroup PhoneBook
	 *
	 * @apiParam {String} bid Phonebook ID
	 * @apiParam {String} [lname] Optional lastname query filter
	 * @apiParam {String} [area] Optional 3-digit areacode query filter
	 *
	 * @apiParamExample {url} Retrieve PhoneEntry:
	 *	http://localhost:8080/lab3/rest/pbook/3?lname=Doe&area=586
	 *
	 * @apiUse BadRequestError
	 * @apiUse InternalServerError
	 * @apiUse NotFoundError
	 *
	 * @apiSuccessExample {json} Success-Response:
	 * 	HTTP/1.1 200 OK
	 *	{
	 *		{
	 *			"phone" : "9876543210",
	 *			"firstName" : "John",
	 *			"lastName" : "Doe",
	 *			"bookid" : "345"
	 *		},
	 *		{
	 *			"phone" : "8765432109",
	 *			"firstName" : "Jane",
	 *			"lastName" : "Johnson",
	 *			"bookid" : "345"
	 *		},
	 *	}
	 */
	@GET
	@Path("/{bid}")
	public Response getPhoneBook(@PathParam("bid") String bid,
		@QueryParam("lname") String lname, @QueryParam("area") String area) {
		
		List<PhoneEntry> entries;
		try {
			entries = pbook.filter(bid, area, lname);
			String eList = new ObjectMapper().writeValueAsString(entries);
			return Response.status.Response.Status.OK).entity(eList).build();
		} catch (BadRequestException bre) {
			return Response.status(Response.Status.BAD_REQUEST).entity(
				"{ \"message\" : \"" + bre.toString() + "\"}").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
				"{ \"message\" : \"" + e.toString() + "\"}").build();
		}
	}
	
	
	@DELETE
	@Path("/{bookid}")
	public Response removePhoneBook(@PathParam("bookid") String num) {
		try {
			return Response.status(Response.Status.NO_CONTENT).build();
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
	
	/*
		_uriInfo.getAbsolutePath().toString() == http://localhost:8080/lab3/rest/pbook
		
		_uriInfo.getBaseUri().toString() == http://localhost:8080/lab3/rest
		
		_uriInfo.getPath().toString() == pbook
	*/
}
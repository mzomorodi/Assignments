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

@Path("/pentry")
@Produces({MediaType.APPLICATION_JSON})

public class PhoneEntryResource {
	
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
	 * @api {get} /pentry:pnumber Retrieve specific PhoneEntry
     * @apiName getEntry
     * @apiGroup PhoneEntry
	 *
	 * @apiParam {String} pnumber 10-digit phone number
	 *
	 * @apiParamExample {url} Retrieve PhoneEntry:
	 *	http://localhost:8080/lab3/rest/pentry/9876543210
	 *
	 * @apiUse BadRequestError
	 * @apiUse InternalServerError
	 * @apiUse NotFoundError
	 *
	 * @apiSuccessExample {json} Success-Response:
	 * 	HTTP/1.1 200 OK
	 *	{
	 *		"phone" : "9876543210",
	 *		"firstName" : "John",
	 *		"lastName" : "Doe",
	 *		"bookid" : "345"
	 *	}
	 */	
	@GET
	@Path("/{pnumber}")
	public Response getEntry(@PathParam("pnumber") String num) {
		PhoneEntry pe;
		try {
			pe = pbook.getEntry(num);
			if (pe == null) {
				return Response.status(Response.Status.NOT_FOUND).entity(
				"{ \"message\" : \"" + nfe.toString() + "\"}").build();
			} else {
				String pStr = new ObjectMapper().writeValueAsString(pe);
				return Response.status(Response.Status.OK).entity(pStr).build();
			}
		} catch (BadRequestException bre) {
			return Response.status(Response.Status.BAD_REQUEST).entity(
				"{ \"message\" : \"" + bre.toString() + "\"}").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
				"{ \"message\" : \"" + e.toString() + "\"}").build();
		}
	}
	
	/**
	 * @api {post} /pentry Create new PhoneEntry
     * @apiName createEntry
     * @apiGroup PhoneEntry
	 *
	 * @apiParam {String} pnumber 10-digit phone number
	 * @apiParam {String} firstName First name of contact
	 * @apiParam {String} lastName Last name of contact
	 *
	 * @apiParamExample {json} Create:
	 *	{
	 *		"phone" : "9876543210",
	 *		"firstName" : "John",
	 *		"lastName" : "Doe"
	 *	}
	 *
	 * @apiUse BadRequestError
	 * @apiUse ConflictError
	 * @apiUse InternalServerError
     * @apiUse NotFoundError
	 *
	 * @apiSuccessExample {header} Success-Response:
	 * 	HTTP/1.1 201 CREATED
	 *	{
	 *		"Location" : "http://localhost:8080/lab3/rest/pentry/9876543210"
	 *	}
	 */
	@POST
	@Consumes("application/json")
	public Response createEntry(PhoneEntry pe) {
		try {
			pbook.createEntry(pe);
			return Response.status(Response.Status.CREATED).
				header("Location", String.format("%s/%s",
				_uriInfo.getAbsolutePath().toString(), pe.getPhone())).build();
		} catch (BadRequestException bre) {
			return Response.status(Response.Status.BAD_REQUEST).entity(
				"{ \"message\" : \"" + bre.toString() + "\"}").build();
		} catch (ConflictException ce) {
			return Response.status(Response.Status.CONFLICT).entity(
				"{ \"message\" : \"" + ce.toString() + "\"}").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
				"{ \"message\" : \"" + e.toString() + "\"}").build();
		}
	}
	
	/**
	 * @api {put} /pentry:pnumber Update PhoneEntry with given number
     * @apiName updateEntry
     * @apiGroup PhoneEntry
	 *
	 * @apiParam {String} pnumber New 10-digit phone number
	 * @apiParam {String} firstName First name of contact
	 * @apiParam {String} lastName Last name of contact
	 * @apiParam {String} bookid Phonebook ID of Entry
	 *
	 * @apiParamExample {json} Update:
	 *	http://localhost:8080/lab3/rest/pentry/9876543211
	 *	{
	 *		"phone" : "9876543211",
	 *		"firstName" : "John",
	 *		"lastName" : "Doe",
	 *		"bookid" : "3"
	 *	}
	 *
	 * @apiUse BadRequestError
	 * @apiUse ConflictError
	 * @apiUse InternalServerError
     * @apiUse NotFoundError
	 *
	 * @apiSuccessExample {header} Success-Response:
	 * 	HTTP/1.1 204 NO_CONTENT
	 *	{
	 *		"Location" : "http://localhost:8080/lab3/rest/pentry/9876543211"
	 *	}
	 */
	@PUT
	@Path("/{pnumber}")
	@Consumes("application/json")
	public Response updateEntry(@PathParam("pnumber") String num, PhoneEntry pe) {
		try {
			pbook.updateEntry(pe);
			return Response.status(Response.Status.NO_CONTENT).
				header("Location", String.format("%s/%s",
				_uriInfo.getAbsolutePath().toString(), pe.getPhone())).build();
		} catch (NotFoundException nfe) {
			return Response.status(Response.Status.NOT_FOUND).entity(
				"{ \"message\" : \"" + nfe.toString() + "\"}").build();
		} catch (BadRequestException bre) {
			return Response.status(Response.Status.BAD_REQUEST).entity(
				"{ \"message\" : \"" + bre.toString() + "\"}").build();
		} catch (ConflictException ce) {
			return Response.status(Response.Status.CONFLICT).entity(
				"{ \"message\" : \"" + ce.toString() + "\"}").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
				"{ \"message\" : \"" + e.toString() + "\"}").build();
		}
	}
	
	/**
	 * @api {delete} /pentry:pnumber Delete specific PhoneEntry
     * @apiName deleteEntry
     * @apiGroup PhoneEntry
	 *
	 * @apiParam {String} pnumber 10-digit phone number of Entry
	 *
	 * @apiParamExample {url} Delete PhoneEntry:
	 *	http://localhost:8080/lab3/rest/pentry/9876543210
	 *
	 * @apiUse BadRequestError
	 * @apiUse InternalServerError
     * @apiUse NotFoundError
	 *
	 * @apiSuccessExample Success-Response:
	 * 	HTTP/1.1 204 NO_CONTENT
	 */
	@DELETE
	@Path("/{pnumber}")
	public Response removeEntry(@PathParam("pnumber") String num) {
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
	
	@POST
	@Consumes("application/json")
	public Response createEntry(PhoneEntry pe) {
		System.out.println("Number: " + pe.getPhone());
		System.out.println("First Name: " + pe.getFirstName());
		System.out.println("Last Name: " + pe.getLastName());
		System.out.println("PID: " + pe.getBookID());
		
		return Response.status(201).entity("{ \"PID\" : \"" +
			pe.getBookID() + "\"}").build();
	}
}
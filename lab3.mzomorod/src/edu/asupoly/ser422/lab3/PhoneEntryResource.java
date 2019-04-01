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
	
	@GET
	public Response getEntry() {
		PhoneEntry entry = new PhoneEntry("3135589334",
			"John", "Doe");
		
		try {
			String str = new ObjectMapper().writeValueAsString(entry);
			return Response.status(200).entity(str).build();
		} catch (Exception e) {
			System.err.println(e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{ \" Error: Unable to create new Entry. \"}").build();
		}
	}
	
	
	@GET
	@Path("/{pnumber}")
	public Response getEntry(@PathParam("pnumber") String num) {
		PhoneEntry entry = pbook.getEntry(num);
		if (entry != null) {
			try {
				String str = new ObjectMapper().writeValueAsString(entry);
				return Response.status(Response.Status.CREATED).entity(str).build();
			} catch (Exception e) {
				System.err.println(e);
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{ \" Error: Unable to create new Entry. \"}").build();
			}
		} else {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{ \" Error: Unable to create new Entry. \"}").build();
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
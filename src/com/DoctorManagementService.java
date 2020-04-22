package com;

import java.sql.Date;
import java.text.SimpleDateFormat;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.DoctorManagement;


@Path("/Doctors")

public class DoctorManagementService {

	DoctorManagement App1 = new DoctorManagement();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String ReadDoctor() {

		return App1.ReadDoctor();

	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String addDoctor(@FormParam("doctor_no") String docNo,@FormParam("specialization") String special, @FormParam("doctor_name") String docName,
			@FormParam("user_id") String userId, @FormParam("gender") String gender, @FormParam("email") String email,
			@FormParam("contact_number") String Number) {
		String output = App1.addDoctor(docNo, special, docName, userId, gender, email, Number);
		return output;
	}
	@PUT
	@Path("/App1/{apmnt_id}/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String UpdateDoctor(

			@FormParam("specialization") String special, @FormParam("doctor_name") String docName,
			@FormParam("user_id") String userId, @FormParam("gender") String gender, @FormParam("email") String email,
			@FormParam("contact_number") String Number,
			@PathParam("doctor_no") String docNo) {
		String output = App1.UpdateDoctor(docNo, special, docName, userId, gender, email, Number);
		System.out.println(docNo);
		return output;
	}

	@DELETE
	@Path("/delete/App1/{doctor_no}/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String DeleteDoctor(@PathParam("doctor_no") String docNo) {

		// Read the value from the element <AppID>
		String output = App1.DeleteDoctor(docNo);
		return output;

	}

}

package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import config.DBConnector;
public class DoctorManagement {

	public String addDoctor(String docNo,String special, String docName, String userId, String gender, String email, String Number) {

		try (Connection con = DBConnector.getConnection()) {

			String addAppQuery = " insert into doctortable (`doctor_no`,`specialization`,`doctor_name`,`user_id`,`gender`,`email`,`contact_number`)"
					+ "values(?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement pstmnt = con.prepareStatement(addAppQuery);

			pstmnt.setString(1, docNo);
			pstmnt.setString(2, special);
			pstmnt.setString(3, docName);
			pstmnt.setString(4, userId);
			pstmnt.setString(5, gender);
			pstmnt.setString(6, email);
			pstmnt.setString(7, Number);

			pstmnt.execute();
			return "Doctor added successfully";

		} catch (SQLException e) {
			return "Error occured during adding an Doctor\n" + e.getMessage();
		}

	}

	public String ReadDoctor() {

		try (Connection con = DBConnector.getConnection()) {

			String readQuery = "select * from doctortable";

			PreparedStatement pstmt = con.prepareStatement(readQuery);

			String output = "<table border=\"1\"><tr><th>Doctor ID</th>" + "<th>Specialization</th> "
					+ "<th>Doctor Name</th>" + "<th>User ID</th>" + "<th>Gender</th>"
					+ "<th>Email</th>" + "<th>Phone number</th>" + "<th>Update</th><th>Remove</th></tr>";

			ResultSet rs = pstmt.executeQuery(readQuery);
			while (rs.next()) {
				int DocID = rs.getInt("doctor_no");
				String special = rs.getString("specialization");
				String doc_name = rs.getString("doctor_name");
				String user_id = rs.getString("user_id");
				String gender = rs.getString("gender");
				String email = rs.getString("email");
				String number = rs.getString("contact_number");

				output += "<tr><td>" + DocID + "</td>";
				output += "<td>" + special + "</td>";
				output += "<td>" + doc_name + "</td>";
				output += "<td>" + user_id + "</td>";
				output += "<td>" + gender + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + number + "</td>";

				output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"items.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\">"
						+ "<input name=\"itemID\" type=\"hidden\" value=\"" + DocID + "\">" + "</form></td></tr>";

			}

			output += "</table>";
			return output;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return "Error occured during retrieving data";
		}
	}

	public String UpdateDoctor(String docNo,String special, String docName, String userId, String gender, String email, String Number) {

		try (Connection con = DBConnector.getConnection()) {

			String updateAppQuery = "UPDATE doctortable SET specialization=?,doctor_name=?,user_id=?,gender?,email?,contact_number? WHERE doctor_no=?";
			PreparedStatement pstmnt = con.prepareStatement(updateAppQuery);
			pstmnt.setInt(1, 0);
			pstmnt.setString(2, special);
			pstmnt.setString(3, docName);
			pstmnt.setString(4, userId);
			pstmnt.setString(5, gender);
			pstmnt.setString(6, email);
			pstmnt.setString(7, Number);

		

			System.out.println(pstmnt.toString());
			pstmnt.execute();
			return "Doctor Updated successfully";

		} catch (SQLException e) {
			return "Error occured during Updating an Doctor\n" + e.getMessage();
		}

	}

	public String DeleteDoctor(String docNo) {

		try (Connection con = DBConnector.getConnection()) {

			// create a prepared statement
			String Deletequery = "delete from doctortable where doctor_no=?";

			PreparedStatement pstmnt = con.prepareStatement(Deletequery);
			pstmnt.setString(1, docNo);
			pstmnt.execute();
			return "Doctor Deleted successfully";

		} catch (SQLException e) {

			return "Error occurrd during Deleting\n" + e.getMessage();
		}

	}

}

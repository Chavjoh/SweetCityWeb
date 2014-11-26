package ch.hesso.master.sweetcity.database.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import ch.hesso.master.sweetcity.database.entity.Report;
import ch.hesso.master.sweetcity.database.entity.Account;

import com.googlecode.objectify.ObjectifyService;

public class ReportDao {
	
	static {
		ObjectifyService.register(Report.class);
	}

	public static void addReport(Report report) {
		ofy().save().entity(report).now();
	}

	public static void saveReport(Report report) {
		// To edit, we overwrite
		addReport(report);
	}
	
	public static Report getReport(Long id) {
		return ofy().load().type(Report.class).id(id).now();
	}

	public static List<Report> listReport() {
		return ofy().load().type(Report.class).list();
	}

	public static List<Report> listReportUser(Account user) {
		return ofy().load().type(Report.class).filter("user", user.getEmail()).list();
	}

	public static void removeReport(Report report) {
		ofy().delete().entity(report).now();
	}
	
}

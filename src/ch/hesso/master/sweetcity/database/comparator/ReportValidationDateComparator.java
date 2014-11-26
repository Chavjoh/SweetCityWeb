package ch.hesso.master.sweetcity.database.comparator;

import java.util.Comparator;
import java.util.Date;

import ch.hesso.master.sweetcity.database.entity.Report;

public class ReportValidationDateComparator implements Comparator<Report> {
	
	public static final ReportValidationDateComparator INSTANCE = new ReportValidationDateComparator();
	
	@Override
	public int compare(Report report1, Report report2) {
		Date date1 = report1.getValidationDate();
		Date date2 = report2.getValidationDate();

		return date1.compareTo(date2);
	}
	
}

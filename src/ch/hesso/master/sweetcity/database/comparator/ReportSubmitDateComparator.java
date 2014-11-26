package ch.hesso.master.sweetcity.database.comparator;

import java.util.Comparator;
import java.util.Date;

import ch.hesso.master.sweetcity.database.entity.Report;

public class ReportSubmitDateComparator implements Comparator<Report> {
	
	public static final ReportSubmitDateComparator INSTANCE = new ReportSubmitDateComparator();
	
	@Override
	public int compare(Report report1, Report report2) {
	    Date date1 = report1.getSubmitDate();
	    Date date2 = report2.getSubmitDate();

	    return date1.compareTo(date2);
	}
}

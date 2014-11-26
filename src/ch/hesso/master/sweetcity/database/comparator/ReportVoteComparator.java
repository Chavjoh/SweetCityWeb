package ch.hesso.master.sweetcity.database.comparator;

import java.util.Comparator;

import ch.hesso.master.sweetcity.database.entity.Report;

public class ReportVoteComparator implements Comparator<Report> {
	
	public static final ReportVoteComparator INSTANCE = new ReportVoteComparator();
	
	@Override
	public int compare(Report report1, Report report2) {
		Integer vote1 = report1.getVote();
		Integer vote2 = report2.getVote();

		return vote1.compareTo(vote2);
	}

}

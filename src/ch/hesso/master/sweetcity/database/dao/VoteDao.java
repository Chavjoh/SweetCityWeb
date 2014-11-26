package ch.hesso.master.sweetcity.database.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import ch.hesso.master.sweetcity.database.entity.Account;
import ch.hesso.master.sweetcity.database.entity.Report;
import ch.hesso.master.sweetcity.database.entity.Vote;
import ch.hesso.master.sweetcity.utils.DateUtils;

import com.googlecode.objectify.ObjectifyService;

public class VoteDao {
	
	static {
		ObjectifyService.register(Vote.class);
	}

	public static void addVote(Vote vote) {
		ofy().save().entity(vote).now();
	}

	public static void saveVote(Vote vote) {
		// To edit, we overwrite
		addVote(vote);
	}

	public static List<Vote> listVote() {
		return ofy().load().type(Vote.class).list();
	}

	public static List<Vote> listVoteUser(Account user) {
		return ofy().load().type(Vote.class).filter("user", user.getEmail()).list();
	}
	
	public static List<Vote> listVoteUserReport(Account user, Report report) {
		List<Vote> listVote = listVoteUser(user);
		Iterator<Vote> iterator = listVote.iterator();

		while (iterator.hasNext()) {
			Vote vote = iterator.next();

			if (!vote.getReport().equals(report)) {
				iterator.remove();
			}
		}
		
		return listVote;
	}
	
	public static void removeVote(Vote vote) {
		ofy().delete().entity(vote).now();
	}

	public static boolean hasVoted(Account user, Report report) {
		return getVoteUserReportToday(user, report) != null;
	}
	
	public static Vote getVoteUserReportToday(Account user, Report report) {
		// Datastore limitation : 1 filter per query
		// we need to check each vote record
		
		List<Vote> listVote = listVoteUserReport(user, report);
		Date today = DateUtils.now();
		
		for (Vote vote:listVote) {
			if (DateUtils.sameDay(today, vote.getDate())) {
				return vote;
			}
		}
		
		return null;
	}
	
}

package ch.hesso.master.sweetcity.service;

import java.util.Collections;
import java.util.List;

import ch.hesso.master.sweetcity.Project;
import ch.hesso.master.sweetcity.database.comparator.ReportSubmitDateComparator;
import ch.hesso.master.sweetcity.database.comparator.ReportValidationDateComparator;
import ch.hesso.master.sweetcity.database.comparator.ReportVoteComparator;
import ch.hesso.master.sweetcity.database.dao.ReportDao;
import ch.hesso.master.sweetcity.database.dao.TagDao;
import ch.hesso.master.sweetcity.database.dao.VoteDao;
import ch.hesso.master.sweetcity.database.entity.Account;
import ch.hesso.master.sweetcity.database.entity.Report;
import ch.hesso.master.sweetcity.database.entity.Tag;
import ch.hesso.master.sweetcity.database.entity.Vote;
import ch.hesso.master.sweetcity.utils.DateUtils;
import ch.hesso.master.sweetcity.utils.ServiceUtils;
import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.api.server.spi.response.NotFoundException;

@Api(
        name = "reportService",
        version = "v1",
        description = "API to manage user reports on SweetCity.", 
		scopes = {Project.EMAIL_SCOPE},
		clientIds = {
				Project.WEB_CLIENT_ID,  
				Project.ANDROID_CLIENT_ID, 
				com.google.api.server.spi.Constant.API_EXPLORER_CLIENT_ID
		},
		audiences = {Project.ANDROID_AUDIENCE}
    )
public class ReportService {
	
	@ApiMethod(path = "listReportByVote", httpMethod = HttpMethod.GET)
	public List<Report> getListReportByVote(User user) throws UnauthorizedException {
		ServiceUtils.getCurrentAccount(user);
		List<Report> listReport = ReportDao.listReport();
		Collections.sort(listReport, ReportVoteComparator.INSTANCE);
		return listReport;
	}
	
	@ApiMethod(path = "listReportBySubmitDate", httpMethod = HttpMethod.GET)
	public List<Report> getListReportBySubmitDate(User user) throws UnauthorizedException {
		ServiceUtils.getCurrentAccount(user);
		List<Report> listReport = ReportDao.listReport();
		Collections.sort(listReport, ReportSubmitDateComparator.INSTANCE);
		return listReport;
	}
	
	@ApiMethod(path = "listReportByValidationDate", httpMethod = HttpMethod.GET)
	public List<Report> getListReportByValidationDate(User user) throws UnauthorizedException {
		ServiceUtils.getCurrentAccount(user);
		List<Report> listReport = ReportDao.listReport();
		Collections.sort(listReport, ReportValidationDateComparator.INSTANCE);
		return listReport;
	}
	
	@ApiMethod(path = "listReportUserByVote", httpMethod = HttpMethod.GET)
	public List<Report> getListReportUserByVote(User user) throws UnauthorizedException {
		Account currentAccount = ServiceUtils.getCurrentAccount(user);
		List<Report> listReport = ReportDao.listReportUser(currentAccount);
		Collections.sort(listReport, ReportVoteComparator.INSTANCE);
		return listReport;
	}
	
	@ApiMethod(path = "listReportUserBySubmitDate", httpMethod = HttpMethod.GET)
	public List<Report> getListReportUserBySubmitDate(User user) throws UnauthorizedException {
		Account currentAccount = ServiceUtils.getCurrentAccount(user);
		List<Report> listReport = ReportDao.listReportUser(currentAccount);
		Collections.sort(listReport, ReportSubmitDateComparator.INSTANCE);
		return listReport;
	}
	
	@ApiMethod(path = "listReportUserByValidationDate", httpMethod = HttpMethod.GET)
	public List<Report> getListReportUserByValidationDate(User user) throws UnauthorizedException {
		Account currentAccount = ServiceUtils.getCurrentAccount(user);
		List<Report> listReport = ReportDao.listReportUser(currentAccount);
		Collections.sort(listReport, ReportValidationDateComparator.INSTANCE);
		return listReport;
	}
	
	@ApiMethod(path = "addReport", httpMethod = HttpMethod.POST)
	public void addReport(Report report, User user) throws UnauthorizedException {
		Account currentAccount = ServiceUtils.getCurrentAccount(user);
		report.setUser(currentAccount);
		report.resetDefault();
		ReportDao.addReport(report);
	}
	
	@ApiMethod(path = "removeReport/{reportId}", httpMethod = HttpMethod.GET)
	public void removeReport(@Named("reportId") Long reportId, User user) throws UnauthorizedException, NotFoundException {
		Account currentAccount = ServiceUtils.getCurrentAccount(user);
		Report report = ReportDao.getReport(reportId);
		
		if (report == null) {
			throw new NotFoundException("Report not found");
		} else if (!currentAccount.equals(report.getUser())) {
			throw new UnauthorizedException("You are not allowed to remove a other user reports than yours");
		}
		
		ReportDao.removeReport(report);
	}
	
	@ApiMethod(path = "addReportWith/{image}/{latitude}/{longitude}", httpMethod = HttpMethod.POST)
	public void addReportWith(@Named("image") String image, @Named("latitude") Float latitude, @Named("longitude") Float longitude, User user) throws UnauthorizedException {
		Account currentAccount = ServiceUtils.getCurrentAccount(user);
		Report report = new Report(currentAccount, image, latitude, longitude);
		ReportDao.addReport(report);
	}
	
	@ApiMethod(path = "addTag/{reportId}/{tagId}", httpMethod = HttpMethod.GET)
	public void addTag(@Named("reportId") Long reportId, @Named("tagId") Long tagId, User user) throws UnauthorizedException, NotFoundException {
		Account currentAccount = ServiceUtils.getCurrentAccount(user);
		Report report = ReportDao.getReport(reportId);
		Tag tag = TagDao.getTag(tagId);
		
		if (report == null) {
			throw new NotFoundException("Report not found");
		} else if (tag == null) {
			throw new NotFoundException("Tag not found");
		} else if (!currentAccount.equals(report.getUser())) {
			throw new UnauthorizedException("You are not allowed to add tags to other user reports than yours");
		}
		
		report.addTag(tag);
		ReportDao.saveReport(report);
	}
	
	@ApiMethod(path = "removeTag/{reportId}/{tagId}", httpMethod = HttpMethod.GET)
	public void removeTag(@Named("reportId") Long reportId, @Named("tagId") Long tagId, User user) throws UnauthorizedException, NotFoundException {
		Account currentAccount = ServiceUtils.getCurrentAccount(user);
		Report report = ReportDao.getReport(reportId);
		Tag tag = TagDao.getTag(tagId);
		
		if (report == null) {
			throw new NotFoundException("Report not found");
		} else if (tag == null) {
			throw new NotFoundException("Tag not found");
		} else if (!currentAccount.equals(report.getUser())) {
			throw new UnauthorizedException("You are not allowed to add tags to other user reports than yours");
		} 
		
		report.removeTag(tag);
		ReportDao.saveReport(report);
	}
	
	@ApiMethod(path = "addVote/{reportId}", httpMethod = HttpMethod.GET)
	public void addVote(@Named("reportId") Long reportId, User user) throws UnauthorizedException, NotFoundException {
		Account currentAccount = ServiceUtils.getCurrentAccount(user);
		Report report = ReportDao.getReport(reportId);
		
		if (VoteDao.hasVoted(currentAccount, report)) {
			throw new UnauthorizedException("You are not allowed to vote more than one time the same day for a report");
		}
		
		Vote vote = new Vote(DateUtils.now(), currentAccount, report);
		VoteDao.addVote(vote);
	}

}

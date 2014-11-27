package ch.hesso.master.sweetcity.service;

import java.util.List;

import ch.hesso.master.sweetcity.Project;
import ch.hesso.master.sweetcity.database.dao.ReportDao;
import ch.hesso.master.sweetcity.database.dao.VoteDao;
import ch.hesso.master.sweetcity.database.entity.Account;
import ch.hesso.master.sweetcity.database.entity.Report;
import ch.hesso.master.sweetcity.database.entity.Vote;
import ch.hesso.master.sweetcity.utils.ServiceUtils;

import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.NotFoundException;
import com.google.api.server.spi.response.UnauthorizedException;

@Api(
        name = "voteService",
        version = "v1",
        description = "API to manage user votes on SweetCity.", 
		scopes = {Project.EMAIL_SCOPE},
		clientIds = {
				Project.WEB_CLIENT_ID,  
				Project.ANDROID_CLIENT_ID_DEV1,
				Project.ANDROID_CLIENT_ID_DEV2,
				com.google.api.server.spi.Constant.API_EXPLORER_CLIENT_ID
		},
		audiences = {Project.ANDROID_AUDIENCE}
    )
public class VoteService {

	@ApiMethod(path = "voteToday/{reportId}", httpMethod = HttpMethod.GET)
	public Vote getVoteToday(@Named("reportId") Long reportId, User user) throws UnauthorizedException, NotFoundException {
		Account currentUser = ServiceUtils.getCurrentAccount(user);
		Report report = ReportDao.getReport(reportId);
		
		if (report == null) {
			throw new NotFoundException("Report not found");
		}
		
		return VoteDao.getVoteUserReportToday(currentUser, report);
	}

	@ApiMethod(path = "voteAll/{reportId}", httpMethod = HttpMethod.GET)
	public List<Vote> getVoteAll(@Named("reportId") Long reportId, User user) throws UnauthorizedException, NotFoundException {
		Account currentUser = ServiceUtils.getCurrentAccount(user);
		Report report = ReportDao.getReport(reportId);
		
		if (report == null) {
			throw new NotFoundException("Report not found");
		}
		
		return VoteDao.listVoteUserReport(currentUser, report);
	}
	
}

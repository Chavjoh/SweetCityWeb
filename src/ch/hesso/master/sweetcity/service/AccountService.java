package ch.hesso.master.sweetcity.service;

import ch.hesso.master.sweetcity.Project;
import ch.hesso.master.sweetcity.database.dao.AccountDao;
import ch.hesso.master.sweetcity.database.entity.Account;
import ch.hesso.master.sweetcity.utils.ServiceUtils;

import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.UnauthorizedException;

@Api(
        name = "accountService",
        version = "v1",
        description = "API to manage user on SweetCity.", 
		scopes = {Project.EMAIL_SCOPE},
		clientIds = {
				Project.WEB_CLIENT_ID,
				Project.ANDROID_CLIENT_ID_DEV1,
				Project.ANDROID_CLIENT_ID_DEV2,
				com.google.api.server.spi.Constant.API_EXPLORER_CLIENT_ID
		},
		audiences = {Project.ANDROID_AUDIENCE}
    )
public class AccountService {
	
	@ApiMethod(path = "get", httpMethod = HttpMethod.GET)
	public Account getCurrentAccount(User user) throws UnauthorizedException {
		return ServiceUtils.getCurrentAccount(user);
	}
	
	@ApiMethod(path = "register/{login}", httpMethod = HttpMethod.GET)
	public void register(@Named("login") String login, User user) throws UnauthorizedException {
		if (user == null) {
			throw new UnauthorizedException("User is not valid (please use OAuth).");
		}
		
		Account currentAccount = AccountDao.getAccount(user.getEmail());
		
		if (currentAccount != null) {
			throw new UnauthorizedException("User is already registered.");
		}
		
		currentAccount = new Account(login, user.getEmail());
		AccountDao.addAccount(currentAccount);
	}


}
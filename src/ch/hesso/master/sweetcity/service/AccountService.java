package ch.hesso.master.sweetcity.service;

import java.util.Collections;
import java.util.List;

import ch.hesso.master.sweetcity.Project;
import ch.hesso.master.sweetcity.database.comparator.AccountRankingComparator;
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
				Project.ANDROID_CLIENT_ID_DEV,
				Project.ANDROID_CLIENT_ID_PROD,
				com.google.api.server.spi.Constant.API_EXPLORER_CLIENT_ID
		},
		audiences = {Project.ANDROID_AUDIENCE}
    )
public class AccountService {
	
	@ApiMethod(path = "get", httpMethod = HttpMethod.GET)
	public Account getCurrentAccount(User user) {
		try {
			return ServiceUtils.getCurrentAccount(user);
		} catch (UnauthorizedException e) {
			return null;
		}
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

	@ApiMethod(path = "ranking", httpMethod = HttpMethod.GET)
	public List<Account> ranking(User user) throws UnauthorizedException {
		ServiceUtils.getCurrentAccount(user);
		List<Account> listAccount = AccountDao.listAccount();
		Collections.sort(listAccount, AccountRankingComparator.INSTANCE);
		return listAccount;
	}
}
package ch.hesso.master.sweetcity.utils;

import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.response.UnauthorizedException;

import ch.hesso.master.sweetcity.database.dao.AccountDao;
import ch.hesso.master.sweetcity.database.entity.Account;

public class ServiceUtils {

	public static Account getCurrentAccount(User user) throws UnauthorizedException {
		if (user == null) {
			throw new UnauthorizedException("User is not valid");
		}
		
		Account currentAccount = AccountDao.getAccount(user.getEmail());
		
		if (currentAccount == null) {
			throw new UnauthorizedException("User is not registered");
		}
		
		return currentAccount;
	}
	
}

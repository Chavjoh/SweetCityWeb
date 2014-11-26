package ch.hesso.master.sweetcity.database.dao;

import java.util.List;

import ch.hesso.master.sweetcity.database.entity.Account;

import com.googlecode.objectify.ObjectifyService;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class AccountDao {

	static {
		ObjectifyService.register(Account.class);
	}

	public static void addAccount(Account account) {
		ofy().save().entity(account).now();
	}

	public static void saveAccount(Account account) {
		// To edit, we overwrite
		addAccount(account);
	}

	public static List<Account> listAccount() {
		return ofy().load().type(Account.class).list();
	}

	public static Account getAccount(String email) {
		return ofy().load().type(Account.class).id(email).now();
	}

	public static void removeAccount(Account account) {
		ofy().delete().entity(account).now();
	}
}

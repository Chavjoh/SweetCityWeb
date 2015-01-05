package ch.hesso.master.sweetcity.utils;

import com.google.appengine.api.users.UserServiceFactory;

public class UserUtils {

	public static boolean isAdmin() {
		return UserServiceFactory.getUserService().isUserAdmin();
	}
}

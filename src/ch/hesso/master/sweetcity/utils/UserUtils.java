package ch.hesso.master.sweetcity.utils;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class UserUtils {

	public static boolean isAdmin() {
		UserService userService = UserServiceFactory.getUserService();
		return userService.isUserAdmin();
	}
}

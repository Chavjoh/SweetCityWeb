package ch.hesso.master.sweetcity.database.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import ch.hesso.master.sweetcity.database.entity.Reward;

import com.googlecode.objectify.ObjectifyService;

public class RewardDao {
	
	static {
		ObjectifyService.register(Reward.class);
	}

	public static void addReward(Reward reward) {
		ofy().save().entity(reward).now();
	}

	public static void saveReward(Reward reward) {
		// To edit, we overwrite
		addReward(reward);
	}

	public static Reward getReward(Long rewardId) {
		return ofy().load().type(Reward.class).id(rewardId).now();
	}

	public static List<Reward> listReward() {
		return ofy().load().type(Reward.class).list();
	}

	public static void removeReward(Reward reward) {
		ofy().delete().entity(reward).now();
	}
}

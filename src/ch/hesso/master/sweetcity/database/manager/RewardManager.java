package ch.hesso.master.sweetcity.database.manager;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dev.util.collect.HashMap;

import ch.hesso.master.sweetcity.Project;
import ch.hesso.master.sweetcity.database.dao.AccountDao;
import ch.hesso.master.sweetcity.database.dao.ReportDao;
import ch.hesso.master.sweetcity.database.dao.RewardDao;
import ch.hesso.master.sweetcity.database.entity.Account;
import ch.hesso.master.sweetcity.database.entity.Reward;

public class RewardManager {
	
	public static HashMap<String, Reward> rewardListToMap() {
		List<Reward> rewardList = RewardDao.listReward();
		HashMap<String, Reward> rewardMap = new HashMap<String, Reward>();
		
		for (Reward reward:rewardList) {
			rewardMap.put(reward.getId(), reward);
		}
		
		return rewardMap;
	}
	
	public static List<Reward> rewardMapToList(HashMap<String, Reward> map) {
		return new ArrayList<Reward>(map.values());
	}
	
	public static void addRewardIfNecessary(Reward reward, Account user) {
		if (reward == null)
			return;
		
		if (!user.getListReward().contains(reward)) {
			user.addReward(reward);
		}
	}

	public static void refreshType(EntityType type, Account user) {
		
		HashMap<String, Reward> rewardMap = rewardListToMap();

		switch (type) {
			case ACCOUNT:
				
				break;
				
			case REPORT:
				int reportCount = ReportDao.countReport(user);
				
				if (reportCount >= 1) 
					addRewardIfNecessary(rewardMap.get("report_1"), user);
				
				if (reportCount >= 30)
					addRewardIfNecessary(rewardMap.get("report_2"), user);
				
				if (reportCount >= 100)
					addRewardIfNecessary(rewardMap.get("report_3"), user);

				break;
				
			case REWARD:
				
				break;
				
			case TAG:
				
				break;
				
			case VOTE:
				
				break;
		}
		
		AccountDao.saveAccount(user);
	}
	
}

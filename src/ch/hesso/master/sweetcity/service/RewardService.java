package ch.hesso.master.sweetcity.service;

import java.util.Collections;
import java.util.List;

import ch.hesso.master.sweetcity.Project;
import ch.hesso.master.sweetcity.database.comparator.RewardIdComparator;
import ch.hesso.master.sweetcity.database.dao.RewardDao;
import ch.hesso.master.sweetcity.database.entity.Reward;
import ch.hesso.master.sweetcity.utils.ServiceUtils;

import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.NotFoundException;
import com.google.api.server.spi.response.UnauthorizedException;

@Api(
        name = "rewardService",
        version = "v1",
        description = "API to manage user rewards on SweetCity.", 
		scopes = {Project.EMAIL_SCOPE},
		clientIds = {
				Project.WEB_CLIENT_ID,  
				Project.ANDROID_CLIENT_ID_DEV,
				Project.ANDROID_CLIENT_ID_PROD,
				com.google.api.server.spi.Constant.API_EXPLORER_CLIENT_ID
		},
		audiences = {Project.ANDROID_AUDIENCE}
    )
public class RewardService {

	@ApiMethod(path = "list", httpMethod = HttpMethod.GET)
	public List<Reward> getRewardList(User user) throws UnauthorizedException {
		ServiceUtils.getCurrentAccount(user);
		List<Reward> listReward = RewardDao.listReward();
		Collections.sort(listReward, RewardIdComparator.INSTANCE);
		return listReward;
	}
	
	@ApiMethod(path = "add", httpMethod = HttpMethod.POST)
	private void add(Reward reward, User user) throws UnauthorizedException {
		ServiceUtils.getCurrentAccount(user);
		RewardDao.addReward(reward);
	}
	
	@ApiMethod(path = "remove/{rewardId}", httpMethod = HttpMethod.GET)
	private void remove(@Named("rewardId") Long rewardId, User user) throws UnauthorizedException, NotFoundException {
		ServiceUtils.getCurrentAccount(user);
		Reward reward = RewardDao.getReward(rewardId);
		
		if (reward == null) {
			throw new NotFoundException("Reward not found");
		}
		
		RewardDao.removeReward(reward);
	}
	
}

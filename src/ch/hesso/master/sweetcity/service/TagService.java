package ch.hesso.master.sweetcity.service;

import java.util.Collections;
import java.util.List;

import ch.hesso.master.sweetcity.Project;
import ch.hesso.master.sweetcity.database.comparator.TagPointComparator;
import ch.hesso.master.sweetcity.database.dao.TagDao;
import ch.hesso.master.sweetcity.database.entity.Tag;
import ch.hesso.master.sweetcity.utils.ServiceUtils;
import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.NotFoundException;
import com.google.api.server.spi.response.UnauthorizedException;

@Api(
        name = "tagService",
        version = "v1",
        description = "API to manage tags on SweetCity.", 
		scopes = {Project.EMAIL_SCOPE},
		clientIds = {
				Project.WEB_CLIENT_ID,  
				Project.ANDROID_CLIENT_ID_DEV,
				Project.ANDROID_CLIENT_ID_PROD,
				com.google.api.server.spi.Constant.API_EXPLORER_CLIENT_ID
		},
		audiences = {Project.ANDROID_AUDIENCE}
    )
public class TagService {

	@ApiMethod(path = "list", httpMethod = HttpMethod.GET)
	public List<Tag> getTagList(User user) throws UnauthorizedException {
		ServiceUtils.getCurrentAccount(user);
		List<Tag> listTag = TagDao.listTag();
		Collections.sort(listTag, TagPointComparator.INSTANCE);
		return listTag;
	}
	
	@ApiMethod(path = "addTag", httpMethod = HttpMethod.POST)
	private void addTag(Tag tag, User user) throws UnauthorizedException {
		ServiceUtils.getCurrentAccount(user);
		TagDao.addTag(tag);
	}
	
	@ApiMethod(path = "removeTag/{tagId}", httpMethod = HttpMethod.GET)
	private void removeTag(@Named("tagId") Long tagId, User user) throws UnauthorizedException, NotFoundException {
		ServiceUtils.getCurrentAccount(user);
		Tag tag = TagDao.getTag(tagId);
		
		if (tag == null) {
			throw new NotFoundException("Tag not found");
		} 
		
		TagDao.removeTag(tag);
	}
	
}

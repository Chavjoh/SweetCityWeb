package ch.hesso.master.sweetcity.database.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import ch.hesso.master.sweetcity.database.entity.Tag;

import com.googlecode.objectify.ObjectifyService;

public class TagDao {
	
	static {
		ObjectifyService.register(Tag.class);
	}

	public static void addTag(Tag tag) {
		ofy().save().entity(tag).now();
	}

	public static void saveTag(Tag tag) {
		// To edit, we overwrite
		addTag(tag);
	}

	public static List<Tag> listTag() {
		return ofy().load().type(Tag.class).list();
	}

	public static void removeTag(Tag tag) {
		ofy().delete().entity(tag).now();
	}

	public static Tag getTag(Long tagId) {
		return ofy().load().type(Tag.class).id(tagId).now();
	}
	
}

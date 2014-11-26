package ch.hesso.master.sweetcity.database.comparator;

import java.util.Comparator;

import ch.hesso.master.sweetcity.database.entity.Tag;

public class TagNameComparator implements Comparator<Tag> {
	
	public static final TagNameComparator INSTANCE = new TagNameComparator();
	
	@Override
	public int compare(Tag tag1, Tag tag2) {
		String name1 = tag1.getName();
		String name2 = tag2.getName();

		return name1.compareTo(name2);
	}

}
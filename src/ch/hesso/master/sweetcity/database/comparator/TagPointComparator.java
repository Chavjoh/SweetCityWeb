package ch.hesso.master.sweetcity.database.comparator;

import java.util.Comparator;

import ch.hesso.master.sweetcity.database.entity.Tag;

public class TagPointComparator implements Comparator<Tag> {
	
	public static final TagPointComparator INSTANCE = new TagPointComparator();
	
	@Override
	public int compare(Tag tag1, Tag tag2) {
		Integer points1 = tag1.getPoints();
		Integer points2 = tag2.getPoints();

		return points1.compareTo(points2);
	}

}
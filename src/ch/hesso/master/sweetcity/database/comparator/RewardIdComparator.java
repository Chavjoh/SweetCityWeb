package ch.hesso.master.sweetcity.database.comparator;

import java.util.Comparator;

import ch.hesso.master.sweetcity.database.entity.Reward;

public class RewardIdComparator implements Comparator<Reward> {
	
	public static final RewardIdComparator INSTANCE = new RewardIdComparator();

	@Override
	public int compare(Reward reward1, Reward reward2) {
		String id1 = reward1.getId();
		String id2 = reward2.getId();

		return id1.compareTo(id2);
	}
}
package ch.hesso.master.sweetcity.database.comparator;

import java.util.Comparator;

import ch.hesso.master.sweetcity.database.entity.Reward;

public class RewardNameComparator implements Comparator<Reward> {
	
	public static final RewardNameComparator INSTANCE = new RewardNameComparator();
	
	@Override
	public int compare(Reward reward1, Reward reward2) {
		String name1 = reward1.getName();
		String name2 = reward2.getName();

		return name1.compareTo(name2);
	}

}
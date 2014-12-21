package ch.hesso.master.sweetcity.database.comparator;

import java.util.Comparator;

import ch.hesso.master.sweetcity.database.entity.Account;

public class AccountRankingComparator implements Comparator<Account> {
	
	public static final AccountRankingComparator INSTANCE = new AccountRankingComparator();

	@Override
	public int compare(Account account1, Account account2) {
		Integer points1 = account1.getPoints();
		Integer points2 = account2.getPoints();

		return (-1) * points1.compareTo(points2);
	}
}
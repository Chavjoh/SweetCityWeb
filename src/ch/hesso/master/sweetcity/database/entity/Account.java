package ch.hesso.master.sweetcity.database.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import ch.hesso.master.sweetcity.database.tools.Deref;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Account implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id 
	private String email;
	@Index
	private String pseudo;
	private Integer points;
	private List<Ref<Reward>> listReward;
	private Date registrationDate;
	
	public Account() {
		listReward = new ArrayList<Ref<Reward>>();
	}
	
	public Account(String pseudo, String email) {
		this();
		setPseudo(pseudo);
		setEmail(email);
		setPoints(0);
		setRegistrationDate(new Date());
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == this) { return true; }
		if (object == null || object.getClass() != this.getClass()) { return false; }

		Account user = (Account)object;
		return email.equals(user.getEmail());
	}
	
	/**
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}
	
	/**
	 * @param pseudo the pseudo to set
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the user points
	 */
	public Integer getPoints() {
		return points;
	}
	
	/**
	 * @param points the points to set
	 */
	public void setPoints(Integer points) {
		this.points = points;
	}
	
	/**
	 * @return the list of reward
	 */
	public List<Reward> getListReward() {
		List<Reward> derefList = Deref.deref(this.listReward);
		derefList.removeAll(Collections.singleton(null));
		return derefList;
	}
	
	/**
	 * @param listReward the list of reward to set
	 */
	public void setListReward(List<Reward> listReward) {
		this.listReward.clear();
		for (Reward reward:listReward) {
			addReward(reward);
		}
	}
	
	/**
	 * @param reward the reward to add
	 */
	public void addReward(Reward reward) {
		this.listReward.add(Ref.create(reward));
	}
	
	/**
	 * @return the registration date
	 */
	public Date getRegistrationDate() {
		return registrationDate;
	}

	/**
	 * @param registrationDate the registration date to set
	 */
	protected void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	
}

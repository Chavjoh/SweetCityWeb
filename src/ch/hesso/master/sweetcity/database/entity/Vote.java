package ch.hesso.master.sweetcity.database.entity;

import java.io.Serializable;
import java.util.Date;

import ch.hesso.master.sweetcity.database.tools.Deref;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Vote implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	private Date date;
	private Ref<Account> user;
	private Ref<Report> report;

	public Vote() {
		
	}
	
	public Vote(Date date, Account user, Report report) {
		setDate(date);
		setUser(user);
		setReport(report);
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == this) { return true; }
		if (object == null || object.getClass() != this.getClass()) { return false; }

		Vote vote = (Vote)object;
		return id.equals(vote.getId());
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * @return the user
	 */
	public Account getUser() {
		return Deref.deref(user);
	}
	
	/**
	 * @param user the user to set
	 */
	public void setUser(Account user) {
		this.user = Ref.create(user);
	}
	
	/**
	 * @return the report
	 */
	public Report getReport() {
		return Deref.deref(report);
	}
	
	/**
	 * @param report the report to set
	 */
	public void setReport(Report report) {
		this.report = Ref.create(report);
	}
	
}

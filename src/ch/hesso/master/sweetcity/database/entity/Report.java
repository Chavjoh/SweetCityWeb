package ch.hesso.master.sweetcity.database.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import ch.hesso.master.sweetcity.database.tools.Deref;
import ch.hesso.master.sweetcity.utils.DateUtils;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Report implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	@Index
	private Ref<Account> user;
	private String image;
	private List<Ref<Tag>> listTag;
	private Integer vote;
	private Float latitude;
	private Float longitude;
	private Date submitDate;
	private Date validationDate;
	
	public Report() {
		listTag = new ArrayList<Ref<Tag>>();
	}
	
	public Report(Account user, String image, Float latitude, Float longitude) {
		this();
		setUser(user);
		setImage(image);
		setLatitude(latitude);
		setLongitude(longitude);
		resetDefault();
	}
	
	public Report(Account user, String image, List<Tag> listTag, Float latitude, Float longitude) {
		this(user, image, latitude, longitude);
		setListTag(listTag);
	}
	
	public void resetDefault() {
		setVote(0);
		setSubmitDate(DateUtils.now());
		setValidationDate(null);
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == this) { return true; }
		if (object == null || object.getClass() != this.getClass()) { return false; }

		Report report = (Report)object;
		return id.equals(report.getId());
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
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
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	
	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	
	/**
	 * @return the listTag
	 */
	public List<Tag> getListTag() {
		List<Tag> derefList = Deref.deref(this.listTag);
		derefList.removeAll(Collections.singleton(null));
		return derefList;
	}
	
	/**
	 * @param listTag the listTag to set
	 */
	public void setListTag(List<Tag> listTag) {
		this.listTag.clear();
		for (Tag tag:listTag) {
			addTag(tag);
		}
	}
	
	/**
	 * @param tag the tag to delete
	 */
	public void removeTag(Tag tag) {
		this.listTag.remove(Ref.create(tag));
	}
	
	/**
	 * @param tag the tag to add
	 */
	public void addTag(Tag tag) {
		this.listTag.add(Ref.create(tag));
	}
	
	/**
	 * @return the vote
	 */
	public Integer getVote() {
		return vote;
	}
	
	/**
	 * @param vote the vote to set
	 */
	public void setVote(Integer vote) {
		this.vote = vote;
	}
	
	/**
	 * @return the latitude
	 */
	public Float getLatitude() {
		return latitude;
	}
	
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}
	
	/**
	 * @return the longitude
	 */
	public Float getLongitude() {
		return longitude;
	}
	
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the submitDate
	 */
	public Date getSubmitDate() {
		return submitDate;
	}

	/**
	 * @param submitDate the submitDate to set
	 */
	private void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	/**
	 * @return the validationDate
	 */
	public Date getValidationDate() {
		return validationDate;
	}

	/**
	 * @param validationDate the validationDate to set
	 */
	public void setValidationDate(Date validationDate) {
		this.validationDate = validationDate;
	}
	
	/**
	 * @return true if the report is validated
	 */
	public boolean isValidated() {
		return validationDate != null;
	}
	
}

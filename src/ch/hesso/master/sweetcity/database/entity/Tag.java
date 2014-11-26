package ch.hesso.master.sweetcity.database.entity;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Tag implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	@Index
	private String name;
	private String description;
	private Integer points;
	
	public Tag() {
		
	}
	
	public Tag(String name, String description, Integer points) {
		setName(name);
		setDescription(description);
		setPoints(points);
	}
	
	@Override
	public boolean equals(Object object)
		{
		if (object == this) { return true; }
		if (object == null || object.getClass() != this.getClass()) { return false; }

		Tag tag = (Tag)object;
		return id.equals(tag.getId());
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the points
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

}

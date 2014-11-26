package ch.hesso.master.sweetcity.database.entity;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Reward implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	@Index
	private String name;
	private String description;
	private String type; 
	
	public Reward() {
		
	}
	
	public Reward(String name, String description, RewardType type) {
		setName(name);
		setDescription(description);
		setType(type);
	}
	
	@Override
	public boolean equals(Object object)
		{
		if (object == this) { return true; }
		if (object == null || object.getClass() != this.getClass()) { return false; }

		Reward reward = (Reward)object;
		return id.equals(reward.getId());
		}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(RewardType type) {
		this.type = type.toString();
	}
	
}

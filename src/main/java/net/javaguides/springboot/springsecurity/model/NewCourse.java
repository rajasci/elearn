package net.javaguides.springboot.springsecurity.model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;


@Entity
//Table(name = "course_registration",uniqueConstraints = @UniqueConstraint(columnNames = "course_id"))
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "course_id"))
public class NewCourse {
	 
	  @Id
      @GeneratedValue(strategy = GenerationType.AUTO)
	  private long course_id;
	  @Column(nullable = false)
	  private long user_id;
	  private String User_name;
	  @NotEmpty
	  private String Course_name;
	  private String Category;
	  private Blob image;
	  
	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	private String Description;
	  
	//  User user= new User();
	  public long getCourse_id() {
		return course_id;
	}

	public void setCourse_id(long course_id) {
		this.course_id = course_id;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return User_name;
	}

	public void setUser_name(String user_name) {
		User_name = user_name;
	}

	public String getCourse_name() {
		return Course_name;
	}

	public void setCourse_name(String course_name) {
		Course_name = course_name;
	}

	

	}
package net.javaguides.springboot.springsecurity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "content_id"))
public class Content {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	  private long content_id;
	  private long course_id;
	  private long section_id;
	  private long user_id;
	  private String User_name;
	  private String Course_name;
	  private String Content_name;
	  private String Description;
	  
	  @Column(name = "vedio")
	  //, columnDefinition="BLOB"
	  @Lob
			  
	  private String vedio;	
	public String getVedio() {
		return vedio;
	}
	public void setVedio(String vedio) {
		this.vedio = vedio;
	}
	public long getSection_id() {
		return section_id;
	}
	public void setSection_id(long section_id) {
		this.section_id = section_id;
	}
	
	
	public long getContent_id() {
		return content_id;
	}
	public void setContent_id(long content_id) {
		this.content_id = content_id;
	}
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
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getCourse_name() {
		return Course_name;
	}
	public void setCourse_name(String course_name) {
		Course_name = course_name;
	}
	public String getContent_name() {
		return Content_name;
	}
	public void setContent_name(String content_name) {
		Content_name = content_name;
	}
	
	
}

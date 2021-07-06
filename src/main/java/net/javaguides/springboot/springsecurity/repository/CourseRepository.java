package net.javaguides.springboot.springsecurity.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.Column;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.springsecurity.model.NewCourse;
import net.javaguides.springboot.springsecurity.model.Employee;
@Repository
public interface CourseRepository  extends JpaRepository<NewCourse, Long> {

	NewCourse save(NewCourse course);
	
	@Query("SELECT u FROM NewCourse u WHERE u.user_id = ?1")
    public List<NewCourse> findbyuserid(Long user_id);
	
	@Query("SELECT u FROM NewCourse u WHERE u.Course_name = ?1 and u.user_id=?2")
    public List<NewCourse> findbyCousename(String coursename, Long id);
	
	
	@Query("SELECT u FROM NewCourse u WHERE u.course_id = ?1 and u.user_id=?2")
    public List<NewCourse> findbyidcid(Long cid,Long uid);
	
	
	
}

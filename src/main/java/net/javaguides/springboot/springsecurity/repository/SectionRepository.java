package net.javaguides.springboot.springsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.javaguides.springboot.springsecurity.model.Content;
import net.javaguides.springboot.springsecurity.model.NewCourse;
import net.javaguides.springboot.springsecurity.model.Section;

public interface SectionRepository extends JpaRepository<Section, Long> { 

	
	Section save(Section course);
	
	@Query("SELECT u FROM Section u WHERE u.course_id = ?1 and u.user_id=?2")
    public List<Section> findbyuserid(Long user_id,long uid);
    
    
    @Query("SELECT u FROM Section u WHERE u.course_id = ?1")
    public List<NewCourse> findbycid(Long course_id);

    @Query("SELECT u FROM Section u WHERE u.section_name = ?1 and u.user_id=?2")
	public List<Section> findbysectionname(String sectionname, Long id);

    @Query("SELECT u FROM Section u WHERE u.section_id = ?1 and u.user_id=?2")
	List<Section> getbysiduid(Long sid, Long id);
}

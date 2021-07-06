package net.javaguides.springboot.springsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.javaguides.springboot.springsecurity.model.NewCourse;
import net.javaguides.springboot.springsecurity.model.Section;
import net.javaguides.springboot.springsecurity.model.Content;

public interface contentRepository extends JpaRepository<Content, Long> {

	Content save(Content course);
	
	@Query("SELECT u FROM Content u WHERE u.section_id = ?1")
    public List<Content> findbyuserid(Long section_id);
	
    
    @Query("SELECT u FROM Content u WHERE u.content_id = ?1")
    public Content contentid(Long content_id);

	
    
    @Query("SELECT u FROM Content u WHERE u.Content_name= ?1 and u.user_id=?2")
    List<Content> findbycontentame(String content_name, Long id);
    
  
	
	
}
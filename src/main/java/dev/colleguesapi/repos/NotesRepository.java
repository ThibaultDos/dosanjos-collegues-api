package dev.colleguesapi.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.colleguesapi.Notes;

public interface NotesRepository extends JpaRepository<Notes, String> {

	List<Notes> findById(Integer id);

	List<Notes> findByCommentaire(String commentaire);
}

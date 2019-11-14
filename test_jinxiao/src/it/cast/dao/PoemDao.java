package it.cast.dao;

import it.cast.domain.Poem;

import java.util.List;

public interface PoemDao {

    List<Poem> findByCategory(String category);

    Poem findById(String id);
}

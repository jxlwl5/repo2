package it.cast.service;

import it.cast.domain.Poem;

import java.util.List;

public interface PoemService {

    String findByCategory(String category);

    Poem findById(String id);
}

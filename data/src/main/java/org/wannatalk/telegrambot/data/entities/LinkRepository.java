package org.wannatalk.telegrambot.data.entities;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LinkRepository extends MongoRepository<Link, String> {

    List<Link> findAllByTextSearchLike(String text);
    Optional<Link> findByUrl(String url);
}

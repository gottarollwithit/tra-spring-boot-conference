package com.gottarollwithit.traconference.repository;

import com.gottarollwithit.traconference.model.Talk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TalkRepository extends JpaRepository<Talk, Long> {

    Optional<Talk> findOneByName(String name);

}

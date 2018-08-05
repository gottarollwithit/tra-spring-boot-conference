package com.gottarollwithit.traconference.service;

import com.gottarollwithit.traconference.dto.TalkForm;
import com.gottarollwithit.traconference.model.Talk;

import java.util.List;
import java.util.Optional;

public interface TalkService {

    Optional<Talk> findOneByName(String name);

    Talk create(TalkForm form);

    List<Talk> findAll();

    List<Talk> fillDemoData();

}

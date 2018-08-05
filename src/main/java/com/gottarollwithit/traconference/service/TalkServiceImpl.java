package com.gottarollwithit.traconference.service;

import com.gottarollwithit.traconference.dto.TalkForm;
import com.gottarollwithit.traconference.model.Talk;
import com.gottarollwithit.traconference.repository.TalkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TalkServiceImpl implements TalkService {

    @Autowired
    private TalkRepository talkRepository;

    @Override
    public Optional<Talk> findOneByName(String name) {
        log.debug("Getting Talk={}", name);
        return talkRepository.findOneByName(name);
    }

    @Override
    public Talk create(TalkForm form) {
        log.debug("Creating talk with name={} and duration={}", form.getName(), form.getDuration());
        Talk talk = new Talk();
        talk.setName(form.getName().trim());
        talk.setDuration(form.getDuration());
        return talkRepository.save(talk);
    }

    @Override
    public List<Talk> findAll() {
        return talkRepository.findAll();
    }

    @Override
    public List<Talk> fillDemoData() {
        talkRepository.deleteAll();
        List<Talk> demoDataList = new ArrayList<>();
        demoDataList.add(new Talk("Architecting Your Codebase", 60));
        demoDataList.add(new Talk("Overdoing it in Python", 45));
        demoDataList.add(new Talk("Flavors of Concurrency in Java", 30));
        demoDataList.add(new Talk("Ruby Errors from Mismatched Gem Versions", 45));
        demoDataList.add(new Talk("JUnit 5 - Shaping the Future of Testing on the JVM ", 45));
        demoDataList.add(new Talk("Cloud Native Java lightning", 60));
        demoDataList.add(new Talk("Communicating Over Distance", 60));
        demoDataList.add(new Talk("AWS Technical Essentials", 45));
        demoDataList.add(new Talk("Continuous Delivery", 30));
        demoDataList.add(new Talk("Monitoring Reactive Applications", 30));
        demoDataList.add(new Talk("Pair Programming vs Noise", 45));
        demoDataList.add(new Talk("Rails Magic", 60));
        demoDataList.add(new Talk("Microservices \"Just Right\"", 60));
        demoDataList.add(new Talk("Perfect Scalability", 30));
        demoDataList.add(new Talk("Apache Spark", 30));
        demoDataList.add(new Talk("Async Testing on JVM", 60));
        demoDataList.add(new Talk("A World Without HackerNews", 30));
        demoDataList.add(new Talk("User Interface CSS in Apps", 30));
        return talkRepository.saveAll(demoDataList);
    }

}

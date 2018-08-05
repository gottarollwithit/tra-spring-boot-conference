package com.gottarollwithit.traconference.controller;

import com.gottarollwithit.traconference.dto.Event;
import com.gottarollwithit.traconference.helper.ScheduleHelper;
import com.gottarollwithit.traconference.model.Talk;
import com.gottarollwithit.traconference.service.TalkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalTime;
import java.util.*;

@Slf4j
@Controller
public class ScheduleController {

    private static int MORNING_SESSION_DURATION = 180;
    private static int TOTAL_DURATION = 360;
    private static int AFTERNOON_SESSION_DURATION = 180;
    private static int AFTERNOON_SESSION_DURATION_MAX = 240;


    private Map<Integer, List<Event>> eventMap;

    @Autowired
    private TalkService talkService;

    @Autowired
    private ScheduleHelper scheduleHelper;

    @RequestMapping(value = "/schedule", method = RequestMethod.GET)
    public String schedule(Model model) {
        eventMap = new TreeMap<>();
        List<Talk> talkList = talkService.findAll();
        Collections.shuffle(talkList);
        Integer totalDuration = getTotalDuration(talkList);

        int sessionCount = totalDuration / TOTAL_DURATION;
        for (int i = 1; i <= sessionCount; i++) {
            addMorningSessions(i, talkList);
            addNoonBreak(i);
            addAfternoonSessions(i, talkList);
            addRemainingTalks(i, talkList);
            addNetworkSession(i);
        }

        model.addAttribute("eventMap", eventMap);
        model.addAttribute("unusedTalks", talkList);

        log.debug("Getting schedule page");
        return "SCHEDULE_URL";
    }

    private void addMorningSessions(int trackNo, List<Talk> talkList) {
        Integer duration = 0;
        List<Event> eventList = eventMap.computeIfAbsent(trackNo, k -> new ArrayList<>());
        LocalTime time = LocalTime.of(9, 0);
        List<Talk> exactSumTalk = scheduleHelper.getSolution(talkList, MORNING_SESSION_DURATION);
        if (exactSumTalk != null) {
            for (Talk talk : exactSumTalk) {
                eventList.add(new Event(talk.getName(), talk.getDuration(), time, trackNo));
                time = time.plusMinutes(talk.getDuration());
            }
            talkList.removeAll(exactSumTalk);
        } else {
            Iterator<Talk> iterator = talkList.iterator();
            while (iterator.hasNext()) {
                Talk talk = iterator.next();
                duration += talk.getDuration();
                if (duration < MORNING_SESSION_DURATION) {
                    eventList.add(new Event(talk.getName(), talk.getDuration(), time, trackNo));
                    time = time.plusMinutes(talk.getDuration());
                    iterator.remove();
                } else if (duration.equals(MORNING_SESSION_DURATION)) {
                    eventList.add(new Event(talk.getName(), talk.getDuration(), time, trackNo));
                    iterator.remove();
                    break;
                }
            }
        }

    }

    private void addNoonBreak(int trackNo) {
        eventMap.get(trackNo).add(new Event("Lunch", 60, LocalTime.of(12, 0), trackNo));
    }

    private void addAfternoonSessions(int trackNo, List<Talk> talkList) {
        Integer duration = 0;
        LocalTime time = LocalTime.of(13, 0);
        List<Event> eventList = eventMap.get(trackNo);

        List<Talk> exactSumTalk = scheduleHelper.getSolution(talkList, AFTERNOON_SESSION_DURATION);
        if (exactSumTalk != null) {
            for (Talk talk : exactSumTalk) {
                eventList.add(new Event(talk.getName(), talk.getDuration(), time, trackNo));
                time = time.plusMinutes(talk.getDuration());
            }
            talkList.removeAll(exactSumTalk);
        } else {
            Iterator<Talk> iterator = talkList.iterator();
            while (iterator.hasNext()) {
                Talk talk = iterator.next();
                duration += talk.getDuration();
                if (duration <= AFTERNOON_SESSION_DURATION) {
                    eventList.add(new Event(talk.getName(), talk.getDuration(), time, trackNo));
                    time = time.plusMinutes(talk.getDuration());
                    iterator.remove();
                }
            }
        }
    }


    private void addRemainingTalks(int trackNo, List<Talk> talkList) {
        List<Event> eventList = eventMap.get(trackNo);
        LocalTime lastTalkEndTime = eventList.get(eventList.size() - 1).getEndTime();
        LocalTime eventEndTime = LocalTime.of(17, 0);
        if (lastTalkEndTime.isBefore(LocalTime.of(17, 0))) {
            Iterator<Talk> iterator = talkList.iterator();
            while (iterator.hasNext()) {
                Talk talk = iterator.next();
                if (lastTalkEndTime.plusMinutes(talk.getDuration()).isBefore(eventEndTime) || (lastTalkEndTime.plusMinutes(talk.getDuration()).compareTo(eventEndTime) == 0)) {
                    eventList.add(new Event(talk.getName(), talk.getDuration(), lastTalkEndTime, trackNo));
                    lastTalkEndTime = lastTalkEndTime.plusMinutes(talk.getDuration());
                    iterator.remove();
                }
            }
        }
    }

    private void addNetworkSession(int trackNo) {
        List<Event> eventList = eventMap.get(trackNo);
        LocalTime lastTalkEndTime = eventList.get(eventList.size() - 1).getEndTime();
        LocalTime eventEndTime = LocalTime.of(17, 0);
        if (lastTalkEndTime.isBefore(LocalTime.of(17, 0))) {
            eventList.add(new Event("Network Session", lastTalkEndTime.isBefore(LocalTime.of(16, 0)) ? LocalTime.of(16, 0) : lastTalkEndTime, eventEndTime, trackNo));
        }
    }

    private int getTotalDuration(List<Talk> talkList) {
        return talkList.stream().mapToInt(Talk::getDuration).sum();
    }

}
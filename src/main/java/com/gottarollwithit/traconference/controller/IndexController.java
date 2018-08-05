package com.gottarollwithit.traconference.controller;

import com.gottarollwithit.traconference.helper.StaticURLs;
import com.gottarollwithit.traconference.model.Talk;
import com.gottarollwithit.traconference.service.TalkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Slf4j
@Controller
public class IndexController {

    @Autowired
    private TalkService talkService;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String registration(Model model) {
        List<Talk> talkList = talkService.findAll();

        model.addAttribute("talks", talkList);
        log.debug("Getting index page");
        return StaticURLs.INDEX_URL;
    }

}
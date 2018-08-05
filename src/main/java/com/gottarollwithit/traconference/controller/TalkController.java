package com.gottarollwithit.traconference.controller;

import com.gottarollwithit.traconference.dto.TalkForm;
import com.gottarollwithit.traconference.helper.StaticURLs;
import com.gottarollwithit.traconference.service.TalkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Slf4j
@Controller
public class TalkController {

    @Autowired
    private TalkService talkService;

    @RequestMapping(value = "/talk", method = RequestMethod.GET)
    public String talk(Model model) {
        model.addAttribute("talkForm", new TalkForm());
        log.debug("Getting registration page");
        return StaticURLs.TALK_URL;
    }

    @RequestMapping(value = "/talk", method = RequestMethod.POST)
    public String talk(@Valid @ModelAttribute("talkForm") TalkForm talkForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return StaticURLs.TALK_URL;
        }

        if (talkService.findOneByName(talkForm.getName()).isPresent()) {
            log.warn("Talk with same name exists");
            bindingResult.reject("Talk with same name exists");
            return StaticURLs.TALK_URL;
        }

        talkService.create(talkForm);
        return "redirect:" + StaticURLs.INDEX_URL;
    }

    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public String fillDemoData() {
        talkService.fillDemoData();
        return "redirect:" + StaticURLs.INDEX_URL;
    }


}
package com.gottarollwithit.traconference;

import com.gottarollwithit.traconference.dto.TalkForm;
import com.gottarollwithit.traconference.service.TalkService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Random;

import static org.mockito.Mockito.verifyZeroInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TraConferenceApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TalkService talkServiceMock;


    @Test
    public void findAll() throws Exception {

        TalkForm talkForm = new TalkForm();
        talkForm.setName(createStringWithLength(50));
        talkForm.setDuration(new Random().nextInt(100) + 5);


//        mockMvc.perform(post("talk")
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .sessionAttr("talk", talkForm)
//        )
//                .andExpect(status().isOk())
//                .andExpect(view().name("talk"))
//                .andExpect(forwardedUrl("index"))
//                .andExpect(model().attributeHasFieldErrors("talks", "talks"));
//        verifyZeroInteractions(talkServiceMock);
    }

    private static String createStringWithLength(int length) {
        StringBuilder builder = new StringBuilder();

        for (int index = 0; index < length; index++) {
            builder.append("a");
        }

        return builder.toString();
    }
}


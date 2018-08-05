package com.gottarollwithit.traconference;

import com.gottarollwithit.traconference.controller.IndexController;
import com.gottarollwithit.traconference.model.Talk;
import com.gottarollwithit.traconference.service.TalkService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.Random;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TraConferenceApplicationTests {

    private MockMvc mockMvc;

    @Mock
    private TalkService talkService;

    @InjectMocks
    private IndexController indexController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(indexController)
                .build();
    }

    @Test
    public void index_should_return_all_talks() throws Exception {

        Talk talk1 = new Talk();
        talk1.setName(createStringWithLength(50));
        talk1.setDuration(new Random().nextInt(100) + 5);
        Talk talk2 = new Talk();
        talk2.setName(createStringWithLength(50));
        talk2.setDuration(new Random().nextInt(100) + 5);

        when(talkService.findAll()).thenReturn(Arrays.asList(talk1, talk2));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("talks", hasSize(2)));

        verify(talkService, times(1)).findAll();
        verifyNoMoreInteractions(talkService);
    }

    private static String createStringWithLength(int length) {
        StringBuilder builder = new StringBuilder();

        for (int index = 0; index < length; index++) {
            builder.append("a");
        }

        return builder.toString();
    }
}


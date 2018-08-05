package com.gottarollwithit.traconference;

import com.gottarollwithit.traconference.controller.TalkController;
import com.gottarollwithit.traconference.service.TalkService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class SmokeTest {

    @InjectMocks
    private TalkController talkController;

    @InjectMocks
    private TalkService talkService;

    @Test
    public void contexLoads() {
        assertThat(talkController).isNotNull();
        assertThat(talkService).isNotNull();
    }
}
package com.gottarollwithit.traconference.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@Setter
public class TalkForm {

    @Size(min = 3, max = 255, message = "Name should be between 3-255 characters")
    private String name;

    @Min(value = 5,message = "Duration must be at least 5 minutes")
    private Integer duration;

}
package com.example.first_project.image;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ImageDto {
    private String chunk;
    private int index;
    private int total;
}

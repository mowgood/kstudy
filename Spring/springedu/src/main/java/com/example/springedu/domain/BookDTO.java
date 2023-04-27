package com.example.springedu.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {
    private int id;
    private String title;
    private String bookPrice;
    private String kind;
}

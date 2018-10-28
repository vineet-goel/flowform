package com.example.webflow.vm;

import com.example.webflow.annotations.FormField;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UserVM {

    @FormField(type = "text")
    private String name;

    @FormField(type = "select", values = {"Mars", "Venus", "Earth"})
    private int age;
}

package com.zxx.demorepository.sort;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Person {
    private String id;
    private String name;
    private int age;
    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
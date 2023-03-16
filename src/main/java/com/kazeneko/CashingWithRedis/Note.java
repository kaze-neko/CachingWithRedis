package com.kazeneko.CashingWithRedis;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Note implements Serializable{
    public static final String HASH_KEY = "Note";
    private int id;
    private String title;
    private String text;
}

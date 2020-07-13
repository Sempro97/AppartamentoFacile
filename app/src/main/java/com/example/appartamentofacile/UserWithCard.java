package com.example.appartamentofacile;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserWithCard {
    @Embedded public User user;
    @Relation(
            parentColumn = "user_id",
            entityColumn = "user_creator_id"
    )

    public List<CardItem> cards;
}

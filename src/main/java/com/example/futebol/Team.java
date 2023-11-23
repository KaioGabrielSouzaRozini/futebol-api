package com.example.futebol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "my-teams")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    @Id
    private ObjectId _id;

    private int id;
    private String name;
    private String shield;
    private int points;
    private int goals_scored;
    private int goals_conceded;
    private int win_probability;
    private int position;
    private int wins;
    private int loses;
    private int matches;
    private int draws;

}

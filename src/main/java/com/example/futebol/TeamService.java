package com.example.futebol;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TeamService {
    @Autowired
    private TeamsRepository teamsRepository;

    @Autowired
    private MongoTemplate mongoTemplate;
    public List<Team> allTeams(){

        return teamsRepository.findAll();
    }

    public Optional<Team> teamById(int id){
        return teamsRepository.findTeamById(id);
    }

    public Optional<Team> updateTeam(String id, String match, String goals_scored, String goals_conceded) {
        int idInt = Integer.parseInt(id);
        int goals_scoredInt = Integer.parseInt(goals_scored);
        int goals_concededInt = Integer.parseInt(goals_conceded);

        if(Objects.equals(match, "win")){
            mongoTemplate.update(Team.class).matching(Criteria.where("id").is(idInt)).apply(new Update().inc("wins", 1)).first();
            mongoTemplate.update(Team.class).matching(Criteria.where("id").is(idInt)).apply(new Update().inc("points", 3)).first();
        } else if(Objects.equals(match, "lose")){
            mongoTemplate.update(Team.class).matching(Criteria.where("id").is(idInt)).apply(new Update().inc("loses", 1)).first();
        } else if(Objects.equals(match, "draw")){
            mongoTemplate.update(Team.class).matching(Criteria.where("id").is(idInt)).apply(new Update().inc("draws", 1)).first();
            mongoTemplate.update(Team.class).matching(Criteria.where("id").is(idInt)).apply(new Update().inc("points", 1)).first();
        }

        mongoTemplate.update(Team.class).matching(Criteria.where("id").is(idInt)).apply(new Update().inc("goals_scored", goals_scoredInt)).first();
        mongoTemplate.update(Team.class).matching(Criteria.where("id").is(idInt)).apply(new Update().inc("goals_conceded", goals_concededInt)).first();
        mongoTemplate.update(Team.class).matching(Criteria.where("id").is(idInt)).apply(new Update().inc("matches", 1)).first();


        return teamById(idInt);
    }

}

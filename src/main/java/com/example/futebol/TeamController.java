package com.example.futebol;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v2/teams")
public class TeamController {
    @Autowired
    private TeamService teamService;
    @CrossOrigin("http://localhost:4200")
    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams(){
        return new ResponseEntity<List<Team>>(teamService.allTeams(), HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Team>> getOneTeam(@PathVariable int id){
        return new ResponseEntity<Optional<Team>>(teamService.teamById(id), HttpStatus.OK);
    }


    @CrossOrigin("http://localhost:4200")
    @PostMapping
    public ResponseEntity<Optional<Team>> updateWins(@RequestBody Map<String, String> id) {
        return new ResponseEntity<Optional<Team>>(teamService.updateTeam(id.get("id"), id.get("match"),id.get("goals_scored"), id.get("goals_conceded")), HttpStatus.OK);
    }

}

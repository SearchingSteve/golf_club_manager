package edu.keyin.stephencrocker.tournament;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TournamentController {
    @Autowired
    private TournamentService tournamentService;

    // Get all tournaments
    @GetMapping("/tournaments")
    public List<Tournament> getAllTournaments() {
        return tournamentService.findAllTournaments();
    }

    // Get tournament by id
    @GetMapping("tournaments{id}")
    public Tournament getTournamentById(@PathVariable Long id) {
        return tournamentService.findTournamentById(id);
    }

    // Create new tournament
    @PostMapping("/tournament")
    public Tournament createTournament(@RequestBody Tournament newTournament) {
        return tournamentService.createTournament(newTournament);
    }

    // Update tournament
    @PutMapping("tournament{id}")
    public Tournament updateTournament(@PathVariable Long id, @RequestBody Tournament tournament) {
        return tournamentService.updateTournament(id, tournament);
    }
}
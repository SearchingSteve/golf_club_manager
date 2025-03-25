package edu.keyin.stephencrocker.tournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import edu.keyin.stephencrocker.member.Member;

import java.time.LocalDate;
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
    @GetMapping("/tournaments/{id}")
    public Tournament getTournamentById(@PathVariable Long id) {
        return tournamentService.findTournamentById(id);
    }

    // Search tournaments
    @GetMapping("/tournaments/search")
    public List<Tournament> searchTournaments(
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) LocalDate endDate) {
        return tournamentService.searchTournaments(startDate, location, endDate);
    }

    // Get all members in a tournament
    @GetMapping("/tournaments/{id}/members")
    public List<Member> getTournamentMembers(@PathVariable Long id) {
        return tournamentService.findMembersInTournament(id);
    }

    // Create new tournament
    @PostMapping("/tournament")
    public Tournament createTournament(@RequestBody Tournament newTournament) {
        return tournamentService.createTournament(newTournament);
    }

    // Update tournament
    @PutMapping("/tournament/{id}")
    public Tournament updateTournament(@PathVariable Long id, @RequestBody Tournament tournament) {
        return tournamentService.updateTournament(id, tournament);
    }

    // Add members to tournament by their IDs
    @PostMapping("/tournament/{id}/members")
    public Tournament addMembersToTournament(@PathVariable Long id, @RequestBody List<Long> memberIds) {
        return tournamentService.addMembersToTournament(id, memberIds);
    }
}
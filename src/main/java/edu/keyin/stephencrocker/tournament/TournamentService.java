package edu.keyin.stephencrocker.tournament;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TournamentService {
    @Autowired
    private TournamentRepository tournamentRepository;

    public List<Tournament> findAllTournaments() {
        return (List<Tournament>) tournamentRepository.findAll();
    }

    public Tournament findTournamentById(long id) {
        Optional<Tournament> optionalTournament = tournamentRepository.findById(id);

        return optionalTournament.orElse(null);
    }

    public Tournament createTournament(Tournament newTournament) {
        return tournamentRepository.save(newTournament);
    }

    public Tournament updateTournament(long id, Tournament updatedTournament) {
        Tournament tournamentToUpdate = findTournamentById(id);

        if (tournamentToUpdate != null) {
           tournamentToUpdate.setStartDate(updatedTournament.getStartDate());
           tournamentToUpdate.setEndDate(updatedTournament.getEndDate());
           tournamentToUpdate.setLocation(updatedTournament.getLocation());
           tournamentToUpdate.setEntryFee(updatedTournament.getEntryFee());
           tournamentToUpdate.setCashPrize(updatedTournament.getCashPrize());
            return tournamentRepository.save(tournamentToUpdate);
        }

        return null;
    }
}
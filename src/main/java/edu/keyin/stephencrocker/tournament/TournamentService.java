package edu.keyin.stephencrocker.tournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.keyin.stephencrocker.member.Member;
import edu.keyin.stephencrocker.member.MemberRepository;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class TournamentService {
    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private MemberRepository memberRepository;

    public List<Tournament> findAllTournaments() {
        return (List<Tournament>) tournamentRepository.findAll();
    }

    public Tournament findTournamentById(long id) {
        Optional<Tournament> optionalTournament = tournamentRepository.findById(id);
        return optionalTournament.orElse(null);
    }

    public List<Tournament> findTournamentsByStartDate(LocalDate startDate) {
        return tournamentRepository.findByStartDate(startDate);
    }

    public List<Tournament> findTournamentsByLocation(String location) {
        return tournamentRepository.findByLocationContainingIgnoreCase(location);
    }

    public List<Tournament> findTournamentsByDateRange(LocalDate startDate, LocalDate endDate) {
        return tournamentRepository.findByStartDateBetween(startDate, endDate);
    }

    public List<Tournament> searchTournaments(LocalDate startDate, String location, LocalDate endDate) {
        if (startDate != null && endDate != null) {
            return findTournamentsByDateRange(startDate, endDate);
        } else if (startDate != null) {
            return findTournamentsByStartDate(startDate);
        } else if (location != null && !location.trim().isEmpty()) {
            return findTournamentsByLocation(location);
        } else {
            return findAllTournaments();
        }
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

            if (updatedTournament.getParticipatingMembers() != null) {
                List<Member> members = new ArrayList<>();
                for (Member member : updatedTournament.getParticipatingMembers()) {
                    if (member.getId() > 0) {
                        Optional<Member> existingMember = memberRepository.findById(member.getId());
                        if (existingMember.isPresent()) {
                            members.add(existingMember.get());
                        }
                    }
                }
                tournamentToUpdate.setParticipatingMembers(members);
            }

            return tournamentRepository.save(tournamentToUpdate);
        }

        return null;
    }

    public List<Member> findMembersInTournament(long tournamentId) {
        Tournament tournament = findTournamentById(tournamentId);
        return tournament != null ? tournament.getParticipatingMembers() : new ArrayList<>();
    }

    public Tournament addMembersToTournament(long tournamentId, List<Long> memberIds) {
        Tournament tournament = findTournamentById(tournamentId);
        if (tournament == null) {
            return null;
        }

        List<Member> currentMembers = tournament.getParticipatingMembers();
        if (currentMembers == null) {
            currentMembers = new ArrayList<>();
        }

        for (Long memberId : memberIds) {
            Optional<Member> member = memberRepository.findById(memberId);
            if (member.isPresent() && !currentMembers.contains(member.get())) {
                currentMembers.add(member.get());
            }
        }

        tournament.setParticipatingMembers(currentMembers);
        return tournamentRepository.save(tournament);
    }
}
package edu.keyin.stephencrocker.tournament;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDate;

@Repository
public interface TournamentRepository extends CrudRepository<Tournament, Long> {
    // Search by start date
    List<Tournament> findByStartDate(LocalDate startDate);

    // Search by location
    List<Tournament> findByLocationContainingIgnoreCase(String location);

    // Search by date range
    List<Tournament> findByStartDateBetween(LocalDate startDate, LocalDate endDate);

}
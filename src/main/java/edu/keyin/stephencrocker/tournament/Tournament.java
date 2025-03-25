package edu.keyin.stephencrocker.tournament;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import edu.keyin.stephencrocker.member.Member;

@Entity
public class Tournament {
    @Id
    @SequenceGenerator(name = "tournament_sequence", sequenceName = "tournament_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "tournament_sequence")
    private long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String location;
    private double entryFee;
    private double cashPrize;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tournament_members", joinColumns = @JoinColumn(name = "tournament_id"), inverseJoinColumns = @JoinColumn(name = "member_id"))
    private List<Member> participatingMembers = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getEntryFee() {
        return entryFee;
    }

    public void setEntryFee(double entryFee) {
        this.entryFee = entryFee;
    }

    public double getCashPrize() {
        return cashPrize;
    }

    public void setCashPrize(double cashPrize) {
        this.cashPrize = cashPrize;
    }

    public List<Member> getParticipatingMembers() {
        return participatingMembers;
    }

    public void setParticipatingMembers(List<Member> participatingMembers) {
        this.participatingMembers = participatingMembers;
    }
}

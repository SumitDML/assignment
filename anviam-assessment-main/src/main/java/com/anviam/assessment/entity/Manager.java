package com.anviam.assessment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter

@NoArgsConstructor
public class Manager {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "manager_fname")
    private String firstName;
    @Column(name = "manager_lname")
    private String lastName;
    @Column(name = "manager_id")
    private String idNumber;
    @Column(name = "email",unique = true)
    private String email;
    @OneToOne(cascade = CascadeType.MERGE)
    private Agent agent;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name ="manager_teams",
            joinColumns = { @JoinColumn(name ="manager_id") },
            inverseJoinColumns = { @JoinColumn(name = "team_id") })
    private Set<Team> managedTeams;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Set<Team> getManagedTeams() {
        return managedTeams;
    }

    public void setManagedTeams(Set<Team> managedTeams) {
        this.managedTeams = managedTeams;
    }

    public Manager(Long id, String firstName, String lastName, String idNumber, String email, Agent agent, Set<Team> managedTeams) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.email = email;
        this.agent = agent;
        this.managedTeams = managedTeams;
    }
}

package com.anviam.assessment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "team_name",unique = true)
    private String name;
    @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "managedTeams")
    private Set<Manager> managers;

    @OneToMany(mappedBy = "team")
    private List<Agent> agents;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Manager> getManagers() {
        return managers;
    }

    public void setManagers(Set<Manager> managers) {
        this.managers = managers;
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public void setAgents(List<Agent> agents) {
        this.agents = agents;
    }

    public Team(Long id, String name, Set<Manager> managers, List<Agent> agents) {
        this.id = id;
        this.name = name;
        this.managers = managers;
        this.agents = agents;
    }
}

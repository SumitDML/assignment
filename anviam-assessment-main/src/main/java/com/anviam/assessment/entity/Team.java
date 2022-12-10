package com.anviam.assessment.entity;





import javax.persistence.*;
import java.util.Set;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "team_name",unique = true)
    private String name;

    @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "managedTeams")
    private Set<Manager> managers;

    @OneToMany(mappedBy = "team")
    private Set<Agent> agents;

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


    public Set<Agent> getAgents() {
        return agents;
    }

    public void setAgents(Set<Agent> agents) {
        this.agents = agents;
    }

    public Team(Long id, String name, Set<Manager> managers, Set<Agent> agents) {
        this.id = id;
        this.name = name;
        this.managers = managers;
        this.agents = agents;
    }

    public Team() {
    }
}

package com.anviam.assessment.service.impl;

import com.anviam.assessment.entity.Agent;
import com.anviam.assessment.entity.Manager;
import com.anviam.assessment.entity.Team;
import com.anviam.assessment.exception.InvalidArguementExceptions;
import com.anviam.assessment.exception.ItemNotFoundException;
import com.anviam.assessment.model.request.CreateTeamRequest;
import com.anviam.assessment.model.TeamDTO;
import com.anviam.assessment.model.response.TeamResponse;
import com.anviam.assessment.model.response.UIBean;
import com.anviam.assessment.repository.AgentRepository;
import com.anviam.assessment.repository.ManagerRepository;
import com.anviam.assessment.repository.TeamRepository;
import com.anviam.assessment.service.TeamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    AgentRepository agentRepository;

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public UIBean createNewTeam(CreateTeamRequest createTeamRequest) {
        Team existingteam = teamRepository.findByName(createTeamRequest.getName());
        if(existingteam == null){
            Team team  = modelMapper.map(createTeamRequest,Team.class);

            Team savedTeam = teamRepository.save(team);
            TeamDTO teamDTO = modelMapper.map(savedTeam, TeamDTO.class);
            return new UIBean(teamDTO) ;
        }
      else {
         throw new InvalidArguementExceptions("Team Already Exists!");
       }
    }

    @Override
    public UIBean findTeamById(Long id) {
        Team existingTeam =teamRepository.findById(id).orElse(null);
        if(existingTeam != null){
            TeamResponse teamResponse = modelMapper.map(existingTeam, TeamResponse.class);
            return new UIBean(teamResponse);
        }
        else {
            throw new ItemNotFoundException("Sorry! Team Does not Exist!");
        }
    }

    @Override
    public UIBean getAllTeams(Integer pageSize, Integer pageNumber) {
        Pageable p = PageRequest.of(pageNumber,pageSize);

        Page<Team> pageItems= teamRepository.findAll(p);
        List<Team> allTeams = pageItems.getContent();
        if(allTeams != null){
            List<TeamDTO> teamsList = new ArrayList<>();
            allTeams.forEach(a-> {
                teamsList.add(modelMapper.map(a, TeamDTO.class));
            });
            return new UIBean(teamsList);
        }
        else {
            throw new ItemNotFoundException("Sorry! No Teams Found! ");
        }
    }

    @Override
    public UIBean assignAgent(Long teamId, Long agentId) {
        Agent existingAgent = agentRepository.findById(agentId).orElse(null);
        if(existingAgent != null){
            Manager agentManager = existingAgent.getReportingManager();
            Team agentTeam = existingAgent.getTeam();
            if(agentTeam == null){
                Team existingTeam = teamRepository.findById(teamId).orElse(null);

                if(existingTeam != null){
                    Set<Manager> teamManager = existingTeam.getManagers();
                    if(!teamManager.contains(agentManager)){
                        throw new InvalidArguementExceptions("Managers Do not Match!");
                    }
                   existingAgent.setTeam(existingTeam);
                   agentRepository.save(existingAgent);
                }else {
                    throw new ItemNotFoundException("Sorry! Team Not Found!");
                }
            }
            else {
                throw new InvalidArguementExceptions("Agent is already assigned to team: "+agentTeam.getName());
            }
        }else {
            throw new ItemNotFoundException("Sorry! Agent Not Found!!");
        }
        return new UIBean("Agent Assigned Successfully!!");
    }

    @Override
    public UIBean findEmptyTeams() {
        List<Team> allTeams  = teamRepository.findAll();
        List<TeamDTO> filteredTeams = new ArrayList<>();

        allTeams.forEach(t -> {
            if(t.getAgents().isEmpty() && t.getManagers().isEmpty()){
                filteredTeams.add(modelMapper.map(t, TeamDTO.class));
            }
        });
        if (filteredTeams.isEmpty()){
            throw new ItemNotFoundException("No Empty Teams Found!");
        }
        return new UIBean(filteredTeams);
    }
}

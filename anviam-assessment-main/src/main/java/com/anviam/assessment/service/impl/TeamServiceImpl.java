package com.anviam.assessment.service.impl;

import com.anviam.assessment.entity.Agent;
import com.anviam.assessment.entity.Team;
import com.anviam.assessment.exception.InvalidArguementExceptions;
import com.anviam.assessment.exception.ItemNotFoundException;
import com.anviam.assessment.model.request.CreateTeamRequest;
import com.anviam.assessment.model.response.AgentResponse;
import com.anviam.assessment.model.response.TeamResponse;
import com.anviam.assessment.model.response.UIBean;
import com.anviam.assessment.repository.AgentRepository;
import com.anviam.assessment.repository.TeamRepository;
import com.anviam.assessment.service.TeamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    AgentRepository agentRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public UIBean createNewTeam(CreateTeamRequest createTeamRequest) {
        Team existingteam = teamRepository.findByName(createTeamRequest.getName());
        if(existingteam == null){
            Team team  = modelMapper.map(createTeamRequest,Team.class);

            Team savedTeam = teamRepository.save(team);
            TeamResponse teamResponse = modelMapper.map(savedTeam,TeamResponse.class);
            return new UIBean(teamResponse) ;
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
            List<TeamResponse> teamsList = new ArrayList<>();
            allTeams.forEach(a-> {
                teamsList.add(modelMapper.map(a, TeamResponse.class));
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
            Team agentTeam = existingAgent.getTeam();
            if(agentTeam == null){
                Team existingTeam = teamRepository.findById(teamId).orElse(null);
                if(existingTeam != null){
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
        List<TeamResponse> filteredTeams = new ArrayList<>();

        allTeams.forEach(t -> {
            if(t.getAgents()==null && t.getManagers()==null){
                filteredTeams.add(modelMapper.map(t, TeamResponse.class));
            }
        });
        if (filteredTeams.isEmpty()){
            throw new ItemNotFoundException("No Empty Teams Found!");
        }
        return new UIBean(filteredTeams);
    }
}

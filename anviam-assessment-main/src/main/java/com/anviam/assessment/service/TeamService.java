package com.anviam.assessment.service;


import com.anviam.assessment.model.request.CreateTeamRequest;
import com.anviam.assessment.model.response.UIBean;

public interface TeamService {
    UIBean createNewTeam(CreateTeamRequest createTeamRequest);
    UIBean findTeamById(Long id);
    UIBean getAllTeams(Integer pageSize ,Integer pageNumber);

    UIBean assignAgent(Long teamId,Long agentId);


    UIBean findEmptyTeams();
}

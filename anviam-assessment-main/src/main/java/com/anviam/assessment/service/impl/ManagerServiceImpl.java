package com.anviam.assessment.service.impl;

import com.anviam.assessment.entity.Manager;
import com.anviam.assessment.entity.Team;
import com.anviam.assessment.exception.InvalidArguementExceptions;
import com.anviam.assessment.model.TeamDTO;
import com.anviam.assessment.model.request.CreateManagerRequest;
import com.anviam.assessment.model.response.ManagerResponse;
import com.anviam.assessment.model.response.UIBean;
import com.anviam.assessment.repository.ManagerRepository;
import com.anviam.assessment.repository.TeamRepository;
import com.anviam.assessment.service.ManagerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    ManagerRepository managerRepository;
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    TeamRepository teamRepository;

    @Override
    public UIBean createNewManager(CreateManagerRequest createManagerRequest) {

        Manager existingManager = managerRepository.findByEmail(createManagerRequest.getEmail());
        if(existingManager == null){
            Set<Team> teamList = new HashSet<>();
            Manager manager = modelMapper.map(createManagerRequest, Manager.class);

            String id = UUID.randomUUID().toString().replace("-","").substring(0,8);
            manager.setIdNumber(id);

            Set<TeamDTO> teamNames = createManagerRequest.getManagedTeams();
            teamNames.forEach(t ->{
                Team team = teamRepository.findByName(t.getName());
                if(team.getManagers().size()==2){
                    throw new InvalidArguementExceptions(team.getName()+" already has 2 existing managers!");
                }
                teamList.add(team);
            });
            manager.setManagedTeams(teamList);

            Manager savedManager =  managerRepository.save(manager);
            ManagerResponse returnValue = modelMapper.map(savedManager,ManagerResponse.class);

            return new UIBean(returnValue) ;
        }
        else {
            return new UIBean("Manager Already Exists!");
        }

    }

}

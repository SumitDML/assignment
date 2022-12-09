package com.anviam.assessment.service.impl;

import com.anviam.assessment.entity.Manager;
import com.anviam.assessment.model.request.CreateManagerRequest;
import com.anviam.assessment.model.response.UIBean;
import com.anviam.assessment.repository.ManagerRepository;
import com.anviam.assessment.service.ManagerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    ManagerRepository managerRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public UIBean createNewManager(CreateManagerRequest createManagerRequest) {

        Manager existingManager = managerRepository.findByEmail(createManagerRequest.getEmail());
        if(existingManager == null){
            String id = UUID.randomUUID().toString().replace("-","").substring(0,8);
            Manager manager = modelMapper.map(createManagerRequest, Manager.class);
            manager.setIdNumber(id);
            managerRepository.save(manager);
            return new UIBean("Manager Created Successfully!") ;
        }
        else {
            return new UIBean("Manager Already Exists!");
        }

    }

}

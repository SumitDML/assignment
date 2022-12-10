package com.anviam.assessment.controller;

import com.anviam.assessment.exception.ConstraintViolationException;
import com.anviam.assessment.model.request.CreateAgentRequest;
import com.anviam.assessment.model.request.CreateManagerRequest;
import com.anviam.assessment.model.response.ResponseModel;
import com.anviam.assessment.model.response.UIBean;
import com.anviam.assessment.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
public class ManagerController {

    @Autowired
    ManagerService managerService;

    @PostMapping("/manager/create")
    public ResponseEntity createNewManager(@Valid @RequestBody CreateManagerRequest createManagerRequest){
        try{
            UIBean returnValue = managerService.createNewManager(createManagerRequest);
            ResponseModel responseModel = new ResponseModel(HttpStatus.OK,"Manager Created Successfully!",null,returnValue);
            return new ResponseEntity(responseModel, HttpStatus.OK);
        }
        catch (ValidationException | ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }




}

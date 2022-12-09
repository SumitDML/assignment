package com.anviam.assessment.controller;


import com.anviam.assessment.exception.ConstraintViolationException;
import com.anviam.assessment.model.request.CreateAgentRequest;
import com.anviam.assessment.model.response.ResponseModel;
import com.anviam.assessment.model.response.UIBean;
import com.anviam.assessment.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
public class AgentController {
    @Autowired
    AgentService agentService;
    @PostMapping("/agent/createAgent")
    public ResponseEntity createNewAgent(@Valid @RequestBody CreateAgentRequest createAgentRequest){
        try{
            UIBean returnValue = agentService.createNewAgent(createAgentRequest);
            ResponseModel responseModel = new ResponseModel<>(HttpStatus.OK,"Agent Created Successfully!",null,returnValue);
            return new ResponseEntity(responseModel, HttpStatus.OK);
        }
        catch (ValidationException | ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("agent/{id}")
    public ResponseEntity findAgent(@PathVariable("id") Long id) {
        UIBean returnValue = agentService.findAgentById(id);
        return new ResponseEntity<>(returnValue,HttpStatus.OK);
    }

    @GetMapping("/agents/findAll")
    public ResponseEntity findAllAgents( @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                         @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize)
    {
        UIBean returnValue = agentService.getAllAgents(pageSize,pageNumber);
        return new ResponseEntity(returnValue,HttpStatus.OK);
    }

}

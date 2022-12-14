package com.anviam.assessment.model.request;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
public class CreateAgentRequest {
   @NotBlank(message = "First name cannot be null!")
    private String firstName;
    @NotBlank(message = "Last name cannot be null")
    private String lastName;
    @Email
    @NotNull(message = "Email name cannot be null")
    private String email;
    @NotNull(message = "Manager ID cannot be null")
    private Long managerId;

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

 public String getEmail() {
  return email;
 }

 public void setEmail(String email) {
  this.email = email;
 }

 public Long getManagerId() {
  return managerId;
 }

 public void setManagerId(Long managerId) {
  this.managerId = managerId;
 }
}

package com.anviam.assessment.model;

public class AgentDTO {

    private String idNumber;
    private String firstName;
    private String lastName;

    public AgentDTO(String idNumber, String firstName, String lastName) {
        this.idNumber = idNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public AgentDTO() {
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

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
}

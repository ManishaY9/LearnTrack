package com.airtribe.learntrack.entity;

public class Trainer extends Person {
    private String specialization;
    private int yearsOfExperience;

    public Trainer(int id, String firstName, String lastName, String email, String specialization, int yearsOfExperience) {
        super(id, firstName, lastName, email);
        this.specialization = specialization;
        this.yearsOfExperience = yearsOfExperience;
    }

    @Override
    public String getDisplayName() {
        return "Trainer " + super.getDisplayName() + " (" + specialization + ")";
    }

}
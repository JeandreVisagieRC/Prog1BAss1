public class Patient {//class and variable declarations
    private String patientID;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String medicalCondition;
    private PatientCategory category;

    public Patient(String patientID, String firstName, String lastName, int age, String gender, String medicalCondition, PatientCategory category) { //patient variable creation
        this.patientID = patientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.medicalCondition = medicalCondition;
        this.category = category;
    }

    //gets and sets
    public String getPatientID () {return patientID;}
    public String getLastName () { return lastName;}
    public PatientCategory getCategory () { return category;}

    public void setMedicalCondition(String medicalCondition) { this.medicalCondition = medicalCondition; } //set med. condition

    public String displayDetails() {//formatted detail display
        return String.format("ID: %s | Name: %s %s | Age: %d | Gender: %s | Condition: %s | Category: %s",
                patientID, firstName, lastName, age, gender, medicalCondition, category);
}}
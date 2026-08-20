public class Inpatient extends Patient {
    private String wardNumber;
    private String bedNumber;
    
    public Inpatient(String patientID, String firstName, String lastName, int age, String gender, String medicalCondition, String wardNumber) {//constructor for Inpatient class
        super(patientID, firstName, lastName, age, gender, medicalCondition, PatientCategory.INPATIENT);//calling parent class using super
        this.wardNumber = wardNumber;
        this.bedNumber = "Not Assigned";
    }

    public String getBedNumber() { return bedNumber; }
    public void setBedNumber(String bedNumber) { this.bedNumber = bedNumber; }

    @Override
    public String displayDetails() {
        return super.displayDetails() + String.format(" | Ward: %s | Bed: %s", wardNumber, bedNumber);
    }
}

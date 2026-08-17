import java.util.ArrayList;
import java.util.List;

public class HospitalManager {
    private List<Patient> patients;//list to store patients

    public HospitalManager() {
        this.patients = new ArrayList<>();
    }

    public boolean registerPatient(Patient patient) {//method to register patient
        if (searchPatient(patient.getPatientID()) != null) return false; // Prevent duplicates
        patients.add(patient);
        return true;
    }

    public Patient searchPatient(String id) {//method to search patient by ID
        for (Patient p : patients) {
            if (p.getPatientID().equals(id)) return p;
        }
        return null;//return null if not found
    }

    public boolean deletePatient(String id) {//method to delete patient by ID
        Patient p = searchPatient(id);
        if (p != null) {
            patients.remove(p);
            return true;
        }
        return false;
    }

    public List<Patient> getAllPatients() {//method to get all patients
        return patients;
    }

    private String[][] beds = new String[4][5];
    private String[][] occupants = new String[4][5]; // stores patient id

    // Call this in the constructor
    public void initializeBeds() {
        int bedCounter = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                beds[i][j] = String.format("B%02d", bedCounter++);
                occupants[i][j] = null; // null means available
            }
        }
    }

    public boolean allocateBed(String patientId) {
        Patient p = searchPatient(patientId);
        if (p == null || p.getCategory() != PatientCategory.INPATIENT) return false;
        
        Inpatient inpatient = (Inpatient) p;
        if (!inpatient.getBedNumber().equals("Not Assigned")) return false; // already has a bed

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (occupants[i][j] == null) {
                    occupants[i][j] = patientId;
                    inpatient.setBedNumber(beds[i][j]);
                    return true;
                }
            }
        }
        return false; // no beds available
    }

    public void releaseBed(String patientId) {
        Patient p = searchPatient(patientId);
        if (p != null && p instanceof Inpatient) {
            ((Inpatient) p).setBedNumber("Not Assigned");
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 5; j++) {
                    if (patientId.equals(occupants[i][j])) {
                        occupants[i][j] = null;
                        return;
                    }
                }
            }
        }
    }

    public void displayWardLayout() {//method to display ward layout
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(beds[i][j] + (occupants[i][j] == null ? "(Free) " : "(Occ)  "));//display bed status
            }
            System.out.println();
        }
    }

    public int getTotalOccupiedBeds() {//method to get total occupied beds
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (occupants[i][j] != null) count++;
            }
        }
        return count;
    }

    public void displayWardOccupancyPercentage() {//method to display ward occupancy percentage
        int occupied = getTotalOccupiedBeds();
        double percentage = ((double) occupied / 20) * 100;
        System.out.printf("Ward Occupancy: %.2f%%\n", percentage);
    }
}


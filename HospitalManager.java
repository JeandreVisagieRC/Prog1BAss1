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
}


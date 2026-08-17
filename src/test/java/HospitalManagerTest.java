import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HospitalManagerTest {
    private HospitalManager manager;

    @BeforeEach
    public void setUp() {
        manager = new HospitalManager();
        manager.initializeBeds();
    }

    @Test
    public void testRegisterAndPreventDuplicate() {
        Patient p1 = new Patient("P01", "Jane", "Doe", 25, "F", "Cold", PatientCategory.OUTPATIENT);
        assertTrue(manager.registerPatient(p1), "Patient should be registered successfully");

        Patient p2 = new Patient("P01", "John", "Smith", 40, "M", "Flu", PatientCategory.OUTPATIENT);
        assertFalse(manager.registerPatient(p2), "Duplicate Patient ID should be rejected");
    }

    @Test
    public void testSearchPatient() {
        Patient p1 = new Patient("P02", "Mark", "Taylor", 30, "M", "Fever", PatientCategory.OUTPATIENT);
        manager.registerPatient(p1);

        assertNotNull(manager.searchPatient("P02"), "Patient should be found");
        assertNull(manager.searchPatient("P99"), "Non-existent patient should return null");
    }

    @Test
    public void testDeletePatient() {
        Patient p1 = new Patient("P03", "Sarah", "Connor", 35, "F", "Checkup", PatientCategory.OUTPATIENT);
        manager.registerPatient(p1);

        assertTrue(manager.deletePatient("P03"), "Patient should be deleted");
        assertNull(manager.searchPatient("P03"), "Deleted patient should no longer exist");
    }

    @Test
    public void testAllocateAndReleaseBed() {
        Inpatient inP = new Inpatient("I01", "Alice", "Brown", 50, "F", "Surgery", "Ward 1");
        manager.registerPatient(inP);

        assertTrue(manager.allocateBed("I01"), "Bed allocation should succeed");
        assertNotEquals("Not Assigned", inP.getBedNumber(), "Bed number should be updated");

        manager.releaseBed("I01");
        assertEquals("Not Assigned", inP.getBedNumber(), "Bed should be released");
    }

    @Test
    public void testPreventAllocatingOccupiedBed() {
        Inpatient inP1 = new Inpatient("I02", "Tom", "Hardy", 45, "M", "Observation", "Ward 1");
        manager.registerPatient(inP1);

        assertTrue(manager.allocateBed("I02"));
        assertFalse(manager.allocateBed("I02"), "Patient already in a bed should not get another");
    }
}
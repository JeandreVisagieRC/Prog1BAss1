import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
        assertTrue(manager.registerPatient(p1));
        
        Patient p2 = new Patient("P01", "John", "Smith", 40, "M", "Flu", PatientCategory.OUTPATIENT);
        assertFalse(manager.registerPatient(p2)); // Should fail duplicate ID
    }

    @Test
    public void testAllocateAndReleaseBed() {
        Inpatient inP = new Inpatient("I01", "Alice", "Brown", 50, "F", "Surgery", "Ward 1");
        manager.registerPatient(inP);
        
        assertTrue(manager.allocateBed("I01"));
        assertNotEquals("Not Assigned", inP.getBedNumber());
        
        manager.releaseBed("I01");
        assertEquals("Not Assigned", inP.getBedNumber());
    }
}

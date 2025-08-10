package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.model.Patient;
import java.util.List;

public interface IPatientService {
    void registerPatient(Patient patient);
    List<Patient> listPatients();
    void updatePatient(Patient patient);
    void deletePatient(String id);
}
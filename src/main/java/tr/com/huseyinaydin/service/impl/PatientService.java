package tr.com.huseyinaydin.service.impl;

import tr.com.huseyinaydin.model.Patient;
import tr.com.huseyinaydin.repository.IPatientRepository;
import java.util.List;
import tr.com.huseyinaydin.service.IPatientService;

public class PatientService implements IPatientService {

    private final IPatientRepository patientRepository;

    public PatientService(IPatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void registerPatient(Patient patient) {
        patientRepository.add(patient);
    }

    @Override
    public List<Patient> listPatients() {
        return patientRepository.getAll();
    }

    @Override
    public void updatePatient(Patient patient) {
        patientRepository.update(patient);
    }

    @Override
    public void deletePatient(String id) {
        patientRepository.delete(id);
    }
}
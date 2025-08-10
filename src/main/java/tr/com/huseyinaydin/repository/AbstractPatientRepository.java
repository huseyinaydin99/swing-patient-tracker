package tr.com.huseyinaydin.repository;

import tr.com.huseyinaydin.model.Patient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractPatientRepository implements IPatientRepository {
    protected Map<String, Patient> patients = new HashMap<>();

    @Override
    public void add(Patient patient) {
        patients.put(patient.getId(), patient);
    }

    @Override
    public Optional<Patient> getById(String id) {
        return Optional.ofNullable(patients.get(id));
    }

    @Override
    public List<Patient> getAll() {
        return patients.values().stream().toList();
    }

    @Override
    public void update(Patient patient) {
        patients.put(patient.getId(), patient);
    }

    @Override
    public void delete(String id) {
        patients.remove(id);
    }
}
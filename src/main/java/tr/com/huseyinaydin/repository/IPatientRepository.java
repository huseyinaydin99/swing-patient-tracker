package tr.com.huseyinaydin.repository;

import tr.com.huseyinaydin.model.Patient;
import java.util.List;
import java.util.Optional;

public interface IPatientRepository {
    void add(Patient patient);
    Optional<Patient> getById(String id);
    List<Patient> getAll();
    void update(Patient patient);
    void delete(String id);
}
package tr.com.huseyinaydin.repository.impl;

import tr.com.huseyinaydin.model.Patient;
import tr.com.huseyinaydin.utils.FileUtils;
import java.util.List;
import tr.com.huseyinaydin.repository.AbstractPatientRepository;

public class FilePatientRepository extends AbstractPatientRepository {

    private final String filePath;

    public FilePatientRepository(String filePath) {
        this.filePath = filePath;
        loadFromFile();
    }

    private void loadFromFile() {
        List<String> lines = FileUtils.readFile(filePath);
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 6) {
                Patient patient = new Patient(parts[0], parts[1], parts[2],
                        Integer.parseInt(parts[3]), parts[4], parts[5]);
                add(patient);
            }
        }
    }

    private void saveToFile() {
        List<String> lines = patients.values().stream()
                .map(p -> String.join(",", p.getId(), p.getFirstName(), p.getLastName(),
                        String.valueOf(p.getAge()), p.getPhone(), p.getEmail()))
                .toList();
        FileUtils.writeFile(filePath, lines);
    }

    @Override
    public void add(Patient patient) {
        super.add(patient);
        saveToFile();
    }

    @Override
    public void update(Patient patient) {
        super.update(patient);
        saveToFile();
    }

    @Override
    public void delete(String id) {
        super.delete(id);
        saveToFile();
    }
}
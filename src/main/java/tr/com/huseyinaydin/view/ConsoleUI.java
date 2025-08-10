package tr.com.huseyinaydin.view;

import tr.com.huseyinaydin.model.Patient;
import tr.com.huseyinaydin.service.IPatientService;

import java.util.Scanner;
import java.util.UUID;

public class ConsoleUI {
    private final IPatientService patientService;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleUI(IPatientService patientService) {
        this.patientService = patientService;
    }

    public void start() {
        while (true) {
            System.out.println("\n--- Hasta Kayıt Sistemi ---");
            System.out.println("1. Hasta Kaydet");
            System.out.println("2. Hasta Listele");
            System.out.println("3. Hasta Güncelle");
            System.out.println("4. Hasta Sil");
            System.out.println("5. Çıkış");
            System.out.print("Seçim: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addPatient();
                case "2" -> listPatients();
                case "3" -> updatePatient();
                case "4" -> deletePatient();
                case "5" -> { return; }
                default -> System.out.println("Geçersiz seçim!");
            }
        }
    }

    private void addPatient() {
        String id = UUID.randomUUID().toString();
        System.out.print("Ad: ");
        String firstName = scanner.nextLine();
        System.out.print("Soyad: ");
        String lastName = scanner.nextLine();
        System.out.print("Yaş: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Telefon: ");
        String phone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        Patient patient = new Patient(id, firstName, lastName, age, phone, email);
        patientService.registerPatient(patient);
        System.out.println("Hasta kaydedildi.");
    }

    private void listPatients() {
        patientService.listPatients().forEach(System.out::println);
    }

    private void updatePatient() {
        System.out.print("Güncellenecek hasta ID: ");
        String id = scanner.nextLine();
        Patient existing = patientService.listPatients()
                .stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        if (existing == null) {
            System.out.println("Hasta bulunamadı!");
            return;
        }

        System.out.print("Yeni Ad: ");
        existing.setFirstName(scanner.nextLine());
        System.out.print("Yeni Soyad: ");
        existing.setLastName(scanner.nextLine());
        System.out.print("Yeni Yaş: ");
        existing.setAge(Integer.parseInt(scanner.nextLine()));
        System.out.print("Yeni Telefon: ");
        existing.setPhone(scanner.nextLine());
        System.out.print("Yeni Email: ");
        existing.setEmail(scanner.nextLine());

        patientService.updatePatient(existing);
        System.out.println("Hasta bilgileri güncellendi.");
    }

    private void deletePatient() {
        System.out.print("Silinecek hasta ID: ");
        String id = scanner.nextLine();
        patientService.deletePatient(id);
        System.out.println("Hasta silindi.");
    }
}
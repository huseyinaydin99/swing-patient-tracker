package tr.com.huseyinaydin.model;

public class Patient {
    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private String phone;
    private String email;

    public Patient() {
    }

    public Patient(String id, String firstName, String lastName, int age, String phone, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phone = phone;
        this.email = email;
    }

    public String getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setAge(int age) { this.age = age; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return id + " | " + firstName + " " + lastName + " | " + age + " | " + phone + " | " + email;
    }
}
import java.util.Date;

public class User {
    private static int currentId = 1;

    private int userId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private int gender;
    private Date birthDate;
    private String address;
    private String permanentAddress;
    private String occupation;
    private String maritalStatus;
    private double income;
    private String id;
    private String imagePath;
    private boolean isAdmin;
    private String age;
    public User(String firstName, String lastName, String age, int gender, Date birthDate, String address, String permanentAddress,
                String occupation, String maritalStatus, double income, String id, String imagePath) {
        this.userId = currentId++;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.birthDate = birthDate;
        this.address = address;
        this.permanentAddress = permanentAddress;
        this.occupation = occupation;
        this.maritalStatus = maritalStatus;
        this.income = income;
        this.id = id;
        this.imagePath = imagePath;
        this.isAdmin = isAdmin;
    }
    // Getter và Setter cho tất cả các thuộc tính
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public boolean isAdmin() {
        return isAdmin;
    }
    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + " - " + gender + " - " + birthDate + " - " + address + " - " +
                permanentAddress + " - " + occupation + " - " + maritalStatus + " - " + income + " - " + id;
    }
}


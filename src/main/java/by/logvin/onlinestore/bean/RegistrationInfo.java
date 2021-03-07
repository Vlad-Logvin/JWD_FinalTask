package by.logvin.onlinestore.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class RegistrationInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;
    private String password;
    private String name;
    private String surname;
    private Date dateOfBirth;


    public RegistrationInfo() {
    }

    public RegistrationInfo(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationInfo that = (RegistrationInfo) o;
        return Objects.equals(email, that.email) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }

    @Override
    public String toString() {
        return "RegistrationInfo{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

package Blockbuster.Model;

import jakarta.validation.constraints.Size;

public class UserRegistrationRequest {
    private String email;
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters.")
    private String password;
    private String firstName;

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

    public String getfirstName() {
        return firstName;
    }

    public void setName(String firstName) {
        this.firstName = firstName;
    }
}

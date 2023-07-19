package Blockbuster.DTO;

import Blockbuster.Model.Customer;
import jakarta.validation.constraints.*;

import java.util.Objects;

public class CustomerDto {
    @NotBlank
    @NotEmpty
    private String fName;
    @NotBlank
    @NotEmpty
    private String lName;
    @NotBlank
    @NotEmpty
    private String username;
    @Email
    @NotBlank
    @NotEmpty
    private String email;
    @NotBlank
    @NotEmpty
    @Min(value=4)
    @Max(value=9)
    private int phone;

    public CustomerDto(String fName, String lName, String username, String email, int phone) {
        this.fName = fName;
        this.lName = lName;
        this.username = username;
        this.email = email;
        this.phone = phone;
    }

    public CustomerDto(){

    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDto that = (CustomerDto) o;
        return Objects.equals(fName, that.fName) &&
                Objects.equals(lName,that.lName) &&
                Objects.equals(username,that.username)&&
                Objects.equals(phone, that.phone) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fName,lName,username, phone, email);
    }

}

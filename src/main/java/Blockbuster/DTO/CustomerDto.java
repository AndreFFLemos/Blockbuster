package Blockbuster.DTO;

import Blockbuster.Model.Customer;

public class CustomerDto {
    private String firstN;
    private String lastN;
    private String username;
    private String email;
    private int phone;

    public CustomerDto(String firstN, String lastN, String username,int phone, String email) {
        this.firstN = firstN;
        this.lastN = lastN;
        this.username = username;
        this.phone = phone;
        this.email = email;
    }
    public CustomerDto(){

    }

    public String getFirstN() {
        return firstN;
    }

    public void setFirstN(String firstN) {
        this.firstN = firstN;
    }

    public String getLastN() {
        return lastN;
    }

    public void setLastN(String lastN) {
        this.lastN = lastN;
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
}

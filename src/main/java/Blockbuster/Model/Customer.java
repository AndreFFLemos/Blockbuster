package Blockbuster.Model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Customer {

    private int id;
    private String name;
    private String address;
    private int contact;
    private String email;
    private Map<Integer, Rental> customerRentals;
    private boolean customerHasFines=false;
    private int numberOfMoviesRented = 0;
    private int numberOfMoviesDelivered = 0;


    public Customer(int id,String name, String address, int contact, String email) {
        this.id=id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.customerHasFines=customerHasFines;
        this.customerRentals = customerRentals;
        this.email=email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }
    public Map<Integer,Rental> getCustomerRentals() {
        return customerRentals;
    }

    public void setCustomerRentals(Map <Integer,Rental> customerRentals) {
        this.customerRentals = customerRentals;
    }

    public int getNumberOfMoviesRented() {
        return numberOfMoviesRented;
    }

    public void setNumberOfMoviesRented(int numberOfMoviesRented) {
        this.numberOfMoviesRented = numberOfMoviesRented;
    }

    public int getNumberOfMoviesDelivered() {
        return numberOfMoviesDelivered;
    }

    public void setNumberOfMoviesDelivered(int numberOfMoviesDelivered) {
        this.numberOfMoviesDelivered = numberOfMoviesDelivered;
    }

    public boolean getCustomerHasFines() {
        return customerHasFines;
    }

    public void setCustomerHasFines(boolean customerHasFines) {
        this.customerHasFines = customerHasFines;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact=" + contact +
                ", email='" + email + '\'' +
                ", customerRentals=" + customerRentals +
                ", customerHasFines=" + customerHasFines +
                ", numberOfMoviesRented=" + numberOfMoviesRented +
                ", numberOfMoviesDelivered=" + numberOfMoviesDelivered +
                '}';
    }
}
package Blockbuster.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Data //Lombok takes care of the getters and setters
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="fname")
    private String fName;
    @Column(name="lname")
    private String lName;
    @Column(name="username")
    private String username;
    @Column(name="hashedpass")
    private String password;
    @Column(name="phone")
    private String phone;
    @Column(name="email")
    private String email;
    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List <Rental> rentals;

    public Customer(int id, String fName, String lName, String username, String password, String phone, String email) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }
}
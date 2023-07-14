package Blockbuster.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


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
    @Size(min = 8, max = 20)
    private String password;
    @Column(name="phone")
    @Digits(integer = 5,fraction = 0,message = "The number has to have 5 digits")
    private int phone;
    @Column(name="email")
    @NotEmpty(message = "This field should not be empty")
    @Email(message = "Wrong format")
    private String email;
    @ManyToMany
    @JoinTable(
            name = "customer_movie",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private List <Movie> watchedMovies= new ArrayList<>();//arraylist use less memory and is quicker with large lists

    public Customer(int id, String fName, String lName, String username, String password, int phone, String email) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }
}
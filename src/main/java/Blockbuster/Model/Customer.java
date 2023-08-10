package Blockbuster.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Builder
@Data //Lombok takes care of the getters and setters
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="firstname",nullable = false)
    private String firstName;
    @Column(name="lastname",nullable = false)
    private String lastName;
    @Column(name="username",nullable = false,unique = true)
    private String username;
    @Column(name="hashedpass",nullable = false)
    @Size(min = 8, max = 20)
    private String password;
    @Column(name="phone")
    @Digits(integer = 5,fraction = 0,message = "The number has to have 5 digits")
    private int phone;
    @Column(name="email", unique = true)
    @NotEmpty(message = "This field should not be empty")
    @Email(message = "Wrong format")
    private String email;
    @ManyToMany
    @JoinTable(
            name = "customer_movie",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    @ToString.Exclude //the tostring was causing a recursive issue between the customer and movie objects
    @Builder.Default
    private List <Movie> watchedMovies= new ArrayList<>();//arraylist use less memory and is quicker with large lists

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
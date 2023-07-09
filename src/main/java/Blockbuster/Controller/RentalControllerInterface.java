package Blockbuster.Controller;

import Blockbuster.Model.Customer;
import Blockbuster.Model.Rental;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RentalControllerInterface {

    ResponseEntity<Rental> createRental(Rental rental);
    void deleteRental( Rental actualRental);
    ResponseEntity <Rental> findRental(Rental actualRental);
    ResponseEntity <List<Rental>> findAllRentals();

}

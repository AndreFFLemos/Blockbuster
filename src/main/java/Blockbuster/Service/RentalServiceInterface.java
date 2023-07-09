package Blockbuster.Service;

import Blockbuster.DTO.RentalDto;
import Blockbuster.Model.Customer;
import Blockbuster.Model.Rental;

import java.util.List;
import java.util.Optional;

public interface RentalServiceInterface {

    Optional<RentalDto> createRental(RentalDto rentalDto);
    void deleteRental(RentalDto rentalDto);
    Optional<RentalDto> findRentalById(int id);
    List<RentalDto> findAll();
    List<Rental> findRentalByCustomer(Customer customer);
}

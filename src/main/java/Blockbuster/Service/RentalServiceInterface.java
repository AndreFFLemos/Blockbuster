package Blockbuster.Service;

import Blockbuster.Model.Customer;
import Blockbuster.Model.Rental;

import java.util.List;

public interface RentalServiceInterface {

    public Rental createRental(Rental rental);
    public void deleteRentalById(int id);
    public Rental findRentalById(int id);
    List<Rental> findAll();
}

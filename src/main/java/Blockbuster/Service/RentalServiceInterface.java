package Blockbuster.Service;

import Blockbuster.Model.Customer;
import Blockbuster.Model.Rental;

public interface RentalServiceInterface {

    public Rental createRental(Rental rental);
    public void deleteRentalById(Rental rental);
    public Rental findRentalById(int id);

}

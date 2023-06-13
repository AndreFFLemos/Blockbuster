package Blockbuster.Controller;

import Blockbuster.Model.Customer;
import Blockbuster.Model.Rental;

public interface RentalControllerInterface {

    public Rental createRental( Rental rental);
    public String deleteRental( Rental actualRental);
    public Rental findRental(Rental actualRental);

}

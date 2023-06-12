package Blockbuster.Controller;

import Blockbuster.Model.Customer;
import Blockbuster.Model.Rental;

public interface RentalControllerInterface {

    public double createRental(Customer customer, Rental rental);
    public String deleteRental(Customer customer, Rental actualRental);
    public String updateRental(Customer customer,Rental actualRental);
    public Rental findRental(Customer customer, Rental actualRental);
    public double rentalDelivery(Customer customer, Rental actualRental);
}

package Blockbuster.Service;

import Blockbuster.Model.Customer;
import Blockbuster.Model.Rental;

public interface RentalServiceInterface {

    public double createRental(Customer customer, Rental rental);
    public String deleteRental(Customer customer, Rental actualRental);
    public String updateRental(Customer customer,Rental actualRental);
    public Rental findRental(Customer customer, Rental actualRental);
    public double rentalDelivery(Customer customer, Rental actualRental);
    public double rentalFine(Rental actualRental);
    public int daysBetweenRentalAndDelivery( Rental actualRental);
}

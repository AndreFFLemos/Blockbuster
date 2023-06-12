package Blockbuster.Controller;

import Blockbuster.Model.Customer;
import Blockbuster.Model.Rental;
import Blockbuster.Service.RentalServiceInterface;

public class RentalController implements RentalControllerInterface {

    private Customer customer;
    private RentalServiceInterface rsi;


    @Override
    public double createRental(Customer customer, Rental rental) {
        return 0;
    }

    @Override
    public String deleteRental(Customer customer, Rental actualRental) {
        return null;
    }

    @Override
    public String updateRental(Customer customer, Rental actualRental) {
        return null;
    }

    @Override
    public Rental findRental(Customer customer, Rental actualRental) {
        return null;
    }

    @Override
    public double rentalDelivery(Customer customer, Rental actualRental) {
        return 0;
    }
}

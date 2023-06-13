package Blockbuster.Controller;

import Blockbuster.Model.Customer;
import Blockbuster.Model.Rental;
import Blockbuster.Service.RentalServiceInterface;
import org.springframework.stereotype.Controller;

@Controller
public class RentalController implements RentalControllerInterface {

    private Customer customer;
    private RentalServiceInterface rsi;

    @Override
    public Rental createRental(Rental rental) {
        return null;
    }

    @Override
    public String deleteRental(Rental actualRental) {
        return null;
    }

    @Override
    public Rental findRental(Rental actualRental) {
        return null;
    }
}
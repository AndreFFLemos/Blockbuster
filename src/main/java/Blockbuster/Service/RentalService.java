package Blockbuster.Service;

import Blockbuster.Controller.RentalController;
import Blockbuster.Model.Customer;
import Blockbuster.Model.Rental;
import Blockbuster.Repository.CustomerRepositoryInterface;
import Blockbuster.Repository.MovieRepositoryInterface;
import Blockbuster.Repository.RentalRepositoryInterface;

import java.time.temporal.ChronoUnit;

public class RentalService implements RentalServiceInterface {

    private RentalController rc;
    private RentalRepositoryInterface rri;
    private CustomerRepositoryInterface cri;
    private MovieRepositoryInterface mri;

    public double createRental(Customer customer, Rental rental) {
        rri.createRental(customer, rental);
        double cost = mri.getMoviePrice(rental.getMovie());

        return cost;
    }

    public double rentalDelivery(Customer customer, Rental actualRental) {
        if (cri.findCustomer(customer) != null && rri.findRental(customer, actualRental) != null) {
            rri.rentalDelivery(customer, actualRental);
        }
        if (rentalFine(actualRental) == 0.0) {
            return 0.0;
        }
        return rentalFine(actualRental);
    }

    public int daysBetweenRentalAndDelivery(Rental actualRental) {
        if (!actualRental.getEnd().isAfter(actualRental.getStart())) {
            throw new IllegalArgumentException("Return date before rental start");
        }
        int days = (int) ChronoUnit.DAYS.between(actualRental.getEnd(), actualRental.getStart());

        return days;
    }

    public double rentalFine(Rental actualRental) {
        int days = daysBetweenRentalAndDelivery(actualRental);
        if (days > 1) {
            double amount = mri.getMoviePrice(actualRental.getMovie()) * days;
            return amount;
        }
        return 0.0;
    }

    public String deleteRental(Customer customer, Rental actualRental) {
        rri.deleteRental(customer, actualRental);
        return "true";
    }

    public String updateRental(Customer customer,Rental actualRental){
        rri.updateRental(customer,actualRental);
        return "true";
    }

    public Rental findRental(Customer customer, Rental actualRental){
        return rri.findRental(customer,actualRental);

    }
}
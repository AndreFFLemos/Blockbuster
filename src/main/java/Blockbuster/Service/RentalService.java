package Blockbuster.Service;

import Blockbuster.Model.Rental;
import Blockbuster.Repository.RentalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
public class RentalService implements RentalServiceInterface {

    private RentalRepository rr;

    @Override
    public Rental createRental(Rental rental) {
        return rr.save(rental);
    }

    @Override
    public void deleteRentalById(Rental rental) {
        Rental existingRental=rr.findById(rental.getId()).orElseThrow(()-> new NoSuchElementException("The rental doesn't exist"));
            rr.deleteById(existingRental.getId());
    }

    @Override
    public Rental findRentalById(int id) {
        return rr.findById(id).orElseThrow(()->new NoSuchElementException("Rental doesn't exist"));
    }
}
package Blockbuster.Repository;

import Blockbuster.Model.Customer;
import Blockbuster.Model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {

    List<Rental> findRentalByCustomer(Customer customer);

}

package Blockbuster.Repository;

import Blockbuster.Model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RentalRepository extends JpaRepository<Rental, Integer> {
}

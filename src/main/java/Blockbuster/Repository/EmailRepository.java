package Blockbuster.Repository;

import Blockbuster.Model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email,Integer> {

}

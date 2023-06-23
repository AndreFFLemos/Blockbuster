package Blockbuster.Service;

import Blockbuster.Model.Rental;
import Blockbuster.Repository.RentalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.ComponentScan;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ComponentScan(basePackages = "Blockbuster")
class RentalServiceTest {

    private Rental rental;
    @Mock
    private RentalRepository rr;
    @InjectMocks
    private RentalService rs;

    @BeforeEach
    void setup(){
        rental=new Rental();
    }
    @Test
    void createRental() {

        when(rr.save(rental)).thenReturn(rental);
        Rental mockedR= rs.createRental(rental);

        assertEquals(mockedR,rental);
        verify(rr).save(any(Rental.class));
    }

    @Test
    void deleteRentalById() {
        rental.setId(1);
        when(rr.findById(anyInt())).thenReturn(Optional.of(rental));
        doNothing().when(rr).deleteById(anyInt());

        rs.deleteRentalById(1);
        verify(rr).deleteById(anyInt());
    }

    @Test
    void findRentalById() {
        rental.setId(1);
        when(rr.findById(1)).thenReturn(Optional.of(rental));

        Rental mockedR= rs.findRentalById(1);
        assertEquals(mockedR,rental);


        verify(rr).findById(anyInt());
    }

    @Test
    void findAllRentals(){
        Rental r1=new Rental();
        List<Rental> rentals= new LinkedList<>();
        rentals.add(rental);
        rentals.add(r1);

        when(rr.findAll()).thenReturn(rentals);
        List<Rental> mockedR= rs.findAll();

        assertEquals(2,mockedR.size());
        verify(rr).findAll();
    }
}
package Blockbuster.Service;

import Blockbuster.DTO.RentalDto;
import Blockbuster.Model.Customer;
import Blockbuster.Model.Movie;
import Blockbuster.Model.Rental;
import Blockbuster.Repository.RentalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RentalServiceTest {

    private Rental rental;
    private Customer customer;
    private Movie movie;
    private LocalDate rentalDate;
    ModelMapper modelMapper;
    List<Rental> rentals;
    RentalDto rentalDto;
    RentalDto rentalDtoSaved;

    @Mock
    private RentalRepository rr;
    @InjectMocks
    private RentalService rs;

    @BeforeEach
    void setup(){
        customer=new Customer();
        movie=new Movie();
        rentalDate=LocalDate.now();
        rental=new Rental();
        rental.setId(1);
        rental.setCustomer(customer);
        rental.setMovie(movie);
        rental.setRentalDate(rentalDate);
        rentals=new LinkedList<>();
        rentals.add(rental);

        rentalDto=new RentalDto();
        modelMapper=new ModelMapper();
        rentalDtoSaved= modelMapper.map(rental,RentalDto.class);
    }
    @Test
    void createRentalTest() {

        when(rr.save(rental)).thenReturn(rental);
        Optional<RentalDto> mockedR= rs.createRental(rentalDto);

        assertEquals(mockedR,rentalDtoSaved);
        verify(rr).save(rental);
    }

    @Test
    void deleteRentalByIdTest() {
        when(rr.findById(1)).thenReturn(Optional.of(rental));
        doNothing().when(rr).deleteById(1);

        rs.deleteRental(rentalDto);
        verify(rr).deleteById(1);
    }

    @Test
    void findRentalByIdTest() {

        when(rr.findById(1)).thenReturn(Optional.of(rental));
        Optional<RentalDto> mockedR= rs.findRentalById(1);

        assertEquals(mockedR,rentalDtoSaved);

        verify(rr).findById(1);
    }

    @Test
    void findAllRentalsTest(){
        Rental r1=new Rental();
        rentals.add(rental);
        rentals.add(r1);

        when(rr.findAll()).thenReturn(rentals);
        List<RentalDto> mockedR= rs.findAll();

        assertEquals(2,mockedR.size());
        verify(rr).findAll();
    }


}
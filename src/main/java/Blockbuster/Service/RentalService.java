package Blockbuster.Service;

import Blockbuster.DTO.RentalDto;
import Blockbuster.Model.Customer;
import Blockbuster.Model.Rental;
import Blockbuster.Repository.RentalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RentalService implements RentalServiceInterface {

    private RentalRepository rr;
    private ModelMapper modelMapper;

    @Autowired
    public RentalService(RentalRepository rr, ModelMapper modelMapper) {
        this.rr = rr;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<RentalDto> createRental(RentalDto rentalDto) {
        Rental rental= modelMapper.map(rentalDto, Rental.class);
        rental=rr.save(rental);

        return Optional.of(modelMapper.map(rental,RentalDto.class));
    }

    @Override
    public void deleteRental(RentalDto rentalDto) {
        Rental rental= modelMapper.map(rentalDto, Rental.class);
        rr.delete(rental);
    }

    @Override
    public Optional<RentalDto> findRentalById(int id) {
        Optional <Rental> rental= rr.findById(id);
        if (rental.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(modelMapper.map(rental,RentalDto.class));

    }

    @Override
    public List<RentalDto> findAll() {

        List <Rental> rentals= rr.findAll();
        if (rentals.isEmpty()){
            return Collections.emptyList();
        }
        return rentals.stream()
                .map(rental -> modelMapper.map(rental,RentalDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public List<Rental> findRentalByCustomer(Customer customer) {
        return null;
    }
}
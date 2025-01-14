package com.scaler.bookmyshowjuly24.services;

import com.scaler.bookmyshowjuly24.models.*;
import com.scaler.bookmyshowjuly24.repositories.BookingRepository;
import com.scaler.bookmyshowjuly24.repositories.ShowRepository;
import com.scaler.bookmyshowjuly24.repositories.ShowSeatRepository;
import com.scaler.bookmyshowjuly24.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService{
    private UserRepository userRepository;
    private ShowRepository showRepository;
    private PriceCalculationService priceCalculationService;
    private  ShowSeatRepository showSeatRepository;
    private BookingRepository bookingRepository;
    public BookingServiceImpl (UserRepository userRepository, ShowRepository showRepository, ShowSeatRepository showSeatRepository,
                               PriceCalculationService  priceCalculationService,BookingRepository bookingRepository ){
        this.userRepository=userRepository;
        this.showRepository=showRepository;
        this.showSeatRepository=showSeatRepository;
        this.priceCalculationService=priceCalculationService;
        this.bookingRepository=bookingRepository;
    }
    @Override
    @Transactional
    public Booking createBooking(Long userId, Long showId, List<Long> showSeatsIds) {
        /*
          1.Get the user details from userId from the DB.
          2.Get the show details from showId from the DB.
          3.Get the showSeat details from showSeatIds from the DB.
          4.Check if all the showSeats are available or not.
          5.If not available throw an exception.
          6. else, change the show seatStatus to BLOCKED.
          7.save the changes to DB
          8.Create the booking object with pending status and save to DB
          9.Move to the Payment Gateway

         */

        //1.Get the user details from userId from the DB.
        Optional<User> optionalUser =userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new RuntimeException("Invalid UserId" + userId);
        }
       User user= optionalUser.get();

        //2.Get the show details from showId from the DB.
        Optional<Show> optionalShow=showRepository.findById(showId);
        if(optionalShow.isEmpty()){
            throw new RuntimeException("Invalid showId" + showId);
        }

        // 3.Get the showSeat details from showSeatIds from the DB.
        List<ShowSeat>showSeats=showSeatRepository.findAllById(showSeatsIds);

        //4.Check if all the showSeats are available or not.
        //5.If not available throw an exception.
        for(ShowSeat showSeat:showSeats){
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)){
              throw new RuntimeException("show seat Not AVAILABLE" );
            }
        }
      //  6. else, change the show seatStatus to BLOCKED.
        // 7.save the changes to DB
        for(ShowSeat showSeat:showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            showSeatRepository.save(showSeat);
        }

        //  8.Create the booking object with pending status and save to DB
        Booking booking=new Booking();
        booking.setUser(user);
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setShowSeats(showSeats);
        booking.setAmount(priceCalculationService.calculateAmount(showSeats));


        return bookingRepository.save(booking);
    }
}

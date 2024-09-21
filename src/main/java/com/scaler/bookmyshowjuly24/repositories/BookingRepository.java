package com.scaler.bookmyshowjuly24.repositories;

import com.scaler.bookmyshowjuly24.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    @Override
  Booking save(Booking booking);
}

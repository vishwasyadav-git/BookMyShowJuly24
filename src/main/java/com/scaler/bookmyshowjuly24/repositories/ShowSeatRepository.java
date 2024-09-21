package com.scaler.bookmyshowjuly24.repositories;

import com.scaler.bookmyshowjuly24.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShowSeatRepository extends JpaRepository<ShowSeat,Long> {
    List<ShowSeat> findAllById(Iterable<Long> showSeatIds);

    @Override
   ShowSeat save(ShowSeat showSeat);
    //Update+insert=>upsert
}

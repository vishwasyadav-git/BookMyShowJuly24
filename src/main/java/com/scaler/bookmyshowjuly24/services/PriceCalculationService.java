package com.scaler.bookmyshowjuly24.services;

import com.scaler.bookmyshowjuly24.models.Show;
import com.scaler.bookmyshowjuly24.models.ShowSeat;
import com.scaler.bookmyshowjuly24.models.ShowSeatType;
import com.scaler.bookmyshowjuly24.repositories.ShowSeatTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculationService {
    private ShowSeatTypeRepository showSeatTypeRepository;
    public PriceCalculationService(ShowSeatTypeRepository showSeatTypeRepository){
        this.showSeatTypeRepository=showSeatTypeRepository;
    }
    public int  calculateAmount(List<ShowSeat> showSeats){
        Show show =showSeats.get(0).getShow();
        List<ShowSeatType> showSeatTypes=showSeatTypeRepository.findAllByShow(show);
        int amount=0;
        for(ShowSeatType showSeatType: showSeatTypes){
            for(ShowSeat showSeat: showSeats){
                if(showSeatType.getSeatType().equals(showSeat.getSeat())){
                    amount+=showSeatType.getPrice();

                }
            }
        }
        return amount;
    }
}

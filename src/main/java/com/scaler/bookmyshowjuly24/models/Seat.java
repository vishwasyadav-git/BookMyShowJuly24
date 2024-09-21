package com.scaler.bookmyshowjuly24.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel{
    private String seatName;
    private int rowNum;
    private int colNum;

    @ManyToOne
    private  SeatType seatType;

}

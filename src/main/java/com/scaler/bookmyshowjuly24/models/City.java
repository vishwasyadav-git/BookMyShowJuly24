package com.scaler.bookmyshowjuly24.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class City extends BaseModel{

    private String name;
    private String pinCode;

//    @OneToMany
//    private List<Theatre> theatres;

}

package com.scaler.bookmyshowjuly24.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity(name = "shows")
public class Show extends BaseModel{
    @ManyToOne
    private Movie movie;
    private Date startTime;
    private Date endTime;
    @ManyToOne
    private Screen screens;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> features;
    private String language;
    private  ShowStatus showStatus;
}

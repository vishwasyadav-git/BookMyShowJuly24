package com.scaler.bookmyshowjuly24.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //Auto incremented id
    private long Id;

    @CreatedDate
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @LastModifiedDate
    @Temporal(TemporalType.DATE)
    private Date lastModifiedAt;

}
//ORM
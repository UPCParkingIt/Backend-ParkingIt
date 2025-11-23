package com.parkingit.reservations.domain.model.aggregates;

import com.parkingit.iam.domain.model.aggregates.User;
import com.parkingit.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Reservation extends AuditableAbstractAggregateRoot<Reservation> {
    @NotNull
    private Date reservationDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private String location;

    private Integer hours;

    public Reservation() {}

    public Reservation(Date reservationDate, User user, String location, Integer hours) {
        this.reservationDate = reservationDate;
        this.user = user;
        this.location = location;
        this.hours = hours;
    }
}

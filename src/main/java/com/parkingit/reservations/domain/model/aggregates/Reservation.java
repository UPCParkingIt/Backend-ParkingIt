package com.parkingit.reservations.domain.model.aggregates;

import com.parkingit.iam.domain.model.aggregates.User;
import com.parkingit.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
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

    @Column(unique = true, nullable = false, length = 6)
    private String accessCode;

    @Column(nullable = false)
    private Boolean isActive = true;

    @Column(nullable = false)
    private Date expirationDate;

    private Integer hours;

    public Reservation() {}

    public Reservation(Date reservationDate, User user, String location, Integer hours) {
        this.reservationDate = reservationDate;
        this.user = user;
        this.location = location;
        this.hours = hours;
        this.isActive = true;
        this.accessCode = generateAccessCode();
        this.expirationDate = calculateExpirationDate(reservationDate, hours);
    }

    private String generateAccessCode() {
        return String.format("%06d", (int)(Math.random() * 1000000));
    }

    private Date calculateExpirationDate(Date startDate, Integer hours) {
        return new Date(startDate.getTime() + (hours * 60 * 60 * 1000));
    }
}

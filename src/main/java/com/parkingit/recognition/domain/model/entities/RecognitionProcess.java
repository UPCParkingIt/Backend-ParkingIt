package com.parkingit.recognition.domain.model.entities;

import com.parkingit.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.parkingit.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RecognitionProcess extends AuditableAbstractAggregateRoot<RecognitionProcess> {
    private Boolean isActive;
    private String urlIOT;

    public RecognitionProcess() {
        this.isActive = false;
    }

    public void startProcess() {
        this.isActive = true;
    }

    public void stopProcess() {
        this.isActive = false;
    }
}

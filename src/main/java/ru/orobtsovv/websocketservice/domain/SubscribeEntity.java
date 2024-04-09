package ru.orobtsovv.websocketservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(SubscribeId.class)
public class SubscribeEntity {
    @Id
    @Column(name = "from_id")
    private int from;

    @Id
    @Column(name = "to_id")
    private int to;
}

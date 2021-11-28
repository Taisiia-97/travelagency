package com.taisiia.travelagency.domain.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String confirmationToken;
    private LocalDateTime createdDate;
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private User user;

    public ConfirmationToken(User user) {
        this.user = user;
        createdDate = LocalDateTime.now();
        confirmationToken = UUID.randomUUID().toString();
    }

}

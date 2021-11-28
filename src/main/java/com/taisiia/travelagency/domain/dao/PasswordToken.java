package com.taisiia.travelagency.domain.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PasswordToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String passwordToken;
    private LocalDateTime createdDate;
    private LocalDateTime expirationDate;
    private boolean isActive;
    @ManyToOne
    private User user;

    public PasswordToken(User user) {
        this.user = user;
        this.passwordToken = UUID.randomUUID().toString();
        this.isActive = true;
        this.createdDate = LocalDateTime.now();
        this.expirationDate = createdDate.plusWeeks(1);
    }

}

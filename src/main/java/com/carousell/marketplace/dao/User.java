package com.carousell.marketplace.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

/**
 * @author faizanmalik
 * creation date 3/4/20
 */
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private Date createdAt;

    public User(String username) {
        this.username = username;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = Optional.ofNullable(this.createdAt).orElse(new Date());
    }
}

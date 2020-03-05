package com.carousell.marketplace.dao;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

/**
 * @author faizanmalik
 * creation date 3/4/20
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "category")
@EntityListeners(AuditingEntityListener.class)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date createdAt;

    public Category(String name) {
        this.name = name;
    }
    @PrePersist
    public void prePersist() {
        this.createdAt = Optional.ofNullable(this.createdAt).orElse(new Date());
    }

}

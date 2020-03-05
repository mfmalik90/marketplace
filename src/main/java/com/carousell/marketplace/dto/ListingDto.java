package com.carousell.marketplace.dto;

import com.carousell.marketplace.dao.Listing;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

import static com.carousell.marketplace.utils.Constants.LISTING_FORMATTER;

/**
 * @author faizanmalik
 * creation date 3/5/20
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListingDto {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private String username;
    private String category;
    private Date createdAt;

    public static ListingDto fromListing(Listing listing) {
        return ListingDto.builder()
            .id(listing.getId())
            .title(listing.getTitle())
            .description(listing.getDescription())
            .price(listing.getPrice())
            .username(listing.getUser().getUsername())
            .category(listing.getCategory().getName())
            .createdAt(listing.getCreatedAt())
            .build();
    }

    @Override
    public String toString(){
        return String.format(
            LISTING_FORMATTER,
            this.getTitle(),
            this.getDescription(),
            this.getPrice(),
            this.getCreatedAt(),
            this.getCategory(),
            this.getUsername()
        );
    }
}

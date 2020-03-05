package com.carousell.marketplace.error.exception;

import com.carousell.marketplace.error.ErrorType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author faizanmalik
 * creation date 3/4/20
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ApplicationException extends RuntimeException {
    private ErrorType type;

    ApplicationException(ErrorType errorType) {
        super(errorType.getMessage());
        this.type = errorType;
    }
}

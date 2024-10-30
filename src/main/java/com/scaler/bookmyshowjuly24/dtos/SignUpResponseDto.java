package com.scaler.bookmyshowjuly24.dtos;

import com.scaler.bookmyshowjuly24.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpResponseDto {
    private Long userId;
    private User user;
    private ResponseStatus responseStatus;
}

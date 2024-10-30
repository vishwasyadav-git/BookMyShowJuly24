package com.scaler.bookmyshowjuly24.controllers;

import com.scaler.bookmyshowjuly24.dtos.*;
import com.scaler.bookmyshowjuly24.exception.UserNotFoundException;
import com.scaler.bookmyshowjuly24.models.User;
import com.scaler.bookmyshowjuly24.services.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;
    public UserController(UserService userService){
        this.userService=userService;
    }
    public SignUpResponseDto signUp(SignUpRequestDto requestDto){
        SignUpResponseDto responseDto=new SignUpResponseDto();
        try{
            User user=userService.signUp(
                    requestDto.getName(),
                    requestDto.getEmail(),
                    requestDto.getPassword()
            );
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            responseDto.setUserId(user.getId());
        }catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
    return responseDto;
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto){
        LoginResponseDto responseDto=new LoginResponseDto();
        try {
            User user= userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {

            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}

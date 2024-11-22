package com.example.finance_web_demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ProfileDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private Date dateOfBirth;
    public List<AccountDTO> accountDTO;
}

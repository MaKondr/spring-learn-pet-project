package com.example.finance_web_demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserInfoDTO {
    public Integer id;
    public String name;
    public String email;
    public ProfileDTO profileDTO;

}

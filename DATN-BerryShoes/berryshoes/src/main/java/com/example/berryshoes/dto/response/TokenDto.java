package com.example.berryshoes.dto.response;

import com.example.berryshoes.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TokenDto {

    private String token;

    private String role;
}

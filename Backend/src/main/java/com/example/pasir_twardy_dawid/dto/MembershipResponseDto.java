package com.example.pasir_twardy_dawid.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MembershipResponseDto {
    private Long id;
    private Long userId;
    private Long groupId;
    private String userEmail;
}

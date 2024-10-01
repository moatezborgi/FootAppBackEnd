package com.borgi.footappbackend.dto.shareddto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NationalityDto {
    private int nationalityId;
    private String nationalityName;
}

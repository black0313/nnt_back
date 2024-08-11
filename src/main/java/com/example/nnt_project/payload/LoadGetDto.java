package com.example.nnt_project.payload;

import com.example.nnt_project.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoadGetDto {
    private Load load;
    private List<ShipperConsignee> shipperConsignees;
}

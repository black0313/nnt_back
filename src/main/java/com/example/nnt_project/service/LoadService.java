package com.example.nnt_project.service;

import com.example.nnt_project.entity.Load;
import com.example.nnt_project.entity.ShipperConsignee;
import com.example.nnt_project.mapper.LoadMapper;
import com.example.nnt_project.mapper.ShipperConsigneeMapper;
import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.payload.LoadDto;
import com.example.nnt_project.payload.ShipperConsigneeDto;
import com.example.nnt_project.repository.LoadRepository;
import com.example.nnt_project.repository.ShipperConsigneeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoadService {

    private final LoadRepository loadRepository;
    private final LoadMapper loadMapper;
    private final ShipperConsigneeMapper shipperConsigneeMapper;
    private final ShipperConsigneeRepository shipperConsigneeRepository;

    public ApiResponse create(LoadDto loadDto) {
        Load load = loadRepository.save(loadMapper.toEntity(loadDto));
        List<ShipperConsignee> allEntity =
                shipperConsigneeMapper.toEntity(loadDto.getShipperConsigneeDtoList());
        for (ShipperConsignee shipperConsignee : allEntity) {
            shipperConsignee.setLoad(load);
            shipperConsigneeRepository.save(shipperConsignee);
        }
        return new ApiResponse("successfully created", true);
    }
}

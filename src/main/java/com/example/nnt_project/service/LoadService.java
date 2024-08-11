package com.example.nnt_project.service;

import com.example.nnt_project.bot.MyTelegramBot;
import com.example.nnt_project.entity.*;
import com.example.nnt_project.mapper.LoadMapper;
import com.example.nnt_project.mapper.ShipperConsigneeMapper;
import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.payload.LoadDto;
import com.example.nnt_project.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoadService {

    private final MyTelegramBot myTelegramBot;
    private final LoadRepository loadRepository;
    private final LoadMapper loadMapper;
    private final ShipperConsigneeMapper shipperConsigneeMapper;
    private final ShipperConsigneeRepository shipperConsigneeRepository;
    private final DispatchersTeamRepository dispatchersTeamRepository;
    private final PickupAddressRepository pickupAddressRepository;
    private final DriverRepository driverRepository;
    private final BrokerRepository brokerRepository;
    private final TrailersRepository trailersRepository;
    private final TruckRepository truckRepository;
    private final FacilityRepository facilityRepository;
    private final DispatchersRepository dispatchersRepository;

    public ApiResponse create(LoadDto loadDto) {
        Load load = loadMapper.toEntity(loadDto);

        pickupAddressRepository.findById(loadDto.getAddressId()).ifPresent(load::setAddress);
        driverRepository.findById(loadDto.getDriverId()).ifPresent(load::setDriver);
        brokerRepository.findById(loadDto.getBrokerId()).ifPresent(load::setBroker);
        trailersRepository.findById(loadDto.getTrailerId()).ifPresent(load::setTrailers);
        truckRepository.findById(loadDto.getTruckId()).ifPresent(load::setTruck);
        dispatchersTeamRepository.findById(loadDto.getDispatcherTeamId()).ifPresent(load::setDispatchersTeam);
        facilityRepository.findById(loadDto.getFacilityId()).ifPresent(load::setFacility);
        dispatchersRepository.findById(loadDto.getDispatcherId()).ifPresent(load::setDispatchers);

        List<ShipperConsignee> allEntity =
                shipperConsigneeMapper.toEntity(loadDto.getShipperConsigneeDtoList());

        loadRepository.save(load);
        for (ShipperConsignee shipperConsignee : allEntity) {
            shipperConsignee.setLoad(load);
            shipperConsigneeRepository.save(shipperConsignee);
        }

        Optional<DispatchersTeam> optionalDispatchersTeam =
                dispatchersTeamRepository.findById(loadDto.getDispatcherTeamId());

        if (optionalDispatchersTeam.isPresent()) {
            DispatchersTeam dispatchersTeam = optionalDispatchersTeam.get();
            if (dispatchersTeam.getGroupId() != null) {
                myTelegramBot.sendMessageToGroup(dispatchersTeam.getGroupId(),
                        "<b>New load created</b>= " + load.getId() + "\n" +
                                "<b> groupName <\b>= " + dispatchersTeam.getName() + "\n" +
                                "<b> created at <\b>= " + load.getCreatedAt() + "\n" +
                                "<b> address <\b>= " + load.getAddress().getAddress() + "\n" +
                                "Entity =" + loadDto.toString());
            }
        }
        return new ApiResponse("successfully created", true);
    }

    public ApiResponse getAll() {
        List<LoadDto> loadDtoList = loadMapper.loadDtoList(loadRepository.findAll());
        for (LoadDto loadDto : loadDtoList) {
            loadDto.setShipperConsigneeDtoList(shipperConsigneeMapper.toDto(shipperConsigneeRepository.findAllByLoadId(loadDto.getId())));
        }
        return new ApiResponse("found", true, loadDtoList);
    }

    public ApiResponse getById(UUID id) {
        Optional<Load> optionalLoad = loadRepository.findById(id);
        if (optionalLoad.isEmpty())
            return new ApiResponse("not found");

        LoadDto dto = loadMapper.toDto(optionalLoad.get());
        dto.setShipperConsigneeDtoList(shipperConsigneeMapper.toDto(shipperConsigneeRepository.findAllByLoadId(dto.getId())));

        return new ApiResponse("found", true, dto);
    }

    public ApiResponse delete(UUID id) {
        Optional<Load> optionalLoad = loadRepository.findById(id);
        if (optionalLoad.isEmpty())
            return new ApiResponse("not found");

        Load load = optionalLoad.get();
        shipperConsigneeRepository.deleteAllByLoadId(load.getId());
        loadRepository.delete(load);
        return new ApiResponse("successfully deleted", true);
    }
}

package com.example.nnt_project.service;

import com.example.nnt_project.bot.MyTelegramBot;
import com.example.nnt_project.entity.*;
import com.example.nnt_project.mapper.LoadMapper;
import com.example.nnt_project.mapper.ShipperConsigneeMapper;
import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.payload.LoadDto;
import com.example.nnt_project.payload.LoadGetDto;
import com.example.nnt_project.payload.ShipperConsigneeDto;
import com.example.nnt_project.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        Optional<DispatchersTeam> optionalDispatchersTeam =
                dispatchersTeamRepository.findById(loadDto.getDispatcherTeamId());
        if (optionalDispatchersTeam.isEmpty())
            return new ApiResponse("no dispatcher team found");
        DispatchersTeam dispatchersTeam = optionalDispatchersTeam.get();

        // load qo'shish
        Load load = loadMapper.toEntity(loadDto);
        driverRepository.findById(loadDto.getDriverId()).ifPresent(load::setDriver);
        brokerRepository.findById(loadDto.getBrokerId()).ifPresent(load::setBroker);
        trailersRepository.findById(loadDto.getTrailerId()).ifPresent(load::setTrailers);
        truckRepository.findById(loadDto.getTruckId()).ifPresent(load::setTruck);
        dispatchersTeamRepository.findById(loadDto.getDispatcherTeamId()).ifPresent(load::setDispatchersTeam);
        facilityRepository.findById(loadDto.getFacilityId()).ifPresent(load::setFacility);
        dispatchersRepository.findById(loadDto.getDispatcherId()).ifPresent(load::setDispatchers);
        loadRepository.save(load);

        StringBuilder message =
                new StringBuilder("\uD83D\uDE9A " + load.getTruck().getTruckNumber() + "\n" +
                        "\uD83D\uDD16 " + dispatchersTeam.getName() + "\n" +
                        "\uD83D\uDC68 " + load.getDriver().getDriverName() + "\n \n");


        List<String> pickupAddresses = new ArrayList<>();
        List<String> consigneeAddresses = new ArrayList<>();
        List<ShipperConsigneeDto> dtoList = loadDto.getShipperConsigneeDtoList();
        for (ShipperConsigneeDto shipperConsigneeDto : dtoList) {
            ShipperConsignee shipperConsignee = shipperConsigneeMapper.toEntity(shipperConsigneeDto);
            pickupAddressRepository.findById(shipperConsigneeDto.getAddressId()).ifPresent(shipperConsignee::setPickupAddress);
            shipperConsignee.setLoad(load);
            shipperConsigneeRepository.save(shipperConsignee);
            if (shipperConsignee.isShipper()) {
                String pickupDate = shipperConsignee.getPickDate() != null ? shipperConsignee.getPickDate().toString() : "";
                pickupAddresses.add("Pick up: \uD83C\uDFED \n " + shipperConsignee.getPickupAddress().getAddress() + "\n" +
                        "Arrive: \n " + pickupDate + "\n");
            } else {
                String consigneeDate = shipperConsignee.getDeliveryDate() != null ? shipperConsignee.getDeliveryDate().toString() : "";
                consigneeAddresses.add("\n Last Stop: \uD83C\uDFED \n " + shipperConsignee.getPickupAddress().getAddress() + "\n" +
                        "Arrive: \n " + consigneeDate + "\n");
            }
        }

        for (String pickupAddress : pickupAddresses) {
            message.append(pickupAddress);
        }

        for (String consigneeAddress : consigneeAddresses) {
            message.append(consigneeAddress);
        }

        String messageLast = "⚠\uFE0F-Traffic/Construction/Weather or other delays (photos or videos) -  should be updated in good time by drivers\n" +
                "⚠\uFE0F-Please Scale the load after pick up, to avoid axle overweight. Missing scale ticket - 200$ penalty fee.\n" +
                "⚠\uFE0F-After hooking up the trailer, PTI should be done !!!\n";
        message.append(messageLast);

        if (dispatchersTeam.getGroupId() != null) {
            myTelegramBot.sendMessageToGroup(dispatchersTeam.getGroupId(), message.toString());
        }

        return new ApiResponse("successfully created", true);
    }


    public ApiResponse getAll() {
        List<Load> all = loadRepository.findAllByDeleteFalse();
        if (all.isEmpty())
            return new ApiResponse("not found", false);

        List<LoadGetDto> loadDtoList = new ArrayList<>();
        for (Load load : all) {
            LoadGetDto loadGetDto = new LoadGetDto();
            loadGetDto.setLoad(load);
            loadGetDto.setShipperConsignees(shipperConsigneeRepository.findAllByLoadIdAndDeleteFalse(load.getId()));
            loadDtoList.add(loadGetDto);
        }

        return new ApiResponse("found", true, loadDtoList);
    }

    public ApiResponse getById(UUID id) {
        Optional<Load> optionalLoad = loadRepository.findById(id);
        if (optionalLoad.isEmpty())
            return new ApiResponse("not found");
        LoadGetDto loadGetDto = new LoadGetDto();
        loadGetDto.setLoad(optionalLoad.get());
        loadGetDto.setShipperConsignees(shipperConsigneeRepository.findAllByLoadIdAndDeleteFalse(optionalLoad.get().getId()));
        return new ApiResponse("found", true, loadGetDto);
    }

    public ApiResponse delete(UUID id) {
        Optional<Load> optionalLoad = loadRepository.findById(id);
        if (optionalLoad.isEmpty())
            return new ApiResponse("not found");

        Load load = optionalLoad.get();
        load.setDelete(true);
        loadRepository.save(load);

        for (ShipperConsignee shipperConsignee : shipperConsigneeRepository.findAllByDeleteFalse()) {
            shipperConsignee.setDelete(true);
            shipperConsigneeRepository.save(shipperConsignee);
        }

        return new ApiResponse("successfully deleted", true);
    }
}

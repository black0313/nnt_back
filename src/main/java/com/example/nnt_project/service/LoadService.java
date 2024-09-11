package com.example.nnt_project.service;

import com.example.nnt_project.bot.MyTelegramBot;
import com.example.nnt_project.builder.TelegramMessageBuilder;
import com.example.nnt_project.entity.*;
import com.example.nnt_project.mapper.LoadMapper;
import com.example.nnt_project.mapper.ShipperConsigneeMapper;
import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.payload.LoadDto;
import com.example.nnt_project.payload.LoadGetDto;
import com.example.nnt_project.payload.ShipperConsigneeDto;
import com.example.nnt_project.repository.*;
import com.example.nnt_project.validator.LoadValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoadService {

    private final MyTelegramBot myTelegramBot;
    private final LoadRepository loadRepository;
    private final LoadMapper loadMapper;
    private final ShipperConsigneeMapper shipperConsigneeMapper;
    private final LoadValidator loadValidator;
    private final TelegramMessageBuilder telegramMessageBuilder;
    private final ShipperConsigneeRepository shipperConsigneeRepository;
    private final DriverRepository driverRepository;
    private final BrokerRepository brokerRepository;
    private final TruckRepository truckRepository;
    private final TrailersRepository trailersRepository;
    private final DispatchersRepository dispatchersRepository;
    private final DispatchersTeamRepository dispatchersTeamRepository;
    private final PickupAddressRepository pickupAddressRepository;
    private final FacilityRepository facilityRepository;

    // CREATE
    public ApiResponse create(LoadDto loadDto) {
        // 1. Validate incoming data
        loadValidator.validateLoadDto(loadDto);

        // 2. Map DTO to Entity and calculate ride metrics
        Load load = loadMapper.toEntity(loadDto);
        load.calculateRideMetrics(loadDto.getLoadMile(), loadDto.getDeadHead(), loadDto.getTotalRide());

        // 3. Set related entities and save load
        setLoadDetails(loadDto, load);

        // 4. Save shippers/consignees
        List<ShipperConsignee> shipperConsigneeList = saveShippersConsignees(loadDto, load);
        markLastStopForLoad(load.getId());

        // 5. Build and send Telegram message
        String message = telegramMessageBuilder.buildMessage(load, shipperConsigneeList);
        myTelegramBot.sendMessageToGroup(load.getDispatchersTeam().getGroupId(), message, "HTML");

        return new ApiResponse("Successfully created", true);
    }

    // UPDATE
    public ApiResponse update(UUID id, LoadDto loadDto) {
        // 1. Find existing load or throw exception if not found
        Load load = loadRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Load not found"));

        // 2. Update load fields
        setLoad(loadDto, load);
        setLoadDetails(loadDto, load);
        saveShippersConsignees(loadDto, load);

        return new ApiResponse("Successfully updated", true);
    }

    // GET ALL
    public ApiResponse getAll() {
        // 1. Fetch all non-deleted loads
        List<Load> loads = loadRepository.findAllByDeleteFalse();
        if (loads.isEmpty()) {
            return new ApiResponse("No loads found", false);
        }

        // 2. Map each load to its corresponding DTO with shippers/consignees
        List<LoadGetDto> loadDtos = loads.stream()
                .map(load -> loadMapper.toGetDto(load, shipperConsigneeRepository.findAllByLoadIdAndDeleteFalse(load.getId())))
                .collect(Collectors.toList());

        return new ApiResponse("Loads found", true, loadDtos);
    }

    // GET BY ID
    public ApiResponse getById(UUID id) {
        // 1. Find load by ID or throw exception if not found
        Load load = loadRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Load not found"));

        // 2. Fetch related shippers/consignees
        List<ShipperConsignee> shipperConsignees = shipperConsigneeRepository.findAllByLoadIdAndDeleteFalse(load.getId());

        // 3. Convert to DTO and return
        LoadGetDto loadDto = loadMapper.toGetDto(load, shipperConsignees);
        return new ApiResponse("Load found", true, loadDto);
    }

    // DELETE
    public ApiResponse delete(UUID id) {
        // 1. Find load by ID or throw exception if not found
        Load load = loadRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Load not found"));

        // 2. Mark load as deleted
        List<ShipperConsignee> all = shipperConsigneeRepository.findAllByLoadIdAndDeleteFalse(load.getId());
        for (ShipperConsignee shipperConsignee : all) {
            shipperConsignee.setDelete(true);
        }
        shipperConsigneeRepository.saveAll(all);

        load.setDelete(true);
        loadRepository.save(load);

        return new ApiResponse("Successfully deleted", true);
    }

    // Helper methods

    // Set related entities (dispatchers team, driver, broker, truck) for the load
    private void setLoadDetails(LoadDto loadDto, Load load) {
        loadValidator.setLoadEntities(load, loadDto);
        loadRepository.save(load);
    }

    // Save shippers/consignees related to the load
    private List<ShipperConsignee> saveShippersConsignees(LoadDto loadDto, Load load) {
        List<ShipperConsigneeDto> dtoList = loadDto.getShipperConsigneeDtoList();
        List<ShipperConsignee> shipperConsignees = new ArrayList<>();
        dtoList.forEach(dto -> {
            ShipperConsignee shipperConsignee = shipperConsigneeMapper.toEntity(dto);
            pickupAddressRepository.findById(dto.getAddressId()).ifPresent(shipperConsignee::setPickupAddress);
            facilityRepository.findById(dto.getFacilityId()).ifPresent(shipperConsignee::setFacility);
            shipperConsignee.setLoad(load); // Relate to the current load
            shipperConsignees.add(shipperConsigneeRepository.save(shipperConsignee));
        });
        return shipperConsignees;
    }



    public void markLastStopForLoad(UUID loadId) {
        // 1. Load ID bo'yicha barcha ShipperConsignee ob'ektlarini olish
        List<ShipperConsignee> consigneeList = shipperConsigneeRepository.findAllByLoadIdAndDeleteFalseAndShipperFalse(loadId);

        // 2. Eng oxirgi deliveryDate sanani topish
        Optional<ShipperConsignee> latestConsigneeOpt = consigneeList.stream()
                .max((sc1, sc2) -> sc1.getDeliveryDate().compareTo(sc2.getDeliveryDate()));

        // 3. Agar eng oxirgi ob'ekt topilgan bo'lsa, lastStop maydonini true qilish
        latestConsigneeOpt.ifPresent(latestConsignee -> {
            latestConsignee.setLastStop(true);
            shipperConsigneeRepository.save(latestConsignee);
        });
    }

    private void setLoad(LoadDto loadDto, Load load) {
        if (loadDto.getDriverId() != null)
            driverRepository.findById(loadDto.getDriverId()).ifPresent(load::setDriver);

        if (loadDto.getBrokerId() != null)
            brokerRepository.findById(loadDto.getBrokerId()).ifPresent(load::setBroker);

        if (loadDto.getTruckId() != null)
            truckRepository.findById(loadDto.getTruckId()).ifPresent(load::setTruck);

        if (loadDto.getTrailerId() != null)
            trailersRepository.findById(loadDto.getTrailerId()).ifPresent(load::setTrailers);

        if (loadDto.getDispatcherId() != null)
            dispatchersRepository.findById(loadDto.getDispatcherId()).ifPresent(load::setDispatchers);

        dispatchersTeamRepository.findById(loadDto.getDispatcherTeamId()).ifPresent(load::setDispatchersTeam);
        loadRepository.save(load);
    }
}
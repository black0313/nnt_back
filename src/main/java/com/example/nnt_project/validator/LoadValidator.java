package com.example.nnt_project.validator;

import com.example.nnt_project.entity.Load;
import com.example.nnt_project.payload.LoadDto;
import com.example.nnt_project.repository.BrokerRepository;
import com.example.nnt_project.repository.DispatchersTeamRepository;
import com.example.nnt_project.repository.DriverRepository;
import com.example.nnt_project.repository.TruckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoadValidator {

    private final DispatchersTeamRepository dispatchersTeamRepository;
    private final DriverRepository driverRepository;
    private final BrokerRepository brokerRepository;
    private final TruckRepository truckRepository;

    public void validateLoadDto(LoadDto loadDto) {
        if (dispatchersTeamRepository.findById(loadDto.getDispatcherTeamId()).isEmpty()) {
            throw new IllegalArgumentException("Dispatcher team not found");
        }
    }

    public void setLoadEntities(Load load, LoadDto loadDto) {
        dispatchersTeamRepository.findById(loadDto.getDispatcherTeamId())
                .ifPresent(load::setDispatchersTeam);
        driverRepository.findById(loadDto.getDriverId())
                .ifPresent(load::setDriver);
        brokerRepository.findById(loadDto.getBrokerId())
                .ifPresent(load::setBroker);
        truckRepository.findById(loadDto.getTruckId())
                .ifPresent(load::setTruck);
    }
}

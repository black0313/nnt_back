package com.example.nnt_project.service;

import com.example.nnt_project.bot.MyTelegramBot;
import com.example.nnt_project.entity.DispatchersTeam;
import com.example.nnt_project.entity.Load;
import com.example.nnt_project.entity.ShipperConsignee;
import com.example.nnt_project.mapper.LoadMapper;
import com.example.nnt_project.mapper.ShipperConsigneeMapper;
import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.payload.LoadDto;
import com.example.nnt_project.repository.DispatchersTeamRepository;
import com.example.nnt_project.repository.LoadRepository;
import com.example.nnt_project.repository.ShipperConsigneeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoadService {

    private final MyTelegramBot myTelegramBot;
    private final LoadRepository loadRepository;
    private final LoadMapper loadMapper;
    private final ShipperConsigneeMapper shipperConsigneeMapper;
    private final ShipperConsigneeRepository shipperConsigneeRepository;
    private final DispatchersTeamRepository dispatchersTeamRepository;

    public ApiResponse create(LoadDto loadDto) {
        Load load = loadRepository.save(loadMapper.toEntity(loadDto));
        List<ShipperConsignee> allEntity =
                shipperConsigneeMapper.toEntity(loadDto.getShipperConsigneeDtoList());
        for (ShipperConsignee shipperConsignee : allEntity) {
            shipperConsignee.setLoad(load);
            shipperConsigneeRepository.save(shipperConsignee);
        }
        Optional<DispatchersTeam> optionalDispatchersTeam =
                dispatchersTeamRepository.findById(loadDto.getDispatcherTeamId());

        if (optionalDispatchersTeam.isPresent()) {
            DispatchersTeam dispatchersTeam = optionalDispatchersTeam.get();
            if (dispatchersTeam.getGroupId()!=null) {
                myTelegramBot.sendMessageToGroup(dispatchersTeam.getGroupId(),
                        "new load id = " + load.getId() +"\n"+
                        "groupName = " + dispatchersTeam.getName() + "\n"+
                        "created at = " + load.getCreatedAt());
            }
        }
        return new ApiResponse("successfully created", true);
    }
}

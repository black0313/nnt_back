package com.example.nnt_project.service;

import com.example.nnt_project.dto.DispatchersDto;
import com.example.nnt_project.entity.Dispatchers;
import com.example.nnt_project.repository.DispatchersRepository;
import com.example.nnt_project.repository.UserRepository;
import com.example.nnt_project.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DispatchersService {

    private final DispatchersRepository dispatchersRepository;
    private final UserRepository userRepository;

    public Optional<Dispatchers> getDispatcherById(UUID id) {
        return dispatchersRepository.findById(id);
    }

    public Dispatchers saveDispatcher(Dispatchers dispatcher) {
        Dispatchers dispatchers = new Dispatchers();
        dispatchers.setUserId(dispatcher.getUserId());
        return dispatchersRepository.save(dispatcher);
    }

    public void deleteDispatcher(UUID id) {
        dispatchersRepository.deleteById(id);
    }

    public Optional<Dispatchers> updateDispatcher(UUID id, Dispatchers updatedDispatcher) {
        return dispatchersRepository.findById(id).map(existingDispatcher -> {
            existingDispatcher.setNumberOfLoads(updatedDispatcher.getNumberOfLoads());
            existingDispatcher.setGrossRevenue(updatedDispatcher.getGrossRevenue());
            existingDispatcher.setNetProfit(updatedDispatcher.getNetProfit());
            existingDispatcher.setPhoneNumber(updatedDispatcher.getPhoneNumber());
            existingDispatcher.setOpenLoads(updatedDispatcher.getOpenLoads());
            existingDispatcher.setUserId(updatedDispatcher.getUserId());
            return dispatchersRepository.save(existingDispatcher);
        });
    }

    public List<DispatchersDto> getAllDispatchers() {
        List<Dispatchers> allDispatchers = dispatchersRepository.findAll();
        Set<UUID> userIds = allDispatchers.stream()
                .map(Dispatchers::getUserId)
                .collect(Collectors.toSet());

        Map<UUID, User> userMap = userRepository.findAllById(userIds).stream()
                .collect(Collectors.toMap(User::getId, user -> user));

        return allDispatchers.stream()
                .map(dispatchers -> {
                    User user = userMap.get(dispatchers.getUserId());
                    return DispatchersDto.builder()
                            .id(dispatchers.getId())
                            .name(user != null ? user.getFirstname() : "Unknown")
                            .phoneNumber(dispatchers.getPhoneNumber())
                            .grossRevenue(dispatchers.getGrossRevenue())
                            .openLoads(dispatchers.getOpenLoads())
                            .netProfit(dispatchers.getNetProfit())
                            .numberOfLoads(dispatchers.getNumberOfLoads())
                            .build();
                })
                .collect(Collectors.toList());
    }

    public List<Dispatchers> getDispatchersByDateRange(String startDate, String endDate) {
        // Implement the logic to filter dispatchers by date range
        return dispatchersRepository.findAll(); // Placeholder, implement your date filtering logic here
    }
}

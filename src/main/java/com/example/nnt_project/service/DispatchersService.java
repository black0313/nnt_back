package com.example.nnt_project.service;

import com.example.nnt_project.entity.Address;
import com.example.nnt_project.entity.Dispatchers;
import com.example.nnt_project.entity.DispatchersTeam;
import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.payload.DispatcherDTO;
import com.example.nnt_project.repository.AddressRepository;
import com.example.nnt_project.repository.DispatchersRepository;
import com.example.nnt_project.repository.DispatchersTeamRepository;
import com.example.nnt_project.repository.UserRepository;
import com.example.nnt_project.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DispatchersService {

    private final DispatchersRepository dispatchersRepository;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final DispatchersTeamRepository dispatchersTeamRepository;

    public Optional<Dispatchers> getDispatcherById(UUID id) {
        return dispatchersRepository.findById(id);
    }

    public ApiResponse saveDispatcher(DispatcherDTO dispatcherDTO) {

        Optional<DispatchersTeam> optionalDispatchersTeam =
                dispatchersTeamRepository.findById(dispatcherDTO.getDispatchersTeamId());
        if (optionalDispatchersTeam.isEmpty())
            return new ApiResponse("Team not found");

        Optional<User> optionalUser = userRepository.findById(dispatcherDTO.getUserId());
        if (optionalUser.isEmpty())
            return new ApiResponse("User not found");

        Dispatchers dispatchers = new Dispatchers();
        dispatchers.setPhone(dispatcherDTO.getPhone());
        dispatchers.setEmail(dispatcherDTO.getEmail());
        dispatchers.setUserId(dispatcherDTO.getUserId());

        dispatchers.setFirstname(dispatcherDTO.getFirstname());
        dispatchers.setLastname(dispatcherDTO.getLastname());

        Address address = new Address();
        address.setCity(dispatcherDTO.getCity());
        address.setCountry(dispatcherDTO.getCountry());
        address.setPostalCode(dispatcherDTO.getPostalCode());
        address.setState(dispatcherDTO.getState());

        DispatchersTeam dispatchersTeam = optionalDispatchersTeam.get();
        dispatchers.setDispatchersTeam(dispatchersTeam);

        dispatchers.setAddress(addressRepository.save(address));
        dispatchersRepository.save(dispatchers);

        return new ApiResponse("success saved", true);
    }

    public ApiResponse deleteDispatcher(UUID id) {
        Optional<Dispatchers> optionalDispatchers = dispatchersRepository.findById(id);
        if (optionalDispatchers.isEmpty())
            return new ApiResponse("Dispatcher not found");

        Dispatchers dispatchers = optionalDispatchers.get();
        dispatchers.setDelete(true);
        dispatchersRepository.save(dispatchers);
        return new ApiResponse("success deleted",true);
    }

    public ApiResponse updateDispatcher(UUID id, DispatcherDTO dispatcherDTO) {
        Optional<Dispatchers> optionalDispatchers = dispatchersRepository.findById(id);
        if (optionalDispatchers.isEmpty()) {
            return new ApiResponse("not found",false);
        }
        Dispatchers dispatchers = optionalDispatchers.get();

        Optional<DispatchersTeam> optionalDispatchersTeam =
                dispatchersTeamRepository.findById(dispatcherDTO.getDispatchersTeamId());
        if (optionalDispatchersTeam.isPresent()){
            DispatchersTeam dispatchersTeam = optionalDispatchersTeam.get();
            dispatchers.setDispatchersTeam(dispatchersTeam);
        }

        dispatchers.setPhone(dispatcherDTO.getPhone());
        dispatchers.setEmail(dispatcherDTO.getEmail());
        dispatchers.setFirstname(dispatcherDTO.getFirstname());
        dispatchers.setLastname(dispatcherDTO.getLastname());
        dispatchers.setUserId(id);
        Address address = dispatchers.getAddress();
        address.setCity(dispatcherDTO.getCity());
        address.setCountry(dispatcherDTO.getCountry());
        address.setPostalCode(dispatcherDTO.getPostalCode());
        address.setState(dispatcherDTO.getState());
        dispatchers.setAddress(addressRepository.save(address));
        dispatchersRepository.save(dispatchers);
        return new ApiResponse("success updated", true);
    }

    public ApiResponse getAllDispatchers() {
        List<Dispatchers> allDispatchers = dispatchersRepository.findAllByDeleteFalse();
        if (allDispatchers.isEmpty()) {
            return new ApiResponse("not found",false);
        }
        return new ApiResponse("success getAllDispatchers", true, allDispatchers);
    }

    public List<Dispatchers> getDispatchersByDateRange(String startDate, String endDate) {
        // Implement the logic to filter dispatchers by date range
        return dispatchersRepository.findAllByDeleteFalse(); // Placeholder, implement your date filtering logic here
    }
}

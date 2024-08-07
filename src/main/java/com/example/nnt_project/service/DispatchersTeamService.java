package com.example.nnt_project.service;

import com.example.nnt_project.entity.DispatchersTeam;
import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.repository.DispatchersTeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DispatchersTeamService {
    private final DispatchersTeamRepository dispatchersTeamRepository;

    public ApiResponse create(String name) {
        boolean exists = dispatchersTeamRepository.existsByName(name);
        if (exists)
            return new ApiResponse("dispatchersTeam already exists");

        dispatchersTeamRepository.save(new DispatchersTeam(name));
        return new ApiResponse("dispatchersTeam created", true);
    }

    public ApiResponse getAll() {
        List<DispatchersTeam> all =
                dispatchersTeamRepository.findAll();
        if (all.isEmpty())
            return new ApiResponse("dispatchersTeam is empty");

        return new ApiResponse("found " + all.size() + " dispatchersTeams", true, all);
    }

    public ApiResponse update(UUID id, String name, Long groupId) {
        Optional<DispatchersTeam> optional = dispatchersTeamRepository.findById(id);
        if (optional.isEmpty())
            return new ApiResponse("dispatchersTeam not found");
        DispatchersTeam dispatchersTeam = optional.get();
//        if (!dispatchersTeam.getName().equals(name) &&
//                dispatchersTeamRepository.existsById(id))
//            return new ApiResponse("dispatchersTeam already exists");
//
//        if (!dispatchersTeam.getGroupId().equals(groupId)&&
//                dispatchersTeamRepository.existsByGroupId(groupId))
//            return new ApiResponse("dispatchersTeam group id already exists");

        dispatchersTeam.setName(name);
        dispatchersTeam.setGroupId(groupId);
        dispatchersTeamRepository.save(dispatchersTeam);

        return new ApiResponse("dispatchersTeam updated", true);
    }
}

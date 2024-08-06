package com.example.nnt_project.repository;

import com.example.nnt_project.entity.DispatchersTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DispatchersTeamRepository extends JpaRepository<DispatchersTeam, UUID> {
    boolean existsByName(String name);
    boolean existsByGroupId(Long groupId);
}

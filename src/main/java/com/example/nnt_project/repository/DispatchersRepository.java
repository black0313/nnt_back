package com.example.nnt_project.repository;

import com.example.nnt_project.entity.Dispatchers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DispatchersRepository extends JpaRepository<Dispatchers, UUID> {
}

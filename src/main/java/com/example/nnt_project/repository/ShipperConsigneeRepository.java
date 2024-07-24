package com.example.nnt_project.repository;

import com.example.nnt_project.entity.ShipperConsignee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShipperConsigneeRepository  extends JpaRepository<ShipperConsignee, UUID> {
}

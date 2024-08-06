package com.example.nnt_project.entity;

import com.example.nnt_project.entity.template.AbsEntity;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
public class DispatchersTeam extends AbsEntity {
    private String name;

    private Long groupId;

    public DispatchersTeam(String name) {
        this.name = name;
    }
}

package com.example.nnt_project.entity;

import com.example.nnt_project.entity.template.AbsEntity;
import com.example.nnt_project.enums.Permissions;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Role extends AbsEntity {

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<Permissions> permissions;

    private String description;


    public Role(String name, List<Permissions> permissions) {
        this.name = name;
        this.permissions = permissions;
    }

    public void addPermission(Permissions permissions) {
        this.permissions.add(permissions);
    }

}

package com.example.nnt_project.entity;

import com.example.nnt_project.entity.template.AbsEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity(name = "file_data")
@AllArgsConstructor
@NoArgsConstructor
public class FileData extends AbsEntity {


    private String fileName;

    @Lob
    private byte[] fileData;

    private long size;
}

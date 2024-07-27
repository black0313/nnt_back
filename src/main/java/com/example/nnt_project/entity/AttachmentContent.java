package com.example.nnt_project.entity;

import com.example.nnt_project.entity.template.AbsEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Data
@NoArgsConstructor
public class AttachmentContent extends AbsEntity {
    private byte[] mainContent;
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Attachment attachment;

    public AttachmentContent(byte[] mainContent, Attachment attachment) {
        this.mainContent = mainContent;
        this.attachment = attachment;
    }


}

package com.example.academy.common;


import java.util.Date;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@MappedSuperclass  // 상속받은 클래스들이 이 필드를 사용하게 함
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseTimeEntity {

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

//    @Temporal(TemporalType.TIMESTAMP)
//    private Date deletedAt;
//
//    public void softDelete() {
//        this.deletedAt = new Date();
//    }


}

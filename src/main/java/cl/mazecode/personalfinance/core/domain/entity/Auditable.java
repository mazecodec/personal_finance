package cl.mazecode.personalfinance.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Data
@EqualsAndHashCode
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"created_at", "updated_at", "deleted_at"}, allowGetters = true)
public abstract class Auditable<U> {
    @Column(name = "created_at", updatable = false)
    @Temporal(TIMESTAMP)
    @CreatedDate
    @JsonIgnore
    protected Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TIMESTAMP)
    @LastModifiedDate
    @JsonIgnore
    protected Date updatedAt;

    @Column(name = "deleted_at", updatable = false)
    @Temporal(TIMESTAMP)
    @JsonIgnore
    protected Date deletedAt;
}
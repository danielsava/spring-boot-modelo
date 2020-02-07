package bpe.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;


@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable=false, nullable=false)
    public Long id;

    @Version
    @Column(name = "version", nullable = false)
    public Long version;

    @Column(name = "date_create", updatable = false)
    public LocalDateTime dateCreate;

    @Column(name = "date_last_update")
    public LocalDateTime dateLastUpdate;


    public BaseEntity() { }


    @PrePersist
    private void prePersist() {
        dateCreate = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        dateLastUpdate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return  ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }


    @JsonIgnore
    boolean isNew() {
        return Objects.isNull(this.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

package example.models;

import com.yahoo.elide.annotation.LifeCycleHookBinding;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    @CreatedDate
    private Instant createdAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private Instant lastChangeAt;

    @LastModifiedBy
    private String lastChangedBy;

    @LifeCycleHookBinding(hook = NoUpdate.class, operation = LifeCycleHookBinding.Operation.UPDATE)
    @NotNull(message = "This field must not be null")
    private String noUpdateField;

}

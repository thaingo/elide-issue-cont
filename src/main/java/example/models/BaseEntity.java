package example.models;

import com.yahoo.elide.annotation.LifeCycleHookBinding;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity implements HasOwner {

    private String owner;

    @LifeCycleHookBinding(hook = NoUpdate.class, operation = LifeCycleHookBinding.Operation.UPDATE)
    @NotNull(message = "This field must not be null")
    private String noUpdateField;

}

package example.models;

import com.yahoo.elide.annotation.LifeCycleHookBinding;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    @LifeCycleHookBinding(hook = NoUpdate.class, operation = LifeCycleHookBinding.Operation.UPDATE)
    private String noUpdateField;
}

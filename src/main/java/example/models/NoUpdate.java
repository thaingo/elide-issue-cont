package example.models;

import com.yahoo.elide.annotation.LifeCycleHookBinding;
import com.yahoo.elide.core.lifecycle.LifeCycleHook;
import com.yahoo.elide.core.security.ChangeSpec;
import com.yahoo.elide.core.security.RequestScope;

import java.util.Optional;

public class NoUpdate implements LifeCycleHook<BaseEntity> {
    @Override
    public void execute(LifeCycleHookBinding.Operation operation,
                        LifeCycleHookBinding.TransactionPhase transactionPhase, BaseEntity baseEntity,
                        RequestScope requestScope, Optional<ChangeSpec> changeSpec) {
        throw new RuntimeException("Cannot update this field");
    }
}

package example.checks;

import com.yahoo.elide.annotation.SecurityCheck;
import com.yahoo.elide.core.Path;
import com.yahoo.elide.core.exceptions.BadRequestException;
import com.yahoo.elide.core.filter.Operator;
import com.yahoo.elide.core.filter.expression.FilterExpression;
import com.yahoo.elide.core.filter.predicates.FilterPredicate;
import com.yahoo.elide.core.security.RequestScope;
import com.yahoo.elide.core.security.checks.FilterExpressionCheck;
import com.yahoo.elide.core.type.ClassType;
import com.yahoo.elide.core.type.Type;
import example.models.HasOwner;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@SecurityCheck(value = Constants.OWNER)
@Slf4j
public class OwnerCheck extends FilterExpressionCheck<HasOwner> {

    @Override
    public FilterExpression getFilterExpression(Type<?> type, RequestScope requestScope) {
        final String owner = requestScope.getRequestHeaderByName("x-owner");
        log.debug("Entered owner {}", owner);
        if (null == owner || owner.isEmpty()) {
            throw new BadRequestException("No x-owner header") {
            };
        } else {
            final Path.PathElement ownerPath = new Path.PathElement(type, ClassType.STRING_TYPE, "owner");
            log.debug("OwnerCheck with {}", type.getSimpleName());
            return new FilterPredicate(new Path(List.of(ownerPath)), Operator.IN, List.of(owner));
        }
    }

}

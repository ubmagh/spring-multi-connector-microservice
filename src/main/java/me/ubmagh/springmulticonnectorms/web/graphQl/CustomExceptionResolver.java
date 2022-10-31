package me.ubmagh.springmulticonnectorms.web.graphQl;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class CustomExceptionResolver extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if (ex instanceof ResponseStatusException) {
            ErrorType type= null;
            switch ( ((ResponseStatusException) ex).getStatus() ){
                case NOT_FOUND :
                    type = ErrorType.NOT_FOUND;
                    break;
                default:
                    type = ErrorType.BAD_REQUEST;
            }
            return GraphqlErrorBuilder.newError()
                    .errorType(type)
                    .message(((ResponseStatusException) ex).getReason())
                    .path(env.getExecutionStepInfo().getPath())
                    .location(env.getField().getSourceLocation())
                    .build();
        } else {
            return null;
        }
    }

}

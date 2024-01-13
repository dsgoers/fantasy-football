package org.dsgoers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.io.IOException;

/**
 * Lambda function entry point. You can change to use other pojo type or implement
 * a different RequestHandler.
 *
 * @see <a href=https://docs.aws.amazon.com/lambda/latest/dg/java-handler.html>Lambda Java Handler</a> for more information
 */
public class LambdaApp implements RequestHandler<Object, Object> {

    final Main main;

    public LambdaApp() {
        main = new Main(null);
    }

    @Override
    public Object handleRequest(final Object input, final Context context) {
        try {
            return main.main();
        } catch (final IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}


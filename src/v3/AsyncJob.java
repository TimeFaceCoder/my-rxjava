package v3;

import v2.Callback;

import java.io.IOException;

/**
 * Created by rayboot on 15/7/13.
 */
public abstract class AsyncJob<T> {
    public abstract void start(Callback<T> callback) throws IOException;
}

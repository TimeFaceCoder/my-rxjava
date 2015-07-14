package v2;

/**
 * Created by rayboot on 15/7/13.
 */
public interface Callback<T> {
    void onResult(T result);

    void onError(Exception e);
}


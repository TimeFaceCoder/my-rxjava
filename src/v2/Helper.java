package v2;

import models.DynamicItem;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by rayboot on 15/7/13.
 */
public class Helper {

    ApiWrapper apiWrapper = new ApiWrapper();

    public void getTheBestOne(Callback<File> callback) throws IOException {
        apiWrapper.query(new Callback<List<DynamicItem>>() {
            @Override
            public void onResult(List<DynamicItem> items) {
                DynamicItem item = Collections.max(items);
                apiWrapper.store(item, callback);
            }

            @Override
            public void onError(Exception e) {
                callback.onError(e);
            }
        });
    }
}

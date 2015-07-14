package v3;

import models.DynamicItem;
import v2.Callback;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by rayboot on 15/7/13.
 */
public class Helper {
    ApiWrapper apiWrapper = new ApiWrapper();

    public AsyncJob<File> getTheBestOne() {
        return new AsyncJob<File>() {
            @Override
            public void start(Callback<File> cutestCatCallback) {
                try {
                    apiWrapper.query()
                            .start(new Callback<List<DynamicItem>>() {
                                @Override
                                public void onResult(List<DynamicItem> items) {
                                    DynamicItem cutest = Collections.max(items);
                                    try {
                                        apiWrapper.store(cutest)
                                                .start(new Callback<File>() {
                                                    @Override
                                                    public void onResult(File result) {
                                                        cutestCatCallback.onResult(result);
                                                    }

                                                    @Override
                                                    public void onError(Exception e) {
                                                        cutestCatCallback.onError(e);
                                                    }
                                                });
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onError(Exception e) {
                                    cutestCatCallback.onError(e);
                                }
                            });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }

}

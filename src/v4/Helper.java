package v4;

import models.DynamicItem;
import v2.Callback;
import v3.ApiWrapper;
import v3.AsyncJob;

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
        AsyncJob<List<DynamicItem>> itemsListAsyncJob = apiWrapper.query();
        AsyncJob<DynamicItem> maxAsyncJob = new AsyncJob<DynamicItem>() {
            @Override
            public void start(Callback<DynamicItem> callback) throws IOException {
                itemsListAsyncJob.start(new Callback<List<DynamicItem>>() {
                    @Override
                    public void onResult(List<DynamicItem> items) {
                        callback.onResult(Collections.max(items));
                    }

                    @Override
                    public void onError(Exception e) {
                        callback.onError(e);
                    }
                });
            }
        };

        AsyncJob<File> storedFileAsyncJob = new AsyncJob<File>() {
            @Override
            public void start(Callback<File> storeCallBack) throws IOException {
                    maxAsyncJob.start(new Callback<DynamicItem>() {
                        @Override
                        public void onResult(DynamicItem item) {
                            try {
                                apiWrapper.store(item)
                                        .start(new Callback<File>() {
                                            @Override
                                            public void onResult(File file) {
                                                storeCallBack.onResult(file);
                                            }

                                            @Override
                                            public void onError(Exception e) {
                                                storeCallBack.onError(e);
                                            }
                                        });
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(Exception e) {
                            storeCallBack.onError(e);
                        }
                    });
            }
        };
        return storedFileAsyncJob;
    }

}

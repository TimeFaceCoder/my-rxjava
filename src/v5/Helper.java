package v5;

import models.DynamicItem;
import v2.Callback;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * Created by rayboot on 15/7/14.
 */
public class Helper {
    ApiWrapper apiWrapper = new ApiWrapper();

    public AsyncJob<File> getTheBestOne() {
        AsyncJob<List<DynamicItem>> itemsListAsyncJob = apiWrapper.query();
        AsyncJob<DynamicItem> bestOneAsyncJob = itemsListAsyncJob.map(new Func<List<DynamicItem>, DynamicItem>() {
            @Override
            public DynamicItem call(List<DynamicItem> items) {
                return Collections.max(items);
            }
        });

        AsyncJob<File> storedFileAsyncJob = new AsyncJob<File>() {
            @Override
            public void start(Callback<File> storedCallback) {
                bestOneAsyncJob.start(new Callback<DynamicItem>() {
                    @Override
                    public void onResult(DynamicItem item) {
                        apiWrapper.store(item)
                                .start(new Callback<File>() {
                                    @Override
                                    public void onResult(File result) {
                                        storedCallback.onResult(result);
                                    }

                                    @Override
                                    public void onError(Exception e) {
                                        storedCallback.onError(e);
                                    }
                                });
                    }

                    @Override
                    public void onError(Exception e) {
                        storedCallback.onError(e);
                    }
                });
            }
        };
        return storedFileAsyncJob;
    }


    //新的创建 AsyncJob<Cat> cutestCatAsyncJob 的代码只有 6行，并且只有一层嵌套。

}

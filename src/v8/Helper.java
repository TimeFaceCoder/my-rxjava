package v8;

import models.DynamicItem;
import v5.Func;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * Created by rayboot on 15/7/14.
 */
public class Helper {
    ApiWrapper apiWrapper = new ApiWrapper();

    public AsyncJob<File> getTheBestOne() {
        AsyncJob<List<DynamicItem>> catsListAsyncJob = apiWrapper.query();
        AsyncJob<DynamicItem> cutestCatAsyncJob = catsListAsyncJob.map(new Func<List<DynamicItem>, DynamicItem>() {
            @Override
            public DynamicItem call(List<DynamicItem> items) {
                return Collections.max(items);
            }
        });

        AsyncJob<File> storedUriAsyncJob = cutestCatAsyncJob.flatMap(new Func<DynamicItem, AsyncJob<File>>() {
            @Override
            public AsyncJob<File> call(DynamicItem item) {
                return apiWrapper.store(item);
            }
        });
        return storedUriAsyncJob;
    }



    /*
        我们把所有的操作都修改为异步的了，最终的代码 看起来是不是有点眼熟啊？ 再仔细看看。还没发现？
        如果把匿名类修改为 Java 8 的 lambdas 表达式（逻辑是一样的，只是让代码看起来更清晰点）就很容易发现了。
     */
}

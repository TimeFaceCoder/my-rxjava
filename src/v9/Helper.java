package v9;

import models.DynamicItem;
import v8.ApiWrapper;
import v8.AsyncJob;

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
        AsyncJob<DynamicItem> bestOneAsyncJob = itemsListAsyncJob.map(items -> Collections.max(items));
        AsyncJob<File> storedFileAsyncJob = bestOneAsyncJob.flatMap(item -> apiWrapper.store(item));
        return storedFileAsyncJob;
    }


    /*


    vs

    public DynamicItem getTheBestOne() throws IOException {
        List<DynamicItem> items = api.query();
        DynamicItem bestItem = Collections.max(items);
        api.store(bestItem);
        return bestItem;
    }


        现在他们不仅逻辑是一样的，语义上也是一样的。
        同时我们还可以使用组合操作，现在把两个异步操作组合一起并返还另外一个异步操作。
        异常处理也会传递到最终的回调接口中。








     */
}

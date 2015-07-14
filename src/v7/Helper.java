package v7;

import models.DynamicItem;
import v5.ApiWrapper;
import v5.AsyncJob;
import v5.Func;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * Created by rayboot on 15/7/14.
 */
public class Helper {
    ApiWrapper apiWrapper;

    public AsyncJob<File> getTheBestOne() {
        AsyncJob<List<DynamicItem>> catsListAsyncJob = apiWrapper.query();
        AsyncJob<DynamicItem> cutestCatAsyncJob = catsListAsyncJob.map(new Func<List<DynamicItem>, DynamicItem>() {
            @Override
            public DynamicItem call(List<DynamicItem> items) {
                return Collections.max(items);
            }
        });

//        AsyncJob<AsyncJob<File>> storedUriAsyncJob = cutestCatAsyncJob.map(new Func<DynamicItem, AsyncJob<File>>() {
//            @Override
//            public AsyncJob<File> call(DynamicItem item) {
//                return apiWrapper.store(item);
//            }
//        });
//        return storedUriAsyncJob;
//        //^^^^^^^^^^^^^^^^^^^^^^^ 将会导致无法编译
//        //      Incompatible types:
//        //      Required: AsyncJob<Uri>
//        //      Found: AsyncJob<AsyncJob<Uri>>
        return null;
    }




    /*

    这里我们只能拿到  AsyncJob<AsyncJob<File>> 。
    看来还需要更进一步。我们需要压缩一层AsyncJob ，把两个异步操作当做一个单一的异步操作来对待。
    现在我们需要一个参数为 AsyncJob<R> 的 map 转换操作而不是 R。 该操作类似于 map， 但是该操作会把嵌套的 AsyncJob 压缩为（flatten ）
    一层 AsyncJob. 我们称之为 flatMap，
     */

}

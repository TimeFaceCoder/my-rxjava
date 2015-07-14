package v6;

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

//        AsyncJob<File> storedUriAsyncJob = cutestCatAsyncJob.map(new Func<DynamicItem, File>() {
//            @Override
//            public File call(DynamicItem item) {
//                return apiWrapper.store(item);
//                //      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ 将会导致无法编译
//                //      Incompatible types:
//                //      Required: File
//                //      Found: AsyncJob<File>
//            }
//        });
//        return storedUriAsyncJob;
        return null;
    }
}

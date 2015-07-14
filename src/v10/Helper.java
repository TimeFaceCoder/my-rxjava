package v10;

import models.DynamicItem;
import rx.Observable;
import rx.functions.Func1;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * Created by rayboot on 15/7/14.
 */
public class Helper {

    ApiWrapper apiWrapper = new ApiWrapper();

    public Observable<File> getTheBestOne() {
        Observable<List<DynamicItem>> catsListObservable = apiWrapper.query();
        Observable<DynamicItem> cutestCatObservable = catsListObservable.map(new Func1<List<DynamicItem>, DynamicItem>() {
            @Override
            public DynamicItem call(List<DynamicItem> items) {
                return Collections.max(items);
            }
        });
        Observable<File> storedUriObservable = cutestCatObservable.flatMap(new Func1<DynamicItem, Observable<? extends File>>() {
            @Override
            public Observable<? extends File> call(DynamicItem item) {
                return apiWrapper.store(item);
            }
        });
        return storedUriObservable;
    }

}

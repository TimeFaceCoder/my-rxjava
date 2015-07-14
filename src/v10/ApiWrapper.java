package v10;

import models.DynamicItem;
import rx.Observable;
import rx.Subscriber;
import utils.Api;
import utils.ApiInstance;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by rayboot on 15/7/14.
 * 只有一些名字上的不同：
 AsyncJob<T> 等同于 Observable<T>， 不仅仅可以返回一个结果，还可以返回一系列的结果，当然也可能没有结果返回。
 Callback<T> 等同于 Observer<T>， 除了onNext(T t), onError(Throwable t)以外，还有一个onCompleted()函数，
 该函数在结束继续返回结果的时候通知Observable 。
 abstract void start(Callback<T> callback) 和 Subscription subscribe(final Observer<? super T> observer) 类似
 ，返回一个Subscription ，如果你不再需要后面的结果了，可以取消该任务。
 除了 map 和 flatMap 以外， Observable 还有很多其他常见的转换操作。
 */
public class ApiWrapper {
    Api api = new ApiInstance();

    public Observable<List<DynamicItem>> query() {
        return Observable.create(new Observable.OnSubscribe<List<DynamicItem>>() {
            @Override
            public void call(final Subscriber<? super List<DynamicItem>> subscriber) {
                try {
                    api.query(new Api.ItemsQueryCallback() {
                        @Override
                        public void onItemListReceived(List<DynamicItem> items) {
                            subscriber.onNext(items);
                        }

                        @Override
                        public void onError(Exception e) {
                            subscriber.onError(e);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Observable<File> store(final DynamicItem item) {
        return Observable.create(new Observable.OnSubscribe<File>() {
            @Override
            public void call(final Subscriber<? super File> subscriber) {
                api.store(item, new Api.StoreCallback() {

                    @Override
                    public void onImageStored(File file) {

                        subscriber.onNext(file);
                    }

                    @Override
                    public void onStoreFailed(Exception e) {
                        subscriber.onError(e);
                    }
                });
            }
        });
    }

}

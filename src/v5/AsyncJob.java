package v5;

import v2.Callback;

/**
 * Created by rayboot on 15/7/14.
 * 当我们把 AsyncJob 的结果转换为其他类型的时候， 我们需要把一个结果值映射为另外一种类型，这个操作我们称之为 map。
 * 把该函数定义到 AsyncJob 类中比较方便，这样就可以通过 this 来访问 AsyncJob 对象了。
 */
public abstract class AsyncJob<T> {
    public abstract void start(Callback<T> callback);

    public <R> AsyncJob<R> map(Func<T, R> func) {
        final AsyncJob<T> source = this;
        return new AsyncJob<R>() {
            @Override
            public void start(Callback<R> callback) {
                source.start(new Callback<T>() {
                    @Override
                    public void onResult(T result) {
                        R mapped = func.call(result);
                        callback.onResult(mapped);
                    }

                    @Override
                    public void onError(Exception e) {
                        callback.onError(e);
                    }
                });
            }
        };
    }
}

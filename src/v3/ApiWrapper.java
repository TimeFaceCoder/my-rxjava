package v3;

import models.DynamicItem;
import utils.Api;
import utils.ApiInstance;
import v2.Callback;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by rayboot on 15/7/13.
 */
public class ApiWrapper {
    Api api = new ApiInstance();

    public AsyncJob<List<DynamicItem>> query() {
        return new AsyncJob<List<DynamicItem>>() {
            @Override
            public void start(Callback<List<DynamicItem>> catsCallback) throws IOException {
                api.query(new Api.ItemsQueryCallback() {
                    @Override
                    public void onItemListReceived(List<DynamicItem> items) {
                        catsCallback.onResult(items);
                    }

                    @Override
                    public void onError(Exception e) {
                        catsCallback.onError(e);
                    }
                });
            }
        };
    }

    public AsyncJob<File> store(DynamicItem item) {
        return new AsyncJob<File>() {
            @Override
            public void start(Callback<File> uriCallback) {
                api.store(item, new Api.StoreCallback() {
                    @Override
                    public void onImageStored(File file) {
                        uriCallback.onResult(file);
                    }

                    @Override
                    public void onStoreFailed(Exception e) {
                        uriCallback.onError(e);
                    }
                });
            }
        };
    }
}

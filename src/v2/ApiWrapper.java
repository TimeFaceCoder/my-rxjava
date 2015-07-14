package v2;

import models.DynamicItem;
import utils.Api;
import utils.ApiInstance;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by rayboot on 15/7/13.
 */
public class ApiWrapper {
    Api api = new ApiInstance();

    public void query(Callback<List<DynamicItem>> callback) throws IOException {
        api.query(new Api.ItemsQueryCallback() {

            @Override
            public void onItemListReceived(List<DynamicItem> items) {
                callback.onResult(items);
            }

            @Override
            public void onError(Exception e) {
                callback.onError(e);
            }
        });
    }

    public void store(DynamicItem item, Callback<File> fileCallback) {
        api.store(item, new Api.StoreCallback() {

            @Override
            public void onImageStored(File file) {
                fileCallback.onResult(file);
            }

            @Override
            public void onStoreFailed(Exception e) {
                fileCallback.onError(e);
            }
        });
    }
}

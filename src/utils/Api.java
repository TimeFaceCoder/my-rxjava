package utils;

import models.DynamicItem;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by rayboot on 15/7/13.
 */
public interface Api {
    List<DynamicItem> query() throws IOException;
    File store(DynamicItem item) throws IOException;



























    //检索改为异步
    interface ItemsQueryCallback {
        void onItemListReceived(List<DynamicItem> items);
        void onError(Exception e);
    }


    void query(ItemsQueryCallback itemsQueryCallback) throws IOException;







































    //检索改为异步
    interface StoreCallback{
        void onImageStored(File file);
        void onStoreFailed(Exception e);
    }


    void store(DynamicItem item, StoreCallback storeCallback);
}

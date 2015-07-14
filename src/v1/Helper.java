package v1;

import models.DynamicItem;
import utils.Api;
import utils.ApiInstance;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by rayboot on 15/7/13.
 */
public class Helper {

    Api api = new ApiInstance();

    public DynamicItem getTheBestOne() throws IOException {
        List<DynamicItem> items = api.query();
        DynamicItem bestItem = Collections.max(items);
        api.store(bestItem);
        return bestItem;
    }












































    //网络改为异步

    public interface ItemCallback {
        void onItemSaved(File file);

        void onQueryFailed(Exception e);
    }


    //网络改为异步
    public void getTheBestOne(ItemCallback itemCallBack) throws IOException {
        api.query(new Api.ItemsQueryCallback() {
            @Override
            public void onItemListReceived(List<DynamicItem> items) {
                DynamicItem item = Collections.max(items);
                File file = null;
                try {
                    file = api.store(item);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                itemCallBack.onItemSaved(file);
            }

            @Override
            public void onError(Exception e) {
                itemCallBack.onQueryFailed(e);
            }
        });
    }





































    //检索和存储改为异步
    public void getTheBestOneEx(ItemCallback itemCallBack) throws IOException {
        api.query(new Api.ItemsQueryCallback() {
            @Override
            public void onItemListReceived(List<DynamicItem> items) {
                DynamicItem item = Collections.max(items);
                api.store(item, new Api.StoreCallback() {
                    @Override
                    public void onImageStored(File file) {
                        itemCallBack.onItemSaved(file);
                    }

                    @Override
                    public void onStoreFailed(Exception e) {
                        itemCallBack.onQueryFailed(e);
                    }
                });
            }

            @Override
            public void onError(Exception e) {
                itemCallBack.onQueryFailed(e);
            }
        });
    }







































    /*
    现在再来看看我们的业务逻辑代码，是不是和之前的阻塞式调用那么简单、那么清晰？ 当然不一样了，上面的异步操作代码看起来太恐怖了！
    这里有太多的干扰代码了，太多的匿名类了，但是不可否认，他们的业务逻辑其实是一样的。查询猫的列表数据、找出最可爱的并保持其图片。
组合功能也不见了！ 现在你没法像阻塞操作一样来组合调用每个功能了。异步操作中，每次你都必须通过回调接口来手工的处理结果。
那么关于异常传递和处理呢？ 没了！异步代码中的异常不会自动传递了，我们需要手工的重新传递出去。（onStoreFailed 和 onError 就是干这事用的）
上面的代码非常难懂也更难发现潜在的 BUG。
然后呢？我们如何处理这种情况呢？我们是不是就被困在这种无法组合的回调接口困局中呢？ 下次我们来看看如何优化该问题。
     */


}

package utils;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import models.DynamicItem;
import models.DynamicResponse;
import okio.Okio;
import okio.Source;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rayboot on 15/7/13.
 */
public class ApiInstance implements Api {
    final String REQUEST_URL = "http://timefaceapi.timeface.cn/timefaceapi/v2/time/timelist?lastedId&type=1&imgType=2&pageSize=100";
    public OkHttpClient client = new OkHttpClient();
    @Override
    public List<DynamicItem> query() throws IOException {
        Request.Builder requestBuilder = new Request.Builder()
                .url(REQUEST_URL);
        for (Map.Entry entry : getHttpHeaders().entrySet()) {
            requestBuilder.addHeader((String) entry.getKey(), (String) entry.getValue());
        }

        Response response = client.newCall(requestBuilder.build()).execute();
        DynamicResponse dynamicResponse = new Gson().fromJson(GzipUtil.decompress(response.body().bytes()), DynamicResponse.class);
        return dynamicResponse.getDataList();
    }

    @Override
    public File store(DynamicItem item)  throws IOException {
        Request request = new Request.Builder().url(item.getImageObjList().get(0).getImageUrl()).build();
        Response response = client.newCall(request).execute();
        File res = new File(item.getTimeId() + ".jpg");
        Source src = Okio.source(response.body().byteStream());
        Okio.buffer(src).readAll(Okio.sink(res));
        src.close();
        return res;
    }

    @Override
    public void query(ItemsQueryCallback itemsQueryCallback)  throws IOException {
        Request.Builder requestBuilder = new Request.Builder().url(REQUEST_URL);
        for (Map.Entry entry : getHttpHeaders().entrySet()) {
            requestBuilder.addHeader((String) entry.getKey(), (String) entry.getValue());
        }
        client.newCall(requestBuilder.build()).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                itemsQueryCallback.onError(e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                DynamicResponse dynamicResponse = new Gson().fromJson(GzipUtil.decompress(response.body().bytes()), DynamicResponse.class);
                itemsQueryCallback.onItemListReceived(dynamicResponse.getDataList());
            }
        });
    }

    @Override
    public void store(DynamicItem item, StoreCallback storeCallback) {
        try {
            Request request = new Request.Builder().url(item.getImageObjList().get(0).getImageUrl()).build();
            Response response = client.newCall(request).execute();
            File res = new File(item.getTimeId() + ".jpg");
            Source src = Okio.source(response.body().byteStream());
            Okio.buffer(src).readAll(Okio.sink(res));
            src.close();
            storeCallback.onImageStored(res);
        } catch (IOException e) {
            e.printStackTrace();
            storeCallback.onStoreFailed(e);
        }
    }


    public static Map<String, String> getHttpHeaders() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("LOC", "CN");
        headers.put("USERID", "451180853596");
        headers.put("DEVICEID", "46a8839a-2579-38a0-9901-6f4ec162e41a");
        headers.put("TOKEN", "timeface_android");
        headers.put("VERSION", "4");
        headers.put("CHANNEL", "z");
        return headers;
    }
}

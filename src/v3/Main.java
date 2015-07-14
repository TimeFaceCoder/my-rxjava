package v3;

import v2.Callback;

import java.io.File;
import java.io.IOException;

public class Main {

    /*
        分离参数和回调接口
        看看这些新的异步操作（query, store 和 getTheBestOne）。这些函数都有同样的模式。使用一些参数来调用这些函数（query），
        同时还有一个回调接口作为参数。甚至，所有的异步操作都带有一些常规参数和一个额外的回调接口参数。如果我们把他们分离开会如何，
        让每个异步操作只有一些常规参数而把返回一个临时的对象来操作回调接口。
        下面来试试看看这种方式能否有效。
        如果我们返回一个临时的对象作为异步操作的回调接口处理方式，我们需要先定义这个对象。由于对象遵守通用的行为（有一个回调接口参数），
        我们定义一个能用于所有操作的对象。 我们称之为 AsyncJob。
    */

    public static void main(String[] args) {
        try {
            new Helper().getTheBestOne().start(new Callback<File>() {
                @Override
                public void onResult(File result) {
                    System.out.println("best file path = " + result.getAbsolutePath());
                }

                @Override
                public void onError(Exception e) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





























    /*
        这里其实我们返回的是一个 AsyncJob<Uri> 对象

                 (async)                        (sync)                    (async)
        query ===========> List<DynamicItem> -------------> DynamicItem ==========> File
                  query                       collect.max                  store





    */


}

package v4;

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
        虽然代码量多了，但是看起来更加清晰了。 嵌套的回调函数没那么多层级了，异步操作的名字也更容易理解了
        ------------
        需要简化

        简单的映射

        先来看看我们创建 AsyncJob<Cat> cutestCatAsyncJob 的代码：
        AsyncJob<DynamicItem> maxAsyncJob = new AsyncJob<DynamicItem>() {
            @Override
            public void start(Callback<DynamicItem> callback) throws IOException {
                itemsListAsyncJob.start(new Callback<List<DynamicItem>>() {
                    @Override
                    public void onResult(List<DynamicItem> items) {
                        callback.onResult(Collections.max(items));
                    }

                    @Override
                    public void onError(Exception e) {
                        callback.onError(e);
                    }
                });
            }
        };
        这 16 行代码中，只有一行代码是我们的业务逻辑代码：
        Collections.max(items)
        其他的代码只是为了启动 AsyncJob 并接收结果和处理异常的干扰代码。 但是这些代码是通用的，我们可以把他们放到其他地方来让我们更加专注业务逻辑代码。
        那么如何实现呢？需要做两件事情：
        1.转换 AsyncJob 的结果
        2.转换的函数
        但是由于 Java 的限制，无法把函数作为参数， 所以需要用一个接口（或者类）并在里面定义一个转换函数：
    */


}

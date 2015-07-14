package v2;

import java.io.File;
import java.io.IOException;

public class Main {

    /*
        泛型接口
        仔细的看看这些回调接口，你会发现一个通用的模式：
        这些接口都有一个函数来返回结果（onSaved, onItemListReceived, onImageSaved）。
        这里还都有一个返回异常情况的函数（onError, onQueryFailed, onStoreFailed）。
        所以我们可以使用一个泛型接口来替代这三个接口。
        由于我们无法修改 API
        调用的参数类型，必须要创建一个包装类来调用泛型接口。
    */

    public static void main(String[] args) {
        try {
            new Helper().getTheBestOne(new Callback<File>() {
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
        看看这些新的异步操作（query, store 和 getTheBestOne）。这些函数都有同样的模式。使用一些参数来调用这些函数（query），
        同时还有一个回调接口作为参数。甚至，所有的异步操作都带有一些常规参数和一个额外的回调接口参数。如果我们把他们分离开会如何，
        让每个异步操作只有一些常规参数而把返回一个临时的对象来操作回调接口。


        如果我们返回一个临时的对象作为异步操作的回调接口处理方式，我们需要先定义这个对象。由于对象遵守通用的行为（有一个回调接口参数），
        我们定义一个能用于所有操作的对象。 我们称之为 AsyncJob。
     */


}

package v10;

import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

import java.io.File;

/**
 * Created by rayboot on 15/7/14.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("--    you and main     --");
        new Helper().getTheBestOne().subscribe(new Action1<File>() {
            @Override
            public void call(File file) {
                System.out.println("the result file = " + file.getAbsolutePath());
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
            }
        }, new Action0() {
            @Override
            public void call() {
            }
        });
    }
}

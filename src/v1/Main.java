package v1;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            new Helper().getTheBestOneEx(new Helper.ItemCallback() {
                @Override
                public void onItemSaved(File file) {
                    System.out.println("best file path = " + file.getAbsolutePath());
                }

                @Override
                public void onQueryFailed(Exception e) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("got some error with " + e.getMessage());
        }
    }
}

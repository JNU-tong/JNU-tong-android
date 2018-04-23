package kr.ac.jejunu.jnu_tong.detail.shuttle_bus;

import android.graphics.drawable.Drawable;

/**
 * Created by seung-yeol on 2018. 4. 23..
 */

public class PagerProvider {
    private String title;
    private int imgId;

    PagerProvider(String title, int imgId) {
        this.title = title;
        this.imgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public int getImgId() {
        return imgId;
    }
}

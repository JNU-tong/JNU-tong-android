package kr.ac.jejunu.jnu_tong.main.sticky_recycler;

import com.brandongogetap.stickyheaders.exposed.StickyHeader;

/**
 * Created by seung-yeol on 2018. 4. 11..
 */

public class HeaderItem implements StickyHeader , Item{
    private String headerTitle;

    public HeaderItem(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }
}

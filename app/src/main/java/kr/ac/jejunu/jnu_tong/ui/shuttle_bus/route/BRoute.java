package kr.ac.jejunu.jnu_tong.ui.shuttle_bus.route;

import kr.ac.jejunu.jnu_tong.R;

/**
 * Created by seung-yeol on 2018. 6. 8..
 */

public enum BRoute implements Route {
    정문출(1, "정문(출발)", R.drawable.route_jeongmoon),
    제2도서관해대(2, "제2도서관\n(해대 방면)", R.drawable.route_second_library),
    해양대학1호관본(3, "해양과학대학\n(교양동 방면)", R.drawable.route_heayang_1ho),
    교양강의동(13, "교양강의동", R.drawable.route_kyoyangdong),
    해대4호관(12, "해대4호관", R.drawable.route_haeyang_4ho),
    공대4호관(11,"공대4호관", R.drawable.route_gongdae_4ho),
    의학전문대학원(10, "의학전문\n대학원", R.drawable.route_medical_specialty),
    중앙도서관(9, "도서관", R.drawable.route_center_library),
    인문대학동(8, "인문대학 동쪽", R.drawable.route_inmoondae_east),
    학생생활관(7, "학생생활관", R.drawable.route_kisooksa),
    입문대학서(6, "인문대학 서쪽", R.drawable.route_inmoondae_west),
    학생회관(5, "학생회관 남쪽", R.drawable.route_sin_gwan),
    본관(4, "본관", R.drawable.route_bon_gwan),
    해양대학1호관정(14, "해양과학대학\n(정문 방면)", R.drawable.route_heayang_1ho),
    제2도서관정문(15, "제2도서관\n(정문 방면)", R.drawable.route_second_library),
    정문종(16, "정문(종점)", R.drawable.route_jeongmoon);

    private final int id;
    private final String title;
    private final Integer imgId;

    BRoute(int id, String title, int imgId) {
        this.id = id;
        this.title = title;
        this.imgId = imgId;
    }

    @Override
    public int getId(){
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Integer getImgId() {
        return imgId;
    }
}
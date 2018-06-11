package kr.ac.jejunu.jnu_tong.bus.shuttle.route;

import kr.ac.jejunu.jnu_tong.R;

/**
 * Created by seung-yeol on 2018. 6. 8..
 */

public enum ARoute implements Route {
    정문출(0, "정문(출발)", R.drawable.route_jeongmoon),
    제2도서관해대(1, "제2도서관\n(해대 방면)", R.drawable.route_second_library),
    해양대학1호관본(2,"해양과학대학\n(본관 방면)", R.drawable.route_heayang_1ho),
    본관(3, "본관", R.drawable.route_bon_gwan),
    학생회관(4, "학생회관 남쪽", R.drawable.route_sin_gwan),
    입문대학서(5, "인문대학 서쪽", R.drawable.route_inmoondae_west),
    학생생활관(6, "학생생확관", R.drawable.route_kisooksa),
    인문대학동(7, "인문대학 동쪽", R.drawable.route_inmoondae_east),
    중앙도서관(8, "도서관", R.drawable.route_center_library),
    의학전문대학원(9, "의학전문\n대학원", R.drawable.route_medical_specialty),
    공대4호관(10, "공대4호관", R.drawable.route_gongdae_4ho),
    해대4호관(11, "해대4호관", R.drawable.route_haeyang_4ho),
    교양강의동(12, "교양강의동", R.drawable.route_kyoyangdong),
    해양대학1호관정(13, "해양과학대학\n(정문 방면)", R.drawable.route_heayang_1ho),
    제2도서관정문(14, "제2도서관\n(정문 방면)", R.drawable.route_second_library),
    정문종(15, "정문(종점)", R.drawable.route_jeongmoon);

    private final int id;
    private final String title;
    private final Integer imgId;

    ARoute(int id, String title, int imgId) {
        this.id = id;
        this.title = title;
        this.imgId = imgId;
    }

    @Override
    public int getId() {
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
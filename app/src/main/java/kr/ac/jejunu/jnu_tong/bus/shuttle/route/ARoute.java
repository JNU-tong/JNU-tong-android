package kr.ac.jejunu.jnu_tong.bus.shuttle.route;

import kr.ac.jejunu.jnu_tong.R;

/**
 * Created by seung-yeol on 2018. 6. 8..
 */

public enum ARoute implements Route {
    정문출("정문(출발)", R.drawable.route_jeongmoon),
    제2도서관해대("제2도서관\n(해대 방면)", R.drawable.route_second_library),
    해양대학1호관본("해양과학대학\n(본관 방면)", R.drawable.route_heayang_1ho),
    본관("본관", R.drawable.route_bon_gwan),
    학생회관("학생회관 남쪽", R.drawable.route_sin_gwan),
    입문대학서("인문대학 서쪽", R.drawable.route_inmoondae_west),
    학생생활관("학생생확관", R.drawable.route_kisooksa),
    인문대학동("인문대학 동쪽", R.drawable.route_inmoondae_east),
    중앙도서관("도서관", R.drawable.route_center_library),
    의학전문대학원("의학전문\n대학원", R.drawable.route_medical_specialty),
    공대4호관("공대4호관", R.drawable.route_gongdae_4ho),
    해대4호관("해대4호관", R.drawable.route_haeyang_4ho),
    교양강의동("교양강의동", R.drawable.route_kyoyangdong),
    해양대학1호관정("해양과학대학\n(정문 방면)", R.drawable.route_heayang_1ho),
    제2도서관정문("제2도서관\n(정문 방면)", R.drawable.route_second_library),
    정문종("정문(종점)", R.drawable.route_jeongmoon);

    String title;
    Integer imgId;

    ARoute(String title, int imgId) {
        this.title = title;
        this.imgId = imgId;
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
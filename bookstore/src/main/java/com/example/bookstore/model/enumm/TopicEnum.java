package com.example.bookstore.model.enumm;

public enum TopicEnum {
    vanhoc("Sách văn học"),
    kinte("Sách kinh tế"),
    thieunhi("Sách thiếu nhi"),
    kynangsong("Sách kỹ năng sống"),
    bameembe("Sách bà mẹ - em bé"),
    giaokhoagiaotrinh("Sách giáo khoa - giáo trình"),
    ngoaingu("Sách học ngoại ngữ"),
    thamkhao("Sách tham khảo"),
    tudien("Từ điển"),
    kienthuctonghop("Sách kiến thức tổng hợp"),
    khoahockythuat("Sách khoa học - kỹ thuật"),
    lichsu("Sách lịch sử"),
    đienanhnhachoa("Điện ảnh - nhạc - hoạ"),
    truyentranhmangacomic("Truyện tranh, manga, comic"),
    tongiaotamlinh("Sách tôn giáo - tâm linh"),
    vanhaodialydulich("Sách văn hoá - địa lý - du lịch"),
    chinhtriphaply("Chính trị - pháp lý"),

    nonglamngunghiep("Sách nông - lâm - ngư nhiệp"),
    congnghethongtin("Sách Công nghệ thông tin"),

    yhoc("Sách y học"),
    tapchi("Tạp chí"),
    tamlygioitinh("Sách tâm lý - giới tính"),
    thuongthucgiadinh("Sách thường thức - gia đình"),
    theducthethao("Thể dục - thể thao");




    private final String NAME;

    TopicEnum(String NAME) {
        this.NAME = NAME;
    }


    @Override
    public String toString() {
        return NAME;
    }
}

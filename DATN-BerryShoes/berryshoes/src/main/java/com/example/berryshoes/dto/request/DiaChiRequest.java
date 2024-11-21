package com.example.berryshoes.dto.request;

import com.example.berryshoes.entity.KhachHang; // import KhachHang
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class DiaChiRequest {
    private String tenDuong;
    private String xaPhuong;
    private String quanHuyen;
    private String tinhThanhPho;
    private String tenNguoiNhan;
    private String sdtNguoiNhan;
    private Integer provinceId;
    private Integer districtId;
    private String wardCode;
}

package com.example.berryshoes.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class DotGiamGiaRequest {
    private Integer giaTriGiam;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp ngayBatDau;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp ngayKetThuc;

    private String nguoiTao;
    private String nguoiCapNhat;
    private Integer trangThai; // 0: không hoạt động, 1: hoạt động
}

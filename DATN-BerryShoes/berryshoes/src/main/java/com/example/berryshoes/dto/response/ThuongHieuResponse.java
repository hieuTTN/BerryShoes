package com.example.berryshoes.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ThuongHieuResponse {
    private Integer id;
    private String tenThuongHieu;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp ngayTao;

    private String nguoiTao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp lanCapNhatCuoi;

    private String nguoiCapNhat;
    private Integer trangThai;
}
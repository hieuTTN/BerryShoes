package com.example.berryshoes.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SanPham")
public class SanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne // Thiết lập mối quan hệ ManyToOne với ThuongHieu
    @JoinColumn(name = "IdThuongHieu", nullable = false)
    private ThuongHieu thuongHieu;

    @ManyToOne // Thiết lập mối quan hệ ManyToOne với ChatLieu
    @JoinColumn(name = "IdChatLieu", nullable = false)
    private ChatLieu chatLieu;

    @ManyToOne // Thiết lập mối quan hệ ManyToOne với DeGiay
    @JoinColumn(name = "IdDeGiay", nullable = false)
    private DeGiay deGiay;

    @Column(name = "MaSanPham")
    private String maSanPham;

    @Column(name = "anh")
    private String anh;

    @Column(name = "TenSanPham", nullable = false, length = 300)
    private String tenSanPham;

    @Column(name = "giaBan")
    private Integer giaBan;

    @CreationTimestamp
    @Column(name = "NgayTao", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp ngayTao;

    @Column(name = "NguoiTao", length = 100)
    private String nguoiTao;

    @UpdateTimestamp
    @Column(name = "LanCapNhatCuoi")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp lanCapNhatCuoi;

    @Column(name = "NguoiCapNhat", length = 100)
    private String nguoiCapNhat;

    @Column(name = "TrangThai", columnDefinition = "int default 1")
    private Integer trangThai;
}

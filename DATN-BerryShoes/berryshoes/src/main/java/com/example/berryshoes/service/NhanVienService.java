package com.example.berryshoes.service;

import com.example.berryshoes.dto.request.NhanVienRequest;
import com.example.berryshoes.entity.NhanVien;

import java.util.List;
import java.util.Optional;

public interface NhanVienService {
    List<NhanVien> getAllNhanVien();

    Optional<NhanVien> getNhanVienById(Integer id);

    NhanVien createNhanVien(NhanVienRequest requestDTO);

    NhanVien updateNhanVien(Integer id, NhanVienRequest requestDTO);

    void deleteNhanVien(Integer id);
}

package com.example.berryshoes.service.impl;

import com.example.berryshoes.dto.request.DotGiamGiaRequest;
import com.example.berryshoes.dto.response.DotGiamGiaResponse;
import com.example.berryshoes.entity.DotGiamGia;
import com.example.berryshoes.repository.DotGiamGiaRepository;
import com.example.berryshoes.service.DotGiamGiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class DotGiamGiaServiceImpl implements DotGiamGiaService {
    private final DotGiamGiaRepository dotGiamGiaRepository;

    @Override
    public List<DotGiamGiaResponse> getAllDotGiamGia() {
        return dotGiamGiaRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    @Override
    public Optional<DotGiamGiaResponse> getDotGiamGiaById(Integer id) {
        return dotGiamGiaRepository.findById(id)
                .map(this::convertToResponse);
    }

    @Override
    public DotGiamGiaResponse createDotGiamGia(DotGiamGiaRequest requestDTO) {
        DotGiamGia dotGiamGia = new DotGiamGia();
        dotGiamGia.setGiaTriGiam(requestDTO.getGiaTriGiam());
        dotGiamGia.setNgayBatDau(requestDTO.getNgayBatDau());
        dotGiamGia.setNgayKetThuc(requestDTO.getNgayKetThuc());
        dotGiamGia.setNguoiTao(requestDTO.getNguoiTao());
        dotGiamGia.setNguoiCapNhat(requestDTO.getNguoiCapNhat());
        dotGiamGia.setTrangThai(requestDTO.getTrangThai());
        DotGiamGia savedDotGiamGia = dotGiamGiaRepository.save(dotGiamGia);
        return convertToResponse(savedDotGiamGia);
    }

    @Override
    public DotGiamGiaResponse updateDotGiamGia(Integer id, DotGiamGiaRequest requestDTO) {
        Optional<DotGiamGia> optionalDotGiamGia = dotGiamGiaRepository.findById(id);
        if (optionalDotGiamGia.isPresent()) {
            DotGiamGia dotGiamGia = optionalDotGiamGia.get();
            dotGiamGia.setGiaTriGiam(requestDTO.getGiaTriGiam());
            dotGiamGia.setNgayBatDau(requestDTO.getNgayBatDau());
            dotGiamGia.setNgayKetThuc(requestDTO.getNgayKetThuc());
            dotGiamGia.setNguoiCapNhat(requestDTO.getNguoiCapNhat());
            dotGiamGia.setTrangThai(requestDTO.getTrangThai());
            DotGiamGia updatedDotGiamGia = dotGiamGiaRepository.save(dotGiamGia);
            return convertToResponse(updatedDotGiamGia);
        }
        return null;
    }

    @Override
    public void deleteDotGiamGia(Integer id) {
        dotGiamGiaRepository.deleteById(id);
    }

    private DotGiamGiaResponse convertToResponse(DotGiamGia dotGiamGia) {
        return DotGiamGiaResponse.builder()
                .id(dotGiamGia.getId())
                .giaTriGiam(dotGiamGia.getGiaTriGiam())
                .ngayBatDau(dotGiamGia.getNgayBatDau())
                .ngayKetThuc(dotGiamGia.getNgayKetThuc())
                .ngayTao(dotGiamGia.getNgayTao())
                .nguoiTao(dotGiamGia.getNguoiTao())
                .lanCapNhatCuoi(dotGiamGia.getLanCapNhatCuoi())
                .nguoiCapNhat(dotGiamGia.getNguoiCapNhat())
                .trangThai(dotGiamGia.getTrangThai())
                .build();
    }

    @Override
    public Page<DotGiamGia> findAllOrderByNgayTaoDESC(String keySearch, Timestamp tungaySearch, Timestamp denngaySearch, Integer ttSearch, Pageable pageable) {
        return dotGiamGiaRepository.findAllOrderByNgayTaoDESC(keySearch, tungaySearch, denngaySearch, ttSearch, pageable);
    }

    @Override
    public DotGiamGia findDotGiamGiaById(Integer id) {
        return dotGiamGiaRepository.findDotGiamGiaById(id);
    }

    @Override
    public DotGiamGia findFirstByOrderByNgayTaoDesc() {
        return dotGiamGiaRepository.findFirstByOrderByNgayTaoDesc();
    }
}

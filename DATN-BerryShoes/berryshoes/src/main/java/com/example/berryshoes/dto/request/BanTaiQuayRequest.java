package com.example.berryshoes.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BanTaiQuayRequest {

    List<SanPhamChiTietPayment> sanPhamChiTietPayment = new ArrayList<>();

}

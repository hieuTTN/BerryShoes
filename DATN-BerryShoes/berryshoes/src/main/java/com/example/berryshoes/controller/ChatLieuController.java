package com.example.berryshoes.controller;

import com.example.berryshoes.service.ChatLieuService;
import com.example.berryshoes.dto.request.ChatLieuRequest;
import com.example.berryshoes.dto.response.ChatLieuResponse;
import com.example.berryshoes.entity.ChatLieu;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/chat-lieu")
@RequiredArgsConstructor
public class ChatLieuController {
    private final ChatLieuService chatLieuService;

    // Lấy tất cả chất liệu
    @GetMapping
    public ResponseEntity<List<ChatLieuResponse>> getAllChatLieu() {
        List<ChatLieu> chatLieuList = chatLieuService.getAllChatLieu();
        List<ChatLieuResponse> responseList = chatLieuList.stream().map(chatLieu -> {
            ChatLieuResponse response = new ChatLieuResponse();
            response.setId(chatLieu.getId());
            response.setTenChatLieu(chatLieu.getTenChatLieu());
            response.setNgayTao(chatLieu.getNgayTao());
            response.setNguoiTao(chatLieu.getNguoiTao());
            response.setLanCapNhatCuoi(chatLieu.getLanCapNhatCuoi());
            response.setNguoiCapNhat(chatLieu.getNguoiCapNhat());
            response.setTrangThai(chatLieu.getTrangThai());
            return response;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }

    // Lấy chất liệu theo ID
    @GetMapping("/{id}")
    public ResponseEntity<ChatLieuResponse> getChatLieuById(@PathVariable Integer id) {
        Optional<ChatLieu> chatLieu = chatLieuService.getChatLieuById(id);
        return chatLieu.map(chat -> {
            ChatLieuResponse response = new ChatLieuResponse();
            response.setId(chat.getId());
            response.setTenChatLieu(chat.getTenChatLieu());
            response.setNgayTao(chat.getNgayTao());
            response.setNguoiTao(chat.getNguoiTao());
            response.setLanCapNhatCuoi(chat.getLanCapNhatCuoi());
            response.setNguoiCapNhat(chat.getNguoiCapNhat());
            response.setTrangThai(chat.getTrangThai());
            return ResponseEntity.ok(response);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Tạo mới chất liệu
    @PostMapping
    public ResponseEntity<ChatLieuResponse> createChatLieu(@RequestBody ChatLieuRequest requestDTO) {
        ChatLieu createdChatLieu = chatLieuService.createChatLieu(requestDTO);
        ChatLieuResponse response = new ChatLieuResponse();
        response.setId(createdChatLieu.getId());
        response.setTenChatLieu(createdChatLieu.getTenChatLieu());
        response.setNgayTao(createdChatLieu.getNgayTao());
        response.setNguoiTao(createdChatLieu.getNguoiTao());
        response.setLanCapNhatCuoi(createdChatLieu.getLanCapNhatCuoi());
        response.setNguoiCapNhat(createdChatLieu.getNguoiCapNhat());
        response.setTrangThai(createdChatLieu.getTrangThai());
        return ResponseEntity.ok(response);
    }

    // Cập nhật chất liệu
    @PostMapping("/{id}")
    public ResponseEntity<ChatLieuResponse> updateChatLieu(@PathVariable Integer id, @RequestBody ChatLieuRequest requestDTO) {
        ChatLieu updatedChatLieu = chatLieuService.updateChatLieu(id, requestDTO);
        return updatedChatLieu != null ? ResponseEntity.ok(convertToResponse(updatedChatLieu)) : ResponseEntity.notFound().build();
    }

    // Xóa chất liệu
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChatLieu(@PathVariable Integer id) {
        chatLieuService.deleteChatLieu(id);
        return ResponseEntity.noContent().build();
    }

    private ChatLieuResponse convertToResponse(ChatLieu chatLieu) {
        ChatLieuResponse response = new ChatLieuResponse();
        response.setId(chatLieu.getId());
        response.setTenChatLieu(chatLieu.getTenChatLieu());
        response.setNgayTao(chatLieu.getNgayTao());
        response.setNguoiTao(chatLieu.getNguoiTao());
        response.setLanCapNhatCuoi(chatLieu.getLanCapNhatCuoi());
        response.setNguoiCapNhat(chatLieu.getNguoiCapNhat());
        response.setTrangThai(chatLieu.getTrangThai());
        return response;
    }
    @GetMapping("/search")
    public ResponseEntity<List<ChatLieu>> searchChatLieu(@RequestParam String ten, @RequestParam(required = false) Integer trangThai) {
        List<ChatLieu> result = chatLieuService.findByTenAndTrangThai(ten, trangThai);
        return ResponseEntity.ok(result);
    }
}

package com.Event_Booking.event_booking.service;


import com.Event_Booking.event_booking.DTO.Auth.AuthResponseDTO;
import com.Event_Booking.event_booking.DTO.Auth.LoginRequestDTO;
import com.Event_Booking.event_booking.DTO.Auth.RegisterRequestDTO;
import com.Event_Booking.event_booking.DTO.Users.UserResponseDTO;

public interface AuthService {

    // Đăng ký tài khoản mới
    UserResponseDTO register(RegisterRequestDTO request);

    // Đăng nhập → trả về access token + refresh token
    AuthResponseDTO login(LoginRequestDTO request, String ipAddress);

    // Dùng refresh token để lấy access token mới
    AuthResponseDTO refreshToken(String refreshToken, String ipAddress);

    // Logout device hiện tại (revoke refresh token này)
    void logout(String refreshToken);

    // Logout tất cả thiết bị
    void logoutAll(String accessToken);
}

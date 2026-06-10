package com.Event_Booking.event_booking.DTO.Auth;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotBlank(message = "Mật khẩu không được để trống")
    private String password;

    // Thiết bị đăng nhập — lưu vào refresh_tokens.device_info
    private String deviceInfo;
}


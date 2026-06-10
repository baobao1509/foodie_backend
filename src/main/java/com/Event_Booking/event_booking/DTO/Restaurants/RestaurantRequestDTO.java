package com.Event_Booking.event_booking.DTO.Restaurants;
import java.math.BigDecimal;
import java.time.LocalTime;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RestaurantRequestDTO {

    @NotBlank(message = "Tên nhà hàng không được để trống")
    @Size(max = 200)
    private String name;

    // Slug tự sinh từ name nếu không truyền lên
    private String slug;

    private String description;

    @Size(max = 20)
    private String phone;

    @Email(message = "Email không hợp lệ")
    private String email;

    @NotBlank(message = "Địa chỉ không được để trống")
    private String fullAddress;

    private String ward;
    private String district;

    @NotBlank(message = "Thành phố không được để trống")
    private String city;

    @DecimalMin(value = "-90.0")  @DecimalMax(value = "90.0")
    private BigDecimal latitude;

    @DecimalMin(value = "-180.0") @DecimalMax(value = "180.0")
    private BigDecimal longitude;

    private LocalTime openingTime;
    private LocalTime closingTime;

    @DecimalMin(value = "0.0", message = "Giá trị đơn tối thiểu không được âm")
    private BigDecimal minOrderValue;

    @DecimalMin(value = "0.0", message = "Phí giao hàng không được âm")
    private BigDecimal deliveryFee;
}

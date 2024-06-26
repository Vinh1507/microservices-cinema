# Project microservices với Spring boot và kubernetes

## Hệ thống bao gồm các dịch vụ:
- cinema_client: Dịch vụ hiển thị giao diện cho người dùng
- cinema_user_service: Dịch vụ quản lý thông tin users trong hệ thống
- cinema_movie_show_service: Dịch vụ quản lý phòng chiếu, lịch chiếu, ghế ngồi, vé xem phim
- cinema_online_booking_service: Dịch vụ xử lý đặt vé xem phim của người dùng
- cinema_ticket_bill_service: Dịch vụ thực hiện truy vấn các yêu cầu liên quan đến hóa đơn mua vé của khách hàng
- cinema_supplier_service: Dịch vụ thực hiện các yêu cầu liên quan tới Nhà cung cấp phim
- cinema_importing_bill_service: Dịch vụ thực hiện các yêu cầu liên quan tới phiếu nhập phim
- cinema_handle_import_movie_service: Dịch vụ thực hiện các yêu cầu nghiệp vụ nhập phim
- cinema_handle_statistic_service: Dịch vụ thực hiện các thống kê về phim, suất chiếu, hóa đơn trong hệ thống


[Tài liệu phân tích thiết kế](./documentation/Tài%20liệu%20phân%20tích%20thiết%20kế.pdf)
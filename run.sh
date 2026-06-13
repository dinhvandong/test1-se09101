#!/bin/bash

# Di chuyển về thư mục chứa script
cd "$(dirname "$0")"

echo "===================================================="
echo "    BẮT ĐẦU BIÊN DỊCH VÀ CHẠY DỰ ÁN QUẢN LÝ SINH VIÊN"
echo "===================================================="

# Biên dịch các file Java trong src/student/
echo "[1/2] Đang biên dịch mã nguồn Java..."
javac src/student/*.java

if [ $? -eq 0 ]; then
    echo "[2/2] Biên dịch thành công! Đang khởi chạy chương trình..."
    echo "===================================================="
    # Chạy chương trình
    java -cp src student.Main
else
    echo "[LỖI] Quá trình biên dịch thất bại. Vui lòng kiểm tra lại lỗi biên dịch ở trên!"
    exit 1
fi

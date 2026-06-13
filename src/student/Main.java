package student;

import java.util.List;
import java.util.Scanner;

/**
 * Lớp chạy chương trình chính, xử lý giao diện người dùng console.
 */
public class Main {
    private static final StudentManager manager = new StudentManager();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Khởi tạo một số dữ liệu mẫu để thuận tiện cho việc kiểm thử
        initMockData();

        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    addStudentMenu();
                    break;
                case "2":
                    displayAllStudents();
                    break;
                case "3":
                    sortStudentsMenu();
                    break;
                case "4":
                    searchStudentMenu();
                    break;
                case "5":
                    updateStudentMenu();
                    break;
                case "6":
                    deleteStudentMenu();
                    break;
                case "7":
                    System.out.println("Cảm ơn bạn đã sử dụng chương trình. Tạm biệt!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn từ 1 đến 7.");
            }
            System.out.println("\nNhấn Enter để tiếp tục...");
            scanner.nextLine();
        }
    }

    private static void printMenu() {
        System.out.println("\n================ HỆ THỐNG QUẢN LÝ SINH VIÊN ================");
        System.out.println("1. Thêm mới sinh viên");
        System.out.println("2. Hiển thị danh sách sinh viên");
        System.out.println("3. Sắp xếp sinh viên theo điểm tăng dần");
        System.out.println("4. Tìm kiếm sinh viên (theo ID hoặc Tên)");
        System.out.println("5. Cập nhật thông tin sinh viên theo ID");
        System.out.println("6. Xóa sinh viên khỏi hệ thống");
        System.out.println("7. Thoát chương trình");
        System.out.print("Vui lòng chọn chức năng (1-7): ");
    }

    private static void initMockData() {
        manager.addStudent(new Student("SV001", "Nguyen Van An", 8.5));
        manager.addStudent(new Student("SV002", "Tran Thi Binh", 4.8));
        manager.addStudent(new Student("SV003", "Le Hoang Cuong", 9.2));
        manager.addStudent(new Student("SV004", "Pham Minh Duc", 6.7));
        manager.addStudent(new Student("SV005", "Hoang Ngoc Diep", 7.4));
    }

    private static void addStudentMenu() {
        System.out.println("\n--- THÊM MỚI SINH VIÊN ---");
        
        // Nhập ID
        String id;
        while (true) {
            System.out.print("Nhập mã sinh viên (ví dụ SV006): ");
            id = scanner.nextLine().trim();
            if (id.isEmpty()) {
                System.out.println("Mã sinh viên không được để trống!");
            } else if (manager.findStudentById(id) != null) {
                System.out.println("Mã sinh viên này đã tồn tại trên hệ thống! Vui lòng nhập mã khác.");
            } else {
                break;
            }
        }

        // Nhập Tên
        String name;
        while (true) {
            System.out.print("Nhập họ và tên sinh viên: ");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Họ tên sinh viên không được để trống!");
            } else {
                break;
            }
        }

        // Nhập Điểm
        double score = -1;
        while (true) {
            System.out.print("Nhập điểm số (từ 0.0 đến 10.0): ");
            String scoreStr = scanner.nextLine().trim();
            try {
                score = Double.parseDouble(scoreStr);
                if (score < 0.0 || score > 10.0) {
                    System.out.println("Điểm số phải nằm trong khoảng từ 0.0 đến 10.0!");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Điểm số phải là một số thực hợp lệ!");
            }
        }

        Student s = new Student(id, name, score);
        if (manager.addStudent(s)) {
            System.out.println("Thêm sinh viên thành công!");
        } else {
            System.out.println("Có lỗi xảy ra, thêm sinh viên thất bại.");
        }
    }

    private static void displayAllStudents() {
        System.out.println("\n--- DANH SÁCH TẤT CẢ SINH VIÊN ---");
        manager.printTable(manager.getStudents());
    }

    private static void sortStudentsMenu() {
        System.out.println("\n--- SẮP XẾP SINH VIÊN THEO ĐIỂM TĂNG DẦN ---");
        System.out.println("1. Sử dụng thuật toán Sắp xếp nổi bọt (Bubble Sort - Học thuật)");
        System.out.println("2. Sử dụng thư viện chuẩn của Java (Java Collections/Comparator - Tối ưu)");
        System.out.print("Lựa chọn phương pháp (1-2): ");
        String choice = scanner.nextLine().trim();
        
        long startTime = System.nanoTime();
        if (choice.equals("1")) {
            manager.bubbleSortByScoreAscending();
            long endTime = System.nanoTime();
            System.out.println("Đã sắp xếp thành công bằng thuật toán Bubble Sort trong " + (endTime - startTime) + " ns!");
        } else if (choice.equals("2")) {
            manager.sortByScoreAscending();
            long endTime = System.nanoTime();
            System.out.println("Đã sắp xếp thành công bằng Java Comparator trong " + (endTime - startTime) + " ns!");
        } else {
            System.out.println("Lựa chọn không hợp lệ, không thực hiện sắp xếp.");
            return;
        }

        // Hiển thị lại danh sách sau khi sắp xếp
        manager.printTable(manager.getStudents());
    }

    private static void searchStudentMenu() {
        System.out.println("\n--- TÌM KIẾM SINH VIÊN ---");
        System.out.println("1. Tìm kiếm theo Mã sinh viên");
        System.out.println("2. Tìm kiếm theo Họ tên");
        System.out.print("Lựa chọn kiểu tìm kiếm (1-2): ");
        String choice = scanner.nextLine().trim();

        if (choice.equals("1")) {
            System.out.print("Nhập mã sinh viên cần tìm: ");
            String id = scanner.nextLine().trim();
            Student s = manager.findStudentById(id);
            if (s != null) {
                System.out.println("Kết quả tìm kiếm:");
                System.out.println("+--------------+---------------------------+----------+----------------------+");
                System.out.println("| Mã Sinh Viên | Họ và Tên                 | Điểm Số  | Học Lực              |");
                System.out.println("+--------------+---------------------------+----------+----------------------+");
                System.out.println(s);
                System.out.println("+--------------+---------------------------+----------+----------------------+");
            } else {
                System.out.println("Không tìm thấy sinh viên nào có mã: " + id);
            }
        } else if (choice.equals("2")) {
            System.out.print("Nhập tên hoặc từ khóa tìm kiếm: ");
            String name = scanner.nextLine().trim();
            List<Student> results = manager.searchByName(name);
            if (!results.isEmpty()) {
                System.out.println("Kết quả tìm kiếm:");
                manager.printTable(results);
            } else {
                System.out.println("Không tìm thấy sinh viên nào khớp với tên: " + name);
            }
        } else {
            System.out.println("Lựa chọn không hợp lệ.");
        }
    }

    private static void updateStudentMenu() {
        System.out.println("\n--- CẬP NHẬT THÔNG TIN SINH VIÊN ---");
        System.out.print("Nhập mã sinh viên cần cập nhật: ");
        String id = scanner.nextLine().trim();
        Student s = manager.findStudentById(id);

        if (s == null) {
            System.out.println("Không tìm thấy sinh viên nào có mã: " + id);
            return;
        }

        System.out.println("Thông tin hiện tại:");
        System.out.println(s);

        System.out.print("Nhập họ tên mới (nhấn Enter để giữ nguyên: " + s.getName() + "): ");
        String newName = scanner.nextLine().trim();
        if (newName.isEmpty()) {
            newName = s.getName();
        }

        double newScore = s.getScore();
        while (true) {
            System.out.print("Nhập điểm mới (nhấn Enter để giữ nguyên: " + s.getScore() + "): ");
            String scoreStr = scanner.nextLine().trim();
            if (scoreStr.isEmpty()) {
                break;
            }
            try {
                newScore = Double.parseDouble(scoreStr);
                if (newScore < 0.0 || newScore > 10.0) {
                    System.out.println("Điểm số phải nằm trong khoảng từ 0.0 đến 10.0!");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Điểm số phải là một số thực hợp lệ!");
            }
        }

        if (manager.updateStudent(id, newName, newScore)) {
            System.out.println("Cập nhật thông tin sinh viên thành công!");
        } else {
            System.out.println("Có lỗi xảy ra, cập nhật thất bại.");
        }
    }

    private static void deleteStudentMenu() {
        System.out.println("\n--- XÓA SINH VIÊN ---");
        System.out.print("Nhập mã sinh viên cần xóa: ");
        String id = scanner.nextLine().trim();
        Student s = manager.findStudentById(id);

        if (s == null) {
            System.out.println("Không tìm thấy sinh viên nào có mã: " + id);
            return;
        }

        System.out.println("Thông tin sinh viên sẽ bị xóa:");
        System.out.println(s);
        System.out.print("Bạn có chắc chắn muốn xóa sinh viên này? (Y/N): ");
        String confirm = scanner.nextLine().trim();

        if (confirm.equalsIgnoreCase("Y")) {
            if (manager.deleteStudent(id)) {
                System.out.println("Đã xóa sinh viên thành công!");
            } else {
                System.out.println("Có lỗi xảy ra, xóa thất bại.");
            }
        } else {
            System.out.println("Hủy thao tác xóa.");
        }
    }
}

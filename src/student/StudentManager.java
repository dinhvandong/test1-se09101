package student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Lớp quản lý danh sách sinh viên.
 */
public class StudentManager {
    private List<Student> studentList;

    public StudentManager() {
        this.studentList = new ArrayList<>();
    }

    /**
     * Thêm sinh viên mới. Kiểm tra trùng mã sinh viên.
     * @param student Đối tượng sinh viên cần thêm.
     * @return true nếu thêm thành công, false nếu mã sinh viên đã tồn tại.
     */
    public boolean addStudent(Student student) {
        if (findStudentById(student.getId()) != null) {
            return false; // Trùng mã sinh viên
        }
        studentList.add(student);
        return true;
    }

    /**
     * Lấy toàn bộ danh sách sinh viên.
     * @return Danh sách sinh viên.
     */
    public List<Student> getStudents() {
        return studentList;
    }

    /**
     * Tìm kiếm sinh viên theo mã ID.
     * @param id Mã sinh viên cần tìm.
     * @return Đối tượng Student nếu tìm thấy, ngược lại là null.
     */
    public Student findStudentById(String id) {
        for (Student s : studentList) {
            if (s.getId().equalsIgnoreCase(id.trim())) {
                return s;
            }
        }
        return null;
    }

    /**
     * Tìm kiếm các sinh viên theo tên (không phân biệt hoa thường, tìm kiếm tương đối).
     * @param name Từ khóa tên cần tìm.
     * @return Danh sách các sinh viên khớp với tên.
     */
    public List<Student> searchByName(String name) {
        List<String> keywords = List.of(name.trim().toLowerCase().split("\\s+"));
        List<Student> result = new ArrayList<>();
        for (Student s : studentList) {
            String studentNameLower = s.getName().toLowerCase();
            boolean match = true;
            for (String keyword : keywords) {
                if (!studentNameLower.contains(keyword)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                result.add(s);
            }
        }
        return result;
    }

    /**
     * Cập nhật thông tin sinh viên theo mã ID.
     * @param id Mã sinh viên cần sửa.
     * @param newName Họ tên mới.
     * @param newScore Điểm mới.
     * @return true nếu cập nhật thành công, false nếu không tìm thấy sinh viên.
     */
    public boolean updateStudent(String id, String newName, double newScore) {
        Student s = findStudentById(id);
        if (s == null) {
            return false;
        }
        s.setName(newName);
        s.setScore(newScore);
        return true;
    }

    /**
     * Xóa sinh viên theo mã ID.
     * @param id Mã sinh viên cần xóa.
     * @return true nếu xóa thành công, false nếu không tìm thấy sinh viên.
     */
    public boolean deleteStudent(String id) {
        Student s = findStudentById(id);
        if (s == null) {
            return false;
        }
        studentList.remove(s);
        return true;
    }

    /**
     * Sắp xếp danh sách sinh viên theo điểm tăng dần sử dụng bộ so sánh (Comparator) tiêu chuẩn của Java.
     */
    public void sortByScoreAscending() {
        studentList.sort(Comparator.comparingDouble(Student::getScore));
    }

    /**
     * Sắp xếp danh sách sinh viên theo điểm tăng dần sử dụng thuật toán sắp xếp nổi bọt (Bubble Sort).
     * Thuật toán này hữu ích cho mục đích học tập cấu trúc dữ liệu và giải thuật.
     */
    public void bubbleSortByScoreAscending() {
        int n = studentList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (studentList.get(j).getScore() > studentList.get(j + 1).getScore()) {
                    // Tráo đổi vị trí của phần tử j và j+1
                    Student temp = studentList.get(j);
                    studentList.set(j, studentList.get(j + 1));
                    studentList.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * In danh sách sinh viên ra console dưới dạng bảng đẹp mắt.
     */
    public void printTable(List<Student> list) {
        if (list.isEmpty()) {
            System.out.println("Danh sách trống.");
            return;
        }
        System.out.println("+--------------+---------------------------+----------+----------------------+");
        System.out.println("| Mã Sinh Viên | Họ và Tên                 | Điểm Số  | Học Lực              |");
        System.out.println("+--------------+---------------------------+----------+----------------------+");
        for (Student s : list) {
            System.out.println(s);
        }
        System.out.println("+--------------+---------------------------+----------+----------------------+");
    }
}

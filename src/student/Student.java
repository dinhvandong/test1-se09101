package student;

/**
 * Lớp đại diện cho một Sinh viên.
 */
public class Student {
    private String id;
    private String name;
    private double score;

    public Student(String id, String name, double score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    /**
     * Tự động xếp loại học lực của sinh viên dựa trên điểm số.
     * @return Xếp loại dưới dạng chuỗi (Tiếng Việt & Tiếng Anh đi kèm)
     */
    public String getRank() {
        if (score < 0.0 || score > 10.0) {
            return "Không hợp lệ";
        }
        if (score < 5.0) {
            return "Yếu (Fail)";
        } else if (score < 6.5) {
            return "Trung bình (Medium)";
        } else if (score < 7.5) {
            return "Khá (Good)";
        } else if (score < 9.0) {
            return "Giỏi (Very Good)";
        } else {
            return "Xuất sắc (Excellent)";
        }
    }

    @Override
    public String toString() {
        return String.format("| %-12s | %-25s | %-8.1f | %-20s |", id, name, score, getRank());
    }
}

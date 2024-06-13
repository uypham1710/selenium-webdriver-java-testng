package javaTester;

public class Topic_09_Array {
    int[] studentAge = {14, 5,4 ,2,3};
    String[] studentName = {"Nguyễn Văn A", "Văn Tèo"};

    public static void main(String[] args) {
        String[] studentAddress = new String[5];
        studentAddress[0] = "Văn Từng";
        studentAddress[1] = "Thảo";
        studentAddress[2] = "Hùng";
        studentAddress[3] = "Huy";
        studentAddress[4] = "Trinh";

        System.out.println(studentAddress[3]);
        for (int i = 0; i < studentAddress.length; i++) {
            System.out.println(studentAddress[i]);
        }
    }
}

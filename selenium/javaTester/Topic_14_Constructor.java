package javaTester;

public class Topic_14_Constructor {

    // là 1 hàm cùng tên với class
    // ko có kiểu dữ liệu trả về
    // trong 1 class có thể có nhiều constructor
    // 1 class nếu ko define cái constructor cụ thể thì nó sẽ có 1 constuctor rỗng (default)
    // nếu mình define thì khi khởi tạo bắt buộc phải gọi tới constructor mình define
    public Topic_14_Constructor(String name){
        System.out.println(name);
    }
    public static void main(String[] args){
        Topic_14_Constructor topic = new Topic_14_Constructor("automation");
    }
}

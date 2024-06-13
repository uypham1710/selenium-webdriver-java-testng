package javaTester;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Topic_11_List {
    @Test
    public void testList(){
        // ArrayList - truy xuất dữ liệu (query)
        // LinkedList - thêm sửa xóa
        List<String> studenName = new ArrayList<String>();
        studenName.add("Nguyễn Văn B");
        studenName.add("Hoàng Văn Nam");
        studenName.add("Nguyễn Thị Bưởi");

        // 3 element trong list
        System.out.println(studenName.size());

        System.out.println(studenName.get(0));
        System.out.println(studenName.size() - 1);
    }
}

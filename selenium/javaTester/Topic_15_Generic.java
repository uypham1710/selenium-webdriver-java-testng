package javaTester;

import com.beust.ah.A;

import java.util.ArrayList;
import java.util.List;

public class Topic_15_Generic {
    public static void main(String[] args) {
        // Generic
        List<String> students = new ArrayList<String>();
        students.add("uy");
        students.add("pham");
        students.add("Duy");

        //Non generic
        List addressess = new ArrayList<>();
        addressess.add("avc");
        addressess.add(15);
        addressess.add(true);


    }
}

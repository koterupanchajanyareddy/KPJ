import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

public class Solution {
    Hashtable<Integer, String> h1;
    Hashtable<Integer, Integer> h2;

    public Solution(String fileName1, String filename2) throws IOException {
        h1 = new Hashtable<Integer, String>();
        this.parseData(fileName1);
        h2 = new Hashtable<>();
        this.parseLogs(filename2);
        
    }

    private void parseData(String fileName) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(fileName + ".txt"));
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(";");
            h1.put(Integer.parseInt(data[0]), data[1]);
        }
    }
    private void parseLogs(String fileName) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(fileName + ".txt"));
        String row;
        for (int i = 0; i < h1.size() + 1; i++) {
                h2.put(i, 0);
        }
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(" ");
            String[] temp = data[1].split(",");
            int integer = h2.get(Integer.parseInt(data[3]));
            h2.put(Integer.parseInt((String)data[3]), integer + 1);
        }
    }
    public void topOrder(int top) {
        List<Integer> l = new ArrayList<Integer>(h2.values());
        int size = h1.size();
        Collections.sort(l);
        l = l.subList(l.size() - top,l.size());
        Collections.sort(l);
        for (int i = top - 1; i >= 0 ; i--) {
            for (int j = 0; j < size; j++) {
                if (h2.get(j) == l.get(i)) System.out.println(h1.get(j)+","+" " + l.get(i));
            }
            
        }
    }
public static void main(String[] args) throws IOException {
    Solution sol = new Solution("emails", "email-logs");
    sol.topOrder(10);
}
}
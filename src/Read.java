
import java.io.*;
public class Read{
    public static  void main(String args[])throws Exception {
        FileReader fr = new FileReader("/Users/ts-sathi.reddy/Desktop/java_examples/SpringBoot/example/src/sample.txt");
        BufferedReader bf =  new BufferedReader(fr);
        String s = "";
        FileWriter fw = new FileWriter("out.txt");
        FileWriter fws = new FileWriter("out2.txt");


        int count = -1;
            while (count < 1000 && (s = bf.readLine()) != null){
                System.out.println(s);
                count++;
//                System.out.println(count);
            }
        fw.write(s);
        fws.write(s);
        fr.close();
        fw.close();
    }
}

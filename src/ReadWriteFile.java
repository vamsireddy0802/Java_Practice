
import java.io.*;
class ReadWriteFile {
    public static void main(String[] args)
    {
        try {
            FileReader fr = new FileReader("/Users/ts-sathi.reddy/Desktop/java_examples/SpringBoot/example/src/sample.txt");
            FileWriter fw = new FileWriter("/Users/ts-sathi.reddy/Desktop/java_examples/SpringBoot/example/src/out.txt");
            FileWriter fws = new FileWriter("/Users/ts-sathi.reddy/Desktop/java_examples/SpringBoot/example/src/out2.txt");
            String str = " ";
            int i;
            while ((i = fr.read()) != -1) {
                str += (char)i;
            }
            System.out.println(str);

            fw.write(str);
            fws.write(str);
            fr.close();
            fw.close();
            fws.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}

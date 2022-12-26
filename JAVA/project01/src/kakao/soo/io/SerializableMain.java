package kakao.soo.io;

import java.io.*;

public class SerializableMain {
    public static void main(String[] args) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./sample.dat", true))) {
            Data data = new Data(1, "adam", "군계");
            oos.writeObject(data);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./sample.dat"))) {
            Data data = (Data) ois.readObject();
            System.out.println(data);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}

package utils;

import java.io.*;

public class FileUtil {

    public static void ghiFile(String path, Object obj) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
    oos.writeObject(obj);
} catch (Exception e) {
    System.out.println("Lỗi ghi file: " + e.getMessage());
}
}

public static Object docFile(String path) {
    try {
        File f = new File(path);
        if (!f.exists()) return null;

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
        return ois.readObject();

    } catch (Exception e) {
        return null;
    }
}
}

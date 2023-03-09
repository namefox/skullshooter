package skullshooter.engine;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;

// Pre-loads assets to a HashMap for easy access.
public final class Assets {

    private static HashMap<String, Object> assets;

    public static void init() throws IOException {
        assets = new HashMap<>();

        map(new File("assets/"), "");
    }

    private static void map(File dir, String path) throws IOException {
        File[] files = dir.listFiles();
        if (files == null) return;

        for (File file:
             files) {
            String p = path + (path.isBlank() ? "" : "/") + file.getName();
            if (file.isDirectory()) {
                map(file, p);
                continue;
            }

            Object value = getValueFrom(file);
            assets.put(p, value);
        }
    }

    public static BufferedImage getImage(String str) {
        return (BufferedImage) assets.get(str);
    }

    private static Object getValueFrom(File file) throws IOException {
        String mimeType = Files.probeContentType(file.toPath());

        if (mimeType.contains("image/")) return ImageIO.read(file);
        if (mimeType.contains("audio/")) return new Sound(file);

        if (file.length() <= 3000000)
            return Files.readString(file.toPath());

        return "error/file-too-long";
    }
}
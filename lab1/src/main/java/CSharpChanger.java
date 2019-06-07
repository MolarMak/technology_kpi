import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CSharpChanger {

    public String[] getDirectoryList() {
        File file = new File("example");
        String[] directories = file.list(new FilenameFilter() {
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
        return directories;
    }

    public void changeIfOnFi(String filepath) {
        try {
            Path path = Paths.get(filepath);
            Charset charset = StandardCharsets.UTF_8;

            String content = new String(Files.readAllBytes(path), charset);
            content = content.replaceAll("if", "fi");
            Files.write(path, content.getBytes(charset));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

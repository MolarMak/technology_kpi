import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

public class ChangeWorker implements Callable<Object[]> {

    private String directoryName;
    private CSharpChanger changer = new CSharpChanger();

    public ChangeWorker(String directoryName) {
        this.directoryName = directoryName;
    }

    public Object[] call() throws Exception {
        try (Stream<Path> paths = Files.walk(Paths.get("example/" + directoryName + "/"))) {
            return (Object[]) paths
                    .filter(Files::isRegularFile)
                    .filter(it -> it.getFileName().toString().endsWith(".cs"))
                    .peek(it -> changer.changeIfOnFi(it.toString()))
                    .map(it -> "example/" + directoryName + "/" + it.toString()).toArray();
        }
    }
}

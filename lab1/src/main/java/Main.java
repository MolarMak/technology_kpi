import java.util.Arrays;
import java.util.concurrent.*;
public class Main {
    public static void main(String[] args) throws InterruptedException {
        CSharpChanger changer = new CSharpChanger();
        String[] directories = changer.getDirectoryList();
        System.out.println(Arrays.toString(directories));

        ExecutorService service =  Executors.newFixedThreadPool(directories.length);
        CompletionService<Object[]> completionService =
                new ExecutorCompletionService<>(service);
        for(int i = 0; i < directories.length; i++) {
            ChangeWorker changeWorker = new ChangeWorker(directories[i]);
            completionService.submit(changeWorker);
        }
        int received = 0;
        while (received < directories.length) {
            Future<Object[]> results = completionService.take();
            received++;
            try {
                Object[] result = results.get();
                for(Object res: result) {
                    System.out.println(res);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

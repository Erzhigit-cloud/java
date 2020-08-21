package primary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FileToList {
    public List<String> transform (String fileName){
        Stream<String> streamFromFiles = null;
        try {
            streamFromFiles = Files.lines(Paths.get(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Arrays.asList(streamFromFiles
                .reduce((res, next) -> res + " " + next)
                .map(Object :: toString)
                .orElse(" ")
                .split("\\s+"));
    }


}

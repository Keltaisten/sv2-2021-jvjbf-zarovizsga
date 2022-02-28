package videos;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class VideoPlatform {
    private List<Channel> channels = new ArrayList<>();

    public void readDataFromFile(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            br.readLine();
            while (br.ready()) {
                String line = br.readLine();
                String[] fields = line.split(";");
                channels.add(new Channel(fields[0], Integer.parseInt(fields[1]), Integer.parseInt(fields[2])));
            }
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot open file for read!", ioe);
        }
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public int calculateSumOfVideos() {
        return channels.stream().mapToInt(Channel::getNumber_of_videos).sum();
    }
}

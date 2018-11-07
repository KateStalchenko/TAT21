import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Katsiaryna Stalchanka
 * @since 04.11.2018
 */
class SongUtils {

    Map<String, List<String>> getDuplicatedSongs(ArrayList<Song> songs) {
        Map<String, List<Song>> songsByChecksum = songs.stream().collect(Collectors.groupingBy(Song::getChecksum));
        Map<String, List<String>> songsByChecksumWithPath = new HashMap<>();

        for (String keyCheckSum : songsByChecksum.keySet()) {
            List<Song> tempListSongs = songsByChecksum.get(keyCheckSum);
            if (tempListSongs.size() > 1) {
                Log.info(String.format("The duplicated songs with the same checksum: %s", keyCheckSum));
                for (Song tempSong : tempListSongs) {
                    Log.info(String.format("Checksum: %s. Song: %s. Location: %s.",tempSong.getChecksum(), tempSong.getName(), tempSong.getLink()));
                    if (!songsByChecksumWithPath.containsKey(keyCheckSum)) {
                        songsByChecksumWithPath.put(keyCheckSum, new ArrayList<String>() {{
                            add(tempSong.getLink());
                        }});
                    } else {
                        songsByChecksumWithPath.get(keyCheckSum).add(tempSong.getLink());
                    }
                }
            }
        }
        return songsByChecksumWithPath;
    }
}


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

    Map<String, List<String>> getDuplicatedSongsWithTheSameChecksum(ArrayList<Song> songs) {
        Map<String, List<Song>> songsByChecksum = songs.stream().collect(Collectors.groupingBy(Song::getChecksum));
        Map<String, List<String>> songsByChecksumWithPath = new HashMap<>();

        for (String keyCheckSum : songsByChecksum.keySet()) {
            List<Song> tempListSongs = songsByChecksum.get(keyCheckSum);
            if (tempListSongs.size() > 1) {
                Log.info(String.format("The duplicated songs with the same checksum: %s", keyCheckSum));
                for (Song tempSong : tempListSongs) {
                    Log.info(String.format("Checksum: %s. Song: %s. Location: %s.", tempSong.getChecksum(),
                            tempSong.getTitle(), tempSong.getLink()));
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

    Map<SongAlbumArtistKey, List<String>> getDuplicatedSongArtistAlbumName(ArrayList<Song> songs) {
        Map<SongAlbumArtistKey, List<Song>> songAlbumArtistKeyListMap = songs
                .stream()
                .collect(Collectors.groupingBy(
                        SongUtils::getSongAlbumArtistKey));

        Map<SongAlbumArtistKey, List<String>> duplicatedSongArtistAlbumNamePaths = new HashMap<>();

        for (SongAlbumArtistKey songAlbumArtistKey : songAlbumArtistKeyListMap.keySet()) {
            List<Song> tempSongs = songAlbumArtistKeyListMap.get(songAlbumArtistKey);

            if (tempSongs.size() > 1) {
                Log.info(String.format("The duplicated songs with the same Artist, Album Name and Title: %s, %s, %s",
                        songAlbumArtistKey.getArtist(), songAlbumArtistKey.getAlbumName(), songAlbumArtistKey.getSong()));
                for (Song songKey : tempSongs) {
                    Log.info(String.format("Path: %s.", songKey.getLink()));
                    if (!duplicatedSongArtistAlbumNamePaths.containsKey(songAlbumArtistKey)) {
                        duplicatedSongArtistAlbumNamePaths.put(songAlbumArtistKey,
                                new ArrayList<String>() {{
                                    add(songKey.getLink());
                                }});
                    } else {
                        duplicatedSongArtistAlbumNamePaths.get(songAlbumArtistKey).add(songKey.getLink());
                    }
                }
            }
        }

        return duplicatedSongArtistAlbumNamePaths;
    }

    private static SongAlbumArtistKey getSongAlbumArtistKey(Song song) {
        SongAlbumArtistKey songAlbumArtistKey = new SongAlbumArtistKey();
        songAlbumArtistKey.setSong(song.getTitle());
        songAlbumArtistKey.setAlbumName(song.getAlbumName());
        songAlbumArtistKey.setArtist(song.getArtist());
        return songAlbumArtistKey;
    }
}


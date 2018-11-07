import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * @author Katsiaryna Stalchanka
 * @since 03.11.2018
 */
public class FileTools {
    ArrayList<File> getAllMp3Files_ApacheCommons(String path) throws InvalidPathException {
        String type[] = {"mp3", "wav", "aiff", "ape", "flac"};
        return new ArrayList<>(FileUtils.listFiles(new File(path), type, true));
    }

    public ArrayList<File> getAllMp3Files_SteamAPI(String path) throws IOException {
        ArrayList<File> mp3Files = new ArrayList<>();
        Files.walk(Paths.get(path)).forEach(rootFile -> {
            if (rootFile.getFileName().toString().endsWith("mp3"))
                mp3Files.add(rootFile.toFile());
        });
        return mp3Files;
    }

    ArrayList<Song> createListOfSongs(ArrayList<File> rootList) throws IOException {
        ArrayList<Song> songs = new ArrayList<>();
        for (File song : rootList) {
            AudioFile audioFile;
            int duration;
            try {
                audioFile = AudioFileIO.read(song);
                duration = audioFile.getAudioHeader().getTrackLength();
            } catch (Exception e) {
                System.out.printf("%s not valid and skipped", song.getName());
                continue;
            }

            byte[] fileByte = Files.readAllBytes(Paths.get(song.getPath()));
            String checkSum = DigestUtils.md5Hex(fileByte);

            Song songMp3 = new Song();
            songMp3.setDuration(duration);
            songMp3.setLink(song.getAbsolutePath());
            songMp3.setName(verifyHasName(song));
            songMp3.setAlbumName(verifyAlbumName(audioFile));
            songMp3.setArtist(verifyArtistName(audioFile));
            songMp3.setChecksum(checkSum);
            songs.add(songMp3);
        }
        return songs;
    }

    private String verifyHasName(File rootSong) {
        if ((rootSong.getName() == null) || rootSong.getName().isEmpty()) {
            return "Unknown Name";
        }
        return rootSong.getName();
    }

    private String verifyAlbumName(AudioFile rootSong) {
        if (rootSong.getTag() == null) {
            return "Unknown Album Name";
        }
        if ((rootSong.getTag().getFirst(FieldKey.ALBUM) == null) ||
                rootSong.getTag().getFirst(FieldKey.ALBUM).isEmpty()) {
            return "Unknown Album Name";
        }
        return rootSong.getTag().getFirst(FieldKey.ALBUM);
    }

    private String verifyArtistName(AudioFile rootSong) {
        if (rootSong.getTag() == null) {
            return "Unknown Artist";
        }
        if ((rootSong.getTag().getFirst(FieldKey.ALBUM_ARTIST) == null) ||
                rootSong.getTag().getFirst(FieldKey.ALBUM_ARTIST).isEmpty()) {
            return "Unknown Artist";
        }
        return rootSong.getTag().getFirst(FieldKey.ALBUM_ARTIST);
    }

}

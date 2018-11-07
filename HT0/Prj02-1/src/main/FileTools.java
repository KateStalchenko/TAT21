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
 * Represents useful methods to work with files
 *
 * @author Katsiaryna Stalchanka
 * @since 03.11.2018
 */
class FileTools {
    /**
     * Finds all music files in indicated directory
     *
     * @param path represents String value where all music files must be found
     * @return ArrayList<File> of found files
     * @throws InvalidPathException when invalid path is indicated
     */
    ArrayList<File> getAllMusicFiles(String path) throws InvalidPathException {
        String type[] = {"mp3", "wav", "aiff", "ape", "flac"};
        return new ArrayList<>(FileUtils.listFiles(new File(path), type, true));
    }

    /**
     * Creates list of Songs from list of Files
     *
     * @param rootList represents ArrayList<File> which must be convert to object Song
     * @return ArrayList<Song>
     * @throws IOException when song has incorrect path
     */
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
            songMp3.setTitle(verifyHasName(audioFile));
            songMp3.setAlbumName(verifyAlbumName(audioFile));
            songMp3.setArtist(verifyArtistName(audioFile));
            songMp3.setChecksum(checkSum);
            songs.add(songMp3);
        }
        return songs;
    }

    /**
     * Verifies if AudioFile has tag Title and returns Title
     *
     * @param rootSong represents AudioFile which is verified
     * @return title of AudioFile
     */
    private String verifyHasName(AudioFile rootSong) {
        if (rootSong.getTag() == null) {
            return "Unknown Title";
        }

        String titleTag = rootSong.getTag().getFirst(FieldKey.TITLE);
        if ((titleTag == null) || (titleTag.isEmpty())) {
            return "Unknown Title";
        }
        return titleTag;
    }

    /**
     * Verifies if AudioFile has tag AlbumName and returns it
     *
     * @param rootSong represents AudioFile which is verified
     * @return AlbumName of AudioFile
     */
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

    /**
     * Verifies if AudioFile has tag Artist and returns it
     *
     * @param rootSong represents AudioFile which is verified
     * @return Artist of AudioFile
     */
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

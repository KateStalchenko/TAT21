import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Katsiaryna Stalchanka
 * @since 03.11.2018
 */
public class Solution {
    /**
     * The entry point to the program
     *
     * @param args represents string path received from command line
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        if (!verifyArgs(args)) {
            return;
        }

        String path = args[0];

        ArrayList<File> allMp3FilesFromDir;
        try {
            allMp3FilesFromDir = new FileTools().getAllMusicFiles(path);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        ArrayList<Song> songs = new FileTools().createListOfSongs(allMp3FilesFromDir);

        Map<String, List<String>> duplicateByCheksum = new SongUtils().getDuplicatedSongsWithTheSameChecksum(songs);
        Map<SongAlbumArtistKey, List<String>> duplicatedSongArtistAlbumName = new SongUtils().getDuplicatedSongsArtistAlbumName(songs);

        String outputFilePath =  "E:\\Dev\\music.html";
        try {
            new HtmlExporter().generateHtml(songs, outputFilePath);
        } catch (IOException e) {
            System.out.println("You need to indicate correct path to save xml file.");
            return;
        }
    }

    /**
     * Verifies arguments from command line
     *
     * @param args from command line
     * @return true if the data is correct
     */
    private static boolean verifyArgs(String[] args) {
        if ((args.length == 0) || (args == null)) {
            System.out.println("You need to provide the path to your music.");
            return false;
        }
        return true;
    }
}

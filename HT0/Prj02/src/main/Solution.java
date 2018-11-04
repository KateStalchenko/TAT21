package main;

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
    public static void main(String[] args) throws IOException {
        if (!new Solution().verifyArgs(args)) {
            return;
        }

        String path = args[0];
        ArrayList<File> allMp3FilesFromDir;
        try {
            allMp3FilesFromDir = new FileTools().getAllMp3Files_ApacheCommons(path);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        ArrayList<Song> songs = new FileTools().createListOfSongs(allMp3FilesFromDir);

        Map<String, List<String>> duplicateByCheksum = new SongUtils().getDuplicatedSongs(songs);

        File htmlFile = new File("E:\\Dev\\music.html");
        new GenerateHtml().generateHtml(songs, htmlFile.getPath());
    }

    private boolean verifyArgs(String[] args) {
        if ((args.length == 0) || (args == null)) {
            System.out.println("You need to provide the path to your music.");
            return false;
        }
        return true;
    }
}

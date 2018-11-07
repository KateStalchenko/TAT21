import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Katsiaryna Stalchanka
 * @since 03.11.2018
 */
class GenerateHtml {

    void generateHtml(ArrayList<Song> userSongs, String path) throws IOException {
        StringBuilder html = new StringBuilder();

        html.append("<html>\n" +
                "<body>");
        html.append(generateDataForHtml(userSongs));
        html.append("</html>\n" +
                "</body>");

        File htmlFile = new File(path);
        FileOutputStream write = new FileOutputStream(htmlFile);
        write.write(html.toString().getBytes());
        write.close();
    }

    private String generateDataForHtml(ArrayList<Song> userSongs) {
        Map<String, Map<String, List<Song>>> sortedSongs = userSongs
                .stream()
                .collect(Collectors.groupingBy(
                        Song::getArtist,
                        Collectors.groupingBy(Song::getAlbumName)));

        StringBuilder html = new StringBuilder();

        html.append("<ul style=\"list-style-type:none\">");

        for (String keyArtist : sortedSongs.keySet()) {
            Map<String, List<Song>> albumGroups = sortedSongs.get(keyArtist);
            html.append("<li>").append(keyArtist);
            html.append("<ul style=\"list-style-type:none\">");

            for (String albumKey : albumGroups.keySet()) {
                List<Song> songs = albumGroups.get(albumKey);
                html.append("<li>").append(albumKey);
                html.append("<ul style=\"list-style-type:none\">");

                for (Song userSong : songs) {
                    html.append("<li>")
                            .append(userSong.getName())
                            .append(" ")
                            .append(userSong.getDuration())
                            .append(" ")
                            .append(String.format("<a href=\"%s\">Link</a>", userSong.getLink()));
                    html.append("</li>");
                }
                html.append("</ul>");
                html.append("</li>");
            }
            html.append("</ul>");
            html.append("</li>");
        }
        html.append("</ul>");
        return html.toString();
    }
}

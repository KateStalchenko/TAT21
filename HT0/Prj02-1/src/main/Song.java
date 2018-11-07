import java.util.Objects;

/**
 * @author Katsiaryna Stalchanka
 * @since 03.11.2018
 */
public class Song {
    private String title;
    private String link;
    private String albumName;
    private String artist;
    private String checksum;
    private int duration;

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    int getDuration() {
        return duration;
    }

    void setDuration(int duration) {
        this.duration = duration;
    }

    String getLink() {
        return link;
    }

    void setLink(String link) {
        this.link = link;
    }

    String getAlbumName() {
        return albumName;
    }

    void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    String getArtist() {
        return artist;
    }

    void setArtist(String artist) {
        this.artist = artist;
    }

    String getChecksum() {
        return checksum;
    }

    void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return duration == song.duration &&
                Objects.equals(title, song.title) &&
                Objects.equals(link, song.link) &&
                Objects.equals(albumName, song.albumName) &&
                Objects.equals(artist, song.artist) &&
                Objects.equals(checksum, song.checksum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, duration, link, albumName, artist, checksum);
    }
}

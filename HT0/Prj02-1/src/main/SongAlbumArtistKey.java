import java.util.Objects;

/**
 * Represents SongAlbumArtistKey object to use it when grouping songs
 *
 * @author Katsiaryna Stalchanka
 * @since 07.11.2018
 */
public class SongAlbumArtistKey {
    private String Artist;
    private String AlbumName;
    private String Song;

    String getArtist() {
        return Artist;
    }

    void setArtist(String artist) {
        Artist = artist;
    }

    String getAlbumName() {
        return AlbumName;
    }

    void setAlbumName(String albumName) {
        AlbumName = albumName;
    }

    String getSong() {
        return Song;
    }

    void setSong(String song) {
        Song = song;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SongAlbumArtistKey that = (SongAlbumArtistKey) o;
        return Objects.equals(Artist, that.Artist) &&
                Objects.equals(AlbumName, that.AlbumName) &&
                Objects.equals(Song, that.Song);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Artist, AlbumName, Song);
    }
}

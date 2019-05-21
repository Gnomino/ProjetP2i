package fr.insalyon.p2i2.javaarduino.db;

public class Music {
    private int idMusic, length;
    private String title, author;

    public Music(int idMusic, int length, String title, String author) {
        this.idMusic = idMusic;
        this.length = length;
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getIdMusic() {
        return idMusic;
    }

    public void setIdMusic(int idMusic) {
        this.idMusic = idMusic;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}

package data;

public class Story {

    private final Comment[] comments;
    private final String url;
    private final String title;
    private final String author;
    private final int score;
    private final int time;
    private final int id;

    public Story(Comment[] comments, String url, String title, String author, int score, int time, int id) {
        this.comments = comments;
        this.url = url;
        this.title = title;
        this.author = author;
        this.score = score;
        this.time = time;
        this.id = id;
    }

    public Comment[] getComments() {
        return comments;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getScore() {
        return score;
    }

    public int getTime() {
        return time;
    }

    public int getId() {
        return id;
    }
}

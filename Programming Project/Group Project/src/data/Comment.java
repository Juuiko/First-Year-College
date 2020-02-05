package data;

public class Comment {

    private final Comment[] comments;
    private final int parent;
    private final String text;
    private final int time;
    private final String author;
    private final int id;

    public Comment(Comment[] comments, int parent, String text, int time, String author, int id) {
        this.comments = comments;
        this.parent = parent;
        this.text = text;
        this.time = time;
        this.author = author;
        this.id = id;
    }

    public Comment[] getComments() {
        return comments;
    }

    public int getParent() {
        return parent;
    }

    public String getText() {
        return text;
    }

    public int getTime() {
        return time;
    }

    public String getAuthor() {
        return author;
    }

    public int getId(){
        return id;
    }
}

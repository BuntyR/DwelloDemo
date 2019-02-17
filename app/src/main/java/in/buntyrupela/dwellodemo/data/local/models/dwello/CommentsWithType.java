package in.buntyrupela.dwellodemo.data.local.models.dwello;

public class CommentsWithType {
    String commentType;
    String author;
    String body;
    int count;

    public CommentsWithType(String commentType, String author, String body, int count) {
        this.commentType = commentType;
        this.author = author;
        this.body = body;
        this.count = count;
    }

    public String getCommentType() {
        return commentType;
    }

    public void setCommentType(String commentType) {
        this.commentType = commentType;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CommentsWithType{" +
                "commentType='" + commentType + '\'' +
                ", author='" + author + '\'' +
                ", body='" + body + '\'' +
                ", count=" + count +
                '}';
    }
}

package in.buntyrupela.dwellodemo.data.local.models.dwello;

public class CommentsWithType {
    private String commentType;
    private String author;
    private String body;
    private int count;
    private int depth;
    private int ups;

    public CommentsWithType(String commentType, String author, String body, int count, int depth,
                            int ups) {
        this.commentType = commentType;
        this.author = author;
        this.body = body;
        this.count = count;
        this.depth = depth;
        this.ups = ups;
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

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getUps() {
        return ups;
    }

    public void setUps(int ups) {
        this.ups = ups;
    }

    @Override
    public String toString() {
        return "CommentsWithType{" +
                "commentType='" + commentType + '\'' +
                ", author='" + author + '\'' +
                ", body='" + body + '\'' +
                ", count=" + count +
                ", depth=" + depth +
                ", ups=" + ups +
                '}';
    }
}

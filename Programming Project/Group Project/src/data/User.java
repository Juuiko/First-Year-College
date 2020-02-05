package data;

import java.util.ArrayList;
import java.util.List;

public class User {

    private List<Story> stories = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();
    private String userName;

    
    public User(String userName) {
		this.userName = userName;
	}

    public List<Story> getStories() {
        return stories;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public String getUserName() {
        return userName;
    }
   
    public void addStory(Story story){
    	stories.add(story);
    }
    
    public void addComment(Comment comment){
    	comments.add(comment);
    }

    public int storyPoints(){
        int total=0;
        Story story;
        for(int i=0; i<stories.size(); i++){
            story = stories.get(i);
            total = total + story.getScore();
        }
        return total;
    }
}

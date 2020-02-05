package data;

import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONLoader {

	private static PApplet p;
	private static List<Story> stories = new ArrayList<>();
	private static Map<String, User> users = new HashMap<>();
	private long loadTime;

	public JSONLoader(PApplet p) {
		JSONLoader.p = p;

		long startTime = System.currentTimeMillis();

		String[] lines = p.loadStrings("news.json");

		for (String line : lines) {
			//Load up this line as a JSON object
			JSONObject object = p.parseJSONObject(line);

			// Ignore wrongly-formatted items
			String type = object.getString("type");
			if (type == null) continue;

			// Ignore deleted items
			if (object.hasKey("deleted") && object.getBoolean("deleted")) continue;

			if (type.equals("story")) {
				String url = object.getString("url");
				String title = object.getString("title");
				String author = object.getString("by");
				int score = object.getInt("score");
				int time = object.getInt("time");
				int id = object.getInt("id");

				//Load up comments
				JSONArray kids = object.getJSONArray("kids");
				int kidsSize = kids == null ? 0 : kids.size();
				Comment[] comments = new Comment[kidsSize];

				for (int j = 0; j < kidsSize; j++) {
					int childId = kids.getInt(j);

					//Skip comment if not present in file
					if (childId > lines.length) continue;

					comments[j] = parseComment(lines, childId - 1);
				}

				Story story = new Story(comments, url, title, author, score, time, id);
				stories.add(story);

				User user = users.containsKey(author) ?
						users.get(author) :
						new User(author);

				user.addStory(story);
			}
		}

		long endTime = System.currentTimeMillis();
		loadTime = endTime - startTime;
	}

	public List<Story> getStories() {
		return stories;
	}
	public Map<String, User> getUsers() {
		return users;
	}

	/**
	 * Returns input file load time
	 * @return Load time in milliseconds
	 */
    public long getLoadTime() {
        return loadTime;
    }

    /**
	 * Parses and returns a Comment object
	 * @param lines String array containing the data
	 * @param lineNum Line number on which comment is located
	 * @return The Comment created. Null if not present in data
	 */
	private Comment parseComment(String[] lines, int lineNum){
		if(lineNum > lines.length) return null;
		JSONObject commentObject = p.parseJSONObject(lines[lineNum]);

		String commentText = commentObject.getString("text");
		int commentId = commentObject.getInt("id");
		int commentTime = commentObject.getInt("time");
		int parent = commentObject.getInt("parent");
		String commentAuthor = commentObject.getString("by");

		JSONArray kids = commentObject.getJSONArray("kids");
		int kidsSize = kids == null ? 0 : kids.size();
		Comment[] comments = new Comment[kidsSize];

		for(int i = 0; i < kidsSize; i++)
			comments[i] = parseComment(lines, (kids.getInt(i)-1));

		Comment comment = new Comment(comments,parent,commentText,commentTime,commentAuthor,commentId);

		User user = users.containsKey(commentAuthor) ?
				users.get(commentAuthor) :
				new User(commentAuthor);

		user.addComment(comment);

		return comment;

	}
}

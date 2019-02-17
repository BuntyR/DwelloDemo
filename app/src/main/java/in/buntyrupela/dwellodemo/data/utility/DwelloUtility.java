package in.buntyrupela.dwellodemo.data.utility;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import in.buntyrupela.dwellodemo.data.local.models.dwello.CommentsWithType;
import okhttp3.ResponseBody;

public class DwelloUtility {

    public static List<CommentsWithType> convertToOptimizedList(ResponseBody responseBody,
                                                                Gson gson) {
        List<CommentsWithType> redditCommentsResponses = new ArrayList<>();
        JsonParser jsonParser = new JsonParser();
        JsonArray entries = null;
        try {
            entries = (JsonArray) jsonParser.parse(responseBody.string());
            JsonArray jsonArray = entries.get(1).getAsJsonObject().get("data")
                    .getAsJsonObject().get("children").getAsJsonArray();

            for (JsonElement jsonElement : jsonArray) {

                JsonObject childrenItem = jsonElement.getAsJsonObject();
                JsonElement dataItem = childrenItem.get("data");
                CommentsWithType commentsWithType = gson.fromJson(dataItem,
                        new TypeToken<CommentsWithType>() {
                }.getType());
                commentsWithType.setCommentType(childrenItem.get("kind").getAsString());
                redditCommentsResponses.add(commentsWithType);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return redditCommentsResponses;
    }
}

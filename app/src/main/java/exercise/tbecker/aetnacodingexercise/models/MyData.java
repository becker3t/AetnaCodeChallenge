package exercise.tbecker.aetnacodingexercise.models;

/**
 * Created by Android on 8/7/2017.
 */

public class MyData {

    private String title;
    private String imgUrl;

    public MyData(String title, String imgUrl) {
        this.title = title;
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}

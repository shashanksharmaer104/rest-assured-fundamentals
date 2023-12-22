package objects;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VideoGame {

    private String category;
    private String name;
    private String rating;
    private String releaseDate;
    private Integer reviewScore;

    /**
     *
     * @param reviewScore
     * @param releaseDate
     * @param name
     * @param rating
     * @param category
     */
    public VideoGame(String category, String name, String rating, String releaseDate, Integer reviewScore) {
        //super();
        this.category = category;
        this.name = name;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.reviewScore = reviewScore;
    }

}
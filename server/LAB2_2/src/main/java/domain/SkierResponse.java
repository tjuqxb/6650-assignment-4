package domain;

public class SkierResponse {
    String seasonID;
    Integer totalVert;

    public SkierResponse(String seasonID, Integer totalVert) {
        this.seasonID = seasonID;
        this.totalVert = totalVert;
    }
}

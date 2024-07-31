public class VertexType {       ////顶点结构

    int placeID;        //定点序号
    String placeName;	//顶点名字

    public VertexType(int placeID,String placeName)
    {
        this.placeID = placeID;
        this.placeName = placeName;
    }

    public int getPlaceID() {
        return placeID;
    }

    public void setPlaceID(int placeID) {
        this.placeID = placeID;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
}
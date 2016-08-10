import java.util.ArrayList;
public class MapController
{
    ArrayList<MapBase> listOfMaps = new ArrayList<MapBase>(0);
    public MapController(){
        listOfMaps.add(createMap(10,10,"", MapType.SHIP));
        listOfMaps.add(createMap(10,10, "", MapType.PLANET));
    }
    
    public MapBase createMap(int x, int y, String n, MapType t){
        MapBase m = new MapBase();
        m.mapTiles = MapReader.reader(n);
        //Put the map loading thing here
        m.name = n;
        m.type = t;
        return m;
    }
}

package src;
import java.util.ArrayList;
public class MapController
{
    ArrayList<MapBase> listOfMaps = new ArrayList<MapBase>(0);
    MapReader mapReader;
    public MapController(){
        listOfMaps.add(createMap(10,10,"", MapType.SHIP));
        listOfMaps.add(createMap(10,10, "", MapType.PLANET));
        mapReader = new MapReader();
    }
    
    public MapBase createMap(int x, int y, String n, MapType t){
        MapBase m = new MapBase();
        m.mapTiles = mapReader.reader(n);
        //Put the map loading thing here
        m.name = n;
        m.type = t;
        return m;
    }
}

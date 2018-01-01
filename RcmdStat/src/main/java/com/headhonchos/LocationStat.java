package com.headhonchos;

import com.headhonchos.util.GeoLocation;
import org.apache.commons.lang3.tuple.Pair;

public class LocationStat
{
  public static Pair<Pair<String, String>, Pair<String, String>> getPreferredLng(double latitude, double longitude)
  {
    Pair<Pair<String, String>, Pair<String, String>> of = null;
    double earthRadius = 6371.01D;
    double distance = 100.0D;
    if ((latitude != 0.0D) && (longitude != 0.0D))
    {
      double latRad = 0.0174532925D * latitude;
      double lonRad = 0.0174532925D * longitude;
      GeoLocation myLocation = GeoLocation.fromRadians(latRad, lonRad);
      GeoLocation[] boundingCoordinates = myLocation.boundingCoordinates(distance, earthRadius);
      String maxDegLon = Double.toString(boundingCoordinates[1].degLon);
      String minDegLat = Double.toString(boundingCoordinates[0].degLat);
      
      String maxDegLat = Double.toString(boundingCoordinates[1].degLat);
      String minDegLon = Double.toString(boundingCoordinates[0].degLon);
      
      Pair<String, String> latPair = Pair.of(minDegLat, maxDegLat);
      Pair<String, String> lonPair = Pair.of(minDegLon, maxDegLon);
      
      of = Pair.of(latPair, lonPair);
    }
    return of;
  }
}


/* Location:              /home/yatish/.m2-headhonchos/RcmdStat/1.0/RcmdStat-1.0.jar!/com/headhonchos/LocationStat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
package com.headhonchos.util;

public class GeoLocation
{
  private double radLat;
  private double radLon;
  public double degLat;
  public double degLon;
  private static final double MIN_LAT = Math.toRadians(-90.0D);
  private static final double MAX_LAT = Math.toRadians(90.0D);
  private static final double MIN_LON = Math.toRadians(-180.0D);
  private static final double MAX_LON = Math.toRadians(180.0D);
  
  public static GeoLocation fromDegrees(double latitude, double longitude)
  {
    GeoLocation result = new GeoLocation();
    result.radLat = Math.toRadians(latitude);
    result.radLon = Math.toRadians(longitude);
    result.degLat = latitude;
    result.degLon = longitude;
    result.checkBounds();
    return result;
  }
  
  public static GeoLocation fromRadians(double latitude, double longitude)
  {
    GeoLocation result = new GeoLocation();
    result.radLat = latitude;
    result.radLon = longitude;
    result.degLat = Math.toDegrees(latitude);
    result.degLon = Math.toDegrees(longitude);
    result.checkBounds();
    return result;
  }
  
  private void checkBounds()
  {
    if ((this.radLat < MIN_LAT) || (this.radLat > MAX_LAT) || (this.radLon < MIN_LON) || (this.radLon > MAX_LON)) {
      throw new IllegalArgumentException();
    }
  }
  
  public double getLatitudeInDegrees()
  {
    return this.degLat;
  }
  
  public double getLongitudeInDegrees()
  {
    return this.degLon;
  }
  
  public double getLatitudeInRadians()
  {
    return this.radLat;
  }
  
  public double getLongitudeInRadians()
  {
    return this.radLon;
  }
  
  public String toString()
  {
    return "(" + this.degLat + "°, " + this.degLon + "°) = (" + this.radLat + " rad, " + this.radLon + " rad)";
  }
  
  public double distanceTo(GeoLocation location, double radius)
  {
    return Math.acos(Math.sin(this.radLat) * Math.sin(location.radLat) + Math.cos(this.radLat) * Math.cos(location.radLat) * Math.cos(this.radLon - location.radLon)) * radius;
  }
  
  public GeoLocation[] boundingCoordinates(double distance, double radius)
  {
    if ((radius < 0.0D) || (distance < 0.0D)) {
      throw new IllegalArgumentException();
    }
    double radDist = distance / radius;
    
    double minLat = this.radLat - radDist;
    double maxLat = this.radLat + radDist;
    double minLon;
    double maxLon;
    if ((minLat > MIN_LAT) && (maxLat < MAX_LAT))
    {
      double deltaLon = Math.asin(Math.sin(radDist) / Math.cos(this.radLat));
      
      minLon = this.radLon - deltaLon;
      if (minLon < MIN_LON) {
        minLon += 6.283185307179586D;
      }
      maxLon = this.radLon + deltaLon;
      if (maxLon > MAX_LON) {
        maxLon -= 6.283185307179586D;
      }
    }
    else
    {
      minLat = Math.max(minLat, MIN_LAT);
      maxLat = Math.min(maxLat, MAX_LAT);
      minLon = MIN_LON;
      maxLon = MAX_LON;
    }
    return new GeoLocation[] { fromRadians(minLat, minLon), fromRadians(maxLat, maxLon) };
  }
}


/* Location:              /home/yatish/.m2-headhonchos/RcmdStat/1.0/RcmdStat-1.0.jar!/com/headhonchos/util/GeoLocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
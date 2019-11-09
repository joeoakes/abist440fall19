package edu.psu.ab.ist.sworks.pojo;

public class Mission
{
    private String _climate, _continent, _landmarks, _terrain;
    private boolean _hasLandmarkOfInterest;

    public Mission() {}

    public Mission(String terrain, String climate)
    {
        this._terrain = terrain;
        this._climate = climate; }

    public String getClimate()
    {
        return _climate;
    }

    public void setClimate(String climate)
    {
        this._climate = climate;
    }


    public String getTerrain()
    {
        return _terrain;
    }

    public void setTerrain(String terrain)
    {
        this._terrain = terrain;
    }

}

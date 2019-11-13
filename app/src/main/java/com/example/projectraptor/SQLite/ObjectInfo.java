package com.example.projectraptor.SQLite;

public class ObjectInfo {
    private String u_mission_id, u_timestamp, u_object_found,  u_battery_level, u_ambient_light, u_humidity, u_pressure, u_temperature;

    public String getId(){return u_mission_id;}
    public void setId(String _id){this.u_mission_id = _id;}
    public String getTimestamp(){return u_timestamp;}
    public void setTimestamp(String _timestamp){this.u_timestamp = _timestamp;}
    public String getObjectFound(){return u_object_found;}
    public void setObjectFound(String _objectFound){this.u_object_found = _objectFound;}
    public String getBatteryLevel(){return u_battery_level;}
    public void setBatteryLevel(String _batteryLevel){this.u_battery_level = _batteryLevel;}
    public String getAmbientLight(){return u_ambient_light;}
    public void setAmbientLight(String _ambientLight){this.u_ambient_light = _ambientLight;}
    public String getHumidity(){return u_humidity;}
    public void setHumidity(String _humidity){this.u_humidity = _humidity;}
    public String getPressure(){return u_pressure;}
    public void setPressure(String _pressure){this.u_pressure = _pressure;}
    public String getTemperature(){return u_temperature;}
    public void setTemperatureString (String _temperature){this.u_temperature = _temperature;}

}

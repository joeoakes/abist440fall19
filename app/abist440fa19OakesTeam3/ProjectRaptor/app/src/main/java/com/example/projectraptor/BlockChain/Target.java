package com.example.projectraptor.BlockChain;

public class Target {

    private String _objectType;

    //GPS
    private double _altitude;
    private double _latitude;
    private double _longitude;

    //Date/Time
    private String _dateAndTime;

    //photoCapture
    private String _photo;

    //missionCommander
    private String status;



    //First define the fields in the Target transaction POJO
    public Target(String _objectType, double _altitude, double _latitude, double _longitude, String _dateAndTime, String _photo, String _missionCommander) {
        this._objectType = _objectType;
        this._altitude = _altitude;
        this._latitude = _latitude;
        this._longitude = _longitude;
        this._dateAndTime = _dateAndTime;
        this._photo = _photo;
        this.status = _missionCommander;
    }

    //Now start setters and getters



    public double get_altitude() {
        return _altitude;
    }

    public void set_altitude(double _altitude) {
        this._altitude = _altitude;
    }

    public double get_latitude() {
        return _latitude;
    }

    public void set_latitude(double _latitude) {
        this._latitude = _latitude;
    }

    public double get_longitude() {
        return _longitude;
    }

    public void set_longitude(double _longitude) {
        this._longitude = _longitude;
    }

    public String get_objectType() {return _objectType;}
    public void setObjectType(String _ObjT) {this._objectType = _ObjT;}

    public String get_dateAndTime() {
        return _dateAndTime;
    }
    public void set_dateAndTime(String _dateAndTime) {
        this._dateAndTime = _dateAndTime;
    }

    public String get_photo() {
        return _photo;
    }

    public void set_photo(String _photo) {
        this._photo = _photo;
    }

    public String getStatus() {return status;}
    public void setStatus(String _Status) {this.status = _Status;}
}


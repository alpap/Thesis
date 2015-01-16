package com.ModRobMineCraft.Location;

/**
 * Created by log on 11/27/14.
 */
public class Location {

    int[] loc= new int[2];

    public Location(){
        this.loc[0]=0;
        this.loc[1]=0;
        this.loc[2]=0;
    }
    public Location(int x,int y, int z){
        this.loc[0]=x;
        this.loc[1]=y;
        this.loc[2]=z;
    }
    public Location(Location loc){
        this.loc[0]=loc.getX();
        this.loc[1]=loc.getY();
        this.loc[2]=loc.getZ();
    }

    public void setLocation(Location loc){
        this.loc[0]=loc.getX();
        this.loc[1]=loc.getY();
        this.loc[2]=loc.getZ();
    }

    public void setLocation(int x , int y, int z){
        this.loc[0]=x;
        this.loc[1]=y;
        this.loc[2]=z;
    }

    public int[] getLocation(){
        return this.loc;
    }

    public int getX(){
        return this.loc[0];
    }
    public int getY(){
        return this.loc[1];
    }
    public int getZ(){
        return this.loc[2];
    }

    public void setX(int x){
        this.loc[0]=x;
    }
    public void setY(int y){
        this.loc[1]=y;
    }
    public void setZ(int z){
        this.loc[2]=z;
    }
}

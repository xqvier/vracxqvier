/*
 * Exercise.java	27 avr. 2011
 * xMourgues 
 */
package com.xqvier.muscu;

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Classe gérant les informations d'un exercice de musculation
 * 
 * @author xMourgues
 * @version
 */
public class Exercise implements Parcelable {

    private String name;
    private Date date;
    private int id;
    private int time;
    private int recovery;
    private int delay;
    private int count;
    /**
     * Constructeur multichamps
     * 
     * @param name
     *            le nom de l'exercice
     * @param date
     *            la date a laquelle est effectué l'exercice
     * @param time
     *            le temps d'une serie
     * @param recovery
     *            le temps de recuperation
     * @param delay
     *            le temps de preparation
     * @param count
     *            le nombre de série
     */
    public Exercise(int id, String name, Date date, int time, int recovery, int delay,
	    int count) {
	super();
	this.id = id;
	this.name = name;
	this.date = date;
	this.time = time;
	this.recovery = recovery;
	this.delay = delay;
	this.count = count;
    }

    public Exercise(String name, Date date, int exercise, int recovery,
	    int delay) {
	this.date = date;
	this.name = name;
	this.time = exercise;
	this.recovery = recovery;
	this.delay = delay;
    }

    /**
     * Constructeur multichamps
     * 
     * @param name
     *            le nom de l'exercice
     * @param date
     *            la date de l'exercice
     * @param time
     *            le temps d'une série
     * @param recovery
     *            le temps de récupération
     */
    public Exercise(String name, Date date, int time, int recovery) {
	super();
	this.name = name;
	this.date = date;
	this.recovery = recovery;
	this.time = time;
    }

    /**
     * Constructeur a partir d'un objet Parcel pour l'envoie d'une activité a
     * l'autre (implémentation de l'interface Parcelable)
     * 
     * @param source l'objet Parcel de l'objet exercice
     */
    public Exercise(Parcel source) {
	this.name = source.readString();
	this.date = new Date(source.readString());
	this.delay = source.readInt();
	this.time = source.readInt();
	this.recovery = source.readInt();
	this.count = source.readInt();
    }

    public Exercise() {

    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * @return the date
     */
    public Date getDate() {
	return date;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(Date date) {
	this.date = date;
    }

    /**
     * @return the time
     */
    public int getTime() {
	return time;
    }

    /**
     * @param time
     *            the time to set
     */
    public void setTime(int time) {
	this.time = time;
    }

    /**
     * @return the recovery
     */
    public int getRecovery() {
        return recovery;
    }

    /**
     * @param recovery
     *            the recovery to set
     */
    public void setRecovery(int recovery) {
        this.recovery = recovery;
    }

    /**
     * @param delay
     *            the delay to set
     */
    public void setDelay(int delay) {
	this.delay = delay;
    }

    /**
     * @return the delay
     */
    public int getDelay() {
	return delay;
    }

    public void incrementCount() {
        this.count++;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count
     *            the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Exercise [name=" + name + ", date=" + date + ", count=" + count
                + ", delay=" + delay + ", recovery=" + recovery + ", time="
                + time + ", getClass()=" + getClass() + ", hashCode()="
                + hashCode() + ", toString()=" + super.toString() + "]";
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.os.Parcelable#describeContents()
     */
    @Override
    public int describeContents() {
	return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.os.Parcelable#writeToParcel(android.os.Parcel, int)
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
	dest.writeString(name);
	dest.writeString(date.toString());
	dest.writeInt(delay);
	dest.writeInt(time);
	dest.writeInt(recovery);
	dest.writeInt(count);

    }

    public static final Parcelable.Creator<Exercise> CREATOR = new Parcelable.Creator<Exercise>() {

	@Override
	public Exercise createFromParcel(Parcel source) {
	    return new Exercise(source);
	}

	@Override
	public Exercise[] newArray(int size) {
	    return new Exercise[size];
	}

    };

}

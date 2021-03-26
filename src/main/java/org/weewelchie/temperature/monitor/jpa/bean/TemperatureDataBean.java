package org.weewelchie.temperature.monitor.jpa.bean;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table (name = "temperature_data")
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class TemperatureDataBean extends PanacheEntity {

    @Column(name="temp_celcius")
    public String temperatureCelcius;

    @Column(name="temp_farenheit")
    public String temperatureFarenheit;

    @Column(name="device_id")
    public String deviceID;

    @Column(name="device_name")
    public String deviceName;

    @Column(name="timestamp")
    public LocalDateTime timeStamp;

    public TemperatureDataBean()
    {

    }

    public TemperatureDataBean(String tempCelcius, String tempFarenheit, String deviceID, String deviceName, LocalDateTime timestamp)
    {
        this.temperatureCelcius = tempCelcius;
        this.temperatureFarenheit = tempFarenheit;
        this.deviceID = deviceID;
        this.deviceName = deviceName;
        this.timeStamp = timestamp;
    }

    public static List<TemperatureDataBean> findByDeviceName(String deviceName)
    {
        return list("deviceName",deviceName);
    }

    public static List<TemperatureDataBean> findByDeviceId(String deviceID)
    {
        return list("deviceID",deviceID);
    }

    public static List<TemperatureDataBean> findAllData()
    {
        return list("from TemperatureDataBean order by timestamp");
    }

    @Override
    public String toString() {
        return "TemperatureDataBean [deviceID=" + deviceID + ", deviceName=" + deviceName + ", temperatureCelcius="
                + temperatureCelcius + ", temperatureFarenheit=" + temperatureFarenheit + ", timeStamp=" + timeStamp
                + "]";
    }

    
}

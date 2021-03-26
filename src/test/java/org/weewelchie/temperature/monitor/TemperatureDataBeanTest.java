package org.weewelchie.temperature.monitor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.weewelchie.temperature.monitor.jpa.bean.TemperatureDataBean;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@Transactional
@TestInstance(Lifecycle.PER_CLASS)
public class TemperatureDataBeanTest {

    private List<TemperatureDataBean> data = new ArrayList<TemperatureDataBean>();
    private List<LocalDateTime> times = new ArrayList<LocalDateTime>();
    private static final Integer NUM_RECORDS = 10;
    private static final String DEVICE_NAME = "device1";
    private static final String DEVICE_ID = "28_000000123";

    @BeforeAll
    public void setUp() {

        for (int i=0;i< NUM_RECORDS; i++)
        {
            times.add(LocalDateTime.now());
        }

        //Clear All old data
        List<TemperatureDataBean> dataBeans = new ArrayList<TemperatureDataBean>();
        dataBeans = TemperatureDataBean.findAllData();
        for(TemperatureDataBean t:dataBeans)
        {
            t.delete();
        }
    
        //Create some data here
        for (int i=0; i< NUM_RECORDS;i++)
        {
            data.add(new TemperatureDataBean("14." + i, "66." + i, DEVICE_ID, DEVICE_NAME, times.get(i)));
            data.get(i).persist();
        }
    }

    @Test
    public void testFindByDeviceNameList()
    {
        List<TemperatureDataBean> testData = TemperatureDataBean.findByDeviceName(DEVICE_NAME);
        assertEquals(testData.size(),NUM_RECORDS);
        for(TemperatureDataBean t:testData)
        {
            assertTrue(t.deviceName.contains(DEVICE_NAME)); 
        }

    }

    @Test
    public void testFindDeviceByIDList()
    {
        List<TemperatureDataBean> testData = TemperatureDataBean.findByDeviceId(DEVICE_ID);
        assertEquals(testData.size(),10);
        for(TemperatureDataBean t:testData)
        {
            assertTrue(t.deviceID.equals(DEVICE_ID)); 
        }   
    }

    @Test
    @Transactional
    public void testCreate()
    {
        LocalDateTime localDateTime = LocalDateTime.now();
        TemperatureDataBean dataBean = new TemperatureDataBean("11.11", "66.66", "123456", "device99", localDateTime);
        dataBean.persist();

        List<TemperatureDataBean> testData = TemperatureDataBean.findByDeviceName("device99");
        assertTrue(testData.size() >= 1);
        assertEquals(testData.get(0).deviceName, "device99");
        assertEquals(testData.get(0).deviceID, "123456");
        assertEquals(testData.get(0).temperatureCelcius, "11.11");
        assertEquals(testData.get(0).temperatureFarenheit, "66.66");
        assertEquals(testData.get(0).timeStamp, localDateTime);

    }

    @Test
    public void testDelete()
    {
        LocalDateTime localDateTime = LocalDateTime.now();
        TemperatureDataBean dataBean = new TemperatureDataBean("11.11", "66.66", "123456", "device100", localDateTime);
        dataBean.persist();

        List<TemperatureDataBean> testData = TemperatureDataBean.findByDeviceName("device100");
        assertEquals(testData.size(),1);

        TemperatureDataBean.deleteById(testData.get(0).id);

        testData = TemperatureDataBean.findByDeviceName("device100");
        assertEquals(testData.size(),0);
    }


}

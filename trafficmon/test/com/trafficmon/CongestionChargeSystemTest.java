package com.trafficmon;

import org.junit.jupiter.api.Test;
import org.jmock.Mockery;
import org.jmock.Expectations;
import org.junit.Rule;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CongestionChargeSystemTest extends CongestionChargeSystem{

    Vehicle v1 = Vehicle.withRegistration("test123");

    @Test
    void vehicleEnteringorLeavingZoneTest(){
        Vehicle v1=Vehicle.withRegistration("ABC123");
        Vehicle v2=Vehicle.withRegistration("ABC456");
        vehicleEnteringZone(v1);
        EntryEvent entryevent=new EntryEvent(v1);
        assertEquals(entryevent.getVehicle(),eventLog.get(eventLog.size()-1).getVehicle());
        //entrytest
        vehicleLeavingZone(v1);
        ExitEvent exitevent=new ExitEvent(v1);
        assertEquals(exitevent.getVehicle(),eventLog.get(eventLog.size()-1).getVehicle());
        //exittest
        vehicleLeavingZone(v2);
        ExitEvent exitevent2=new ExitEvent(v2);
        assertNotEquals(exitevent2.getVehicle(),(eventLog.get(eventLog.size()-1).getVehicle()));
        }

    @Test
    void calculateChargestest() throws Exception{
        CongestionChargeSystemTest congestionChargeSystemtest = new CongestionChargeSystemTest();
        congestionChargeSystemtest.vehicleEnteringZone(Vehicle.withRegistration("A123 XYZ"));
        delaySeconds(15);
        congestionChargeSystemtest.vehicleLeavingZone(Vehicle.withRegistration("A123 XYZ"));
        delayMinutes(10);
        congestionChargeSystemtest.vehicleEnteringZone(Vehicle.withRegistration("A103 XRZ"));
        delaySeconds(15);
        congestionChargeSystemtest.vehicleLeavingZone(Vehicle.withRegistration("A103 XRZ"));
        delayMinutes(10);
        congestionChargeSystemtest.calculateCharges();
    }

    public static void delayMinutes(int mins) throws InterruptedException {
        delaySeconds(mins * 1);
    }
    public static void delaySeconds(int secs) throws InterruptedException {
        Thread.sleep(secs * 1);
    }
}
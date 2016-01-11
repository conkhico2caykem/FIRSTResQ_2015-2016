package com.qualcomm.ftcrobotcontroller.FIRST_ResQ;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.ModernRoboticsI2cGyro;

/**
 * Created by Crystal Huynh on 1/11/2016.
 */
public class IntegratedGyro extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException
    {
        ModernRoboticsI2cGyro sensorGyro = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("gyro");
        hardwareMap.logDevices();
        // calibrate the gyro.
        sensorGyro.calibrate();
        waitForStart();
        // make sure the gyro is calibrated.
        while (sensorGyro.isCalibrating()) {
            Thread.sleep(50);
        }
        while (opModeIsActive())
        {
            telemetry.addData("Heading ", sensorGyro.getHeading());
            telemetry.addData("Integrated ", sensorGyro.getIntegratedZValue());
            Thread.sleep(100);
        }
    }
}

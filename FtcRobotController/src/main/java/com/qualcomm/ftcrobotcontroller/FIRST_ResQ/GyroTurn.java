package com.qualcomm.ftcrobotcontroller.FIRST_ResQ;

import com.qualcomm.hardware.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Crystal Huynh on 1/11/2016.
 */
public class GyroTurn extends LinearOpMode
{
    DcMotor rDrive;
    DcMotor lDrive;

    int buffer = 5;
    double POWER = 0.75;
    double adjPow = 0.75;
    @Override
    public void runOpMode() throws InterruptedException
    {
        lDrive = hardwareMap.dcMotor.get("lDrive");
        rDrive = hardwareMap.dcMotor.get("rDrive");
        lDrive.setDirection(DcMotor.Direction.REVERSE);

        float target = 90;

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

            while(Math.abs(sensorGyro.getIntegratedZValue()) < target)
            {
                lDrive.setPower(0.75);
                rDrive.setPower(-0.75);
            }
            lDrive.setPower(0);
            rDrive.setPower(0);
            //Thread.sleep(100);
        }
    }
}
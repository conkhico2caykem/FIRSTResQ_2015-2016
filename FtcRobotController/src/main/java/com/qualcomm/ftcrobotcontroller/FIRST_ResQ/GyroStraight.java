package com.qualcomm.ftcrobotcontroller.FIRST_ResQ;

import com.qualcomm.hardware.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Crystal Huynh on 1/11/2016.
 */
public class GyroStraight extends LinearOpMode
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

            if(sensorGyro.getIntegratedZValue() > buffer)
            {
                //turn right
                lDrive.setPower(1);
                rDrive.setPower(0);
            }
            else if(sensorGyro.getIntegratedZValue() < -buffer)
            {
                //turn left
                lDrive.setPower(0);
                rDrive.setPower(1);
            }
            else
            {
                //go straight
                lDrive.setPower(POWER);
                rDrive.setPower(POWER);
            }
            //Thread.sleep(100);
        }
    }
}

package com.qualcomm.ftcrobotcontroller.FIRST_ResQ;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by Crystal Huynh on 10/3/2015.
 */
public class AndvancedWKSPNorton extends LinearOpMode{

    public static final int POWER = 0;

    HardwareMap.DeviceMapping<TouchSensor> _touchSensor;
    DcMotor rightmotor = hardwareMap.dcMotor.get("rightmotor");
    DcMotor leftmotor = hardwareMap.dcMotor.get("leftmotor");

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();

        // drive out of parking space
        fullSpeed();
        sleep(1000);

        // stop
        stopMotors();
        waitOneFullHardwareCycle();

        // turn right towards beacon
        fullSpeed();
        sleep(500);

        // stop
        stopMotors();

        // forward towards beacon
        /*while (!_touchSensor. && (opModeIsActive())) {
            rightmotor.setPower(100);
            leftmotor.setPower(100);
            waitOneFullHardwareCycle();
        }*/
        // stop
        stopMotors();
    }

    private void fullSpeed() {
        rightmotor.setPower(POWER);
        leftmotor.setPower(POWER);
    }

    private void stopMotors() {
        rightmotor.setPower(0);
        leftmotor.setPower(0);
    }

    private void initializeRobot()
    {
        _touchSensor = hardwareMap.touchSensor;
    }
}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by mars on 10/30/16.
 */
@Autonomous(name="TitanAuto", group="Auto")
public class TitanAuto extends OpMode {

    private DcMotor leftMotor1 = null;
    private DcMotor leftMotor2 = null;
    private DcMotor rightMotor1 = null;
    private DcMotor rightMotor2 = null;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        leftMotor1  = hardwareMap.dcMotor.get("left motor 1");
        leftMotor2  = hardwareMap.dcMotor.get("left1");
        rightMotor1 = hardwareMap.dcMotor.get("right motor 1");
        rightMotor2 = hardwareMap.dcMotor.get("right motor 2");
        leftMotor1.setDirection(DcMotor.Direction.REVERSE);
        leftMotor2.setDirection(DcMotor.Direction.REVERSE);
        /*
        leftMotor1.setTargetPosition(0);
        leftMotor2.setTargetPosition(0);
        rightMotor1.setTargetPosition(0);
        rightMotor2.setTargetPosition(0);
        */
    }

    @Override
    public void loop() {

        //Need to add in a launcher using a motor, one rotation. Then work on Beacons maybe
        
        telemetry.addData("Left 1: ", leftMotor1.getCurrentPosition());
        telemetry.addData("Left 2: ", leftMotor2.getCurrentPosition());
        telemetry.addData("Right 1: ", rightMotor1.getCurrentPosition());
        telemetry.addData("Right 2: ", rightMotor2.getCurrentPosition());

        if (leftMotor1.getCurrentPosition() >= 2000) {
            leftMotor1.setPower(0);
            leftMotor2.setPower(0);
            rightMotor1.setPower(0);
            rightMotor2.setPower(0);
        } else {
            leftMotor1.setPower(.5);
            leftMotor2.setPower(.5);
            rightMotor1.setPower(.5);
            rightMotor2.setPower(.5);
        }

        //int motorPos = leftMotor1.getCurrentPosition();
        //int newMotorPos = motorPos + 3500;

        /*while (motorPos <= newMotorPos) {
            leftMotor1.setPower(.3);
            leftMotor2.setPower(.3);
            rightMotor1.setPower(.3);
            rightMotor2.setPower(.3);
        }

        if (leftMotor1.getCurrentPosition() >= 3500) {
            leftMotor1.setPower(0);
            leftMotor2.setPower(0);
            rightMotor1.setPower(0);
            rightMotor2.setPower(0);
        } else {
            leftMotor1.setPower(.3);
            leftMotor2.setPower(.3);
            rightMotor1.setPower(.3);
            rightMotor2.setPower(.3);
        }*/

        telemetry.addData("Left 1: ", leftMotor1.getCurrentPosition());
        telemetry.addData("Left 2: ", leftMotor2.getCurrentPosition());
        telemetry.addData("Right 1: ", rightMotor1.getCurrentPosition());
        telemetry.addData("Right 2: ", rightMotor2.getCurrentPosition());

    }

    @Override
    public void stop() {

    }
}

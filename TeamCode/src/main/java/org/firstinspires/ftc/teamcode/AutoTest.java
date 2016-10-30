package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by mars on 10/30/16.
 */
@Autonomous(name="Auto", group="Testing")

public class AutoTest extends OpMode {

    private DcMotor leftMotor1 = null;
    private DcMotor leftMotor2 = null;
    private DcMotor rightMotor1 = null;
    private DcMotor rightMotor2 = null;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        leftMotor1 = hardwareMap.dcMotor.get("left motor 1");
        leftMotor2 = hardwareMap.dcMotor.get("left motor 2");
        rightMotor1 = hardwareMap.dcMotor.get("right motor 1");
        rightMotor2 = hardwareMap.dcMotor.get("right motor 2");
        rightMotor1.setDirection(DcMotor.Direction.REVERSE);
        rightMotor2.setDirection(DcMotor.Direction.REVERSE);

        leftMotor1.setTargetPosition(0);
        leftMotor2.setTargetPosition(0);
        rightMotor1.setTargetPosition(0);
        rightMotor2.setTargetPosition(0);
    }

    @Override
    public void loop() {

        if (leftMotor1.getCurrentPosition() >= 8000) {
            leftMotor1.setPower(0);
            leftMotor2.setPower(0);
            rightMotor1.setPower(0);
            rightMotor2.setPower(0);
        } else {
            leftMotor1.setPower(.38);
            leftMotor2.setPower(.38);
            rightMotor1.setPower(.25);
            rightMotor2.setPower(.25);
        }

        telemetry.addData("Left 1: ", leftMotor1.getCurrentPosition());
        telemetry.addData("Left 2: ", leftMotor2.getCurrentPosition());
        telemetry.addData("Right 1: ", rightMotor1.getCurrentPosition());
        telemetry.addData("Right 2: ", rightMotor2.getCurrentPosition());

    }

    @Override
    public void stop() {

    }
}

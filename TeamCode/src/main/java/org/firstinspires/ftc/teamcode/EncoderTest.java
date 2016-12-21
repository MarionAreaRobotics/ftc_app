package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by mars on 12/10/16.
 */
@Autonomous(name="Encoders", group="Auto")
public class EncoderTest extends OpMode {

    private DcMotor leftMotor1 = null;
    private DcMotor leftMotor2 = null;
    private DcMotor rightMotor1 = null;
    private DcMotor rightMotor2 = null;

    @Override
    public void init() {

        leftMotor1  = hardwareMap.dcMotor.get("left motor 1");
        leftMotor2  = hardwareMap.dcMotor.get("left motor 2");
        rightMotor1 = hardwareMap.dcMotor.get("right motor 1");
        rightMotor2 = hardwareMap.dcMotor.get("right motor 2");
        leftMotor1.setDirection(DcMotor.Direction.REVERSE);
        leftMotor2.setDirection(DcMotor.Direction.REVERSE);

        leftMotor1.setTargetPosition(0);
        leftMotor2.setTargetPosition(0);
        rightMotor1.setTargetPosition(0);
        rightMotor2.setTargetPosition(0);
    }

    @Override
    public void loop() {

        if (leftMotor1.getCurrentPosition() >= 10000) {
            leftMotor1.setPower(-1);
            leftMotor2.setPower(-1);
            rightMotor1.setPower(-1);
            rightMotor2.setPower(-1);
        } else {
            leftMotor1.setPower(1);
            leftMotor2.setPower(1);
            rightMotor1.setPower(1);
            rightMotor2.setPower(1);
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

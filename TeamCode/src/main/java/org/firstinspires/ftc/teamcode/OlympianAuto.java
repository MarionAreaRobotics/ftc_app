package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by mars on 10/30/16.
 */
@Autonomous(name="OlympianAuto", group="Auto")
public class OlympianAuto extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftMotor1 = null;
    private DcMotor leftMotor2 = null;
    private DcMotor rightMotor1 = null;
    private DcMotor rightMotor2 = null;
    private Servo launcherServo = null;
    private Number counter = 0;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        leftMotor1 = hardwareMap.dcMotor.get("left motor 1");
        leftMotor2 = hardwareMap.dcMotor.get("left motor 2");
        rightMotor1 = hardwareMap.dcMotor.get("right motor 1");
        rightMotor2 = hardwareMap.dcMotor.get("right motor 2");

        leftMotor1.setDirection(DcMotor.Direction.FORWARD);
        leftMotor2.setDirection(DcMotor.Direction.FORWARD);
        rightMotor1.setDirection(DcMotor.Direction.REVERSE);
        rightMotor2.setDirection(DcMotor.Direction.REVERSE);

        launcherServo = hardwareMap.servo.get("launcher servo");
        launcherServo.setPosition(.4);

        leftMotor1.setTargetPosition(0);
        leftMotor2.setTargetPosition(0);
        rightMotor1.setTargetPosition(0);
        rightMotor2.setTargetPosition(0);

        runtime.reset();

    }

    @Override
    public void loop() {
        /*
        counter = counter + 1;
        telemetry.addData("Counter: ", counter);
        if (counter < 5000) {
            return;
        }
        */

        if(launcherServo.getPosition() == .4) {
            launcherServo.setPosition(1);
        } else if(launcherServo.getPosition() == 1) {


        if (runtime.time() >= 5) {
            leftMotor1.setPower(0);
            leftMotor2.setPower(0);
            rightMotor1.setPower(0);
            rightMotor2.setPower(0);

        } else {
            leftMotor1.setPower(-.5);
            leftMotor2.setPower(-.5);
            rightMotor1.setPower(-.5);
            rightMotor2.setPower(-.5);
        }
        telemetry.addData("Left 1: ", leftMotor1.getCurrentPosition());
        telemetry.addData("Left 2: ", leftMotor2.getCurrentPosition());
        telemetry.addData("Right 1: ", rightMotor1.getCurrentPosition());
        telemetry.addData("Right 2: ", rightMotor2.getCurrentPosition());

    }
}
    @Override
    public void stop() {

    }
}


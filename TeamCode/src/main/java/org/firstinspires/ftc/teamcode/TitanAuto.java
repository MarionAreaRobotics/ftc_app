package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by mars on 11/11/17.
 */
@Disabled
@Autonomous(name = "Backup Auto", group = "Testing")
public class TitanAuto extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftMotor1 = null;
    private DcMotor leftMotor2 = null;
    private DcMotor rightMotor1 = null;
    private DcMotor rightMotor2 = null;

    private int tracker = 0;

    public void motorBrake(DcMotor motor) {
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void telemetrys() {

        telemetry.addData("Left Motor 1 Position: ", leftMotor1.getCurrentPosition());
        telemetry.addData("Left Motor 2 Position: ", leftMotor2.getCurrentPosition());
        telemetry.addData("Right Motor 1 Position: ", rightMotor1.getCurrentPosition());
        telemetry.addData("Right Motor 2 Position: ", rightMotor2.getCurrentPosition());

        telemetry.addData("Tracker: ", tracker);
        telemetry.update();
    }

    public void driveInit() {

        leftMotor1 = hardwareMap.dcMotor.get("left motor 1");
        leftMotor2 = hardwareMap.dcMotor.get("left motor 2");
        rightMotor1 = hardwareMap.dcMotor.get("right motor 1");
        rightMotor2 = hardwareMap.dcMotor.get("right motor 2");

        rightMotor1.setDirection(DcMotor.Direction.REVERSE);
        rightMotor2.setDirection(DcMotor.Direction.REVERSE);

        motorBrake(leftMotor1);
        motorBrake(leftMotor2);
        motorBrake(rightMotor1);
        motorBrake(rightMotor2);
    }

    @Override
    public void init() {

        driveInit();
    }

    @Override
    public void init_loop() {

        telemetrys();
    }

    @Override
    public void start() {

    }

    public void move(double power) {
        leftMotor1.setPower(power);
        leftMotor2.setPower(power);
        rightMotor1.setPower(power);
        rightMotor2.setPower(power);
    }

    @Override
    public void loop() {

        if (tracker == 0 && leftMotor1.getCurrentPosition() <= 3000) {
            move(.3);
        } else if (tracker == 0 && leftMotor1.getCurrentPosition() >= 3000){
            move(0);
            tracker = 1;
        }

        if (tracker == 1) {
            stop();
        }

    }

    @Override
    public void stop() {

    }
}

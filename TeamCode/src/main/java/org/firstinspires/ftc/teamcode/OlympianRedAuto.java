package org.firstinspires.ftc.teamcode;

import android.hardware.Sensor;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by mars on 11/2/17.
 */

@Autonomous(name="Olympian Red Auto", group="Testing")
public class OlympianRedAuto extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftMotor1 = null;
    private DcMotor leftMotor2 = null;
    private DcMotor rightMotor1 = null;
    private DcMotor rightMotor2 = null;

    private DcMotor forkliftMotor = null;

    private Servo blockServo1 = null;
    private Servo blockServo2 = null;

    private Servo jewelServo = null;

    private ColorSensor color = null;

    private int tracker = 0;

    // Same stuff as others
    // jewel stuff
    // Score preloaded block

    public void motorBrake(DcMotor motor) {
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void telemetrys() {

        telemetry.addData("Left Motor 1 Position: ", leftMotor1.getCurrentPosition());
        telemetry.addData("Left Motor 2 Position: ", leftMotor2.getCurrentPosition());
        telemetry.addData("Right Motor 1 Position: ", rightMotor1.getCurrentPosition());
        telemetry.addData("Right Motor 2 Position: ", rightMotor2.getCurrentPosition());

        telemetry.addData("Green Value: ", color.green());
        telemetry.addData("Blue Value; ", color.blue());
        telemetry.addData("Red Value: ", color.red());
        telemetry.addData("Alpha Value: ", color.alpha());

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

    public void lifterInit() {

        forkliftMotor = hardwareMap.dcMotor.get("forklift motor");

        motorBrake(forkliftMotor);

    }

    public void blockServoInit() {

        blockServo1 = hardwareMap.servo.get("block servo 1");
        blockServo2 = hardwareMap.servo.get("block servo 2");

        blockServo1.setDirection(Servo.Direction.REVERSE);

        blockServo1.setPosition(0);
        blockServo2.setPosition(0);

    }

    public void jewelServoInit() {
        jewelServo = hardwareMap.servo.get("jewel servo");

        jewelServo.setPosition(0);
    }

    @Override
    public void init() {

        driveInit();
        lifterInit();
        blockServoInit();
        jewelServoInit();

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

        telemetry.addData("Left Motor 1 Power:", leftMotor1.getPower());
        telemetry.addData("Left Motor 2 Power:", leftMotor2.getPower());
        telemetry.addData("Right Motor 1 Power:", rightMotor1.getPower());
        telemetry.addData("Right Motor 2 Power:", rightMotor1.getPower());

        if (tracker == 0) {
            jewelServo.setPosition(1);
            tracker = 1;
        } else if (tracker == 1) {
            if (color.red() >= 50) { // Red is detected
                move(-.3);
            } else if (color.blue() >= 20) { // Blue is detected
                move(.3);
            } else {
                move(0);
            }
        }
    }
    @Override
    public void stop() {

    }
}

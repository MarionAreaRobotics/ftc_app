package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by mars on 11/2/17.
 */

@Autonomous(name="Titan Blue Auto", group="Testing")
public class TitanBlueAuto extends OpMode {

    /*Change this to go forward at least, need to do more debugging*/

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftMotor1 = null;
    private DcMotor leftMotor2 = null;
    private DcMotor rightMotor1 = null;
    private DcMotor rightMotor2 = null;

    private DcMotor glyphArmMotor = null;

    private Servo jouleThiefServo = null;

    private ColorSensor color = null;

    private int tracker = 0;

    public void motorBrake(DcMotor motor) {
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void telemetrys() {

        telemetry.addData("Left Motor 1 Position: ", leftMotor1.getCurrentPosition());
        telemetry.addData("Left Motor 2 Position: ", leftMotor2.getCurrentPosition());
        telemetry.addData("Right Motor 1 Position: ", rightMotor1.getCurrentPosition());
        telemetry.addData("Right Motor 2 Position: ", rightMotor2.getCurrentPosition());

        telemetry.addData("Glyph Arm Motor Position: ", glyphArmMotor.getCurrentPosition());

        telemetry.addData("Joule Thief Servo Position: ", jouleThiefServo.getPosition());

        telemetry.addData("Color Alpha: ", color.alpha());
        telemetry.addData("Color Red: ", color.red());
        telemetry.addData("Color Blue: ", color.blue());
        telemetry.addData("Color Green: ", color.green());

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

    public void glyphArmInit() {

        glyphArmMotor = hardwareMap.dcMotor.get("glyph arm");

        motorBrake(glyphArmMotor);
    }

    public void jouleThiefInit() {

        jouleThiefServo= hardwareMap.servo.get("joule thief");
        jouleThiefServo.setPosition(0);
    }

    public void colorInit() {

        color = hardwareMap.colorSensor.get("color");
    }

    @Override
    public void init() {

        driveInit();
        glyphArmInit();

        jouleThiefInit();

        colorInit();
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

        if (tracker == 0) {
            jouleThiefServo.setPosition(1);
            tracker = 1;
        } else if (tracker == 1 && runtime.time() >= 5) {
            if (color.red() >= 50) { // Red is detected
                move(.3);
            } else if (color.blue() >= 50) { // Blue is detected
                move(-.3);
            } else {
                move(0);
            }
        }

        if (runtime.time() >= 10) {
            jouleThiefServo.setPosition(0);
            tracker = 2;
        }
    }

    @Override
    public void stop() {

    }
}

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
//COLOR SENSOR FACES THE BACK
@Autonomous(name="Muses Red Auto", group="Testing")
public class MusesRedAuto extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftMotor1 = null;
    private DcMotor leftMotor2 = null;
    private DcMotor rightMotor1 = null;
    private DcMotor rightMotor2 = null;

    private Servo bucketServo1 = null;
    private Servo bucketServo2 = null;

    private Servo jewelServo = null;

    private ColorSensor color = null;

    private int tracker = 0;

    public void motorBrake(DcMotor motor) {
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void telemetrys() {

        telemetry.addData("LeftMotor1 Power ", leftMotor1.getPower());
        telemetry.addData("LeftMotor2 Power ", leftMotor2.getPower());
        telemetry.addData("RightMotor1 Power ", rightMotor1.getPower());
        telemetry.addData("RightMotor2 Power ", rightMotor2.getPower());

        telemetry.addData("Bucket Servo 1 Position: ", bucketServo1.getPosition());
        telemetry.addData("Bucket Servo 2 Position: ", bucketServo2.getPosition());

        telemetry.addData("jewel Servo Position: ", jewelServo.getPosition());

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

    public void bucketServoInit() {

        bucketServo1 = hardwareMap.servo.get("bucket servo 1");
        bucketServo2 = hardwareMap.servo.get("bucket servo 2");

        bucketServo1.setPosition(.5);
        bucketServo2.setPosition(.5);
    }

    public void jewelServoInit() {

        jewelServo = hardwareMap.servo.get("jewel servo");
        jewelServo.setDirection(Servo.Direction.REVERSE);
        jewelServo.setPosition(0);

    }

    public void colorInit() {

        color = hardwareMap.colorSensor.get("color");
    }

    @Override
    public void init() {

        driveInit();

        bucketServoInit();
        jewelServoInit();

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

        telemetrys();

        if (tracker == 0) {
            jewelServo.setPosition(1);
            tracker = 1;
        } else if (tracker == 1) {
            if (color.red() >= 50) { // Red is detected
                move(-.3);
            } else if (color.blue() >= 50) { // Blue is detected
                move(.3);
            } else {
                move(0);
            }
        }

        if (runtime.time() >= 10) {
            jewelServo.setPosition(0);
            tracker = 2;
        }

    }

    @Override
    public void stop() {

    }
}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by mars on 1/3/18.
 */

@Autonomous(name = "Glyph Auto", group = "Testing")
public class glyphAuto extends OpMode {


    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftMotor1 = null;
    private DcMotor leftMotor2 = null;
    private DcMotor rightMotor1 = null;
    private DcMotor rightMotor2 = null;

    private DcMotor lifterMotor = null;
    private Servo blockServo = null;

    private DcMotor relicMotor = null;
    private Servo relicServo1 = null;
    private Servo relicServo2 = null;

    private DcMotor glyphArmMotor = null;

    private Servo jouleThiefServo = null;

    private double motorThreshold = 0.065;

    private int tracker = 0;

    public void telemetrys() {

        telemetry.addData("Runtime: ", runtime.time());

        telemetry.addData("Left Motor Power: ", leftMotor1.getPower());
        telemetry.addData("Left Motor Power: ", leftMotor2.getPower());
        telemetry.addData("Right Motor Power: ", rightMotor1.getPower());
        telemetry.addData("Right Motor Power: ", rightMotor2.getPower());

        telemetry.addData("Lifter Motor Power: ", lifterMotor.getPower());
        telemetry.addData("Block Servo Position: ", blockServo.getPosition());

        telemetry.addData("Relic Arm Motor: ", relicMotor.getPower());
        telemetry.addData("Relic Arm Servo 1: ", relicServo1.getPosition());
        telemetry.addData("Relic Arm Servo 2: ", relicServo2.getPosition());

        telemetry.addData("Glyph Arm Motor Position: ", glyphArmMotor.getCurrentPosition());

        //telemetry.addData("Spinner Servo Position: ", spinnerServo.getPosition());
        telemetry.addData("Joule Thief Servo Position: ", jouleThiefServo.getPosition());
        telemetry.update();
    }

    public void driveInit() {

        leftMotor1 = hardwareMap.dcMotor.get("left motor 1");
        leftMotor2 = hardwareMap.dcMotor.get("left motor 2");
        rightMotor1 = hardwareMap.dcMotor.get("right motor 1");
        rightMotor2 = hardwareMap.dcMotor.get("right motor 2");

        rightMotor1.setDirection(DcMotor.Direction.REVERSE);
        rightMotor2.setDirection(DcMotor.Direction.REVERSE);

        leftMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void lifterInit() {

        lifterMotor = hardwareMap.dcMotor.get("lifter motor");

        lifterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void blockServoInit() {

        blockServo = hardwareMap.servo.get("block servo");
        blockServo.setPosition(0);
    }

    public void relicMotorInit() {

        relicMotor = hardwareMap.dcMotor.get("relic motor");

        relicMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void relicServoInit() {

        relicServo1 = hardwareMap.servo.get("relic servo 1");
        relicServo2 = hardwareMap.servo.get("relic servo 2");
        relicServo1.setPosition(0);
        relicServo2.setPosition(0);

    }

    public void jouleThiefInit() {

        jouleThiefServo = hardwareMap.servo.get("joule thief");
        jouleThiefServo.setPosition(1);
    }

    public void glyphArmInit() {

        glyphArmMotor = hardwareMap.dcMotor.get("glyph arm");
        glyphArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        glyphArmMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void init() {

        driveInit();
        lifterInit();
        relicMotorInit();

        blockServoInit();
        jouleThiefInit();
        relicServoInit();
        glyphArmInit();
    }

    @Override
    public void loop() {

        if (leftMotor1.getCurrentPosition() < 432){
            leftMotor1.setPower(1);
            leftMotor2.setPower(1);
            rightMotor1.setPower(1);
            rightMotor2.setPower(1);

        } else if (leftMotor1.getCurrentPosition() >= 432){
            tracker = 1;

        } else if (glyphArmMotor.getCurrentPosition() < 144 && tracker == 1){
            glyphArmMotor.setPower(1);

        } else if (glyphArmMotor.getCurrentPosition() >= 144 && tracker == 1){
            glyphArmMotor.setPower(0);
            tracker = 2;

        } else if (glyphArmMotor.getCurrentPosition() >= 10 && tracker == 2){
            glyphArmMotor.setPower(-1);

        } else if (tracker == 2){
            glyphArmMotor.setPower(0);

        }
    }
}

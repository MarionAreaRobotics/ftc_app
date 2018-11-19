package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name= "Olympian Rover Auto", group = "Autonomous")
public class OlympianRoverAuto extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftMotor1 = null;
    private DcMotor leftMotor2 = null;
    private DcMotor rightMotor1 = null;
    private DcMotor rightMotor2 = null;

    private Servo grabServo1 = null;
    private Servo grabServo2 = null;
    private Servo armServo = null;
    private DcMotor armMotor = null;
    private DcMotor lifterMotor = null;

    private int tracker = 0;

    private double motorThreshold = 0.065;

    public void telemetrys() {

        telemetry.addData("Runtime: ", (runtime.seconds() / 60));

        telemetry.addData("Left Motor 1 Power: ", leftMotor1.getPower());
        telemetry.addData("Left Motor 2 Power: ", leftMotor2.getPower());
        telemetry.addData("Right Motor 1 Power: ", rightMotor1.getPower());
        telemetry.addData("Right Motor 2 Power: ", rightMotor2.getPower());

        telemetry.addData("Right Motor 1 Position", rightMotor1.getCurrentPosition());

        telemetry.addData("Lifter Motor Position", armMotor.getCurrentPosition());

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

    public void armInit (){

        grabServo1 = hardwareMap.servo.get("grab servo 1");
        grabServo2 = hardwareMap.servo.get("grab servo 2");
        armServo = hardwareMap.servo.get("arm servo");
        armMotor = hardwareMap.dcMotor.get("arm motor");
        lifterMotor = hardwareMap.dcMotor.get("lifter motor");

        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lifterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        grabServo2.setDirection(Servo.Direction.REVERSE);
    }

    @Override
    public void init() {
        driveInit();
        armInit();
    }

    public void move(float power1, float power2, float power3, float power4){

        leftMotor1.setPower(power1);
        leftMotor2.setPower(power2);
        rightMotor1.setPower(power3);
        rightMotor2.setPower(power4);
    }

    @Override
    public void loop() {
        telemetrys();
/*
        if (armMotor.getCurrentPosition() <= 1100){
            armMotor.setPower(1);
        } else if (armMotor.getCurrentPosition() >= 100){
            armMotor.setPower(0);

            if(grabServo1.getPosition() != 1){
                grabServo1.setPosition(1);
            }
        }
*/
        if (lifterMotor.getCurrentPosition() >= -900 && tracker == 0) {
            lifterMotor.setPower(.5);
        } else if (tracker == 0 && lifterMotor.getCurrentPosition() <= -900) {
            tracker = 1;
        } else if (tracker == 1 && lifterMotor.getCurrentPosition() >= -900){
            lifterMotor.setPower(0);
            tracker = 2;
        } else if (tracker == 2 && rightMotor1.getCurrentPosition() >= -300){
            move(1,1,-1,-1);
        } else if(tracker == 2 && rightMotor1.getCurrentPosition() <= -300){
            move(0,0,0,0);
        }

        /*
        if (lifterMotor.getCurrentPosition() >= -1600 && tracker == 0) {
            lifterMotor.setPower(.5);
        } else if (tracker == 0 && lifterMotor.getCurrentPosition() <= -1600) {
            tracker = 1;
        } else if (tracker == 1 && lifterMotor.getCurrentPosition() >= 1600){
            lifterMotor.setPower(0);
        } else if (rightMotor1.getCurrentPosition() >= -4800 && tracker == 1) {
            move(1, 1, -1, -1);
        } else if (tracker == 1 && rightMotor1.getCurrentPosition() <= -4800){
            tracker = 2;
        } else if (rightMotor1.getCurrentPosition() <= -3400 && tracker == 2) {
            move(1, 1, 1, 1);
        } else if (tracker == 2 && rightMotor1.getCurrentPosition() >= -3400){
            tracker = 3;
        } else if (tracker == 3){
            move(0,0,0,0);
            tracker = 4;
        } else if (tracker == 4 && armMotor.getCurrentPosition() <= 1100){
            armMotor.setPower(.5);
        } else if (tracker == 4 && armMotor.getCurrentPosition() >= 1100){
            armMotor.setPower(0);
        }
        */
    }

}

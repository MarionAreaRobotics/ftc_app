package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class TitanRoverAuto extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftMotor1 = null;
    private DcMotor leftMotor2 = null;
    private DcMotor rightMotor1 = null;
    private DcMotor rightMotor2 = null;

    private DcMotor armMotor = null;
    private DcMotor jointMotor = null;
    private DcMotor lifterMotor = null;
    private Servo bucketServo = null;
    private Servo lockServo = null;

    private DcMotor slidingMotor = null;

    private int tracker = 0;

    public void telemetrys() {

        telemetry.addData("Runtime: ", runtime.time());

        telemetry.addData("Left Motor 1 Power: ", leftMotor1.getPower());
        telemetry.addData("Left Motor 2 Power: ", leftMotor2.getPower());
        telemetry.addData("Right Motor 1 Power: ", rightMotor1.getPower());
        telemetry.addData("Right Motor 2 Power: ", rightMotor2.getPower());
        telemetry.addData("Lifter Motor Power: ", lifterMotor.getPower());
        telemetry.addData("Lifter Motor Posistion", lifterMotor.getCurrentPosition());

        telemetry.addData("Gamepad 2 Left-Y Position: ", gamepad2.left_stick_y);

        telemetry.update();
    }

    public void driveInit() {

        leftMotor1 = hardwareMap.dcMotor.get("left motor 1");
        leftMotor2 = hardwareMap.dcMotor.get("left motor 2");
        rightMotor1 = hardwareMap.dcMotor.get("right motor 1");
        rightMotor2 = hardwareMap.dcMotor.get("right motor 2");

        leftMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void armInit() {
        armMotor = hardwareMap.dcMotor.get("arm motor");
        jointMotor = hardwareMap.dcMotor.get("joint motor");
        lifterMotor = hardwareMap.dcMotor.get("lifter motor");
        bucketServo = hardwareMap.servo.get("bucket servo");
        slidingMotor = hardwareMap.dcMotor.get("sliding motor");
        lockServo = hardwareMap.servo.get("lock servo");

        lockServo.setPosition(1);

        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        jointMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lifterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slidingMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }


    @Override
    public void init() {
        driveInit();
        armInit();
    }

    public void move(float power1, float power2, float power3, float power4) {

        leftMotor1.setPower(power1);
        leftMotor2.setPower(power2);
        rightMotor1.setPower(power3);
        rightMotor2.setPower(power4);
    }

    @Override
    public void loop() {
        telemetrys();

        if (lockServo.getPosition() == 1 && tracker == 0) {
            if (lockServo.getPosition() != 0) {
                lockServo.setPosition(0);
            }
        } else if (tracker == 0 && lockServo.getPosition() == 0){
            tracker = 1;

        } else if (lifterMotor.getCurrentPosition() <= 750 && tracker == 1) {
            if (lockServo.getPosition() != 0) {
                lockServo.setPosition(0);
            }
            lifterMotor.setPower(.5);
        } else if (lifterMotor.getCurrentPosition() >= 750 && tracker == 1){
            tracker = 2;
        } else if (tracker == 2){
            lifterMotor.setPower(0);
        }

        /*
        if (lockServo.getPosition() == 1 && tracker == 0) {
            if (lockServo.getPosition() != 0) {
                lockServo.setPosition(0);
            }
        } else if (tracker == 0 && lockServo.getPosition() == 0) {
            tracker = 1;

        } else if (lifterMotor.getCurrentPosition() <= 500 && tracker == 1) {
            if (lockServo.getPosition() != 0) {
                lockServo.setPosition(0);
            }
            lifterMotor.setPower(.5);
        } else if (lifterMotor.getCurrentPosition() >= 500 && tracker == 1) {
            tracker = 2;
        } else if (tracker == 2) {
            lifterMotor.setPower(0);

        } else if (leftMotor1.getCurrentPosition() >= -4800 && tracker == 2) {
            move(-1, -1, 1, 1);
        } else if (tracker == 2 && leftMotor1.getCurrentPosition() <= 4800) {
            tracker = 3;

        } else if (leftMotor1.getCurrentPosition() <= 2000 && tracker == 3) {
            move(1, 1, 1, 1);
        } else if (tracker == 3 && leftMotor1.getCurrentPosition() >= 2000) {
            tracker = 4;

        } else if (tracker == 4 && leftMotor1.getCurrentPosition() >= 2000) {
            move(0, 0, 0, 0);
        }

*/
    }
}
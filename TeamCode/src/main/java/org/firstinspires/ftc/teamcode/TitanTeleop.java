package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Titan", group="Teleop")
public class TitanTeleop extends OpMode {

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
    private Servo pincherServo = null;
    private Servo markerServo = null;

    private DcMotor slidingMotor = null;

    private double motorThreshold = 0.065;

    private int tracker = 0;

    public void telemetrys() {

        telemetry.addData("Runtime: ", runtime.time());

        telemetry.addData("Left Motor 1 Power: ", leftMotor1.getPower());
        telemetry.addData("Left Motor 2 Power: ", leftMotor2.getPower());
        telemetry.addData("Right Motor 1 Power: ", rightMotor1.getPower());
        telemetry.addData("Right Motor 2 Power: ", rightMotor2.getPower());
        telemetry.addData("Lifter Motor Power: ", lifterMotor.getPower());
        telemetry.addData("lifter Motor Position", lifterMotor.getCurrentPosition());
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

    public void armInit(){
        armMotor = hardwareMap.dcMotor.get("arm motor");
        jointMotor = hardwareMap.dcMotor.get("joint motor");
        lifterMotor = hardwareMap.dcMotor.get("lifter motor");
        bucketServo = hardwareMap.servo.get("bucket servo");
        slidingMotor = hardwareMap.dcMotor.get("sliding motor");
        lockServo = hardwareMap.servo.get("lock servo");
        pincherServo = hardwareMap.servo.get("pincher servo");


        bucketServo.setPosition(1);

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

    @Override
    public void init_loop() {

        telemetrys();
    }

    public void driveLoop() {
        telemetry.addData("Status", "Running: " + runtime.toString());

        float leftX = -(gamepad1.left_stick_x);// assigning controller values to a variable
        float rightX = -(gamepad1.right_stick_x);
        float leftY = -(gamepad1.left_stick_y);
        float rightY = -(gamepad1.right_stick_y);

        float coord1 = leftY;
        float coord = rightY;
        DcMotor.Direction dir1 = DcMotor.Direction.REVERSE;
        DcMotor.Direction dir2 = DcMotor.Direction.REVERSE;
        DcMotor.Direction dir3 = DcMotor.Direction.FORWARD;
        DcMotor.Direction dir4 = DcMotor.Direction.FORWARD;

        if (((leftX >= motorThreshold) || (leftX <= -motorThreshold)) && ((rightX >= motorThreshold) || (rightX <= -motorThreshold))) {
            dir1 = DcMotor.Direction.FORWARD;
            dir2 = DcMotor.Direction.REVERSE;
            dir3 = DcMotor.Direction.FORWARD;
            dir4 = DcMotor.Direction.REVERSE;
            coord1 = leftX;
            coord = rightX;
        }

        leftMotor1.setDirection(dir1);// Set to FORWARD if using AndyMark motors
        leftMotor2.setDirection(dir2);
        rightMotor1.setDirection(dir3);
        rightMotor2.setDirection(dir4);
        leftMotor1.setPower(coord1);
        leftMotor2.setPower(coord1);
        rightMotor1.setPower(coord);
        rightMotor2.setPower(coord);

    }

    public void armLoop (){

        if (gamepad2.left_trigger > 0){
            slidingMotor.setPower(gamepad2.left_trigger);

        } else if (gamepad2.right_trigger > 0){
            slidingMotor.setPower(-gamepad2.right_trigger);

        } else {
            slidingMotor.setPower(0);

        }

        if (gamepad2.right_stick_y < -.2){
            armMotor.setPower(gamepad2.right_stick_y);

        } else if (gamepad2.right_stick_y > .2){
            armMotor.setPower(gamepad2.right_stick_y);

        } else {
            armMotor.setPower(0);

        }

        if (gamepad2.left_stick_y < -.2){
            jointMotor.setPower(gamepad2.left_stick_y);

        } else if (gamepad2.left_stick_y > .2) {
            jointMotor.setPower(gamepad2.left_stick_y);

        } else {
            jointMotor.setPower(0);
        }

        if (gamepad2.a){
            if (bucketServo.getPosition() != 1){
                bucketServo.setPosition(1);

            }
        } else if (gamepad2.b){
            if (bucketServo.getPosition() != 0) {
                bucketServo.setPosition(0);

            }
        } else {
            if (bucketServo.getPosition() != .5){
                bucketServo.setPosition(.5);
            }
        }

        if (gamepad2.left_bumper){
            if (bucketServo.getPosition() !=1){
                bucketServo.setPosition(1);
            }
        } else if (gamepad2.right_bumper){
            if (bucketServo.getPosition() != 0){
                bucketServo.setPosition(0);
            }
        }
    }

    public void lifterLoop(){

        if (gamepad1.right_trigger > 0){
           lifterMotor.setPower(gamepad1.right_trigger);

        }else if(gamepad1.left_trigger > 0) {
            lifterMotor.setPower(-gamepad1.left_trigger);

        }else{
            lifterMotor.setPower(0);

        }

    }

    public void lockloop(){
        if (gamepad2.x) {
            lockServo.setPosition(1);
        } else{
            lockServo.setPosition(0);
        }
    }

    @Override
    public void loop() {

        driveLoop();
        armLoop();
        lockloop();
        lifterLoop();

        telemetrys();
    }

    @Override
    public void stop() {

    }
}

/*

        telemetry.addData("Block Servo Position: ", blockServo.getPosition());

        telemetry.addData("Relic Arm Motor: ", relicMotor.getPower());
        telemetry.addData("Relic Arm Servo 1: ", relicServo1.getPosition());
        telemetry.addData("Relic Arm Servo 2: ", relicServo2.getPosition());

        telemetry.addData("Glyph Arm Motor Position: ", glyphArmMotor.getCurrentPosition());


       telemetry.addData("Joule Thief Servo Position: ", jouleThiefServo.getPosition());
       */

 /*

    public void blockServoInit() {

        blockServo = hardwareMap.servo.get("block servo");
        //blockServo.setPosition(0);
    }

    public void relicMotorInit() {

        relicMotor = hardwareMap.dcMotor.get("relic motor");

        relicMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void relicServoInit() {

        relicServo1 = hardwareMap.servo.get("relic servo 1");
        relicServo2 = hardwareMap.servo.get("relic servo 2");
        //relicServo1.setPosition(0);
        //relicServo2.setPosition(0);
        relicServo2.setDirection(Servo.Direction.REVERSE);

    }

    public void jouleThiefInit() {

        jouleThiefServo = hardwareMap.servo.get("joule thief");
        //jouleThiefServo.setPosition(1);
    }

    public void glyphArmInit() {

        glyphArmMotor = hardwareMap.dcMotor.get("glyph arm");
        glyphArmMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        glyphArmMotor.setPower(0);
    }
*/

/*


    public void blockServoLoop() {

        if(gamepad2.right_bumper) {
            if (blockServo.getPosition() != 1) {
                blockServo.setPosition(1);
            }
        } else {
            if (blockServo.getPosition() != 0){
                blockServo.setPosition(0);
            }
        }
    }

    public void jouleThiefLoop() {
        if (gamepad2.a) {
            if (jouleThiefServo.getPosition() != 1) {
                jouleThiefServo.setPosition(1);
            }
        } else if (gamepad2.b) {
            if (jouleThiefServo.getPosition() != 0) {
                jouleThiefServo.setPosition(0);
            }
        }
    }

    public void relicMotorLoop() {

        if (gamepad2.left_trigger > 0) {
            relicMotor.setPower(1);
        } else if (gamepad2.left_bumper) {
            relicMotor.setPower(-1);
        } else {
            relicMotor.setPower(0);
        }
    }

    public void relicServoLoop() {

        if (gamepad2.right_stick_y > 0) {
            relicServo2.setPosition(gamepad2.right_stick_y);
        } else if (gamepad2.right_stick_y < 0) {
            relicServo2.setPosition(-gamepad2.right_stick_y);
        } else {
            if (relicServo2.getPosition() != 0){
                relicServo2.setPosition(0);
            }
        }

        if (gamepad2.x) {
            if (relicServo1.getPosition() != 1) {
                relicServo1.setPosition(1);
                tracker = 1;
            }
        } else if (gamepad2.y) {
            if (relicServo1.getPosition() != 0) {
                relicServo1.setPosition(0);
                tracker = 0;
            }
        }
    }

    public void glyphArmLoop() {

        glyphArmMotor.setTargetPosition(0);
        glyphArmMotor.setPower(-.15);
    }
*/
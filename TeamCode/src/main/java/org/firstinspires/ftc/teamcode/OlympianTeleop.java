package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Olympian", group="Teleop")
public class OlympianTeleop extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftMotor1 = null;
    private DcMotor leftMotor2 = null;
    private DcMotor rightMotor1 = null;
    private DcMotor rightMotor2 = null;

    private Servo grabServo1 = null;
    private Servo grabServo2 = null;
    private Servo driveServo = null;
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

        telemetry.addData("Arm Motor Power: ", armMotor.getPower());
        telemetry.addData("Arm Motor Position: ", armMotor.getCurrentPosition());

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
        driveServo = hardwareMap.servo.get("drive servo");

        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lifterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        grabServo1.setPosition(.5);
        grabServo2.setPosition(.5);
        armServo.setPosition(0);
        driveServo.setPosition(0);

        grabServo2.setDirection(Servo.Direction.REVERSE);
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

    @Override
    public void start() {

        runtime.reset();
    }

    public void driveLoop() {
        // Driving control "telemetry"
        telemetry.addData("Status", "Running: " + runtime.toString());
        telemetry.addData("RightX", gamepad1.right_stick_x);
        telemetry.addData("RightY", gamepad1.right_stick_y);
        telemetry.addData("LeftX", gamepad1.left_stick_x);
        telemetry.addData("LeftY", gamepad1.left_stick_y);

        float leftX = -(gamepad1.left_stick_x/2);// assigning controller values to a variable
        float rightX = -(gamepad1.right_stick_x/2);
        float leftY = -(gamepad1.left_stick_y/2);
        float rightY = -(gamepad1.right_stick_y/2);

        float coord1 = leftY;
        float coord = rightY;
        DcMotor.Direction dir1 = DcMotor.Direction.FORWARD;
        DcMotor.Direction dir2 = DcMotor.Direction.FORWARD;
        DcMotor.Direction dir3 = DcMotor.Direction.REVERSE;
        DcMotor.Direction dir4 = DcMotor.Direction.REVERSE;

        if (((leftX >= motorThreshold) || (leftX <= -motorThreshold)) && ((rightX >= motorThreshold) || (rightX <= -motorThreshold))) {
            dir1 = DcMotor.Direction.REVERSE;
            dir2 = DcMotor.Direction.FORWARD;
            dir3 = DcMotor.Direction.REVERSE;
            dir4 = DcMotor.Direction.FORWARD;
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

    public void ServoMethod(Servo servo, double Position, boolean button) {

        if (button) {
            if (servo.getPosition() != Position) {
                servo.setPosition(Position);
            }
        }
    }

    public void armLoop() {

        ServoMethod(grabServo1, .4, gamepad2.a);
        ServoMethod(grabServo1, 1, gamepad2.b);
        ServoMethod(grabServo2, .4, gamepad2.x);
        ServoMethod(grabServo2, 1, gamepad2.y);




        ServoMethod(armServo,.3, gamepad2.dpad_right);
        ServoMethod(armServo, .7, gamepad2.dpad_left);
        ServoMethod(armServo, 0, gamepad2.dpad_up);
        ServoMethod(armServo, 1, gamepad2.dpad_down);

        ServoMethod(driveServo, 1, gamepad1.a);
        ServoMethod(driveServo, 0, gamepad1.b);


       if (gamepad2.right_trigger > 0){
           armMotor.setPower(gamepad2.right_trigger);
       } else if (gamepad2.left_trigger > 0) {
           armMotor.setPower(-gamepad2.left_trigger);
       } else {
           armMotor.setPower(0);
       }

       if (gamepad1.right_trigger > 0) {
            lifterMotor.setPower(gamepad1.right_trigger);

       } else if (gamepad1.left_trigger > 0) {
            lifterMotor.setPower(-gamepad1.left_trigger);

       } else {
            lifterMotor.setPower(0);

       }
    }

    @Override
    public void loop() {
        driveLoop();
        armLoop();
        telemetrys();
    }

    @Override
    public void stop() {

    }
}

 /*
    public void armLoop () {

        rotateMotor.setPower(-gamepad2.left_stick_x);
        bottomJointMotor.setPower(-gamepad2.left_stick_y);
        topJointMotor.setPower(-gamepad2.right_stick_y);

        if (gamepad2.a) {
            if (clawServo1.getPosition() != 1) {
                clawServo1.setposition(1):
            }
        } else {
            if (clawServo1.getPosition() != 0) {
            clawServo1.setposition(0):
            }
        }

    }
    */

 /*
    public void armInit(){

        rotateMotor = hardwareMap.dcMotor.get("rotate motor");
        bottomJointMotor = hardwareMap.dcMotor.get("bottom joint motor");
        topJointMotor = hardwareMap.dcMotor.get("top joint motor");

        clawServo1 = hardwareMap.dcMotor.get("claw servo 1");
        clawServo1.setPosition(1);
    }
    */
    /*
    public void liftInit() {

        forkliftMotor = hardwareMap.dcMotor.get("forklift motor");
        forkliftMotor.setDirection(DcMotor.Direction.REVERSE);

        forkliftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        blockServo1 = hardwareMap.servo.get("block servo 1");
        blockServo2 = hardwareMap.servo.get("block servo 2");
        blockServo1.setDirection(Servo.Direction.REVERSE);

        blockServo1.setPosition(0);
        blockServo2.setPosition(0);
    }

    public void colorInit() {

        jewelServo = hardwareMap.servo.get("jewel servo");
        color = hardwareMap.colorSensor.get("color");

        jewelServo.setPosition(0);

    }
*/
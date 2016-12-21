package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Olympian", group="Testing1")
public class OlympianTest extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftMotor1 = null;
    private DcMotor leftMotor2 = null;
    private DcMotor rightMotor1 = null;
    private DcMotor rightMotor2 = null;
    private Servo gateServo1 = null;
    //private Servo gateServo2 = null;
    private Servo pushbarServo1 = null;
    private Servo pushbarServo2 = null;
    //private DcMotor harvesterMotor = null;
    private DcMotor launcherMotor1 = null;
    private DcMotor launcherMotor2 = null;
    private double motorThreshold = 0.065;

    public void driveInit() {
        telemetry.addData("Status", "Initialized");

        leftMotor1 = hardwareMap.dcMotor.get("left motor 1");
        leftMotor2 = hardwareMap.dcMotor.get("left motor 2");
        rightMotor1 = hardwareMap.dcMotor.get("right motor 1");
        rightMotor2 = hardwareMap.dcMotor.get("right motor 2");

        // leftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors

        telemetry.addData("Status", "Initialized");
    }

    public void gateServoInit() {
        gateServo1 = hardwareMap.servo.get("gate");
        gateServo1.setDirection(Servo.Direction.REVERSE);
    }

    public void pushbarServoInit() {
        pushbarServo1 = hardwareMap.servo.get("push bar 1");
        pushbarServo1.setDirection(Servo.Direction.REVERSE);
        pushbarServo1.setPosition(0);
        pushbarServo2 = hardwareMap.servo.get("push bar 2");
        pushbarServo2.setPosition(0);
    }
    public void launcherInit() {
        launcherMotor1 = hardwareMap.dcMotor.get("launcher motor 1");
        launcherMotor2 = hardwareMap.dcMotor.get("launcher motor 2");
    }

    @Override
    public void init() {

        driveInit();
        gateServoInit();
        pushbarServoInit();
        //launcherInit();
    }

    @Override
    public void init_loop() {

    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */

    public void driveLoop() {
        // Driving control "telemetry"
        telemetry.addData("Status", "Running: " + runtime.toString());
        telemetry.addData("RightX", gamepad1.right_stick_x);
        telemetry.addData("RightY", gamepad1.right_stick_y);
        telemetry.addData("LeftX", gamepad1.left_stick_x);
        telemetry.addData("LeftY", gamepad1.left_stick_y);

        float leftX = -gamepad1.left_stick_x;// assigning controller values to a variable
        float rightX = -gamepad1.right_stick_x;
        float leftY = -gamepad1.left_stick_y;
        float rightY = -gamepad1.right_stick_y;

        float coord1 = leftY;
        float coord = rightY;
        DcMotor.Direction dir1 = DcMotor.Direction.FORWARD;
        DcMotor.Direction dir2 = DcMotor.Direction.FORWARD;
        DcMotor.Direction dir3 = DcMotor.Direction.REVERSE;
        DcMotor.Direction dir4 = DcMotor.Direction.REVERSE;
        if (((leftX >= motorThreshold) || (leftX <= -motorThreshold)) && ((rightX >= motorThreshold) || (rightX <= -motorThreshold))) {
            dir2 = DcMotor.Direction.REVERSE;
            dir3 = DcMotor.Direction.FORWARD;
            coord = leftX;
            coord1 = rightX;
        }
        leftMotor1.setDirection(dir1);// Set to FORWARD if using AndyMark motors
        leftMotor2.setDirection(dir2);
        rightMotor1.setDirection(dir3);
        rightMotor2.setDirection(dir4);
        leftMotor1.setPower(coord1);
        leftMotor2.setPower(coord1);
        rightMotor1.setPower(coord);
        rightMotor2.setPower(coord);
        //switch the directions on the auto
    }

    public void gateServoLoop() {

        if (gamepad1.a) {
            if (gateServo1.getPosition() != 1) {
                gateServo1.setPosition(1);
            }
        } else {
            if (gateServo1.getPosition() != 0) {
                gateServo1.setPosition(0);
            }
        }

        /*
        if (gamepad2.dpad_up) {
            contServo.setPosition(1);
        } else if (gamepad2.dpad_down) {
            contServo.setPosition(0);
        if (servo.getPosition() == 1) {
            scoringMotor.setPower(1);
        }

        if (servo.getPosition() == 0) {

        } else
            contServo.setPosition(0.5);
        }
        */
    }

    public void pushBarServoLoop() {
        if (gamepad1.left_bumper) {
            if (pushbarServo1.getPosition() != .7) {
                pushbarServo1.setPosition(.7);
                pushbarServo2.setPosition(.7);
            }
        } else {
            if (pushbarServo1.getPosition() != 0) {
                pushbarServo1.setPosition(0);
                pushbarServo2.setPosition(0);
            }
        }
    }

    /*public void launcherLoop(){
        if (gamepad2.right_trigger > 0) {
            launcherMotor1.setDirection(DcMotor.Direction.REVERSE);
            launcherMotor2.setDirection(DcMotor.Direction.FORWARD);
            launcherMotor1.setPower(gamepad2.right_trigger);
            launcherMotor2.setPower(gamepad2.right_trigger);
        } else if (gamepad2.left_trigger > 0) {
            launcherMotor1.setDirection(DcMotor.Direction.FORWARD);
            launcherMotor2.setDirection(DcMotor.Direction.REVERSE);
            launcherMotor1.setPower(gamepad2.left_trigger);
            launcherMotor2.setPower(gamepad2.left_trigger);
        } else {leftMotor2.setPower(coord1);
            launcherMotor1.setPower(0);leftMotor2.setPower(coord1);
            launcherMotor2.setPower(0);
        }
    }
*/
    @Override
    public void loop() {
        driveLoop();
        gateServoLoop();
        pushBarServoLoop();
        //launcherLoop();
    }
    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}

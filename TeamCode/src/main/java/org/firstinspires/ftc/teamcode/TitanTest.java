package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Titan", group="Testing")
public class TitanTest extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftMotor1 = null;
    private DcMotor leftMotor2 = null;
    private DcMotor rightMotor1 = null;
    private DcMotor rightMotor2 = null;

    private DcMotor leftLinearSlide = null;
    private DcMotor rightLinearSlide = null;

    private DcMotor launchMotor = null;

    private DcMotor harvestMotor = null;

    private Servo capballServo = null;

    private Servo holder = null;

    public void driveInit() {

        leftMotor1  = hardwareMap.dcMotor.get("left motor 1");
        leftMotor2  = hardwareMap.dcMotor.get("left motor 2");
        rightMotor1 = hardwareMap.dcMotor.get("right motor 1");
        rightMotor2 = hardwareMap.dcMotor.get("right motor 2");

        rightMotor1.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        rightMotor2.setDirection(DcMotor.Direction.REVERSE);
    }

    public void linearSlidesInit() {

        leftLinearSlide = hardwareMap.dcMotor.get("left linear slide");
        rightLinearSlide = hardwareMap.dcMotor.get("right linear slide");

        leftLinearSlide.setDirection(DcMotor.Direction.REVERSE);
    }

    public void armServosInit() {

        capballServo = hardwareMap.servo.get("capball servo");
        capballServo.setPosition(.2);
    }

    public void launcherInit() {

        launchMotor = hardwareMap.dcMotor.get("launcher");
    }

    public void harvesterInit() {

        harvestMotor = hardwareMap.dcMotor.get("harvester");
    }

    public void holderInit() {

        holder = hardwareMap.servo.get("holder");
        holder.setDirection(Servo.Direction.REVERSE);

        holder.setPosition(.1);
    }

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        driveInit();
        linearSlidesInit();
        armServosInit();
        launcherInit();
        harvesterInit();
        holderInit();
    }

    @Override
    public void init_loop() {
        telemetry.addData("left1", leftMotor2.getPower());
        telemetry.addData("left2", leftMotor1.getPower());
        telemetry.addData("right1", rightMotor1.getPower());
        telemetry.addData("right2", rightMotor2.getPower());

        telemetry.addData("harvester: ", harvestMotor.getCurrentPosition());

        telemetry.addData("Capball Pos: ", capballServo.getPosition());

        telemetry.addData("holder: ", holder.getPosition());
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {runtime.reset();}

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */

    public void driveLoop() {

        if (gamepad1.right_trigger > 0) {
            leftMotor1.setPower((-gamepad1.left_stick_y) / 2);
            leftMotor2.setPower((-gamepad1.left_stick_y) / 2);
            rightMotor1.setPower((-gamepad1.right_stick_y) / 2);
            rightMotor2.setPower((-gamepad1.right_stick_y) / 2);
        } else {
            leftMotor1.setPower(-gamepad1.left_stick_y);
            leftMotor2.setPower(-gamepad1.left_stick_y);
            rightMotor1.setPower(-gamepad1.right_stick_y);
            rightMotor2.setPower(-gamepad1.right_stick_y);
        }
        telemetry.addData("left1", leftMotor2.getPower());
        telemetry.addData("left2", leftMotor1.getPower());
        telemetry.addData("right1", rightMotor1.getPower());
        telemetry.addData("right2", rightMotor2.getPower());

    }

    public void linearSlidesLoop() {

        leftLinearSlide.setPower(-gamepad2.left_stick_y);
        rightLinearSlide.setPower(-gamepad2.right_stick_y);

    }

    public void armServosLoop() {

        if (gamepad2.right_bumper) {
            if (capballServo.getPosition() != 1) {
                capballServo.setPosition(1);
            }
        } else {
            if (capballServo.getPosition() != .2) {
                capballServo.setPosition(.2);
            }
        }

        telemetry.addData("Capball Pos: ", capballServo.getPosition());
    }

    public void launcherLoop() {

        if (gamepad2.a) {
            launchMotor.setPower(1);
        } else {
            launchMotor.setPower(0);
        }

    }

    public void harvesterLoop() {

        if (gamepad2.left_trigger > 0) {
            harvestMotor.setPower(.5);
        } else if (gamepad2.left_bumper) {
            harvestMotor.setPower(-.5);
        } else {
            harvestMotor.setPower(0);
        }

        telemetry.addData("harvestPos: ", harvestMotor.getCurrentPosition());
	    telemetry.addData("harvestPow: ", harvestMotor.getPower());
    }

    public void holderLoop() {

        if (gamepad2.right_trigger > 0) {
            if (holder.getPosition() != .4) {
                holder.setPosition(.4);
            }
        } else {
            if (holder.getPosition() != .1) {
                holder.setPosition(.1);
            }
        }

        telemetry.addData("holder: ", holder.getPosition());
    }

    @Override
    public void loop() {
        telemetry.addData("Status", "Running: " + runtime.toString());

        driveLoop();
        linearSlidesLoop();
        armServosLoop();
        launcherLoop();
        harvesterLoop();
        holderLoop();
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        /*
        * This is going to reset the Servo Position when the program stops
        */

    }

}

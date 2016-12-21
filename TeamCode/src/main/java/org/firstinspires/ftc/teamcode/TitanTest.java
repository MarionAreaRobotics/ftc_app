package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Titan", group="Testing")
public class TitanTest extends OpMode
{

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftMotor1 = null;
    private DcMotor leftMotor2 = null;
    private DcMotor rightMotor1 = null;
    private DcMotor rightMotor2 = null;

    private DcMotor leftLinearSlide = null;
    private DcMotor rightLinearSlide = null;

    private DcMotor launchMotor = null;

    private Servo leftArmServo = null;

    private Servo launchServo = null;

    public void driveInit() {

        leftMotor1  = hardwareMap.dcMotor.get("left motor 1");
        leftMotor2  = hardwareMap.dcMotor.get("left1");
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

        leftArmServo = hardwareMap.servo.get("left arm servo");
        leftArmServo.setPosition(.2);
    }

    public void launchMotorInit() {

        launchMotor = hardwareMap.dcMotor.get("launch motor");

        launchMotor.setTargetPosition(0);
    }

    public void launchServoInit() {

        launchServo = hardwareMap.servo.get("launch servo");
        launchServo.setPosition(0);
    }

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        driveInit();
        linearSlidesInit();
        armServosInit();
//        launchMotorInit();
//        launchServoInit();
    }


    @Override
    public void init_loop() {
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
            if (leftArmServo.getPosition() != 1) {
                leftArmServo.setPosition(1);
            }
        } else {
            if (leftArmServo.getPosition() != .2) {
                leftArmServo.setPosition(.2);
            }
        }

        telemetry.addData("Left Servo Pos: ", leftArmServo.getPosition());
    }

    public void launchMotorLoop() {

        /*
        * This block is going to make the launch motor move 2 rotation on each press of the button allegedly
        */

        if (gamepad2.a) {
            //launchMotor.setPower(1);
            /*
            *This may work, but highly unlikely
            */
            int launchMotorPos = launchMotor.getCurrentPosition();
            launchMotorPos = launchMotorPos + 6000;
            launchMotor.setTargetPosition(launchMotorPos);
        } else {
            launchMotor.setPower(0);
        }

        telemetry.addData("Launch Motor Power:", launchMotor.getPower());
        telemetry.addData("Launch Motor Position:", launchMotor.getCurrentPosition());
    }

    public void launchServoLoop() {

        if (gamepad2.b) {
            if (launchServo.getPosition() != 1) {
                launchServo.setPosition(1);
            }
        }

        telemetry.addData("Launch Servo", launchServo.getPosition());
    }

    @Override
    public void loop() {
        telemetry.addData("Status", "Running: " + runtime.toString());

        driveLoop();
        linearSlidesLoop();
        armServosLoop();
//        launchMotorLoop();
//        launchServoLoop();
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        /*
        * This is going to reset the Servo Position when the program stops
        */
        //launchServo.setPosition(0);

    }

}

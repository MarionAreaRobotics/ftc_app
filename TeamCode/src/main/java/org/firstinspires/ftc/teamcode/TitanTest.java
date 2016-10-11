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

    private Servo leftArmServo = null;
    private Servo rightArmServo = null;

    public void driveInit() {
        leftMotor1  = hardwareMap.dcMotor.get("left motor 1");
        leftMotor2  = hardwareMap.dcMotor.get("left motor 2");
        rightMotor1 = hardwareMap.dcMotor.get("right motor 1");
        rightMotor2 = hardwareMap.dcMotor.get("right motor 2");

        // leftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        rightMotor1.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        rightMotor2.setDirection(DcMotor.Direction.REVERSE);
    }

    public void linearSlidesInit() {
        leftLinearSlide = hardwareMap.dcMotor.get("left linear slide");
        rightLinearSlide = hardwareMap.dcMotor.get("right linear slide");

        rightLinearSlide.setDirection(DcMotor.Direction.REVERSE);
    }

    public void armServosInit() {
        leftArmServo = hardwareMap.servo.get("left arm servo");
        rightArmServo = hardwareMap.servo.get("right arm servo");

        rightArmServo.setDirection(Servo.Direction.REVERSE);
    }

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
        driveInit();
        linearSlidesInit();
        armServosInit();
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

        leftMotor1.setPower(-gamepad1.left_stick_y);
        leftMotor2.setPower(-gamepad1.left_stick_y);
        rightMotor1.setPower(-gamepad1.right_stick_y);
        rightMotor2.setPower(-gamepad1.right_stick_y);

    }

    public void linearSlidesLoop() {

        leftLinearSlide.setPower(-gamepad2.left_stick_y);
        rightLinearSlide.setPower(-gamepad2.left_stick_y);

    }

    public void armServosLoop() {

        if(gamepad1.a){
            rightArmServo.setPosition(1);
        }

    }

    @Override
    public void loop() {
        telemetry.addData("Status", "Running: " + runtime.toString());

        driveLoop();
        linearSlidesLoop();
        armServosLoop();
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}

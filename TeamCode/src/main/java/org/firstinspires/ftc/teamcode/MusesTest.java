package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Muses", group="Testing")
public class MusesTest extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftMotor1 = null;
    private DcMotor leftMotor2 = null;
    private DcMotor rightMotor1 = null;
    private DcMotor rightMotor2 = null;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        leftMotor1 = hardwareMap.dcMotor.get("left motor 1");
        leftMotor2 = hardwareMap.dcMotor.get("left motor 2");
        rightMotor1 = hardwareMap.dcMotor.get("right motor 1");
        rightMotor2 = hardwareMap.dcMotor.get("right motor 2");

        // leftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors

        telemetry.addData("Status", "Initialized");
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
    @Override
    public void loop() {
        telemetry.addData("Status", "Running: " + runtime.toString());
        telemetry.addData("RightX", gamepad1.right_stick_x);
        telemetry.addData("RightY", gamepad1.right_stick_y);
        telemetry.addData("LeftX", gamepad1.left_stick_x);
        telemetry.addData("LeftY", gamepad1.left_stick_y);

        float leftX = -gamepad1.left_stick_x;
        float rightX = -gamepad1.right_stick_x;
        float leftY = -gamepad1.left_stick_y;
        float rightY = -gamepad1.right_stick_y;


        if ((leftX >= 0.05 && rightX >= 0.05) || (leftX <= -0.05 && rightX <= -0.05)) {
            rightMotor1.setDirection(DcMotor.Direction.FORWARD);
            leftMotor1.setDirection(DcMotor.Direction.FORWARD);
            rightMotor2.setDirection(DcMotor.Direction.REVERSE);
            leftMotor2.setDirection(DcMotor.Direction.REVERSE);
            leftMotor1.setPower(rightX);
            rightMotor1.setPower(rightX);
            leftMotor2.setPower(leftX);
            rightMotor2.setPower(leftX);
        } else {
            rightMotor1.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
            rightMotor2.setDirection(DcMotor.Direction.REVERSE);
            leftMotor1.setDirection(DcMotor.Direction.FORWARD);
            leftMotor2.setDirection(DcMotor.Direction.FORWARD);
            leftMotor1.setPower(rightY);
            leftMotor2.setPower(rightY);
            rightMotor1.setPower(leftY);
            rightMotor2.setPower(leftY);
        }
    }
    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}

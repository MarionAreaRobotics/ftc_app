package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="First OpMode", group="Testing")
public class FirstOpMode extends OpMode
{

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftMotor1 = null;
    private DcMotor leftMotor2 = null;
    private DcMotor rightMotor1 = null;
    private DcMotor rightMotor2 = null;

    private double motorThreshold = 0.065;

    //private Servo servo = null;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        leftMotor1 = hardwareMap.dcMotor.get("left motor 1");
        leftMotor2 = hardwareMap.dcMotor.get("left motor 2");
        rightMotor1 = hardwareMap.dcMotor.get("right motor 1");
        rightMotor2 = hardwareMap.dcMotor.get("right motor 2");

        //leftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
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

        float leftX = -(gamepad1.left_stick_x/2);// assigning controller values to a variable
        float rightX = -(gamepad1.right_stick_x/2);
        float leftY = -(gamepad1.left_stick_y/2);
        float rightY = -(gamepad1.right_stick_y/2);

        float coord1 = leftY;
        float coord = rightY;
        DcMotor.Direction dir1 = DcMotor.Direction.REVERSE;
        DcMotor.Direction dir2 = DcMotor.Direction.FORWARD;
        DcMotor.Direction dir3 = DcMotor.Direction.FORWARD;
        DcMotor.Direction dir4 = DcMotor.Direction.REVERSE;

        if (((leftX >= motorThreshold) || (leftX <= -motorThreshold)) && ((rightX >= motorThreshold) || (rightX <= -motorThreshold))) {
            dir1 = DcMotor.Direction.FORWARD;
            dir2 = DcMotor.Direction.FORWARD;
            dir3 = DcMotor.Direction.FORWARD;
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

    /*
     * Code to run ONCE after the driver hits STOP
     */

    @Override
    public void stop() {
    }

}

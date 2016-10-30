package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Olympian", group="Testing")
public class OlympianTest extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftMotor1 = null;
    private DcMotor leftMotor2 = null;
    private DcMotor rightMotor1 = null;
    private DcMotor rightMotor2 = null;
    private DcMotor harvesterMotor = null;
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

    public void harvesterInit() {
        harvesterMotor = hardwareMap.dcMotor.get("harvester motor");
    }

    public void launcherInit() {
        launcherMotor1 = hardwareMap.dcMotor.get("launcher motor 1");
        launcherMotor2 = hardwareMap.dcMotor.get("launcher motor 2");
    }

    @Override
    public void init() {
        driveInit();
        //harvesterInit();
        launcherInit();
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

        float leftX = -gamepad1.left_stick_x;
        float rightX = -gamepad1.right_stick_x;
        float leftY = -gamepad1.left_stick_y;
        float rightY = -gamepad1.right_stick_y;

        float coord = leftY;
        DcMotor.Direction dir1 = DcMotor.Direction.REVERSE;
        DcMotor.Direction dir2 = DcMotor.Direction.REVERSE;
        DcMotor.Direction dir3 = DcMotor.Direction.FORWARD;
        DcMotor.Direction dir4 = DcMotor.Direction.FORWARD;

        if ((leftX >= motorThreshold) || (leftX <= -motorThreshold)) {
            dir1 = DcMotor.Direction.FORWARD;
            dir4 = DcMotor.Direction.REVERSE;
            coord = leftX;
        } else if((rightX >= motorThreshold) || (rightX <= -motorThreshold)){
            dir1 = DcMotor.Direction.FORWARD;
            dir2 = DcMotor.Direction.FORWARD;
            coord = rightX;
        }
        rightMotor1.setDirection(dir1);// Set to FORWARD if using AndyMark motors
        rightMotor2.setDirection(dir2);
        leftMotor1.setDirection(dir3);
        leftMotor2.setDirection(dir4);
        leftMotor1.setPower(coord);
        leftMotor2.setPower(coord);
        rightMotor1.setPower(coord);
        rightMotor2.setPower(coord);
    }

    public void harvesterLoop() {
        if (gamepad2.dpad_up) {
            harvesterMotor.setPower(1);
        } else if (gamepad2.dpad_down) {
            harvesterMotor.setPower(-1);
            } else {
                harvesterMotor.setPower(0);
        }
    }

    public void launcherLoop(){
        if (gamepad2.right_trigger > 0) {
            launcherMotor1.setDirection(DcMotor.Direction.FORWARD);
            launcherMotor2.setDirection(DcMotor.Direction.REVERSE);
            launcherMotor1.setPower(gamepad2.right_trigger);
            launcherMotor2.setPower(gamepad2.right_trigger);
        } else if (gamepad2.left_trigger > 0) {
            launcherMotor1.setDirection(DcMotor.Direction.REVERSE);
            launcherMotor2.setDirection(DcMotor.Direction.FORWARD);
            launcherMotor1.setPower(gamepad2.left_trigger);
            launcherMotor2.setPower(gamepad2.left_trigger);
        } else {
            launcherMotor1.setPower(0);
            launcherMotor2.setPower(0);
        }
    }

    @Override
    public void loop() {
        driveLoop();
        //harvesterLoop();
        launcherLoop();
    }
    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}

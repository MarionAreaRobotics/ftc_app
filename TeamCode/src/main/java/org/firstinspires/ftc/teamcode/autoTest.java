package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by mars on 11/11/17.
 */
@Autonomous(name = "Backup Auto", group = "Testing")
public class autoTest extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftMotor1 = null;
    private DcMotor leftMotor2 = null;
    private DcMotor rightMotor1 = null;
    private DcMotor rightMotor2 = null;

    private int tracker = 0;

    public void motorBrake(DcMotor motor) {
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void telemetrys() {

        telemetry.addData("Left Motor 1 Position: ", leftMotor1.getCurrentPosition());
        telemetry.addData("Left Motor 2 Position: ", leftMotor2.getCurrentPosition());
        telemetry.addData("Right Motor 1 Position: ", rightMotor1.getCurrentPosition());
        telemetry.addData("Right Motor 2 Position: ", rightMotor2.getCurrentPosition());

        telemetry.addData("Tracker: ", tracker);
        telemetry.update();
    }

    public void driveInit() {

        leftMotor1 = hardwareMap.dcMotor.get("left motor 1");
        leftMotor2 = hardwareMap.dcMotor.get("left motor 2");
        rightMotor1 = hardwareMap.dcMotor.get("right motor 1");
        rightMotor2 = hardwareMap.dcMotor.get("right motor 2");

        rightMotor1.setDirection(DcMotor.Direction.REVERSE);
        rightMotor2.setDirection(DcMotor.Direction.REVERSE);

        motorBrake(leftMotor1);
        motorBrake(leftMotor2);
        motorBrake(rightMotor1);
        motorBrake(rightMotor2);
    }

    @Override
    public void init() {

        driveInit();
    }

    @Override
    public void init_loop() {

        telemetrys();
    }

    @Override
    public void start() {

    }

    public void move(double power) {
        leftMotor1.setPower(power);
        leftMotor2.setPower(power);
        rightMotor1.setPower(power);
        rightMotor2.setPower(power);
    }

    @Override
    public void loop() {

        if (tracker == 0 && leftMotor1.getCurrentPosition() > -3000) {
            move(-.3);
        } else if (tracker == 0 && leftMotor1.getCurrentPosition() <= -3000){
            move(0);
            tracker = 1;
        }

        if (tracker == 1 && leftMotor1.getCurrentPosition() <= -2800) {
            move(.3);
        } else if (tracker == 1 && leftMotor1.getCurrentPosition() < 0) {
            move(0);
            tracker = 2;
        }
    }

    @Override
    public void stop() {

    }
}


/*package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import static android.os.SystemClock.sleep;

/**
 * Created by mars on 11/14/17.
 /
@Disabled
@Autonomous(name="AutoTestingStuff", group="Testing")
public class autoTest extends OpMode {

    private DcMotor leftMotor = null;
    private DcMotor rightMotor = null;

    @Override
    public void init() {

        leftMotor  = hardwareMap.dcMotor.get("left motor");
        rightMotor = hardwareMap.dcMotor.get("right motor");

        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    @Override
    public void start() {

    }

    public void motorPowerLoop(double Power){

        leftMotor.setPower(Power);
        rightMotor.setPower(Power);

    }

    @Override
    public void loop() {

        int motorPos = leftMotor.getCurrentPosition();

        telemetry.addData("Left Position: ", leftMotor.getCurrentPosition());
        telemetry.addData("Right Position: ", rightMotor.getCurrentPosition());

        if(motorPos <= 5000){

            motorPowerLoop(.2);

        } else if(motorPos <= 10000){

            motorPowerLoop(.4);

        } else if(motorPos <= 15000){

            motorPowerLoop(.6);

        } else if(motorPos <= 20000){

            motorPowerLoop(.8);

        } else if(motorPos <= 25000){

            motorPowerLoop(1);

        } else {

            motorPowerLoop(0);

        }


        /*if (leftMotor.getCurrentPosition() < 1000) {
            leftMotor.setTargetPosition(1000);
            rightMotor.setTargetPosition(1000);
        } else if (leftMotor.getCurrentPosition() >= 1000) {
            leftMotor.setTargetPosition(5000);
            rightMotor.setTargetPosition(5000);
        }

        leftMotor.setPower(1);
        rightMotor.setPower(1);
*/

        /*if (leftMotor.getCurrentPosition() <= 5000) {
            leftMotor.setPower(1);
            rightMotor.setPower(1);
        } else {
            leftMotor.setPower(0);
            rightMotor.setPower(0);
        }


        //We are going to test whether this is runable or not
        //sleep(1000);
    }

    @Override
    public void stop() {

    }
}
*/
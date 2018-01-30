package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by mars on 5/3/17.
 */

//I have created this program as a template for next year so that we can simplify our programs

public class singleProgramTest extends OpMode {

    public DcMotor leftMotor1 = null;
    public DcMotor leftMotor2 = null;
    public DcMotor rightMotor1 = null;
    public DcMotor rightMotor2 = null;

    public Servo blockServo1 = null;
    public Servo blockServo2 = null;

    private double motorThreshold = 0.065;

    public void driving() {
        float leftX = -(gamepad1.left_stick_x);// assigning controller values to a variable
        float rightX = -(gamepad1.right_stick_x);
        float leftY = -(gamepad1.left_stick_y);
        float rightY = -(gamepad1.right_stick_y);

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

    public void bucketServoSimple(float button1, boolean button2, Servo servo, float stop1,  float stop2, double stop3) {

        if (button1 > 0) {
            if (servo.getPosition() != stop1) {
                servo.setPosition(stop1);
            }
        } else if (button2) {
            if (servo.getPosition() != stop2) {
                servo.setPosition(stop2);
            }
        } else {
            if (servo.getPosition() != stop3) {
                servo.setPosition(stop3);
            }
        }
    }

    public void blockServoMethod(double Position){

        if (blockServo1.getPosition() != Position && blockServo2.getPosition() != Position) {
            blockServo1.setPosition(Position);
            blockServo2.setPosition(Position);
        }

    }

    public void motorMovement(float button1, float button2, DcMotor motor, double power) {

        if (button1 > 0) {
            motor.setPower(power);
        } else if (button2 > 0) {
            motor.setPower(-power);
        } else {
            motor.setPower(0);
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }
}

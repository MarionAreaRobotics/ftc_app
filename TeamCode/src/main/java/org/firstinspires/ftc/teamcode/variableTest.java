package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by mars on 12/9/17.
 */

@Disabled
@TeleOp(name = "variable test", group = "Testing")
public class variableTest extends OpMode {

    private DcMotor leftMotor = null;
    private DcMotor rightMotor = null;

    private int tracker = 0;

    @Override
    public void init(){

        leftMotor = hardwareMap.dcMotor.get("left motor");
        rightMotor = hardwareMap.dcMotor.get("right motor");

        rightMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    //Move a motor to a certain range of power to allow us to move a different motor
    public void motorMovement(float button1, DcMotor motor) {


            /*
            * You are already going in a wrong direction
            *
            * We want this method to move the motor based on the input of the joystick
            *
            * We want a constraint in the loop below that makes one motor run when the other is between 25% and 75% power
            */


    }

    @Override
    public void loop(){



    }
}
    /*
    Programmers way of logic
    public  void powerMethod(float lPower, float rPower){

        float leftx = -gamepad1.left_stick_x;
        float lefty = -gamepad1.left_stick_y;
        float rightx = -gamepad1.right_stick_x;
        float righty = -gamepad1.right_stick_y;

        if (gamepad1.a){
            tracker = 1;
        } else if (gamepad1.b){
            tracker = 0;
        }

        if (tracker == 1){
            lPower = -gamepad1.left_stick_x;
            rPower = rightx;

        } else if (tracker == 0){
            leftPower = lefty;
            rightPower = righty;
        }
    }
*/

    /*
    //Zach's way of logic
    public void powerMethod(int value1, int value2) {
        float leftx = -gamepad1.left_stick_x;
        float lefty = -gamepad1.left_stick_y;
        float rightx = -gamepad1.right_stick_x;
        float righty = -gamepad1.right_stick_y;

        if (gamepad1.a) {
            tracker = value1;
        } else if (gamepad1.b){
            tracker = value2;
        }

        if (tracker == value1) {
            leftPower = leftx;
            rightPower = rightx;
        } else if (tracker == value2) {
            leftPower = lefty;
            rightPower = righty;
        }
    }
*/


/*
    // Needs to move forward with positive y and move backward with positive x
    public void motorMovement(float button1, boolean button2, DcMotor motor, double power) {

        if (button1 > 0) {
            motor.setPower(power);
        } else if (button2) {
            motor.setPower(-power);
        } else {
            motor.setPower(0);
        }
    }

    motorMovement(-gamepad1.left_stick_y, gamepad1.left_bumper, leftMotor, 1);
        motorMovement(-gamepad1.right_stick_y, gamepad1.right_bumper , rightMotor, 1);
*/

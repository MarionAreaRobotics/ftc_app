package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by mars on 11/19/16.
 */
@Autonomous(name="BeaconAuto_Red", group="Auto")
public class BeaconAuto_Red extends OpMode {

    /*
    * We are going to do what the worm gear warriors did, we are going to go over to the wall and find value of the plastic
    * then we are going to move forward and find the value for the images bceause they will be drastically different
    * THIS IS DO-ABLE NOW
    */

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftMotor1 = null;
    private DcMotor leftMotor2 = null;
    private DcMotor rightMotor1 = null;
    private DcMotor rightMotor2 = null;

    private OpticalDistanceSensor optical = null;
    private ColorSensor color = null;

//    int crossedTheLine = 0;

    @Override
    public void init() {

        color = hardwareMap.colorSensor.get("color");
        optical = hardwareMap.opticalDistanceSensor.get("distance");
        color.enableLed(false);

        leftMotor1  = hardwareMap.dcMotor.get("left motor 1");
        leftMotor2  = hardwareMap.dcMotor.get("left motor 2");
        rightMotor1 = hardwareMap.dcMotor.get("right motor 1");
        rightMotor2 = hardwareMap.dcMotor.get("right motor 2");
        leftMotor1.setDirection(DcMotor.Direction.REVERSE);
        leftMotor2.setDirection(DcMotor.Direction.REVERSE);

        leftMotor1.setTargetPosition(0);
        leftMotor2.setTargetPosition(0);
        rightMotor1.setTargetPosition(0);
        rightMotor2.setTargetPosition(0);

    }

    @Override
    public void init_loop() {

        telemetry.addData("Left 1: ", leftMotor1.getCurrentPosition());
        telemetry.addData("Left 2: ", leftMotor2.getCurrentPosition());
        telemetry.addData("Right 1: ", rightMotor1.getCurrentPosition());
        telemetry.addData("Right 2: ", rightMotor2.getCurrentPosition());

        telemetry.addData("OpRawLight: ", optical.getRawLightDetected());

        if (color.blue() >= 2) {
            telemetry.addLine("The Color is Blue");
            telemetry.addData("Color Blue Value: ", color.blue());
        } else if (color.red() >= 2) {
            telemetry.addLine("The Color is Red");
            telemetry.addData("Color Red Value:", color.red());
        } else {
            telemetry.addLine("No Color has been detected yet");
            telemetry.addData("Color Blue Value:", color.blue());
            telemetry.addData("Color Red Value:", color.red());
        }

        telemetry.addData("OpLight: ", optical.getLightDetected());
    }

    /*public int roundIt(int n) {
        n = n - 5;
        return ((n + 4) / 5 * 5);
    }

    public void move(float x) {
        leftMotor1.setPower(x);
        leftMotor2.setPower(x);
        rightMotor1.setPower(x);
        rightMotor2.setPower(x);
    }
*/
    @Override
    public void start() {runtime.reset();}

    @Override
    public void loop() {
        telemetry.addData("ColorAlpha", color.alpha());
        telemetry.addData("ColorBlue", color.blue());
        telemetry.addData("ColorRed", color.red());
        telemetry.addData("ColorGreen", color.green());

        telemetry.addData("OpLight: ", optical.getLightDetected());
        telemetry.addData("OpRawLight: ", optical.getRawLightDetected());
        telemetry.addData("OpRawLightMax: ", optical.getRawLightDetectedMax());

        telemetry.addData("Status", "Running: " + runtime.toString());

        telemetry.addData("Left1: ", leftMotor1.getCurrentPosition());
        telemetry.addData("Left2: ", leftMotor2.getCurrentPosition());
        telemetry.addData("Right1: ", rightMotor1.getCurrentPosition());
        telemetry.addData("Right2: ", rightMotor2.getCurrentPosition());

        if (color.blue() >= 2) {
            telemetry.addLine("The Color is Blue");
            telemetry.addData("Color Blue Value: ", color.blue());
        } else if (color.red() >= 2) {
            telemetry.addLine("The Color is Red");
            telemetry.addData("Color Red Value:", color.red());
        } else {
            telemetry.addLine("No Color has been detected yet");
            telemetry.addData("Color Blue Value:", color.blue());
            telemetry.addData("Color Red Value:", color.red());
        }

        telemetry.addData("OpLight: ", optical.getLightDetected());
        telemetry.addData("OpRawLight", optical.getRawLightDetected());





//-------------------------------------------------------------------------------------------------------------------------

   /*     if (rightMotor2.getCurrentPosition() <= 4300) {
            leftMotor1.setPower(.5);
            leftMotor2.setPower(.5);
            rightMotor1.setPower(.5);
            rightMotor2.setPower(.5);
        } else if (/*rightMotor2.getCurrentPosition() <= 8500 optical.getRawLightDetected() < 0.0733) {
            leftMotor2.setDirection(DcMotor.Direction.FORWARD); // turning right/left?
            rightMotor1.setDirection(DcMotor.Direction.REVERSE);
            leftMotor1.setPower(.15);
            leftMotor2.setPower(.15);
            rightMotor1.setPower(.15);
            rightMotor2.setPower(.15);
        }
        if (/*rightMotor2.getCurrentPosition() >= 8500 && optical.getRawLightDetected() >= 0.0733) {
            leftMotor1.setPower(0);
            leftMotor2.setPower(0);
            rightMotor1.setPower(0);
            rightMotor2.setPower(0);

        } else if (color.blue() >= 2 && rightMotor2.getCurrentPosition() < 5000) {
            rightMotor2.setPower(.05);
            leftMotor2.setPower(.05);

        } else if (color.red() >=2 && leftMotor1.getCurrentPosition() < 5000) {
            rightMotor1.setPower(.05);
            leftMotor1.setPower(.05);

        } else if (rightMotor2.getCurrentPosition() >= 8580) {
            rightMotor2.setDirection(DcMotor.Direction.FORWARD);
            leftMotor2.setDirection(DcMotor.Direction.REVERSE);
            rightMotor1.setPower(.05);
            leftMotor1.setPower(.05);
            rightMotor2.setPower(.05);
            leftMotor2.setPower(.05);
        }
        if (leftMotor1.getCurrentPosition() >= 8580) {
            rightMotor2.setDirection(DcMotor.Direction.REVERSE);
            leftMotor2.setDirection(DcMotor.Direction.REVERSE);
            rightMotor2.setPower(.05);
            leftMotor2.setPower(.05);
            rightMotor2.setPower(.05);
            leftMotor2.setPower(.05);
        }
        if (leftMotor1.getCurrentPosition() >= 8600 || rightMotor2.getCurrentPosition() >= 8600) {
            leftMotor1.setPower(0);
            leftMotor2.setPower(0);
            rightMotor1.setPower(0);
            rightMotor2.setPower(0);
        }
       */
    }



//-----------------------------------------------------------------------------------------------------------------------------------------------

/* This was our first attempt at a beacon auto, but the line was too small for us to accurately align ourself with beacon
        int currentPos = roundIt(rightMotor2.getCurrentPosition());

        telemetry.addData("CurrentPos:", currentPos);
        telemetry.addData("CrossedTheLine", crossedTheLine);

        if (crossedTheLine == 0 && optical.getLightDetected() >= .6) {
            crossedTheLine = roundIt(rightMotor2.getCurrentPosition());
            //Comment this out when done testing
            move(0); //stop
        }

        //For Testing Purposes, I am removing this code to see our margin of Error and to see whether or not it is consistent
        if (crossedTheLine > 0) {
            if (currentPos == crossedTheLine) {
                move(0); //stop
                //This is going to be where we must put in the code for color detection, and button pressing
            } else {
                if (currentPos > crossedTheLine) {
                    move((float) -.05); //backward
                } else {
                    move((float) .05); //forward
                }
            }
        } else {
            move((float) .5); //go
        }

        telemetry.addData("Left 1: ", leftMotor1.getCurrentPosition());
        telemetry.addData("Left 2: ", leftMotor2.getCurrentPosition());
        telemetry.addData("Right 1: ", rightMotor1.getCurrentPosition());
        telemetry.addData("Right 2: ", rightMotor2.getCurrentPosition());

        if (color.blue() >= 2) {
            telemetry.addLine("The Color is Blue");
            telemetry.addData("Color Blue Value: ", color.blue());
        } else if (color.red() >= 2) {
            telemetry.addLine("The Color is Red");
            telemetry.addData("Color Red Value:", color.red());
        } else {
            telemetry.addLine("No Color has been detected yet");
            telemetry.addData("Color Blue Value:", color.blue());
            telemetry.addData("Color Red Value:", color.red());
        }

        telemetry.addData("OpLight: ", optical.getLightDetected());
*/
        /*
        if (color.blue() <= 3 || color.red() <= 3) {
            if (optical.getLightDetected() <= .6) {
                leftMotor1.setPower(.1);
                leftMotor2.setPower(.1);
                rightMotor1.setPower(.1);
                rightMotor2.setPower(.1);
            } else {
                leftMotor1.setPower(0);
                leftMotor2.setPower(0);
                rightMotor1.setPower(0);
                rightMotor2.setPower(0);
                //timer();
            }
        } else {
            leftMotor1.setPower(0);
            leftMotor2.setPower(0);
            rightMotor1.setPower(0);
            rightMotor2.setPower(0);
        }

        for (motorPos = 0; motorPos <= newMotorPos;) {
            leftMotor1.setPower(1);
            leftMotor2.setPower(1);
            rightMotor1.setPower(1);
            rightMotor2.setPower(1);
        }
        */

        /*
        int motorPos = leftMotor1.getCurrentPosition();
        int newMotorPos = motorPos + 2000;

        while (motorPos <= newMotorPos) {
        leftMotor1.setPower(1);
        leftMotor2.setPower(1);
        rightMotor1.setPower(1);
        rightMotor2.setPower(1);
        }
        */

        @Override
        public void stop () {

        }

    }
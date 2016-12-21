package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by mars on 10/30/16.
 */
@Autonomous(name="MusesAuto", group="Auto")
public class MusesAuto extends OpMode {

    /*private DcMotor leftMotor1 = null;
    private DcMotor leftMotor2 = null;
    private DcMotor rightMotor1 = null;
    private DcMotor rightMotor2 = null;
    */
    private DcMotor scoringMotor = null;
    private Servo servo = null;
    private OpticalDistanceSensor distance = null;
    //int timesrun = 0;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
        distance = hardwareMap.opticalDistanceSensor.get("distance");

        //leftMotor1 = hardwareMap.dcMotor.get("left motor 1");
        //leftMotor2 = hardwareMap.dcMotor.get("left motor 2");
        //rightMotor1 = hardwareMap.dcMotor.get("right motor 1");
        //rightMotor2 = hardwareMap.dcMotor.get("right motor 2");
        scoringMotor = hardwareMap.dcMotor.get("scoring motor");
        servo = hardwareMap.servo.get("stopbar");
        servo.setPosition(1);
        scoringMotor.setDirection(DcMotor.Direction.REVERSE);
        //leftMotor1.setDirection(DcMotor.Direction.REVERSE);
        //leftMotor2.setDirection(DcMotor.Direction.REVERSE);

        scoringMotor.setTargetPosition(0);
        //leftMotor1.setTargetPosition(0);
        //leftMotor2.setTargetPosition(0);
        //rightMotor1.setTargetPosition(0);
        //rightMotor2.setTargetPosition(0);

    }


    //public int roundIt(int n) {
        //n = n - 5;
        //return ((n + 4) / 5 * 5);
    //}

    @Override
    public void loop() {
        //Not sure if that is how you say equal to but I'll test it to

        //telemetry.addData("scoring motor", scoringMotor.getCurrentPosition());
        telemetry.addData("stopbar", servo.getPosition());
        telemetry.addData("scoring motor", scoringMotor.getCurrentPosition());
        telemetry.addData("OpLight: ", distance.getLightDetected());
        telemetry.addData("OpRawLight: ", distance.getRawLightDetected());
        telemetry.addData("OpRawLightMax: ", distance.getRawLightDetectedMax());

        if (distance.getRawLightDetected() >= 0.19) {
            servo.setPosition(1);
        } else {
            if (distance.getRawLightDetected() < 0.19) {
                servo.setPosition(0);
            }
        }

        if (servo.getPosition() == 1) {
                scoringMotor.setPower(1);
        } else {
            if (servo.getPosition() == 0) {
                scoringMotor.setPower(0);
            }
        }

      /*  if (servo.getPosition() == 1) {
            scoringMotor.setPower(1);


            else(servo.getPosition() == 0) {
                scoringMotor.setPower(0);
            }
        }    */



        // don't touch the bottom



        /*if (servo.getPosition() == 1) {
            scoringMotor.setPower(1);
        } else if (servo.getPosition() == 0) {
            scoringMotor.setPower(0);
        }
        //the line that isn't working is this bottom one, when I put the telemetry in, it is not giving me any feedback, I'm testing on the actual muses robot
        //and Haley said there was an encoder on it. i don't have time to check and make sure, so this is as far as I got
        if (scoringMotor.getCurrentPosition() >= 3000) {
            servo.setPosition(0);
        }
        telemetry.addData("scoring motor", scoringMotor.getCurrentPosition());
        telemetry.addData("scoring motor", scoringMotor.getCurrentPosition());
        telemetry.addData("scoring motor", scoringMotor.getCurrentPosition());
        telemetry.addData("scoring motor", scoringMotor.getCurrentPosition());
        telemetry.addData("scoring motor", scoringMotor.getCurrentPosition());
*/
    }
/*
        if (roundIt(scoringMotor.getCurrentPosition()) <= 6000) {
            //if (roundIt(scoringMotor.getCurrentPosition()) <= 3000) {
                //scoringMotor.setPower(1);
            //} else {
                //if (timesrun == 0) {
                    //timesrun += 1;
                    //scoringMotor.setPower(0);
                    //servo.setPosition(0);
                    //servo.setPosition(1);
                    //Thread.sleep(1000);
                //} else {
                    //if (roundIt(scoringMotor.getCurrentPosition()) <= 6000) {
                        //scoringMotor.setPower(1);
                    //} //else{
                        //scoringMotor.setPower(0);
                    //}
                //}
            //}
        //}

        //telemetry.addData("Scoring", scoringMotor.getCurrentPosition());
        //telemetry.addData("Servo Pos: ", servo.getPosition());

    }
        /*scoringMotor.setPower(1);

        if (scoringMotor.getCurrentPosition() >= 3000) {
            scoringMotor.setPower(0);
            if (rightMotor1.getCurrentPosition() >= 6500) {
                leftMotor1.setPower(0);
                leftMotor2.setPower(0);
                rightMotor1.setPower(0);
                rightMotor2.setPower(0);
            } else {
                leftMotor1.setPower(.87);
                leftMotor2.setPower(.87);
                rightMotor1.setPower(1);
                rightMotor2.setPower(1);
            }
        } else {
            scoringMotor.setPower(1);
        }*/

        //telemetry.addData("Left 1: ", leftMotor1.getCurrentPosition());
        //telemetry.addData("Left 2: ", leftMotor2.getCurrentPosition());
        //telemetry.addData("Right 1: ", rightMotor1.getCurrentPosition());
        //telemetry.addData("Right 2: ", rightMotor2.getCurrentPosition());

    @Override
    public void stop() {

    }
}
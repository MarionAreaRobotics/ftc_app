package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by mars on 2/18/17.
 */
@Disabled
@Autonomous(name="BeaconRedAuto", group="Auto")
public class BeaconRedAuto extends OpMode{

    // private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftMotor1 = null;
    private DcMotor leftMotor2 = null;
    private DcMotor rightMotor1 = null;
    private DcMotor rightMotor2 = null;

    private int tracker = 0;
    private int keeper = 10;
    //private Servo launcherServo = null;
    // private Servo armRelease = null;

    private ElapsedTime runtime = new ElapsedTime();

    private OpticalDistanceSensor optical = null;
    private ColorSensor color = null;
    //private ColorSensor color0 = null;

    private DcMotor scoringMotor = null;
    private Servo servo = null;

    //  private Number counter = 0;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        runtime.reset();

        color = hardwareMap.colorSensor.get("color");
        optical = hardwareMap.opticalDistanceSensor.get("distance");
        color.enableLed(false);

        //color0 = hardwareMap.colorSensor.get("color0");
        //color0.enableLed(false);


        telemetry.addData("Status", "Initialized");

        leftMotor1 = hardwareMap.dcMotor.get("left motor 1");
        leftMotor2 = hardwareMap.dcMotor.get("left motor 2");
        rightMotor1 = hardwareMap.dcMotor.get("right motor 1");
        rightMotor2 = hardwareMap.dcMotor.get("right motor 2");

        leftMotor1.setTargetPosition(0);
        leftMotor2.setTargetPosition(0);
        rightMotor1.setTargetPosition(0);
        rightMotor2.setTargetPosition(0);

        scoringMotor = hardwareMap.dcMotor.get("scoring motor");
        scoringMotor.setDirection(DcMotor.Direction.REVERSE);
        scoringMotor.setTargetPosition(0);

        servo = hardwareMap.servo.get("stopbar");
        //servo.setPosition(.6);

        runtime.reset();

        telemetry.addData("Left 1: ", leftMotor1.getCurrentPosition());
        telemetry.addData("Left 2: ", leftMotor2.getCurrentPosition());
        telemetry.addData("Right 1: ", rightMotor1.getCurrentPosition());
        telemetry.addData("Right 2: ", rightMotor2.getCurrentPosition());

        telemetry.addData("OpRawLight: ", optical.getRawLightDetected());
        telemetry.addData("OpLight: ", optical.getLightDetected());

        if (color.blue() >= 2) {
            telemetry.addLine("The Color is Blue For Old");
            telemetry.addData("Color Blue Value: ", color.blue());
        } else if (color.red() >= 2) {
            telemetry.addLine("The Color is Red For Old");
            telemetry.addData("Color Red Value:", color.red());
        } else {
            telemetry.addLine("No Color has been detected yet for old");
            telemetry.addData("Color Blue Value:", color.blue());
            telemetry.addData("Color Red Value:", color.red());
        }

        /*if (color0.blue() >= 2) {
            telemetry.addLine("The Color is Blue For New");
            telemetry.addData("Color Blue Value: ", color.blue());
        } else if (color0.red() >= 2) {
            telemetry.addLine("The Color is Red For New");
            telemetry.addData("Color Red Value:", color.red());
        } else {
            telemetry.addLine("No Color has been detected yet for new");
            telemetry.addData("Color Blue Value:", color.blue());
            telemetry.addData("Color Red Value:", color.red());
        }
        */
    }

    @Override
    public void init_loop() {
        telemetry.addData("stopbar", servo.getPosition());
        telemetry.addData("scoring motor", scoringMotor.getCurrentPosition());
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        telemetry.addData("Status", "Running: " + runtime.toString());

        telemetry.addData("Left 1: ", leftMotor1.getCurrentPosition());
        telemetry.addData("Left 2: ", leftMotor2.getCurrentPosition());
        telemetry.addData("Right 1: ", rightMotor1.getCurrentPosition());
        telemetry.addData("Right 2: ", rightMotor2.getCurrentPosition());

        telemetry.addData("stopbar", servo.getPosition());
        telemetry.addData("scoring motor", scoringMotor.getCurrentPosition());
        telemetry.addData("Runtime: ", runtime.toString());

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

        /*if (color0.blue() >= 2) {
            telemetry.addLine("The Color is Blue For New");
            telemetry.addData("Color Blue Value: ", color.blue());
        } else if (color0.red() >= 2) {
            telemetry.addLine("The Color is Red For New");
            telemetry.addData("Color Red Value:", color.red());
        } else {
            telemetry.addLine("No Color has been detected yet for new");
            telemetry.addData("Color Blue Value:", color.blue());
            telemetry.addData("Color Red Value:", color.red());
        }*/

        if (runtime.time() <= 3.35) {
            scoringMotor.setPower(1);
            if (servo.getPosition() != 1) {
                servo.setPosition(1);
            }
        } else {
            if (servo.getPosition() != 0) {
                servo.setPosition(0);
            } else {
                if (runtime.time() >= 3.35 && runtime.time() <= 5.5) {
                    scoringMotor.setPower(0);
                } else {
                    if (runtime.time() >= 5.5 && runtime.time() <= 7.65) {
                        scoringMotor.setPower(1);
                    } else {
                        if (runtime.time() >= 7.65) {
                            scoringMotor.setPower(0);
                        }
                    }
                }
            }
        }

        //this makes it go left
        if (runtime.time() >= 7.65 && runtime.time() <= 9.85) { // for some dam reason right is left
            leftMotor1.setPower(.5);
            leftMotor2.setPower(-.6);
            rightMotor1.setPower(.5);
            rightMotor2.setPower(-.6);

        }

        //this makes it go forward
        if (runtime.time() >= 9.85 && runtime.time() <= 14.85) {
            leftMotor1.setPower(.5);
            leftMotor2.setPower(.5);
            rightMotor1.setPower(-.6);
            rightMotor2.setPower(-.6);

        }
        if (runtime.time() >= 14.85 && runtime.time() <= 15.5) {
            leftMotor1.setPower(0);
            leftMotor2.setPower(0);
            rightMotor1.setPower(0);
            rightMotor2.setPower(0);
        }

        //this makes it go left again
     /*   if (runtime.time() >= 14.85 && runtime.time() <= 15.5) {
            leftMotor1.setPower(.5);
            leftMotor2.setPower(-.5);
            rightMotor1.setPower(.5);
            rightMotor2.setPower(-.5);

        } */

        //this is where robot turns itself
        //NEXT TIME WE GET ON THIS, THE TURNING STUFF IS WHAT WE NEED TO CHANGE AND EDIT AND STUFF
        /*if (runtime.time() >= 5.3 && runtime.time() <= 7.2) {
            leftMotor1.setPower(-.3);
            leftMotor2.setPower(-.3);
            rightMotor1.setPower(-.3); //changed from 0
            rightMotor2.setPower(-.3); //changed from 0
        }*/

        //going backwards
       /* if (runtime.time() >= 15.5 && runtime.time() <= 15.5) { //changed time values to not contradict if before it: they were not dones yet :)
            leftMotor1.setPower(.2);
            leftMotor2.setPower(.2);
            rightMotor1.setPower(-.2);
            rightMotor2.setPower(-.2);
        } */

        //this makes it go forward
        if (runtime.time() >= 15.5 && runtime.time() <= 15.75) {
            leftMotor1.setPower(-.2);
            leftMotor2.setPower(-.2);
            rightMotor1.setPower(.2);
            rightMotor2.setPower(.2);
        }
        if (runtime.time() >= 15.75 && runtime.time() <= 16.25) {
            leftMotor1.setPower(0);
            leftMotor2.setPower(0);
            rightMotor1.setPower(0);
            rightMotor2.setPower(0);
        }

//Note: The colors are reverse of the ones in the Blue beacon auto
        if (color.red() >= 2 && runtime.time() >= 16.25 && runtime.time() <= 17.25 /*|| color0.red() >= 2 && runtime.time() >= 10 && runtime.time() <= 12.5*/) {
            tracker = 1;
            keeper = 20;
        }
        if (tracker == 1) { // if it was blue after first hump, its at second beacon

           /* leftMotor1.setPower(.5);
            leftMotor2.setPower(-.5);
            rightMotor1.setPower(.5);
            rightMotor2.setPower(-.5);*/

            if (runtime.time() >= 17.25 && runtime.time() <= 19.75) {
                leftMotor1.setPower(.4);
                leftMotor2.setPower(-.4);
                rightMotor1.setPower(.4);
                rightMotor2.setPower(-.4);

            }
            if (runtime.time() >= 19.75 && runtime.time() <= 20.75) {
                leftMotor1.setPower(.2);
                leftMotor2.setPower(.2);
                rightMotor1.setPower(-.2);
                rightMotor2.setPower(-.2); // humped the second beacon once
            }
            if (runtime.time() >= 20.75 && runtime.time() <= 21.25) {
                leftMotor1.setPower(-.2);
                leftMotor2.setPower(-.2);
                rightMotor1.setPower(.2);
                rightMotor2.setPower(.2); // humped the second beacon once
            }

            if (runtime.time() >= 21.25 && runtime.time() <= 21.85) {
                leftMotor1.setPower(0);
                leftMotor2.setPower(0);
                rightMotor1.setPower(0);
                rightMotor2.setPower(0);
            }
            if (color.blue() >= 2 /*|| color0.blue() >= 2*/) {
                if (runtime.time() >= 26.85 && runtime.time() <= 27.85) {
                    leftMotor1.setPower(.2);
                    leftMotor2.setPower(.2);
                    rightMotor1.setPower(-.2);
                    rightMotor2.setPower(-.2);
                }
                if (runtime.time() >= 27.85 && runtime.time() <= 27.9) {
                    leftMotor1.setPower(-.2);
                    leftMotor2.setPower(-.2);
                    rightMotor1.setPower(.2);
                    rightMotor2.setPower(.2);
                }
                if (runtime.time() >= 27.9 && runtime.time() <= 27.95) {
                    leftMotor1.setPower(0);
                    leftMotor2.setPower(0);
                    rightMotor1.setPower(0);
                    rightMotor2.setPower(0);
                }
            }

        } else if (color.blue() >= 2 && runtime.time() >= 16.25 && keeper == 10 /*|| color0.blue() >= 2 && runtime.time() >= 10 && keeper == 10*/) {
            tracker = 2;
        }/*if red make it hump wall 5 seconds after first one*/ else if (keeper == 20) {
            tracker = 1;
        }

        if (tracker == 2) {

            if (runtime.time() >= 21.25 && runtime.time() <= 22.25) {
                leftMotor1.setPower(.2);
                leftMotor2.setPower(.2);
                rightMotor1.setPower(-.2);
                rightMotor2.setPower(-.2);
            }
            if (runtime.time() >= 22.25 && runtime.time() <= 22.85) {
                leftMotor1.setPower(-.2);
                leftMotor2.setPower(-.2);
                rightMotor1.setPower(.2);
                rightMotor2.setPower(.2);
            }
            if (runtime.time() >= 22.85 && runtime.time() <= 25.25) {
                leftMotor1.setPower(0);
                leftMotor2.setPower(0);
                rightMotor1.setPower(0);
                rightMotor2.setPower(0);
            }

            //this is after first beacon was red and going to 2nd beacon
            //however, w/ shooting we wont have enough time to do that
            //so it is commented out and has the times for the previous dual beacon auto
            /*if (runtime.time() >= 17.5 && runtime.time() <= 18.6) { // this is temp stop once it hit first beacon twice
                leftMotor1.setPower(.2); // currently in front of second beacon
                leftMotor2.setPower(.2);
                rightMotor1.setPower(-.2);
                rightMotor2.setPower(-.2);
            }
            if (runtime.time() >= 18.6 && runtime.time() <= 19.1) {
                leftMotor1.setPower(-.2);
                leftMotor2.setPower(-.2);
                rightMotor1.setPower(.2);
                rightMotor2.setPower(.2);
            }
            if (runtime.time() >= 19.1 && runtime.time() <= 20.1) {
                leftMotor1.setPower(0);
                leftMotor2.setPower(0);
                rightMotor1.setPower(0);
                rightMotor2.setPower(0);
            }
            if (color.blue() >= 2 || color0.blue() >= 2) {
                if (runtime.time() >= 24.1 && runtime.time() <= 24.6) {
                    leftMotor1.setPower(.2);
                    leftMotor2.setPower(.2);
                    rightMotor1.setPower(-.2);
                    rightMotor2.setPower(-.2);
                }
                if (runtime.time() >= 24.6 && runtime.time() <= 24.9) {
                    leftMotor1.setPower(-.2);
                    leftMotor2.setPower(-.2);
                    rightMotor1.setPower(.2);
                    rightMotor2.setPower(.2);
                }
                if (runtime.time() >= 24.9 && runtime.time() <= 25.5) {
                    leftMotor1.setPower(0);
                    leftMotor2.setPower(0);
                    rightMotor1.setPower(0);
                    rightMotor2.setPower(0);
                }
            }*/

        }

        if (0 > 1) {
            stop();
        }
    }

    @Override
    public void stop() {
        if (servo.getPosition() != 1) {
            servo.setPosition(1);
        }
    }
}

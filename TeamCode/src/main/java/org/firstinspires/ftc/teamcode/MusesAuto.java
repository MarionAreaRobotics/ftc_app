package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by mars on 10/30/16.
 */
@Autonomous(name="MusesAuto", group="Auto")
public class MusesAuto extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();

    /*
    * Haley is in the process of tweaking the Matrix touch sensor,
    * (basically the ball will trigger it now if she does it right)
    * We are going to add in a piece so that we can read whether or not this sensor is pushed, shouldn't be hard
    * Allegedly :)
    */

    private DcMotor leftMotor1 = null;
    private DcMotor leftMotor2 = null;
    private DcMotor rightMotor1 = null;
    private DcMotor rightMotor2 = null;

    private DcMotor scoringMotor = null;
    private Servo servo = null;
    private Servo cap = null;
//    private OpticalDistanceSensor distance = null;

    //private TouchSensor button = null;

    boolean pressed = false;

//    private LegacyModule ultraSonic = null;
//    private ColorSensor color = null;

    //int timesrun = 0;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        runtime.reset();

//        distance = hardwareMap.opticalDistanceSensor.get("distance");
        //color = hardwareMap.colorSensor.get("color");

        leftMotor1 = hardwareMap.dcMotor.get("left motor 1");
        leftMotor2 = hardwareMap.dcMotor.get("left motor 2");
        rightMotor1 = hardwareMap.dcMotor.get("right motor 1");
        rightMotor2 = hardwareMap.dcMotor.get("right motor 2");

        scoringMotor = hardwareMap.dcMotor.get("scoring motor");

        servo = hardwareMap.servo.get("stopbar");
        servo.setPosition(.6);

        cap = hardwareMap.servo.get("cap");
        cap.setPosition(.7);

        scoringMotor.setDirection(DcMotor.Direction.REVERSE);

        leftMotor1.setDirection(DcMotor.Direction.REVERSE);
        leftMotor2.setDirection(DcMotor.Direction.REVERSE);

//        ultraSonic = hardwareMap.legacyModule.get("ultra sonic");
        //button = hardwareMap.touchSensor.get("button");
//        distance.enableLed(false);

        scoringMotor.setTargetPosition(0);
        //leftMotor1.setTargetPosition(0);
        //leftMotor2.setTargetPosition(0);
        rightMotor1.setTargetPosition(0);
        //rightMotor2.setTargetPosition(0);
    }

    @Override
    public void init_loop() {
        telemetry.addData("stopbar", servo.getPosition());
        telemetry.addData("scoring motor", scoringMotor.getCurrentPosition());
        telemetry.addData("cap", cap.getPosition());
//        telemetry.addData("OpLight: ", distance.getLightDetected());
//        telemetry.addData("OpRawLight: ", distance.getRawLightDetected());
//        telemetry.addData("OpRawLightMax: ", distance.getRawLightDetectedMax());
    }
    //public int roundIt(int n) {
    //n = n - 5;
    //return ((n + 4) / 5 * 5);
    //}

    @Override
    public void loop() {

        telemetry.addData("Pressed: ", pressed);
        telemetry.addData("stopbar", servo.getPosition());
        telemetry.addData("scoring motor", scoringMotor.getCurrentPosition());
        telemetry.addData("Runtime: ", runtime.toString());

/*
        if (scoringMotor.getCurrentPosition() <= 3500) {
            scoringMotor.setPower(1);
            if (servo.getPosition() != 1) {
                servo.setPosition(1);
            }
        } else {
            scoringMotor.setPower(0);
        }
*/

// This is used to shoot 2 balls... it doesn't work all the time
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
                    if (runtime.time() <= 9) {
                        scoringMotor.setPower(1);
                    } else {
                        if (runtime.time() >= 9) {
                            scoringMotor.setPower(0);
                            /* else {
                                if (rightMotor1.getCurrentPosition() >= 6500) {
                                    leftMotor1.setPower(0);
                                    leftMotor2.setPower(0);
                                    rightMotor1.setPower(0);
                                    rightMotor2.setPower(0);
                                } else {
                                    leftMotor1.setPower(-.87);
                                    leftMotor2.setPower(.87);
                                    rightMotor1.setPower(1);
                                    rightMotor2.setPower(-1);
                                }
                            }*/
                        }
                    }
                }
            }
        }

    }

        //telemetry.addData("Right 1: ", rightMotor1.getCurrentPosition());
/*
        if (scoringMotor.getCurrentPosition() <= 2000) {
            servo.setPosition(1);
        }

        if (servo.getPosition() == 0) {
            scoringMotor.setPower(0);
        }
        if (button.isPressed()) {
            //pressed = true;
            scoringMotor.setPower(1);
            servo.setPosition(1);
        }
*/
/*
        if (scoringMotor.getCurrentPosition() <= 2000) {
            scoringMotor.setPower(1);
            if (servo.getPosition() != 1) {
                servo.setPosition(1);
            }
        } else {
            if (servo.getPosition() != 0) {
                servo.setPosition(0);
            } else {
                if (button.isPressed()) {
                    pressed = true;
                } else {
                    if (pressed == true) {
                        if (scoringMotor.getCurrentPosition() <= 7000) {
                            scoringMotor.setPower(1);
                        } else {
                            scoringMotor.setPower(0);
                        }
                    } else {
                        scoringMotor.setPower(0);
                    }
                }
            }
        }

    }
    */

    /* This will make everything run inside a if statement of the entire motion of motor
    if (scoringMotor.getCurrentPosition() <= 7000) {
            if (scoringMotor.getCurrentPosition() <= 2000) {
                scoringMotor.setPower(1);
                if (servo.getPosition() != 1) {
                    servo.setPosition(1);
                }
            } else {
                if (servo.getPosition() != 0) {
                    servo.setPosition(0);
                } else {
                    if (button.isPressed()) {
                        pressed = true;
                    } else {
                        if (pressed == true) {
                            scoringMotor.setPower(1);
                        } else {
                            scoringMotor.setPower(0);
                        }
                    }
                }
            }
        } else {
            scoringMotor.setPower(0);
            if (servo.getPosition() != 1) {
                servo.getPosition();
            }
        }
    */
    // This will run the first scoring based off time

        /*
        if (rightMotor1.getCurrentPosition() <= 325) {
            leftMotor1.setPower(-1);
            leftMotor2.setPower(1);
            rightMotor1.setPower(1);
            rightMotor2.setPower(-1);
        } else {
            leftMotor1.setPower(0);
            leftMotor2.setPower(0);
            rightMotor1.setPower(0);
            rightMotor2.setPower(0);
        }
*/
        //Not sure if that is how you say equal to but I'll test it to

        //telemetry.addData("scoring motor", scoringMotor.getCurrentPosition());
        /*telemetry.addData("Pressed or nah: ", button.isPressed());
        telemetry.addData("stopbar", servo.getPosition()); */
        /*telemetry.addData("scoring motor", scoringMotor.getCurrentPosition());
//        telemetry.addData("OpLight: ", distance.getLightDetected());
//        telemetry.addData("OpRawLight: ", distance.getRawLightDetected());
//        telemetry.addData("OpRawLightMax: ", distance.getRawLightDetectedMax());
/*
        if (distance.getLightDetected() >= 0.01) {
            servo.setPosition(1);
        } else {
            if (distance.getLightDetected() < 0.0136) {
                servo.setPosition(0);
            }
        }
*/
        /*
        if (distance.getRawLightDetected() >= 0.19) {
            servo.setPosition(1);
        } else {
            if (distance.getRawLightDetected() < 0.19) {
                servo.setPosition(0);
            }
        }
        */
// Comment this back
/*
        if (servo.getPosition() == 1) {
                scoringMotor.setPower(1);
        } else {
            if (servo.getPosition() == 0) {
                scoringMotor.setPower(0);
            }
        }
        */
        /*
        if (servo.getPosition() == 1) {
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
    //
    // }
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
        if (servo.getPosition() != 1) {
            servo.setPosition(1);
        }
    }
}
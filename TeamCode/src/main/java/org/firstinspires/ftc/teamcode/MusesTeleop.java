package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Muses", group="Teleop")
public class MusesTeleop extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftMotor1 = null;
    private DcMotor leftMotor2 = null;
    private DcMotor rightMotor1 = null;
    private DcMotor rightMotor2 = null;

    private DcMotor lifterMotor = null;
    private DcMotor spinnerMotor = null;

    private DcMotor armMotor = null;
    private Servo relicServo = null;

    private Servo bucketServo1 = null;
    private Servo bucketServo2 = null;

    private Servo jewelServo = null;

    private ModernRoboticsI2cRangeSensor range1 = null;
    private ModernRoboticsI2cRangeSensor range2 = null;

    private double currentPosition = 0;
    private double newPosition = 0;

    private double motorThreshold = 0.065;

    private int tracker = 0;

    public void telemetrys() {

        telemetry.addData("leftY", gamepad1.left_stick_y);
        telemetry.addData("rightY", gamepad1.right_stick_y);

        telemetry.addData("LeftMotor1 Power ", leftMotor1.getPower());
        telemetry.addData("LeftMotor2 Power ", leftMotor2.getPower());
        telemetry.addData("RightMotor1 Power ", rightMotor1.getPower());
        telemetry.addData("RightMotor2 Power ", rightMotor2.getPower());

        telemetry.addData("Lifter Power: ", lifterMotor.getPower());

        //telemetry.addData("Spinner Power: ", spinnerMotor.getPower());

        telemetry.addData("Arm Power: ", armMotor.getPower());
        telemetry.addData("Relic Position:  ", relicServo.getPosition());

        telemetry.addData("Bucket Servo 1 Position: ", bucketServo1.getPosition());
        telemetry.addData("Bucket Servo 2 Position: ", bucketServo2.getPosition());

        telemetry.addData("jewel Servo Position: ", jewelServo.getPosition());

        //telemetry.addData("UltraSonic 1 CMs: ", range1.cmUltrasonic());
        //telemetry.addData("UltraSonic 2 CMs: ", range2.cmUltrasonic());
        //telemetry.addData("Optical 1 CMs: ", range1.cmOptical());
        //telemetry.addData("Optical 2 CMs: ", range2.cmOptical());

        telemetry.addData("Tracker: ", tracker);

        //telemetry.addData("Current Position: ", currentPosition);
        //telemetry.addData("New Position: ", newPosition);

        telemetry.update();
    }

    public void driveInit() {

        leftMotor1 = hardwareMap.dcMotor.get("left motor 1");
        leftMotor2 = hardwareMap.dcMotor.get("left motor 2");
        rightMotor1 = hardwareMap.dcMotor.get("right motor 1");
        rightMotor2 = hardwareMap.dcMotor.get("right motor 2");

        rightMotor1.setDirection(DcMotor.Direction.REVERSE);
        rightMotor2.setDirection(DcMotor.Direction.REVERSE);

        leftMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void lifterInit() {

        lifterMotor = hardwareMap.dcMotor.get("lifter motor");

        lifterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void spinnerInit() {

        spinnerMotor = hardwareMap.dcMotor.get("spinner motor");

        spinnerMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void armInit() {

        armMotor = hardwareMap.dcMotor.get("arm motor");
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        relicServo = hardwareMap.servo.get("relic servo");
    }

    public void bucketServoInit() {

        bucketServo1 = hardwareMap.servo.get("bucket servo 2");
        bucketServo2 = hardwareMap.servo.get("bucket servo 1");

        bucketServo1.setDirection(Servo.Direction.REVERSE);
        bucketServo2.setDirection(Servo.Direction.REVERSE);

        bucketServo1.setPosition(.5);
        bucketServo2.setPosition(.5);
    }

    public void jewelServoInit() {

        jewelServo = hardwareMap.servo.get("jewel servo");
        jewelServo.setPosition(1);
    }

    public void sensorInit() {

        range1 = (ModernRoboticsI2cRangeSensor) hardwareMap.get("range 1");
        range2 = (ModernRoboticsI2cRangeSensor) hardwareMap.get("range 2");
    }

    @Override
    public void init() {
        
        driveInit();
        lifterInit();
        spinnerInit();
        armInit();

        bucketServoInit();
        jewelServoInit();

        sensorInit();
    }

    public void driveLoop() {

        //Drive Controls
        float leftX = -(gamepad1.left_stick_x/2);// assigning controller values to a variable
        float rightX = -(gamepad1.right_stick_x/2);
        float leftY = -(gamepad1.left_stick_y/2);
        float rightY = -(gamepad1.right_stick_y/2);

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

        /*
        * OLD CODE
        * leftMotor1.setPower(-gamepad1.left_stick_y);
        * leftMotor2.setPower(-gamepad1.left_stick_y);
        * rightMotor1.setPower(-gamepad1.right_stick_y);
        * rightMotor2.setPower(-gamepad1.right_stick_y);
        */
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

    public void lifterLoop() {

        motorMovement(gamepad1.right_trigger, gamepad1.left_trigger, lifterMotor, 1);
    }

    public void overrideStatements() {

        //motorMovement(gamepad2.right_stick_y, -gamepad2.right_stick_y, spinnerMotor, .3);
    }

    public void spinForward(float encoderValue) {

        if (gamepad2.a) {
            tracker++;
            //currentPosition = spinnerMotor.getCurrentPosition();
            //newPosition = currentPosition + encoderValue;
        } else {
            overrideStatements();
        }
    }

    public void spinBackward(float encoderValue) {

        if (gamepad2.b) {
            tracker--;
            //currentPosition = spinnerMotor.getCurrentPosition();
            //newPosition = currentPosition - encoderValue;
        } else {
            overrideStatements();
        }
    }

    public void weightChange(float encoderValuePos, float encoderValueNeg) {

        if (tracker == 0) {
            spinForward(encoderValuePos);
        } else if (tracker == 2) {
            spinBackward(encoderValueNeg);
        } else if (tracker > 0) {
            spinForward(encoderValuePos);
            spinBackward(encoderValueNeg);
        } else {
            overrideStatements();
        }
    }

    public void spinnerElseStatement() {

      /*  if (range1.cmUltrasonic() <= 10 && range2.cmUltrasonic() <= 10) {
            weightChange(62,61); // These values are used just to show how variable substitution works
        } else if (range1.cmUltrasonic() <= 10 || range2.cmUltrasonic() <= 10) {
            weightChange(64,60); // These values are used just to show how variable substitution works
        } else {
            weightChange(62,62); // These values are close
        } */
    }

    public void spinnerLoop(double motorPower) {

        //Controls for Spinning the Bucket
        if ((spinnerMotor.getCurrentPosition() < newPosition) && (spinnerMotor.getCurrentPosition() >= currentPosition)) {
            if (currentPosition >= newPosition) {
                spinnerMotor.setPower(0);
            }  else {
                spinnerMotor.setPower(motorPower);
            }
        } else if ((spinnerMotor.getCurrentPosition() > newPosition) && (spinnerMotor.getCurrentPosition() <= currentPosition)) {
            if (currentPosition <= newPosition) {
                spinnerMotor.setPower(0);
            } else {
                spinnerMotor.setPower(-motorPower);
            }
        } else {
            spinnerElseStatement();
        }
    }

    public void armLoop() {

        /*
        * We do not know the controls for this yet, must get them from Rylee
        * They do not yet know how they want it controlled so this will just be empty for now
        *
        * Assumptions on what it will probably look like
        */
        if (gamepad2.x) {
           armMotor.setPower(.2);
        } else if (gamepad2.y) {
           armMotor.setPower(-.2);
        } else {
           armMotor.setPower(0);
        }

        if (gamepad2.dpad_down) {
            if (relicServo.getPosition() != 1) {
                relicServo.setPosition(1);
            }
        } else if (gamepad2.dpad_up) {
            if (relicServo.getPosition() != 0) {
                relicServo.setPosition(0);
            }
        }

        if (gamepad1.a) {
            if (jewelServo.getPosition() != 0) {
                jewelServo.setPosition(0);
            }
        }

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

    public void bucketServoLoop () {
/*
        if (gamepad2.left_trigger > 0) {
            bucketServo1.setPosition(.55);
        } else if (gamepad2.left_trigger == 0) {
            bucketServo1.setPosition(.45);
        } else {
            bucketServo1.setPosition(.5);
        }*/
/*
        if (gamepad2.right_trigger > 0) {
            bucketServo2.setPosition(1);
        } else {
            bucketServo2.setPosition(0);
        }
*/
        if (gamepad2.right_trigger < .45) {
            bucketServo2.setPosition(.45);
        } else {
            bucketServo2.setPosition(gamepad2.right_trigger);
        }

        bucketServo1.setPosition(gamepad2.left_trigger);

        //bucketServoSimple(gamepad2.left_trigger, gamepad2.left_bumper, bucketServo1, 1, 0, .5);
        //bucketServoSimple(gamepad2.right_trigger, gamepad2.right_bumper, bucketServo2, 1, 0, .5);
    }

    @Override
    public void loop() {

        //driving();
        driveLoop();

        lifterLoop();
        //spinnerLoop(.5);
        armLoop();

        bucketServoLoop();

        telemetrys();
    }    

    @Override
    public void stop() {
    }
}
/*
        OLD BUCKET SERVO CODE
        //This is the code for Bucket Servo 1
        if (gamepad2.left_trigger > 0) {
            if (bucketServo1.getPosition() != 1) {
                bucketServo1.setPosition(1);
            }
        } else if (gamepad2.left_bumper) {
            if (bucketServo1.getPosition() != 0) {
                bucketServo1.setPosition(0);
            }
        } else {
            if (bucketServo1.getPosition() != .5) {
                bucketServo1.setPosition(.5);
            }
        }

        //This is the code for Bucket Servo 2
        if (gamepad2.right_trigger > 0) {
            if (bucketServo2.getPosition() != 1) {
                bucketServo2.setPosition(1);
            }
        } else if (gamepad2.right_bumper){
            if (bucketServo2.getPosition() != 0) {
                bucketServo2.setPosition(0);
            }
        } else {
            if (bucketServo2.getPosition() != .5) {
                bucketServo2.setPosition(.5);
            }
        }*/
/*
        OLD LIFTER CODE
        //Controls for the lifter
        if (gamepad1.right_trigger > 0) {
            lifterMotor.setPower(1);
        } else if (gamepad1.left_trigger > 0) {
            lifterMotor.setPower(-1);
        } else {
            lifterMotor.setPower(0);
        }
        */
/*
      OLD OVERRIDE STATEMENTS CODE
        if (gamepad2.right_stick_y > 0) {
            spinnerMotor.setPower(.2);
        } else if (gamepad2.right_stick_y < 0) {
            spinnerMotor.setPower(-.2);
        } else {
            spinnerMotor.setPower(0);
        }
        */

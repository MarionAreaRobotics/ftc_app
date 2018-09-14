package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by mars on 1/3/18.
 */
@Autonomous(name = "Glyph Auto", group = "Testing")
public class glyphAuto extends OpMode {


    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftMotor1 = null;
    private DcMotor leftMotor2 = null;
    private DcMotor rightMotor1 = null;
    private DcMotor rightMotor2 = null;

    private DcMotor lifterMotor = null;
    private Servo blockServo = null;

    private DcMotor relicMotor = null;
    private Servo relicServo1 = null;
    private Servo relicServo2 = null;

    private DcMotor glyphArmMotor = null;

    private Servo jouleThiefServo = null;

    private int tracker = 1;

    public void telemetrys() {

        telemetry.addData("Left Motor 1 Position: ", leftMotor1.getCurrentPosition());
        telemetry.addData("Runtime: ", runtime.time());
        telemetry.addData("Glyph Arm Motor Position: ", glyphArmMotor.getCurrentPosition());
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

    public void blockServoInit() {

        blockServo = hardwareMap.servo.get("block servo");
        blockServo.setPosition(0);
    }

    public void relicMotorInit() {

        relicMotor = hardwareMap.dcMotor.get("relic motor");

        relicMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void relicServoInit() {

        relicServo1 = hardwareMap.servo.get("relic servo 1");
        relicServo2 = hardwareMap.servo.get("relic servo 2");
        relicServo1.setPosition(0);
        //relicServo2.setPosition(0);

    }

    public void jouleThiefInit() {

        jouleThiefServo = hardwareMap.servo.get("joule thief");
        jouleThiefServo.setPosition(0);
    }

    public void glyphArmInit() {

        glyphArmMotor = hardwareMap.dcMotor.get("glyph arm");
        //glyphArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        glyphArmMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void init() {

        driveInit();
        lifterInit();
        relicMotorInit();

        blockServoInit();
        jouleThiefInit();
        relicServoInit();
        glyphArmInit();
    }

    public void motorMethod(double Power){
        leftMotor1.setPower(Power);
        leftMotor2.setPower(Power);
        rightMotor1.setPower(Power);
        rightMotor2.setPower(Power);
    }

    public void armLogic() {

        if (leftMotor1.getCurrentPosition() <= 3000) {
            motorMethod(.5);
        } else if (leftMotor1.getCurrentPosition() > 3000) {
            motorMethod(0);
            if (glyphArmMotor.getCurrentPosition() < 30 && tracker == 1){
                glyphArmMotor.setPower(.12);

            } else if (glyphArmMotor.getCurrentPosition() >= 10 && tracker == 1){
                tracker = 2;
                glyphArmMotor.setPower(0);
                runtime.reset();
            } else if (glyphArmMotor.getCurrentPosition() >= -42 && tracker == 2 && runtime.time() > 5){
                glyphArmMotor.setPower(-.55);

            } else if (tracker == 2){
                glyphArmMotor.setPower(0);

            }
        }
        telemetrys();
    }
    @Override public void start(){

        runtime.reset();
    }
    @Override
    public void loop() {

        armLogic();
    }
}

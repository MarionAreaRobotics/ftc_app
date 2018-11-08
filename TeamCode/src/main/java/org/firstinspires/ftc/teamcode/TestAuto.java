package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous (name = "Test Auto", group = "Autonomous")
public class TestAuto  extends OpMode{

   private int tracker = 0;
   private DcMotor leftMotor1 = null;
   private DcMotor rightMotor1 = null;
   private DcMotor leftMotor2 = null;
   private DcMotor rightMotor2 = null;

    public void telemetrys() {
        telemetry.addData("leftMotor1 position", leftMotor1.getCurrentPosition());
        telemetry.addData("rightMotor1 position", rightMotor1.getCurrentPosition());
        telemetry.addData("leftMotor2 position", leftMotor2.getCurrentPosition());
        telemetry.addData("rightMotor2 position", rightMotor2.getCurrentPosition());

        telemetry.addData("Tracker Value:  ", tracker);

        telemetry.update();
    }

    public void driveinit(){
        leftMotor1 = hardwareMap.dcMotor.get("left motor 1");
        rightMotor1 = hardwareMap.dcMotor.get("right motor 1");
        leftMotor2 = hardwareMap.dcMotor.get("left motor 2");
        rightMotor2 = hardwareMap.dcMotor.get("right motor 2");
        rightMotor1.setDirection(DcMotor.Direction.REVERSE);
        rightMotor2.setDirection(DcMotor.Direction.REVERSE);

        leftMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void move(float power1, float power2, float power3, float power4){
        leftMotor1.setPower(power1);
        leftMotor2.setPower(power2);
        rightMotor1.setPower(power3);
        rightMotor2.setPower(power4);
    }

    public void autoLoop() {
        if (leftMotor1.getCurrentPosition() <= -3800 && tracker == 0) {
            move(-1,-1,-1,-1);
            tracker = 1;
        } else if (leftMotor1.getCurrentPosition() >= -4400  && tracker == 1) {
            move(-1,-1,1,1);
            tracker = 2;
        } else if (leftMotor1.getCurrentPosition() <= 2000  &&  tracker == 2) {
            move(1,1,1,1);
            tracker = 3;
        } else if (leftMotor1.getCurrentPosition() >= 2000 && tracker == 3)  {
            move(0,0,0,0);
        }
    }

    @Override
    public void init() {
        driveinit();
    }

    @Override
    public void loop() {
        autoLoop();
        telemetrys();
    }

}

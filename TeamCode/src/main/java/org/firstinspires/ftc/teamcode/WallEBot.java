package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@Disabled
@TeleOp (name="Wall-E Bot", group = "Teleop")
public class WallEBot extends OpMode{

    private DcMotor leftMotor = null;
    //private DcMotor leftMotor2 = null;
    private DcMotor rightMotor = null;
    //private DcMotor rightMotor2 = null;

    @Override
    public void init() {
        leftMotor = hardwareMap.dcMotor.get("left motor");
        //leftMotor2 = hardwareMap.dcMotor.get("left motor 2");
        rightMotor = hardwareMap.dcMotor.get("right motor");
        //rightMotor2 = hardwareMap.dcMotor.get("right motor 2");

        telemetry.addData("Left Motor Power:  ", leftMotor.getPower());
        //telemetry.addData("Left Motor 2 Power:  ", leftMotor2.getPower());
        telemetry.addData("Right Motor Power :  ", rightMotor.getPower());
        //telemetry.addData("Right Motor 2 Power:  ", rightMotor2.getPower());

        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        //leftMotor2.setDirection(DcMotor.Direction.REVERSE);

    }

    @Override
    public void loop() {

        leftMotor.setPower(gamepad1.left_stick_y);
        //leftMotor2.setPower(gamepad1.left_stick_y);
        rightMotor.setPower(gamepad1.right_stick_y);
        //rightMotor2.setPower(gamepad1.right_stick_y);

    }

    public void stop(){

    }

}

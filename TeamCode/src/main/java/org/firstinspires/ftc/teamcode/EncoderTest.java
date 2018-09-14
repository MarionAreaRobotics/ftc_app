package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by mars on 10/27/17.
 */
@Disabled
@TeleOp(name="Encoder", group ="Testing")
public class EncoderTest extends OpMode {

    private DcMotor spinnerMotor = null;

    int currentPosition = 0;
    int newPosition = 0;

    @Override
    public void init() {

        spinnerMotor = hardwareMap.dcMotor.get("spinner motor");
    }

    @Override
    public void loop() {

        telemetry.addData("spinnerMotor Position: ", spinnerMotor.getCurrentPosition());
        telemetry.addData("spinnerMotor Power: ", spinnerMotor.getPower());
        telemetry.addData("Button A: ", gamepad1.a);
        telemetry.addData("Button B: ", gamepad1.b);

        telemetry.addData("Current Position: ", currentPosition);
        telemetry.addData("New Position: ", newPosition);

        if ((spinnerMotor.getCurrentPosition() < newPosition) && (spinnerMotor.getCurrentPosition() >= currentPosition)) {
            telemetry.addLine("YOU THOUGHT");
            if (currentPosition >= newPosition) {
                spinnerMotor.setPower(0);
            }  else {
                spinnerMotor.setPower(1);
            }
        } else if ((spinnerMotor.getCurrentPosition() > newPosition) && (spinnerMotor.getCurrentPosition() <= currentPosition)) {
            telemetry.addLine("YOU THOUGHT");
            if (currentPosition <= newPosition) {
                spinnerMotor.setPower(0);
            } else {
                spinnerMotor.setPower(-1);
            }
        } else {
            if (gamepad1.a) {
                currentPosition = spinnerMotor.getCurrentPosition();
                newPosition = currentPosition + 900;
            }  else if (gamepad1.b) {
                currentPosition = spinnerMotor.getCurrentPosition();
                newPosition = currentPosition - 900;
            } else {
                spinnerMotor.setPower(0);
                telemetry.addLine("YOU THOUGHT AGAIN");
            }
        }



        /*
        if (spinnerMotor.getCurrentPosition() <= 1000) {
            spinnerMotor.setPower(1);
        } else if (spinnerMotor.getCurrentPosition() <= 10000) {
            spinnerMotor.setPower(.5);
        } else {
            spinnerMotor.setPower(0);
        }
        */

    }
}

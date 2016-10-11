package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by mars on 10/9/16.
 */
@TeleOp(name="Servo", group="Testing")
public class ServoTest extends OpMode {

    private Servo servoTesting = null;

    public void init() {

        servoTesting = hardwareMap.servo.get("servo 1");
        servoTesting.setPosition(0);

    }

    public void loop() {
        telemetry.addData("Servo Position", servoTesting.getPosition());
        if (gamepad1.a) {
            if (servoTesting.getPosition() != 1) {
            servoTesting.setPosition(1);
            }
        } else {
            if (servoTesting.getPosition() != 0) {
                servoTesting.setPosition(0);
            }
        }

    }
}

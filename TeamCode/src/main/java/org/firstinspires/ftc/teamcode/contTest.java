package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by mars on 11/2/16.
 */
@TeleOp(name="contServo", group="Testing")
public class contTest extends OpMode {

    private Servo contServo = null;

    @Override
    public void init() {
        contServo = hardwareMap.servo.get("cont");
    }

    @Override
    public void loop() {
        if (gamepad2.dpad_up) {
            contServo.setPosition(1);
        } else if (gamepad2.dpad_down) {
            contServo.setPosition(0);
        } else {
            contServo.setPosition(0.5);
        }
    }

    @Override
    public void stop() {

    }
}

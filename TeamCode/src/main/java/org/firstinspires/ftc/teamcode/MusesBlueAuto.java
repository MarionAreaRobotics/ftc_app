package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by mars on 12/17/16.
 */

@Autonomous(name="MusesBlueAuto", group="Auto")
public class MusesBlueAuto extends OpMode {
    private DcMotor scoringMotor = null;
    private Servo servo = null;
    private OpticalDistanceSensor distance = null;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
        distance = hardwareMap.opticalDistanceSensor.get("distance");
        scoringMotor = hardwareMap.dcMotor.get("scoring motor");
        servo = hardwareMap.servo.get("stopbar");
        servo.setPosition(1);
        scoringMotor.setDirection(DcMotor.Direction.REVERSE);
        scoringMotor.setTargetPosition(0);
    }

    @Override
        public void loop() {
        telemetry.addData("stopbar", servo.getPosition());
        telemetry.addData("scoring motor", scoringMotor.getCurrentPosition());
        telemetry.addData("OpLight: ", distance.getLightDetected());
        telemetry.addData("OpRawLight: ", distance.getRawLightDetected());
        telemetry.addData("OpRawLightMax: ", distance.getRawLightDetectedMax());

        if (distance.getRawLightDetected() >= 0.09) {
            servo.setPosition(1);
        } else {
            if (distance.getRawLightDetected() < 0.09) {
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
    }

    @Override
    public void stop() {

    }
}
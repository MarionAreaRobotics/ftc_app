package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

/**
 * Created by mars on 10/30/16.
 */
@TeleOp(name="Color", group="Testing")
public class SensorTest extends OpMode {

    private OpticalDistanceSensor optical = null;

    @Override
    public void init() {
        optical = hardwareMap.opticalDistanceSensor.get("color");
    }

    @Override
    public void loop() {
        telemetry.addData("Light: ", optical.getLightDetected());
        telemetry.addData("RawLight: ", optical.getRawLightDetected());
        telemetry.addData("RawLightMax: ", optical.getRawLightDetectedMax());
    }
}

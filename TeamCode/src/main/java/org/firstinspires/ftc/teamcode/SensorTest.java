package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

/**
 * Created by mars on 10/30/16.
 */
@TeleOp(name="Sensor", group="Testing")
public class SensorTest extends OpMode {

    private OpticalDistanceSensor optical = null;
    private ColorSensor color = null;

    @Override
    public void init() {

        color = hardwareMap.colorSensor.get("color");
        optical = hardwareMap.opticalDistanceSensor.get("distance");
        color.enableLed(false);
    }

    @Override
    public void loop() {

        telemetry.addData("ColorAlpha", color.alpha());
        telemetry.addData("ColorBlue", color.blue());
        telemetry.addData("ColorRed", color.red());
        telemetry.addData("ColorGreen", color.green());

        telemetry.addData("OpLight: ", optical.getLightDetected());
        telemetry.addData("OpRawLight: ", optical.getRawLightDetected());
        telemetry.addData("OpRawLightMax: ", optical.getRawLightDetectedMax());
    }

    @Override
    public void stop() {

    }
}

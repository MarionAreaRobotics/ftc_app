package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorMRRangeSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


@TeleOp(name="Sensor", group="Testing")
public class SensorTest extends OpMode {

    private OpticalDistanceSensor optical = null;
    private ColorSensor color = null;
    private ModernRoboticsI2cRangeSensor range = null;

    @Override
    public void init() {

        range = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "range");

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

        telemetry.addData("raw ultrasonic", range.rawUltrasonic());
        telemetry.addData("raw optical", range.rawOptical());
        telemetry.addData("cm optical", "%.2f cm", range.cmOptical());
        telemetry.addData("cm", "%.2f cm", range.getDistance(DistanceUnit.CM));
        telemetry.update();
    }

    @Override
    public void stop() {

    }
}

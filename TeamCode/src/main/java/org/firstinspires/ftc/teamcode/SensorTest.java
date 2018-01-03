package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.Range;

@Disabled
@TeleOp(name="Sensor", group="Testing")
public class SensorTest extends OpMode {

    /*
    * This program is used to test out the different sensors that we can use
    * WE DO NO DELETE ANY CODE FROM THIS PROGRAM, ONLY COMMENT IT OUT
     */

    ColorSensor color = null;

    TouchSensor touch = null;

    GyroSensor gyro = null;

    ModernRoboticsI2cRangeSensor range = null;

    DcMotor motor = null;

    @Override
    public void init() {

        //range = (ModernRoboticsI2cRangeSensor) hardwareMap.get("range");

        //touch = hardwareMap.touchSensor.get("touch");

        color = hardwareMap.colorSensor.get("color");
        motor = hardwareMap.dcMotor.get("motor");

        //gyro = hardwareMap.gyroSensor.get("gyro");
        //gyro.calibrate();
    }

    @Override
    public void loop() {

        //telemetry.addData("Ultrasonic CM", range.cmUltrasonic());
        //telemetry.addData("Optical CM", range.cmOptical());

        //telemetry.addData("Gyro X: ", gyro.rawX());
        //telemetry.addData("Gyro Y: ", gyro.rawY());
        //telemetry.addData("Gyro Z: ", gyro.rawZ());

        /*telemetry.addData("Touch Pressed?: ", touch.isPressed());
        telemetry.addData("Touch Value: ", touch.getValue());
*/
        telemetry.addData("Alpha", String.valueOf(color.alpha()));
        telemetry.addData("ARGB", String.valueOf(color.argb()));
        telemetry.addData("Blue", String.valueOf(color.blue()));
        telemetry.addData("Red", String.valueOf(color.red()));
        telemetry.addData("Green", String.valueOf(color.green()));
        telemetry.update();

        if (color.red() >= 0.1) { // Red is detected

            motor.setPower(1);
        } else if (color.blue() >= 0.1){
            motor.setPower(-1);
        } else {
            motor.setPower(0);
        }







    }

    @Override
    public void stop() {

    }
}

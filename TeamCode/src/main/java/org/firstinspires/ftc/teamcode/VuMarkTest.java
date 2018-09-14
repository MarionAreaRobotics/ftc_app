package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
*
*
* I don't know what is up with this program, we need to do more testing with it...
*
*/

@Disabled
@Autonomous(name="Concept: VuMark Id", group ="Concept")
public class VuMarkTest extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private int tracker = 0;
    private int position = 0;

    private DcMotor leftMotor1 = null;
    private DcMotor leftMotor2 = null;
    private DcMotor rightMotor1 = null;
    private DcMotor rightMotor2 = null;

    private DcMotor glyphArmMotor = null;

    VuforiaLocalizer vuforia;

            /**
            * We don't need the camera monitor on the robot phone because we can't see it when using it so its just wasting battery
            *
            * To add the monitor back in, remove the parameters line below this and replace with these two lines
            *int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
            *VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
            */

    VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

    VuforiaTrackables relicTrackables;
    VuforiaTrackable relicTemplate;

    public void glyphArmInit() {

        glyphArmMotor = hardwareMap.dcMotor.get("glyph arm");
        glyphArmMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void init() {

        glyphArmInit();

        leftMotor1 = hardwareMap.dcMotor.get("left motor 1");
        leftMotor2 = hardwareMap.dcMotor.get("left motor 2");
        rightMotor1 = hardwareMap.dcMotor.get("right motor 1");
        rightMotor2 = hardwareMap.dcMotor.get("right motor 2");

        rightMotor1.setDirection(DcMotor.Direction.REVERSE);
        rightMotor2.setDirection(DcMotor.Direction.REVERSE);

        leftMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        parameters.vuforiaLicenseKey = "AcLd9LX/////AAAAGXMDCxRRokZludaA8XxJ6yM/1vcc97SRoLcHM/waQfat+A8aWxq/0QKu6wmmlbnzn7xNS86D69lG8zgmO6+gUxAEgyWT13hLxs+8MqsEUI/SinXX3kCGcJyf2VY1C17NcAGSHFWdZq4tkX1uDj17ch7DYEWsPQFhIfTlAWaz0pZMXM106xUS4Hgi2FVK6SJqD+yC9I3tH0yiT7FHRW1lyXCiHxCqNzh7eyvIaEcJpZyxPbFkOt99P4I3cHz0QecxkUQvCq7L+7KHnVDz9VI/lTXKxbckmGFHSJbC6HnrW4OFK5b8q+z9QlT+vQ5aBaKxLj/mTt1azTPdntGWaYwoRT36yxKrjIHwk2ORx5+avjRP";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        relicTrackables.activate();

    }

    @Override
    public void start() {

        runtime.reset();
    }

    public void motorMethod(double Power){
        leftMotor1.setPower(Power);
        leftMotor2.setPower(Power);
        rightMotor1.setPower(Power);
        rightMotor2.setPower(Power);
    }

    @Override
    public void loop() {

        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        if (vuMark != RelicRecoveryVuMark.UNKNOWN && tracker == 0) {
            telemetry.addData("VuMark", " visible");

            if (vuMark == RelicRecoveryVuMark.LEFT) {
                tracker++;
                position = 3000;
            } else if (vuMark == RelicRecoveryVuMark.CENTER) {
                tracker++;
                position = 3500;
            } else if (vuMark == RelicRecoveryVuMark.RIGHT) {
                tracker++;
                position = 4000;
            }

            telemetry.addData("VuMark", "%s visible", vuMark);
        } else if (tracker != 0) {
            if (leftMotor1.getCurrentPosition() < position) {
                motorMethod(.5);
            } else {
                motorMethod(0);
                if (glyphArmMotor.getCurrentPosition() < 30 && tracker == 1){
                    glyphArmMotor.setPower(.12);

                } else if (glyphArmMotor.getCurrentPosition() >= 30 && tracker == 1){
                    tracker = 2;
                    glyphArmMotor.setPower(0);
                    runtime.reset();
                } else if (glyphArmMotor.getCurrentPosition() >= 1 && tracker == 2 && runtime.time() >= 5){
                    glyphArmMotor.setPower(-.375);

                } else if (tracker == 2){
                    glyphArmMotor.setPower(0);

                }
            }
        } else {
            telemetry.addData("VuMark", "not visible");
        }

        telemetry.addData("Left Motor 1 Pos: ", leftMotor1.getCurrentPosition());

        telemetry.update();
    }
}

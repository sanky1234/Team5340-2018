package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RobotAdmin on 11/8/2017.
 */

public class CommonVariables extends LinearOpMode {

    public DcMotor left;
    public DcMotor right;
    public DcMotor lift;

    final public double SPEED_ADDITION = .25;
    final public double MOTOR_FAST = 1;
    final public double MOTOR_SLOW = 0.25;
    final public double LIFT_SLOW = 0.5;
    final public double LIFT_MAX_CLICKS = 1850;
    final public double WHEEL_DIAMETER = 4;
    //final public double GEAR_RATIO = 3.0 / 1.0;
    final public int CLICKS_PER_ROTATION = 1180;
    public double motorSlowingFactor = MOTOR_FAST;
    final public double FAST_TURNSPEED = 0.4;
    final public double SLOW_TURNSPEED = 0.2;
    final public double LIFT_CLICKS = 280;


    public Servo leftGrabber;
    public Servo rightGrabber;

    final public double CLOSE_GRABBER = 0.2;
    final public double OPEN_GRABBER = 0.75;
    final public double INITIAL_GRABBER = 1;

    public boolean MotorSlowing = false;



    ///////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// Sensors //////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////
    GyroSensor Gyroscope;
    ModernRoboticsI2cGyro gyro;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////Vuforia////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public VuforiaLocalizer vuforia;
    public int cameraMonitorViewId;
    public  VuforiaLocalizer.Parameters parameters;
    public VuforiaTrackables relicTrackables;
    public VuforiaTrackable relicName;

    public RelicRecoveryVuMark trackableItemThatWasSensed = null;
    public String trackableItemString = null;



    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void initTeleop(){
        left = hardwareMap.dcMotor.get("left");
        right = hardwareMap.dcMotor.get("right");
        lift = hardwareMap.dcMotor.get("lift");
        leftGrabber = hardwareMap.servo.get("leftGrabber");
        rightGrabber = hardwareMap.servo.get("rightGrabber");
        rightGrabber.setDirection(Servo.Direction.REVERSE);

        left.setPower(0);
        right.setPower(0);

        lift.setPower(0);

        leftGrabber.setPosition(INITIAL_GRABBER);
        rightGrabber.setPosition(INITIAL_GRABBER);


        left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        left.setDirection(DcMotor.Direction.REVERSE);
        right.setDirection(DcMotor.Direction.FORWARD);
        lift.setDirection(DcMotor.Direction.FORWARD);
    }

    public void initAuto(){
        left = hardwareMap.dcMotor.get("left");
        right = hardwareMap.dcMotor.get("right");
        lift = hardwareMap.dcMotor.get("lift");
        leftGrabber = hardwareMap.servo.get("leftGrabber");
        rightGrabber = hardwareMap.servo.get("rightGrabber");
        rightGrabber.setDirection(Servo.Direction.REVERSE);

        left.setPower(0);
        right.setPower(0);

        lift.setPower(0);

        leftGrabber.setPosition(INITIAL_GRABBER);
        rightGrabber.setPosition(INITIAL_GRABBER);


        Gyroscope = hardwareMap.gyroSensor.get("gyro");
        gyro = (ModernRoboticsI2cGyro) Gyroscope;
        gyro.calibrate();

        initializeVuforia();

        /** For convenience, gather together all the trackable objects in one easily-iterable collection */



        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left.setDirection(DcMotor.Direction.FORWARD);
        right.setDirection(DcMotor.Direction.REVERSE);
        lift.setDirection(DcMotor.Direction.FORWARD);
    }

    public void initializeVuforia(){
        cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "AVWW28f/////AAAAGfjMlzXbm0qWnsZsQIVnO79fa/WoqEbEpObstVpg9y3bkT6lBKIehRm/F21rle3YZ89neXVRy8V0m5Jxv4kLH+xnMJIcZTL91m2+Lu8JuHFGawOre8U/jET8NwpHCKK42NiDNbwQEGLGuxe74jNPs43uRV628qydDmks27fuSCVc86a6i96f7uBIUbYsQ98MJR8zr0pS/MVpONXjwxzYkj/eVkN0liwIkKlJXTO+lpfPb9PwzcjydRGcEMUGm8aATdPCmMwGXntbHWRHge6i/nhfu1QaGkBxjNmnaHUv8cJR18Sbsh8nuWGzUmH03NtAfot2x9nOI7yLt1U7mJ1SthPSQXlKa85afL2SBgCkyKQp";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicName = relicTrackables.get(0);
        trackableItemThatWasSensed = RelicRecoveryVuMark.UNKNOWN;
    }


    @Override
    public void runOpMode() throws InterruptedException {

    }
}

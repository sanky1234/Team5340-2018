package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "TestServo", group = "Concept")
public class servoTest extends OpMode { //fully up .5 dump .3 hold 1

    Servo leftGrabber;
    Servo rightGrabber;

    @Override
    public void init() {

        leftGrabber = hardwareMap.servo.get("leftGrabber");
        rightGrabber = hardwareMap.servo.get("rightGrabber");
    }

    @Override
    public void loop() {
        leftGrabber.setPosition(gamepad1.right_trigger);
        rightGrabber.setPosition(gamepad1.left_trigger);
        telemetry.addData("Right", rightGrabber.getPosition());
        telemetry.addData("Left",leftGrabber.getPosition());
        telemetry.update();
    }
}


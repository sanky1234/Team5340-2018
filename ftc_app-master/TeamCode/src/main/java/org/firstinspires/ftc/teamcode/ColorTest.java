package org.firstinspires.ftc.teamcode;

/**
 * Created by RobotAdmin on 12/14/2017.
 */

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
/**
 * Created by RobotAdmin on 12/14/2017.
 */

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

/**
 * Created by RobotAdmin on 11/4/2017.
 */
@Autonomous(name = "ColorTest", group = "fisherMan")
public class ColorTest extends CommonVuforia  {

    @Override
    public void runOpMode() throws InterruptedException {
        initAuto();
        waitForStart();
        while (gyro.isCalibrating()) {

        }
        while(opModeIsActive()){
            telemetry.addData("green", colorSensor.green());
            telemetry.addData("red",colorSensor.red());
            telemetry.addData("blue",colorSensor.blue());
            telemetry.update();
        }
    }
}

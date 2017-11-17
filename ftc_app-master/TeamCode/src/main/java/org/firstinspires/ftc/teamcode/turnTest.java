package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by RobotAdmin on 11/9/2017.
 */
@Autonomous(name = "TurnTest", group = "Fisherman")
public class turnTest extends CommonDriving {

    @Override
    public void runOpMode() throws InterruptedException {
        initAuto();
       // gyro.calibrate();
        waitForStart();
        while(gyro.isCalibrating()) {
            telemetry.addData("GYro", "DO NOT MOVE");
            telemetry.update();
        }

        turnRight(90);
        sleep(2000);
        turnLeft(90);
        sleep(5000);
        turnRight(180);
        sleep(2000);
        turnLeft(180);
        sleep(5000);
        turnRight(270);
        sleep(2000);
        turnLeft(270);
        sleep(5000);
        turnRight(360);
        sleep(2000);
        turnLeft(360);
        sleep(5000);
        turnRight(45);
        sleep(2000);
        turnLeft(45);
    }
}

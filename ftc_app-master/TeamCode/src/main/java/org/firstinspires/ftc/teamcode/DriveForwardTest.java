package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by RobotAdmin on 11/8/2017.
 */
@Autonomous(name = "DriveForwardTest", group = "fisherMan")
public class DriveForwardTest extends CommonDriving{
    @Override
    public void runOpMode() throws InterruptedException {
        initAuto();
        waitForStart();
        driveForwardWithEncoders(12, 0.5);
        sleep(2000);
        driveForwardWithEncoders(40, 0.5);
    }

}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

/**
 * Created by RobotAdmin on 11/4/2017.
 */
@Autonomous(name = "First Auto", group = "fisherMan")
public class firstAuto extends CommonVuforia{

    //CommonActuatorClass actuator = new CommonActuatorClass();
    @Override
    public void runOpMode() throws InterruptedException {
        initAuto();
        waitForStart();
        while(gyro.isCalibrating()){

        }
        relicTrackables.activate();
        //closeArms();
        liftUp(3,0.2);

        ///Add color sensor and servo movement.
        //add turn to knock ball off
        //add turn to turn back to 0

        driveForwardWithEncoders(30,0.4);
        sleep(100);
        turnRight(90);
        sleep(100);
        driveForwardWithEncoders(10,0.4);

        while(trackableItemThatWasSensed == RelicRecoveryVuMark.UNKNOWN){
           getTrackable();
        }

        trackableItemString = getTrackableString();

        if(trackableItemString.equalsIgnoreCase("Center")){
            driveForwardWithEncoders(30,0.4);
        }else if(trackableItemString.equalsIgnoreCase("Left")){
            turnLeft(70);
        }else{
            turnRight(70);
            turnLeft(70);
        }
        // using if statement go to the right collumn

        driveForwardWithEncoders(30,0.4);

        liftDown(3,0.2);
        openArms();
        driveBackwardsWithEncoders(5,0.4);
    }


}

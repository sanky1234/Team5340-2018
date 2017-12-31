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
        closeArms();
        sleep(200);
        liftUp(3,0.2);
        sleep(200);
        driveForwardWithEncoders(5,0.4);
        sleep(200);
        setDownBall();
        sleep(1000);


        if(colorSensor.red() > colorSensor.blue()) {

            turnLeft(30);
            bringUpBall();
            turnRight(30);
        }
        else {
            turnRight(30);
            bringUpBall();
            turnLeft(30);
        }

        sleep(1000);


        ///Add color sensor and servo movement.
        //add turn to knock ball off
        //add turn to turn back to 0

        driveForwardWithEncoders(3,0.4);
        sleep(100);

        while(trackableItemThatWasSensed == RelicRecoveryVuMark.UNKNOWN){
           getTrackable();
        }

        trackableItemString = getTrackableString();

        turnLeft(90);
        driveForwardWithEncoders(10,0.5);
        turnRight(90);

        if(trackableItemString.equalsIgnoreCase("Center")){
            driveForwardWithEncoders(10,0.4);
            sleep(100);
            turnRight(90);
            sleep(100);
            driveForwardWithEncoders(10,0.4);
            sleep(100);

        }else if(trackableItemString.equalsIgnoreCase("Right")){

            driveForwardWithEncoders(5 ,0.4);
            sleep(100);
            turnRight(90);
            sleep(100);
            driveForwardWithEncoders(10,0.4);
            sleep(100);


        }else{
            driveForwardWithEncoders(15,0.4);
            sleep(100);
            turnRight(90);
            sleep(100);
            driveForwardWithEncoders(10,0.4);
            sleep(100);
        }
        // using if statement go to the right collumn

        //driveForwardWithEncoders(15,0.4);

        //sleep(100);
        liftDown(3,0.2);
        sleep(100);
        openArms();
        sleep(100);
        liftUp(3,0.2);
        sleep(100);
        driveBackwardsWithEncoders(5,0.4);
        liftDown(3,02);
    }

}

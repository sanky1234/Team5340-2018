package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;

/**
 * Created by RobotAdmin on 11/9/2017.
 */

//@Autonomous(name="VuforiaTesting", group="Fisherman")
public class CommonVuforia extends CommonActuatorClass{

    public void getTrackable() {
        trackableItemThatWasSensed = RelicRecoveryVuMark.from(relicName);
    }

    public String getTrackableString(){
        if(trackableItemThatWasSensed == RelicRecoveryVuMark.CENTER){
            return "Center";
        }else if (trackableItemThatWasSensed == RelicRecoveryVuMark.LEFT){
            return "Left";
        }else{
            return "Right";
        }
    }
}


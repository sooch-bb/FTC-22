package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

//BEGIN NEWCODE-Added for Camera
import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
//END NEWCODE-Added for Camera

@Autonomous(name = "Auto1Red_Cam.java", group = "")
public class Auto1Red_Cam extends LinearOpMode {

  private DcMotor frontLeftWheel;
  private DcMotor backLeftWheel;
  private DcMotor wrubby_motor;
  private DcMotor slide_motor;
  private DcMotor frontRightWheel;
  private DcMotor backRightWheel;
  private Servo servo1;
  private Servo servo2;

  //BEGIN - NEWCODE added for Team Element Jousting
  private Servo servo3;
  //END - NEWCODE added for Team Element Jousting
  
  //BEGIN - NEWCODE added for Camera
  private static final String TFOD_MODEL_ASSET = "FreightFrenzy_BCDM.tflite";
    private static final String[] LABELS = {
      "Ball",
      "Cube",
      "Duck",
      "Marker"
    };

    //Key #1 - Suchi16704-Test
    private static final String VUFORIA_KEY =
            "AbgYMOn/////AAABmThHF2hCQ0AFrsWKpuAmWo5P7imaRXnNdUJXTrGqXYTfaEMFlH0y/DKhy/00oWqlQS+uZTLrojPJJLY8L5Py6TNCyQ5b4QqVLWDY/PLKGgC5EZqc8YdsjqVNNeMMd3U8Le4sa9Wny4lQUObYpWLEBNwhOjlEi0DqJPHJiFCbp4JLTwqF4jw0h0kpLeZKRq7vJLSnKCNiBCO4WUdM5wSEbEs4OZqIyTgIX8hoxqmHNJlHBX3skVjnhcOLubmazVcEg/s8NTiaT2iqj4TtFjIPokeyQqCiLfzou+7BEJtSgMXpZCkOKoZFXLcuiLTwVsQA2RU+wuscsaqClQsrtt/M4aBaZQeCZbBeFcXd9TafTaov";

    //Key #2 - Suchi16704-1
    // private static final String VUFORIA_KEY =  
      //  "AQbQygH/////AAABmV8ESnoWD0L5oRXvpecUpSQx6hI1aHhjdENUEkdyZrzImN0rhVr1S0E+80oVQ5QwfEf7juKVVCupAhXunM3ifLiusOwjDjzQzqRBz4YYP/YI57S1kvft8peHXPE6l/FPRx7XQ6pVzwBm0IwgDV7QSgJ94+vWupZMkU+nmWvooP5DJ3Vxtbry52oDPG5hLGH7M3Ny/zsjmoHgA5zlFtUaHXWMSmWlck9aQlN7RXAiwuIaMiGp9agBcoWg8xw7ZEhX/H5VphzCTzm51oV+OfrxFbTiAB5k2QyiTOQIbB+BTPuNVX5Ewi/RcLObMFtr80YUQiTIaTHNaOPgzx/m/yllYMQ9iLN1X4rFK3t3r4Rs5tW+";
    
    //Key #3 - Suchi16704
    // private static final String VUFORIA_KEY =
    //   "AQxBBbj/////AAABmbpWh4Yh2EbOpSxgE7Ln00eAu7wP09PUTUXpkBT95+1NVxerFwBFNPpVtOGybbL0JD4CmMZFHIB3LUBuU21ZgF/YY7qKVpHduYvLHmohWPtLL17xuDbfhkDGPs5N1GTXypcC946bruBtAjG5SRA20wz6aoR+FytRAa+vPcqK8HRBBu6rQA5FpP/NlV/gvaOYqQrUBvDI3xPDlSdrM/Yt0TmrrS5LFzw/AYuwrJ+35Py46EHrO08fuRJKCwQGTx4RxxwQCmQZDWeWPF+RkyXYlQlRk63l+A1Ca7Ga4N93MNRWfUfI4o2c0KfZgLm6H+QgPfLjiunXqzWpoJ2ZMN4hR8enlwqWOqDhlRZKtZmMhDf3";
    
    private VuforiaLocalizer vuforia;

    private TFObjectDetector tfod;

    //Suchi - POJO
    final class FFViewObject
    {
      public String Oname;
      public int Oposition;
     
      public FFViewObject(String Oname, int Oposition)
      {
         this.Oname = Oname;
         this.Oposition = Oposition;
      }
     }
     
 //END - NEWCODE added for Camera

  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    
    frontLeftWheel = hardwareMap.get(DcMotor.class, "frontLeftWheel");
    backLeftWheel = hardwareMap.get(DcMotor.class, "backLeftWheel");
    frontRightWheel = hardwareMap.get(DcMotor.class, "frontRightWheel");
    backRightWheel = hardwareMap.get(DcMotor.class, "backRightWheel");
    wrubby_motor = hardwareMap.get(DcMotor.class, "wrubby_motor");
    slide_motor = hardwareMap.get(DcMotor.class, "slide_motor");
    servo1 = hardwareMap.get(Servo.class, "servo1");
    servo2 = hardwareMap.get(Servo.class, "servo2");
    
    //BEGIN - NEWCODE added for Jousting
    servo3 = hardwareMap.get(Servo.class, "servo3");
    servo3.setPosition(0.0);
    //END - NEWCODE added for Jousting
    
    // Put initialization blocks here
    servo2.setPosition(0.8);
    servo1.setPosition(0.6);
    slide_motor.setDirection(DcMotorSimple.Direction.REVERSE);
 
   
   //BEGIN - NEWCODE added for Camera
    int pos = 0;
    int slidesleep = 1;  //Change this to what team wants as default if they cant recognize
    initVuforia();
    initTfod();
    if (tfod != null) {
        tfod.activate();
        tfod.setZoom(2.0, 16.0/9.0);
        }
   //END - NEWCODE added for Camera
        
        waitForStart();
        servo2.setPosition(0.6);
 
        //BEGIN - NEWCODE added for Camera
        if (opModeIsActive()) {
            while (opModeIsActive()) {
                if (tfod != null) {
                  FFViewObject ffviewobject = getDetails();
                  pos = ffviewobject.Oposition;
                  telemetry.addData("DUCKPOS", pos);
                  telemetry.update();
              }
             }
        }
        else
        if (tfod != null) {
            tfod.shutdown();
        }
       
       
       telemetry.addData("DUCKPOS", pos);
       telemetry.update();
       if (pos == 1) //We think Duck is Left most
          slidesleep = 1;
       else
      if (pos == 2)  // We think Duck is in the middle
         slidesleep = 8000;
      else
         if (pos == 3) //We think Duck is to the Right
         slidesleep = 12000;
    //END NEWCODE added for Camera
    
    slide_motor.setPower(0.4); //tweak
    sleep(slidesleep); //time it 
    slide_motor.setPower(0.0);
    
    //NEWCODE - To test camera without robot moving and giving me time to stop
    sleep(50000);

    //MOVE FORWARD 2 BLOCKS
    frontLeftWheel.setDirection(DcMotorSimple.Direction.FORWARD);
    backLeftWheel.setDirection(DcMotorSimple.Direction.FORWARD);
    frontRightWheel.setDirection(DcMotorSimple.Direction.REVERSE);
    backRightWheel.setDirection(DcMotorSimple.Direction.REVERSE);
    frontRightWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    frontLeftWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    frontRightWheel.setTargetPosition(870);
    frontLeftWheel.setTargetPosition(870);
    frontRightWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    frontLeftWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    frontRightWheel.setPower(0.4);
    frontLeftWheel.setPower(0.4);
    backRightWheel.setPower(0.4);
    backLeftWheel.setPower(0.4);
    while (!(!frontLeftWheel.isBusy() || !frontRightWheel.isBusy())) {
      // Put loop blocks here
      telemetry.addData("TickFR", frontRightWheel.getCurrentPosition());
      telemetry.addData("TicksFL", frontLeftWheel.getCurrentPosition());
      telemetry.update();
    }
    frontLeftWheel.setPower(0);
    frontRightWheel.setPower(0);
    backRightWheel.setPower(0);
    backLeftWheel.setPower(0);
    
    //grippy
    servo2.setPosition(0.5); //test
    servo1.setPosition(0.2); //test
    sleep(2000);
    
    //MOVE BACKWARD 2 BLOCKS
    frontRightWheel.setDirection(DcMotorSimple.Direction.FORWARD);
    backRightWheel.setDirection(DcMotorSimple.Direction.FORWARD);
    frontLeftWheel.setDirection(DcMotorSimple.Direction.REVERSE);
    backLeftWheel.setDirection(DcMotorSimple.Direction.REVERSE);
    frontRightWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    frontLeftWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    frontRightWheel.setTargetPosition(200);
    frontLeftWheel.setTargetPosition(200);
    frontRightWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    frontLeftWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    frontRightWheel.setPower(0.4);
    frontLeftWheel.setPower(0.4);
    backRightWheel.setPower(0.4);
    backLeftWheel.setPower(0.4);
     while (!(!frontLeftWheel.isBusy() || !frontRightWheel.isBusy())) {
      // Put loop blocks here
      telemetry.addData("TickFR", frontRightWheel.getCurrentPosition());
      telemetry.addData("TicksFL", frontLeftWheel.getCurrentPosition());
      telemetry.update();
    }
    frontRightWheel.setPower(0);
    frontLeftWheel.setPower(0);
    backRightWheel.setPower(0);
    backLeftWheel.setPower(0);
    
    servo2.setPosition(0.6);
    servo1.setPosition(0.6);
    //sleep(2000);

    
    //STRAFE left
    frontRightWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    frontLeftWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    frontLeftWheel.setDirection(DcMotorSimple.Direction.REVERSE);
    backLeftWheel.setDirection(DcMotorSimple.Direction.FORWARD);
    frontRightWheel.setDirection(DcMotorSimple.Direction.REVERSE);
    backRightWheel.setDirection(DcMotorSimple.Direction.FORWARD);
    sleep(1000);
    frontRightWheel.setPower(0.4);
    frontLeftWheel.setPower(0.4);
    backRightWheel.setPower(0.4);
    backLeftWheel.setPower(0.4);
    sleep(3500);//weak
    frontLeftWheel.setPower(0);
    frontRightWheel.setPower(0);
    backRightWheel.setPower(0);
    backLeftWheel.setPower(0);
    sleep(1000);
    
    //MOVE TO CAROUSEL
    backRightWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    backLeftWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    frontLeftWheel.setDirection(DcMotorSimple.Direction.REVERSE);
    backLeftWheel.setDirection(DcMotorSimple.Direction.REVERSE);
    frontRightWheel.setDirection(DcMotorSimple.Direction.FORWARD);
    backRightWheel.setDirection(DcMotorSimple.Direction.FORWARD);
    frontLeftWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    frontRightWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    backLeftWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    backRightWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    //frontRightWheel.setTargetPosition(215); //1755
    //frontLeftWheel.setTargetPosition(215);
    //frontRightWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    //frontLeftWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    backRightWheel.setTargetPosition(211); //1755
    backLeftWheel.setTargetPosition(211);
    backRightWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    backLeftWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    frontRightWheel.setPower(0.2);
    frontLeftWheel.setPower(0.2);
    backRightWheel.setPower(0.2);
    backLeftWheel.setPower(0.2);
    while (!(!backLeftWheel.isBusy() || !backRightWheel.isBusy())) {
      // Put loop blocks here
      telemetry.addData("TickBR", backRightWheel.getCurrentPosition());
      telemetry.addData("TicksBL", backLeftWheel.getCurrentPosition());
      telemetry.update();
    }
    backRightWheel.setPower(0);
    backLeftWheel.setPower(0);
    frontLeftWheel.setPower(0);
    frontRightWheel.setPower(0);

    
    //WRUBBY CODE
    wrubby_motor.setDirection(DcMotorSimple.Direction.REVERSE);
    wrubby_motor.setPower(0.4);
    sleep(4000); //adjust time
    wrubby_motor.setPower(0.0);
    
    //TURN RIGHT
    frontRightWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    frontLeftWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    backRightWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    backLeftWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    frontLeftWheel.setDirection(DcMotorSimple.Direction.FORWARD);
    backLeftWheel.setDirection(DcMotorSimple.Direction.FORWARD);
    frontRightWheel.setDirection(DcMotorSimple.Direction.FORWARD);
    backRightWheel.setDirection(DcMotorSimple.Direction.FORWARD);
    sleep(1000);
    frontRightWheel.setPower(0.3);
    frontLeftWheel.setPower(0.4);
    backRightWheel.setPower(0.3);
    backLeftWheel.setPower(0.4);
    sleep(1200);
    frontLeftWheel.setPower(0);
    frontRightWheel.setPower(0);
    backRightWheel.setPower(0);
    backLeftWheel.setPower(0);
    
    //MOVE FORWARD TO WAREHOUSE
    frontRightWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    frontLeftWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    frontLeftWheel.setDirection(DcMotorSimple.Direction.FORWARD);
    backLeftWheel.setDirection(DcMotorSimple.Direction.FORWARD);
    frontRightWheel.setDirection(DcMotorSimple.Direction.REVERSE);
    backRightWheel.setDirection(DcMotorSimple.Direction.REVERSE);
    frontLeftWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    frontRightWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    frontRightWheel.setTargetPosition(4785);
    frontLeftWheel.setTargetPosition(4785);
    frontRightWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    frontLeftWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    frontRightWheel.setPower(1);
    frontLeftWheel.setPower(1);
    backRightWheel.setPower(1);
    backLeftWheel.setPower(1);
    while (!(!frontLeftWheel.isBusy() || !frontRightWheel.isBusy())) {
      // Put loop blocks here
      telemetry.addData("TickFR", frontRightWheel.getCurrentPosition());
      telemetry.addData("TicksFL", frontLeftWheel.getCurrentPosition());
      telemetry.update();
    }
    // Put run blocks here.
    frontLeftWheel.setPower(0);
    frontRightWheel.setPower(0);
    backRightWheel.setPower(0);
    backLeftWheel.setPower(0);
    
  }

//BEGIN - NEWCODE added for Camera
public FFViewObject getDetails()
    {
         double dpos = 0;
         String Oname = "";
         int Oposition = 0;

         // getUpdatedRecognitions() will return null if no new information is available since
         // the last time that call was made.
         List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
         if (updatedRecognitions != null) {
            telemetry.addData("# Object Detected", updatedRecognitions.size());
            // step through the list of recognitions and display boundary info.
            int i = 0;
            //Suchi 
            boolean isDuckDetected = false;

            for (Recognition recognition : updatedRecognitions) {
               telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
               telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                                recognition.getLeft(), recognition.getTop());
               telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                                recognition.getRight(), recognition.getBottom());
               i++;
                        
               // Suchi - check label to see if the camera now sees a Duck         
               if (recognition.getLabel().equals("Duck")) {            //  ** ADDED **
                    isDuckDetected = true;                             //  ** ADDED **
                    telemetry.addData("Object Detected", "Quack Quack!");//  ** ADDED **
                    Oname = "Duck";
                 } else {                                               //  ** ADDED **
                     isDuckDetected = false;                            //  ** ADDED **
                   }    

                dpos = recognition.getLeft();
                if (dpos < 150 && dpos > 0){
                   telemetry.addData("LOCATION" , "(LEFT)");
                   Oposition = 1;
                   vuforia.setFrameQueueCapacity(0);
                }
                if (dpos < 300 && dpos > 170){
                   telemetry.addData("LOCATION" , "(MIDDLE)");
                   Oposition = 2;
                   vuforia.setFrameQueueCapacity(0);
                }
                if (dpos < 500  && dpos > 370){
                   telemetry.addData("LOCATION" , "(RIGHT)");
                    Oposition = 3;
                    vuforia.setFrameQueueCapacity(0);
                  }
             telemetry.update();
             sleep(2000);
          }
         }

     return new FFViewObject(Oname, Oposition);
    }



    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
    }

    /**
     * Initialize the TensorFlow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
            "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
       tfodParameters.minResultConfidence = 0.8f;
       tfodParameters.isModelTensorFlow2 = true;
       tfodParameters.inputSize = 320;
       tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
       tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
    }
}

package com.datalogic.dlapos.confighelper.configurations.accessor;

import com.datalogic.dlapos.commons.support.APosException;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class IhsCSVHelperTest {

    //region CSV
    final static String IHS_CSV_CONTENT = "-----INFORMATION-----,,\n" +
            "A,ApplicationROM,\n" +
            "R,ApplicationRevisionLevel,\n" +
            "C,ConfigurationFileID,\n" +
            "B,BootloaderROMID,\n" +
            "W,InternalScaleInformation,\n" +
            "D,RemoteDisplayVersion,\n" +
            "S,SerialNumber,\n" +
            "M,TopModelNumber,\n" +
            "m,MainBoardNumber,\n" +
            "I05,Interface,RS232\n" +
            "I5,Interface,RS232\n" +
            "I10,Interface,USB-COM-DL\n" +
            "I20,Interface,SCRS232\n" +
            "I45,Interface,USB-OEM\n" +
            "I47,Interface,USB-COM\n" +
            "I77,Interface,USB-HID\n" +
            "I4D,Interface,USB-COMPOSITE\n" +
            "I,Interface,Unknown\n" +
            "U,UniversalInterfaceApplicationROMID,\n" +
            "u,UniversalInterfaceBootloaderROMID,\n" +
            "X,SecondModelNumber,\n" +
            "Y,SecondSerialNumber,\n" +
            "Z,SecondSoftwareVersionNumber,\n" +
            "s,HandheldSerialNumber,\n" +
            "l,USBLoaderVersion,\n" +
            "Q,SDRAMCfgVersion,\n" +
            "q,SoftwareID,\n" +
            "V,VisionLibraryVersion,\n" +
            "v,VirtualScanLineLibraryVersion,\n" +
            "F,FPGAVersion,\n" +
            "f,TDRFPGAVersion,\n" +
            "P,IPEAppVersion,T7 IR TDR\n" +
            "TI1,TDRType,T8.2 IR TDR\n" +
            "TI2,TDRType,T9 IR TDR\n" +
            "TI3,TDRType,T12 IR TDR\n" +
            "TI4,TDRType,Unknown TDR Status\n" +
            "TI,TDRType,7 Standard TDR\n" +
            "TN1,TDRType,8.2 Standard TDR\n" +
            "TN2,TDRType,9 Standard TDR\n" +
            "TN3,TDRType,12 Standard TDR\n" +
            "TN4,TDRType,Unknown TDR Status\n" +
            "TN,TDRType,Legacy TDR (height unknown)\n" +
            "TL0,TDRType,Legacy TDR (height unknown)\n" +
            "TLO,TDRType,Unknown TDR Status\n" +
            "TL,TDRType,TDR preset: undefined height\n" +
            "TU0,TDRType,Unknown TDR Status\n" +
            "TU,TDRType,TDR present: not ready yet\n" +
            "T??,TDRType,Unknown TDR Status\n" +
            "T?,TDRType,Unknown\n" +
            "T,TDRType,\n" +
            "H,HardwareID,\n" +
            "h,ConnectedDevices,\n" +
            "E,SmartEASVersion,\n" +
            "r,RFScannerRadioVersion,\n" +
            "L,SecondScannerVersion,\n" +
            "b,SecondScannerBootloader,\n" +
            "d,DWMSDKVersion,\n" +
            "t,FormatterVersion,\n" +
            "-----HEALTH-----,,\n" +
            "m,MotorHealth,\n" +
            "h,HorizontalLaserHealth,\n" +
            "v,VerticalLaserHealth,\n" +
            "s,ScaleHealth,\n" +
            "d,RemoteDisplayHealth,\n" +
            "e,EASSystemHealth,\n" +
            "c,CameraHealth,\n" +
            "r,RadioHealth,\n" +
            "H,USBHandheldConnected,\n" +
            "D,USBSerialDongleConnected,\n" +
            "0,IPE0Health,\n" +
            "1,IPE1Health,\n" +
            "2,IPE2Health,\n" +
            "3,IPE3Health,\n" +
            "S,ScaleSentryHealth,\n" +
            "-----STATISTICS-----,,\n" +
            "m,MotorOnTime,\n" +
            "l,LaserOnTime,\n" +
            "L,ScanCount,\n" +
            "z,ScaleZeroAttempts,\n" +
            "c,ScaleCalibrationAttempts,\n" +
            "C,CustomData,\n" +
            "P,PowerOnHours,\n" +
            "E,EASDeactivateCount,\n" +
            "e,EASManualCount,\n" +
            "B,NumberBatteryChargingCycles,\n" +
            "T,NumberTriggerPulls,\n" +
            "K,NumberKeyPresses,\n" +
            "R,TotalResets,\n" +
            "r,ErrorResets,\n" +
            "V,VerticalIPEForcedResets,\n" +
            "H,HorizontalIPEForcedResets,\n" +
            "D,Forced2DResets,\n" +
            "Q,TDRForcedResets,\n" +
            "v,VerticalIPEExcessiveResets,\n" +
            "h,HorizontalIPEExcessiveResets,\n" +
            "d,Excessive2DResets,\n" +
            "q,TDRExcessiveResets,\n" +
            "Z,POSInitiatedZeroRequests,\n" +
            "X,EnforcedZeroEvents,\n" +
            "S,ScaleSentryEvents,\n" +
            "Y,EASRuntimeFaults,\n" +
            "b,BatteryHealth,\n" +
            "s,BatteryChargeStatus,\n";

    //endregion

    @Test
    public void load() throws APosException {
        BufferedReader reader = new BufferedReader(new StringReader(IHS_CSV_CONTENT));
        List<Frame> result = IhsCSVHelper.load(reader);
        assertThat(result.isEmpty()).isFalse();
        assertThat(result.get(0).getId()).isEqualTo("A");
        assertThat(result.get(0).getFieldName()).isEqualTo("ApplicationROM");
        assertThat(result.get(0).getAdditionalData()).isEqualTo("");
        assertThat(result.get(0).getType()).isEqualTo(IhsHelper.FrameType.INFORMATION.name());

        assertThat(result.get(9).getId()).isEqualTo("I05");
        assertThat(result.get(9).getFieldName()).isEqualTo("Interface");
        assertThat(result.get(9).getAdditionalData()).isEqualTo("RS232");
        assertThat(result.get(9).getType()).isEqualTo(IhsHelper.FrameType.INFORMATION.name());

        for (int i = 0; i < 58; i++) {
            assertThat(result.get(i).getType()).isEqualTo(IhsHelper.FrameType.INFORMATION.name());
        }
        for (int i = 58; i < 73; i++) {
            assertThat(result.get(i).getType()).isEqualTo(IhsHelper.FrameType.HEALTH.name());
        }

        for (int i = 73; i < result.size(); i++) {
            assertThat(result.get(i).getType()).isEqualTo(IhsHelper.FrameType.STATISTICS.name());
        }
    }

    @Test(expected = APosException.class)
    public void loadNoSection() throws APosException {
        BufferedReader reader = new BufferedReader(new StringReader("id,field,data"));
        IhsCSVHelper.load(reader);
    }

    @Test(expected = APosException.class)
    public void loadUnknownSection() throws APosException {
        BufferedReader reader = new BufferedReader(new StringReader("-----WRONG-----,,\nid,field,data"));
        IhsCSVHelper.load(reader);
    }

    @Test(expected = APosException.class)
    public void loadInvalidSection() throws APosException {
        BufferedReader reader = new BufferedReader(new StringReader("-----,,\nid,field,data"));
        IhsCSVHelper.load(reader);
    }

    @Test(expected = APosException.class)
    public void loadInvalidLine() throws APosException {
        BufferedReader reader = new BufferedReader(new StringReader("-----HEALTH-----,,\nid,field"));
        IhsCSVHelper.load(reader);
    }
}
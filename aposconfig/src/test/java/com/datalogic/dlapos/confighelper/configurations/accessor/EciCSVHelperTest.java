package com.datalogic.dlapos.confighelper.configurations.accessor;

import com.datalogic.dlapos.commons.support.APosException;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class EciCSVHelperTest {

    private final static String CSV = "//Lines beginning with '//' will be ignored by parser.,,\n" +
            "//DO NOT CHANGE FORMATTING, entries must remain in the format of following header line with a minimum of <ECI_CODE>,<JAVA_ENCODING>\n" +
            "//ECI Code (integer), Java Encoding (empty field means unsupported), Optional Description (ignored by parser)\n" +
            "0,, ISO/IEC 15438 Bar code symbology specification-PDF417: Default character set to 1994 specification with GLI rules\n" +
            "1,, ISO/IEC 15438 Bar code symbology specification-PDF417: Latin 1 character set to 1994 specification with GLI rules\n" +
            "2,, ISO/IEC 15438 Bar code symbology specification-PDF417: Default character set with ECI rules\n" +
            "3,ISO8859_1, ISO/IEC 8859-1 Latin alphabet No. 1\n" +
            "4,ISO8859_2, ISO/IEC 8859-2 Latin alphabet No. 2\n" +
            "5,ISO8859_3, ISO/IEC 8859-3 Latin alphabet No. 3\n" +
            "6,ISO8859_4, ISO/IEC 8859-4 Latin alphabet No. 4\n" +
            "7,ISO8859_5, ISO/IEC 8859-5 Latin/Cyrillic alphabet\n" +
            "8,ISO8859_6, ISO/IEC 8859-6 Latin/Arabic alphabet\n" +
            "9,ISO8859_7, ISO/IEC 8859-7 Latin/Greek alphabet\n" +
            "10,ISO8859_8, ISO/IEC 8859-8 Latin/Hebrew alphabet\n" +
            "11,ISO8859_9, ISO/IEC 8859-9 Latin alphabet No. 5\n" +
            "12,, ISO/IEC 8859-10 Latin alphabet No. 6\n" +
            "13,Cp874, ISO/IEC 8859-11 Latin/Thai alphabet\n" +
            "14,, Reserved\n" +
            "15,ISO8859_13, ISO/IEC 8859-13 Latin alphabet No. 7 (Baltic Rim)\n" +
            "16,, ISO/IEC 8859-14 Latin alphabet No. 8 (Celtic)\n" +
            "17,ISO8859_15, ISO/IEC 8859-15 Latin alphabet No. 9\n" +
            "18,, ISO/IEC 8859-16 Latin alphabet No. 10\n" +
            "19,, Reserved\n" +
            "20,SJIS, Shift JIS (JIS X 0208 Annex 1 + JIS X 0201)\n" +
            "21,Cp1250, Windows 1250 Latin 2 (Central Europe)\n" +
            "22,Cp1251, Windows 1251 Cyrillic\n" +
            "23,Cp1252, Windows 1252 Latin 1\n" +
            "24,Cp1256, Windows 1256 Arabic\n" +
            "25,UTF-16, ISO/IEC 10646 UCS-2 (High order byte first)\n" +
            "26,UTF8, ISO/IEC 10646 UTF-8\n" +
            "27,, ISO/IEC 646:1991 International Reference Version of ISO 7-bit coded character set\n" +
            "28,Big5, Big 5 (Taiwan) Chinese Character Set\n" +
            "29,GB18030, GB (PRC) Chinese Character Set\n" +
            "30,Cp949, Korean Character Set\n" +
            "899,, 8-bit binary data\n";

    @Test
    public void load() throws APosException {
        BufferedReader reader = new BufferedReader(new StringReader(CSV));
        List<Eci> result = EciCSVHelper.load(reader);
        assertThat(result.isEmpty()).isFalse();
        assertThat(result.size()).isEqualTo(32);
        assertThat(result.get(0).eciCode).isEqualTo(0);
        assertThat(result.get(0).javaCode).isEqualTo("");
        assertThat(result.get(4).eciCode).isEqualTo(4);
        assertThat(result.get(4).javaCode).isEqualTo("ISO8859_2");
        assertThat(result.get(31).eciCode).isEqualTo(899);
        assertThat(result.get(31).javaCode).isEqualTo("");
    }

    @Test(expected = APosException.class)
    public void loadNoCSV() throws APosException {
        String notACSV = "blablabla";
        BufferedReader reader = new BufferedReader(new StringReader(notACSV));
        EciCSVHelper.load(reader);
    }

    @Test(expected = APosException.class)
    public void loadNotInteger() throws APosException {
        String notAInt = "blablabla,tutu";
        BufferedReader reader = new BufferedReader(new StringReader(notAInt));
        EciCSVHelper.load(reader);
    }
}
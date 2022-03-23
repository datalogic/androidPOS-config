package com.datalogic.dlapos.confighelper.configurations.accessor;

import com.datalogic.dlapos.confighelper.configurations.accessor.LabelHelper;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;


public class LabelHelperTest {

    @Test
    public void idToString() {
        assertThat(LabelHelper.idToString(new byte[]{0x45})).isEqualTo("45");
        assertThat(LabelHelper.idToString(new byte[]{0x55})).isEqualTo("55");
        assertThat(LabelHelper.idToString(new byte[]{0x5D, 0x43, 0x31})).isEqualTo("5D4331");
        assertThat(LabelHelper.idToString(new byte[]{0x00, 0x0A, 0x0B})).isEqualTo("000A0B");
    }
}
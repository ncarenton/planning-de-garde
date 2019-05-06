package com.github.ncarenton.pdg.common;

import com.github.ncarenton.pdg.TestUtils;
import com.github.ncarenton.pdg.utils.Utils;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static com.github.ncarenton.pdg.TestUtils.grabResource;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

public class UtilsTest {

    @Test(expected = FileNotFoundException.class)
    public void getResourceAsStream_should_throw_exception() throws FileNotFoundException {
        TestUtils.getResourceAsStream("/not_existing_resource.json");
    }

    @Test
    public void getResourceAsStream_should_work() throws IOException {
        assertNotNull(TestUtils.getResourceAsStream("/level1/data.json"));
    }

    @Test(expected = FileNotFoundException.class)
    public void grabResource_should_throw_exception() throws IOException {
        assertNotNull(grabResource("/not_existing_resource.json"));
    }

    @Test
    public void grabResource_should_work() throws IOException {
        assertNotNull(grabResource("/level1/data.json"));
    }

    @Test
    public void commonObjectMapper_should_work() {
        assertThat(Utils.commonObjectMapper().getRegisteredModuleIds())
            .contains("com.fasterxml.jackson.datatype.jsr310.JavaTimeModule");
    }

}

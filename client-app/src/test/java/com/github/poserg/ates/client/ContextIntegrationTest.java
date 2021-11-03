package com.github.poserg.ates.client;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { SSOClientApplication.class })
class ContextIntegrationTest {

    @Test
    void whenLoadApplication_thenSuccess() {
    }

}

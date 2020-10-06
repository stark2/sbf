package com.oreilly.sbf.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import org.junit.jupiter.api.Assertions;

public class HelloControllerUnitTests {

    @Test
    public void sayHello() {
        HelloController controller = new HelloController();
        Model model = new BindingAwareModelMap();
        String result = controller.sayHello("World", model);
        Assertions.assertEquals("hello", result);
        Assertions.assertEquals("World", model.asMap().get("user"));

        Assertions.assertAll(
                () -> Assertions.assertEquals("hello", result),
                () -> Assertions.assertEquals("World", model.getAttribute("user"))
        );

    }
}

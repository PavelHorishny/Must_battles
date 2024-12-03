package org.game.services;

import org.game.unit.Vessel;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
class VesselProcessorTest {
    private Method getGenerateVesselsWithNamesMethod() throws NoSuchMethodException{
        Method method = VesselProcessor.class.getDeclaredMethod("generateVesselsWithNames",boolean.class);
        method.setAccessible(true);
        return method;
    }

    @Test
    void testPrivateMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //ArrayList <Vessel> list = new ArrayList<>();
        //list = (ArrayList<Vessel>) getGenerateVesselsWithNamesMethod().invoke(null,true);
        assertEquals(28,((ArrayList<?>) getGenerateVesselsWithNamesMethod().invoke(new VesselProcessor(),true)).size());
    }
  
}
package de.amin.freecam.util;

import java.lang.reflect.Method;

public class ReflectionHelper {

  private static Class getFreecamModClass() throws ClassNotFoundException {
    return Class.forName("de.amin.v1_8_9.freecam.FreecamMod");
  }

  private static Object getFreecamInstance() {
    try {
      Class freecamModClass = getFreecamModClass();
      Method getInstanceMethod = freecamModClass.getMethod("getInstance");
      return getInstanceMethod.invoke(null);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static void toggleFreecam() {
    try {
      Class freecamModClass = getFreecamModClass();
      Object freecamMod = getFreecamInstance();
      Method enableMethod = freecamModClass.getDeclaredMethod("toggleFreecam");
      enableMethod.invoke(freecamMod);
    } catch (Exception e) {
      throw new RuntimeException();
    }
  }

  public static void initFreecamMod() {
    try {
      Class freecamModClass = getFreecamModClass();
      Object freecamMod = getFreecamInstance();
      Method initMethod = freecamModClass.getMethod("init");
      initMethod.invoke(freecamMod);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
